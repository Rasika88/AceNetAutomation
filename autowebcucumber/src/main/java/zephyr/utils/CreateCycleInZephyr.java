package zephyr.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import zephyr.jwtgenerator.JwtGenerator;
import zephyr.restClient.ZFJCloudRestClient;

public class CreateCycleInZephyr {
	private String API_GET_PROJECTS = "{SERVER}/rest/api/2/project";
	private String API_GET_VERSIONS = "{SERVER}/rest/api/2/project";
	private String API_CREATE_CYCLE = "{SERVER}/public/rest/api/1.0/cycle";
	private String jiraBaseURL;
	private String zephyrBaseURL;
	private String zephyrAccessKey=null;
	private String zephyrSecretKey=null;
	private String jiraUserName=null;
	private String jiraPwd=null;
	private String projectName=null;
	private String versionName=null;
	private static ZFJCloudRestClient client;
	private int jwtExpiryWindowSeconds=360;
	protected static String projectID;
	protected static String versionID;
	
	public CreateCycleInZephyr(String jiraBaseURL, String zephyrBaseURL, String zephyrAccessKey,
			String zephyrSecretKey,String jiraUserName,String jiraPwd,String projectName,String versionName) {
		this.jiraBaseURL = jiraBaseURL;
		this.zephyrBaseURL = zephyrBaseURL;
		this.zephyrAccessKey = zephyrAccessKey;
		this.zephyrSecretKey = zephyrSecretKey;
		this.jiraUserName = jiraUserName;
		this.jiraPwd=jiraPwd;
		this.projectName=projectName;
		this.versionName=versionName;
	}

	//fetches project id corresponding to input project
	private String getProjectID() throws URISyntaxException, ClientProtocolException, IOException {
		String getProjectURI = API_GET_PROJECTS.replace("{SERVER}", jiraBaseURL);
		String response;
		HttpResponse httpResponse = null;
		HttpGet getProjectDtls =null; 
//		String jwtToken;
		URI uri = new URI(getProjectURI);
		
		/*jwtToken=getJWTTokenObj.generateJWT(zephyrBaseURL, zephyrAccessKey, zephyrSecretKey, jiraUserName, "GET",
				uri, jwtExpiryWindowSeconds);*/
		
		HttpClient client = new DefaultHttpClient();
		//for allowing connection through proxy server
		//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
		//client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		//for basic authentication in header
		byte[] bytesEncoded = Base64.encodeBase64((jiraUserName + ":" + jiraPwd).getBytes());
		String authorizationHeader = "Basic " + new String(bytesEncoded);
		Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
		
		
		getProjectDtls = new HttpGet(uri);
		getProjectDtls.addHeader(header);
		getProjectDtls.addHeader("Content-Type", "application/json");
		
		httpResponse = client.execute(getProjectDtls);
		System.out.println("Response code for fetching proj details is:"+httpResponse.getStatusLine().getStatusCode());
		
		HttpEntity entity=httpResponse.getEntity();
		response = EntityUtils.toString(entity);
		System.out.println("Response for fetching proj details is:"+response);
		JSONArray projectsList = new JSONArray(response);
		for (int i=0;i<projectsList.length();i++) {
			JSONObject projectDtl = projectsList.getJSONObject(i);
			//check if the current project name matches the input project name 
			if(projectDtl.getString("name").equalsIgnoreCase(projectName)) {
				projectID=projectDtl.getString("id");
				System.out.println("Project ID:"+projectID);
				return projectID;
			}
		}
		
		return projectID;
	}
	
	
	//fetches version id corresponding to a version in project
	private String getVersionID(String projectId) throws URISyntaxException, ClientProtocolException, IOException {
		String getVersionsURI = API_GET_VERSIONS.replace("{SERVER}", jiraBaseURL);
		String response;
		HttpResponse httpResponse = null;
		HttpGet getVersionDtls =null;
		URI uri = null;
		HttpClient client = null;
		String authorizationHeader =null;
		Header header =null;
		HttpEntity entity=null;
		getVersionsURI=getVersionsURI+"/"+projectId;
		uri = new URI(getVersionsURI);
		client = new DefaultHttpClient();
		//for allowing connection through proxy server
		//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
		//client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		//for basic authentication in header
		byte[] bytesEncoded = Base64.encodeBase64((jiraUserName + ":" + jiraPwd).getBytes());
		authorizationHeader = "Basic " + new String(bytesEncoded);
		header = new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
		
		getVersionDtls = new HttpGet(uri);
		getVersionDtls.addHeader(header);
		getVersionDtls.addHeader("Content-Type", "application/json");
		
		httpResponse = client.execute(getVersionDtls);
		System.out.println("Response code for fetching version id is:"+httpResponse.getStatusLine().getStatusCode());
		
		entity=httpResponse.getEntity();
		response = EntityUtils.toString(entity);
		System.out.println("Response for fetching version id is:"+response);
		
		JSONObject projectDtls = new JSONObject(response);
		JSONArray versionsList = projectDtls.getJSONArray("versions");
		
		for (int i=0;i<versionsList.length();i++) {
			JSONObject versionDtl = versionsList.getJSONObject(i);
			//check if the current version name matches the input version name 
			if(versionDtl.getString("name").equalsIgnoreCase(versionName)) {
				versionID=versionDtl.getString("id");
				System.out.println("Version ID:"+versionID);
				return versionID;
			}
		}
		return versionID;
	}
	
	public String createCycle() throws Exception {
		String cycleName = "";
		String cycleDescription = "";
		String createCycleUri =""; 
		StringEntity cycleJSON = null;
		JSONObject createCycleObj = null;
		URI uri = null;
		ZFJCloudRestClient client =null;
		JwtGenerator jwtGenerator =null;
		String jwt =null;
		HttpResponse response = null;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateFormatDesc=new SimpleDateFormat("hh:mm:ss");
		HttpEntity entity=null;
		String responseMsg,cycleId="";
		try {
			System.out.println("Fetching proj id");
			getProjectID();
			System.out.println("Fetching version id");
			getVersionID(projectID);
			System.out.println("Creating cycle");
			cycleName = "Exec_Cycle_"+dateFormat.format(new Date());
			cycleDescription = "Created by OSAF at:"+dateFormatDesc.format(new Date());
			createCycleUri = API_CREATE_CYCLE.replace("{SERVER}", zephyrBaseURL);
			//creating request body to be posted to API
			createCycleObj = new JSONObject();
			createCycleObj.put("name", cycleName);        			
			createCycleObj.put("description", cycleDescription);			
			createCycleObj.put("startDate",System.currentTimeMillis());
			createCycleObj.put("projectId", projectID);
			createCycleObj.put("versionId", versionID);
			cycleJSON = new StringEntity(createCycleObj.toString());
			System.out.println("POST Body Content---"+createCycleObj.toString());
			
			uri = new URI(createCycleUri);
			System.out.println("Creating rest client");
			client = ZFJCloudRestClient.restBuilder(zephyrBaseURL, zephyrAccessKey, zephyrSecretKey, jiraUserName).build();
			System.out.println("Rest client created");
			jwtGenerator = client.getJwtGenerator();
			jwt = jwtGenerator.generateJWT("POST", uri, jwtExpiryWindowSeconds);
			System.out.println("URI for creating cycle:"+uri.toString());
			System.out.println(jwt);

			
			HttpClient restClient = new DefaultHttpClient();

			//for allowing connection through proxy server
			//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
			//restClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			
			HttpPost createCycleReq = new HttpPost(uri);
			createCycleReq.addHeader("Content-Type", "application/json");
			createCycleReq.addHeader("Authorization", jwt);
			createCycleReq.addHeader("zapiAccessKey", zephyrAccessKey);
			createCycleReq.setEntity(cycleJSON);

			response = restClient.execute(createCycleReq);
			System.out.println("Response code for creating cycle is:"+response.getStatusLine().getStatusCode());
			if(response!=null && response.getStatusLine().getStatusCode()==200) {
				System.out.println("Test Cycle Created!");
				entity=response.getEntity();
				responseMsg = EntityUtils.toString(entity);
				JSONObject cycleDtl = new JSONObject(responseMsg);
				cycleId=cycleDtl.get("id").toString();
				System.out.println("Cycle id:"+cycleId);
				return cycleId;
			}
			else {
				System.out.println("Error in Test Cycle Creation!");
				throw new Exception("Exception while creating cycle!");
			}
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
	}
	
	public static void main(String[] args) throws Exception {
		CreateCycleInZephyr obj=new CreateCycleInZephyr("https://faizahmed1.atlassian.net", "https://prod-api.zephyr4jiracloud.com/connect",
				"MTJhMjJjOTAtMmVjMy0zZDg1LTk5ZWYtOTQ5ZjE0MjExMWYzIGFkbWluIFpBUElrZXk", "ndGi2UzWRbu9uWLEuLY8h4SFqpDDdYPd0EbVDLnOIVQ",
				"a.faiz@tcs.com","a.faiz@tcs.com","TestProj","V1.0");
		
		/*String projId=obj.getProjectID();
		String versionId=obj.getVersionID(projId);*/
		obj.createCycle();
	}
  
}
