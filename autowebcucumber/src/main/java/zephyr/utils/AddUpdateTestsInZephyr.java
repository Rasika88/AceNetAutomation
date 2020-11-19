package zephyr.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import zephyr.jwtgenerator.JwtGenerator;
import zephyr.restClient.ZFJCloudRestClient;

public class AddUpdateTestsInZephyr {

	private String API_ADD_TESTS_TO_CYCLE = "{SERVER}/public/rest/api/1.0/executions/add/cycle/";
	private String API_GET_ISSUES = "{SERVER}/rest/api/2/search";
	private String API_GET_EXECUTION_ID="{SERVER}/public/rest/api/1.0/executions/search/cycle/";
	private String API_UPDATE_EXECUTION_STATUS="{SERVER}/public/rest/api/1.0/execution/";
	private int jwtExpiryWindowSeconds=360;
	private String zephyrSecretKey;
	private String jiraBaseURL;
	private String zephyrBaseURL;
	private String zephyrAccessKey;
	private String jiraUserName;
	private String jiraPwd;
	private String projectID;
	private String versionID;
	private String cycleID;
	private String issueID;
	
	public AddUpdateTestsInZephyr(int jwtExpiryWindowSeconds,String jiraBaseURL, String zephyrBaseURL, String zephyrAccessKey,
			String zephyrSecretKey,String jiraUserName,String jiraPwd,String cycleID) {
		this.jwtExpiryWindowSeconds = jwtExpiryWindowSeconds;
		this.jiraBaseURL = jiraBaseURL;
		this.zephyrBaseURL = zephyrBaseURL;
		this.zephyrAccessKey = zephyrAccessKey;
		this.zephyrSecretKey = zephyrSecretKey;
		this.jiraUserName = jiraUserName;
		this.jiraPwd=jiraPwd;
		this.projectID=CreateCycleInZephyr.projectID;
		this.versionID=CreateCycleInZephyr.versionID;
		this.cycleID=cycleID;
	}
	
	//fetch issue id corresponding to an issue key present in scenario name
	public String fetchIssues(String issueKey) throws ClientProtocolException, IOException, URISyntaxException {
		System.out.println("Fetching issue id");
		String getIssuesURI = API_GET_ISSUES.replace("{SERVER}", jiraBaseURL);
		String response;
		HttpResponse httpResponse = null;
		URI uri = new URI(getIssuesURI);
		JSONObject fetchIssues = null;
		StringEntity fetchIssuesJSON = null;
		HttpClient client = new DefaultHttpClient();
		//for allowing connection through proxy server
		//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
		//client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		//for basic authentication in header
		byte[] bytesEncoded = Base64.encodeBase64((jiraUserName + ":" + jiraPwd).getBytes());
		String authorizationHeader = "Basic " + new String(bytesEncoded);
		//{"jql":"key = TES-2","maxResults":5,"fieldsByKeys":false,"startAt":0}
		fetchIssues = new JSONObject();
		fetchIssues.put("jql", "key="+issueKey);        			
		fetchIssues.put("maxResults", 5);
		fetchIssues.put("fieldsByKeys",false);
		fetchIssues.put("startAt", 0);
		fetchIssuesJSON = new StringEntity(fetchIssues.toString());
		
		HttpPost fetchIssuesReq = new HttpPost(uri);
		fetchIssuesReq.addHeader("Content-Type", "application/json");
		fetchIssuesReq.addHeader("Authorization", authorizationHeader);
		fetchIssuesReq.setEntity(fetchIssuesJSON);
		
		httpResponse = client.execute(fetchIssuesReq);
		System.out.println("Response code for fetching issues is:"+httpResponse.getStatusLine().getStatusCode());
		
		HttpEntity entity=httpResponse.getEntity();
		response = EntityUtils.toString(entity);
		System.out.println("Response for fetching fetching issues:"+response);
		JSONObject issuesDtls = new JSONObject(response);
		JSONArray issuesList=issuesDtls.getJSONArray("issues");
		for (int i=0;i<issuesList.length();i++) {
			JSONObject issueDtl = issuesList.getJSONObject(i);
			//check if the current issue key matches the input key
			if(issueDtl.getString("key").equalsIgnoreCase(issueKey)) {
				issueID=issueDtl.getString("id");
				System.out.println("Issue ID:"+issueID);
				return issueID;
			}
		}
		
		return issueID;
	
	}
	
	//https://prod-api.zephyr4jiracloud.com/connect/public/rest/api/1.0/executions/add/cycle/40af957d-c776-4d24-8bb6-e247a5af2b3a
	public void addTestCasesToCycle(String issueKey) {
		String addTestsUri =""; 
		StringEntity addTestsJSON = null;
		JSONObject addTests = null;
		URI uri = null;
		ZFJCloudRestClient client =null;
		zephyr.jwtgenerator.JwtGenerator jwtGenerator =null;
		String jwt =null;
		HttpResponse response = null;
		String issueId[]=new String[1] ;
		try {
			fetchIssues(issueKey);
			System.out.println("Adding test case to cycle");
			addTestsUri = API_ADD_TESTS_TO_CYCLE.replace("{SERVER}", zephyrBaseURL)+cycleID;
			issueId[0]=issueID;
			//creating request body to be posted to API
			addTests = new JSONObject();
			addTests.put("projectId", projectID);        			
			addTests.put("method", 1);
			addTests.put("issues",issueId);
			addTests.put("versionId", versionID);
			addTestsJSON = new StringEntity(addTests.toString());
			System.out.println("POST Body Content---"+addTests.toString());
			
			uri = new URI(addTestsUri);
			client = ZFJCloudRestClient.restBuilder(zephyrBaseURL, zephyrAccessKey, zephyrSecretKey, jiraUserName).build();
			jwtGenerator = client.getJwtGenerator();
			jwt = jwtGenerator.generateJWT("POST", uri, jwtExpiryWindowSeconds);
			System.out.println("URI for adding test to cycle:"+uri.toString());
			System.out.println(jwt);

			
			HttpClient restClient = new DefaultHttpClient();

			//for allowing connection through proxy server
			//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
			//restClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			
			HttpPost addTestsReq = new HttpPost(uri);
			addTestsReq.addHeader("Content-Type", "application/json");
			addTestsReq.addHeader("Authorization", jwt);
			addTestsReq.addHeader("zapiAccessKey", zephyrAccessKey);
			addTestsReq.setEntity(addTestsJSON);

			response = restClient.execute(addTestsReq);
			if(response!=null && response.getStatusLine().getStatusCode()==200) {
				System.out.println("Test Added!");
			}
			else {
				System.out.println("Error in Adding tests!");
				throw new Exception("Exception while adding tests!");
			}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
 private String fetchExecutionID() throws Exception {
	 String getExecutionIDURI,executionID = null;
	 getExecutionIDURI = API_GET_EXECUTION_ID.replace("{SERVER}", zephyrBaseURL)+cycleID+"?projectId="+projectID+"&versionId="+versionID;
	 URI uri=new URI(getExecutionIDURI);
	 HttpResponse response = null;
	 ZFJCloudRestClient client =null;
	 JwtGenerator jwtGenerator =null;
	 String jwt =null;
	 String responseStr;
	 client = ZFJCloudRestClient.restBuilder(zephyrBaseURL, zephyrAccessKey, zephyrSecretKey, jiraUserName).build();
	 jwtGenerator = client.getJwtGenerator();
	 jwt = jwtGenerator.generateJWT("GET", uri, jwtExpiryWindowSeconds);
	 System.out.println("URI for fetching execution id:"+uri.toString());
	 System.out.println(jwt);
	 
	 HttpGet getExecutionIDReq=new HttpGet(uri);
	 getExecutionIDReq.addHeader("Authorization",jwt);
	 getExecutionIDReq.addHeader("zapiAccessKey",zephyrAccessKey);
	 
	 HttpClient restClient = new DefaultHttpClient();

	 //for allowing connection through proxy server
	// HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
	// restClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
	 response = restClient.execute(getExecutionIDReq);
		if(response!=null && response.getStatusLine().getStatusCode()==200) {
			System.out.println("Fetched Execution ID!");
			HttpEntity entity=response.getEntity();
			responseStr = EntityUtils.toString(entity);
			
			JSONObject issues = new JSONObject(responseStr);
			JSONArray IssuesArray = issues.getJSONArray("searchObjectList");
		
			for (int j = 0; j <= IssuesArray.length() - 1; j++) {
			JSONObject jobj = IssuesArray.getJSONObject(j);
			JSONObject jobj2 = jobj.getJSONObject("execution");
			int IssueId = jobj2.getInt("issueId");
			if(IssueId==Integer.parseInt(issueID))
			{
				executionID = jobj2.getString("id");
				return executionID;
			}
			}
		}
		else {
			System.out.println("Error in fetching exec id!");
			throw new Exception("Exception while fetching exec id!");
		}
		return executionID;
 }

 
 public void updateExecutionStatus(String executionStatus) {
		String updateExecStatusUri =""; 
		StringEntity updateTestsJSON = null;
		JSONObject updateTests = null;
		URI uri = null;
		ZFJCloudRestClient client =null;
		JwtGenerator jwtGenerator =null;
		String jwt =null;
		HttpResponse response = null;
		String executionID;
		try {
			executionID=fetchExecutionID();
			System.out.println("Fetching execution ID");
			updateExecStatusUri = API_UPDATE_EXECUTION_STATUS.replace("{SERVER}", zephyrBaseURL)+executionID;
			
			JSONObject statusObj = new JSONObject();
			statusObj.put("id",( executionStatus.equalsIgnoreCase("Passed")?1:2));

			JSONObject execunIdObj = new JSONObject();
			execunIdObj.put("id", executionID);
			
			//creating request body to be posted to API
			updateTests = new JSONObject();
			updateTests.put("status", statusObj);        			
			updateTests.put("cycleId", cycleID);
			updateTests.put("projectId",projectID);
			updateTests.put("versionId", versionID);
			updateTests.put("comment", "Executed by OSAF");
			updateTests.put("issueId", issueID);
			updateTests.put("execution", execunIdObj);
			
			updateTestsJSON = new StringEntity(updateTests.toString());
			System.out.println("POST Body Content---"+updateTests.toString());
			
			uri = new URI(updateExecStatusUri);
			client = ZFJCloudRestClient.restBuilder(zephyrBaseURL, zephyrAccessKey, zephyrSecretKey, jiraUserName).build();
			jwtGenerator = client.getJwtGenerator();
			jwt = jwtGenerator.generateJWT("PUT", uri, jwtExpiryWindowSeconds);
			System.out.println("URI for adding test to cycle:"+uri.toString());
			System.out.println(jwt);

			
			HttpClient restClient = new DefaultHttpClient();

			//for allowing connection through proxy server
		//	HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
		//	restClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			
			HttpPut updateTestsReq = new HttpPut(uri);
			updateTestsReq.addHeader("Content-Type", "application/json");
			updateTestsReq.addHeader("Authorization", jwt);
			updateTestsReq.addHeader("zapiAccessKey", zephyrAccessKey);
			updateTestsReq.setEntity(updateTestsJSON);

			response = restClient.execute(updateTestsReq);
			if(response!=null && response.getStatusLine().getStatusCode()==200) {
				System.out.println("Test Updated!");
			}
			else {
				System.out.println("Error in Updating tests!");
				throw new Exception("Exception while updating tests!");
			}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
 
	public static void main(String[] args) {
		AddUpdateTestsInZephyr obj=new AddUpdateTestsInZephyr(360,"https://faizahmed1.atlassian.net", "https://prod-api.zephyr4jiracloud.com/connect",
				"MTJhMjJjOTAtMmVjMy0zZDg1LTk5ZWYtOTQ5ZjE0MjExMWYzIGFkbWluIFpBUElrZXk", "ndGi2UzWRbu9uWLEuLY8h4SFqpDDdYPd0EbVDLnOIVQ",
				"a.faiz@tcs.com","a.faiz@tcs.com","433fcc39-e4ad-4d34-b8ba-1b68626a758c");
		obj.addTestCasesToCycle("TES-2");
		obj.updateExecutionStatus("Passed");
	}
}
