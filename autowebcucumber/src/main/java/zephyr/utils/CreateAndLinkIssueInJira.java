package zephyr.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import zephyr.restClient.ZFJCloudRestClient;

public class CreateAndLinkIssueInJira {
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
	
	public CreateAndLinkIssueInJira(String jiraBaseURL,String jiraUserName,String jiraPwd) {
		this.jiraBaseURL = jiraBaseURL;
		this.jiraUserName = jiraUserName;
		this.jiraPwd=jiraPwd;
	//	this.projectName=projectName;
		
	}

	public void createJiraIssue(String tcname,String status,String tcError,String priority,String testKey) throws URISyntaxException {
		 try {
			String API_CREATE_ISSUE = "{SERVER}/rest/api/2/issue";
			String jiraBaseURL="https://rasaza.atlassian.net/";
//			https://rajeshidimannan.atlassian.net/rest/api/2/issue/
			String getProjectURI =API_CREATE_ISSUE.replace("{SERVER}", jiraBaseURL);
			URI uri = new URI(getProjectURI);
			HttpClient client = new DefaultHttpClient();
			//HttpHost proxy = new HttpHost("proxy.tcs.com",8080);
			//client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

			HttpResponse response;
			
			byte[] bytesEncoded = Base64.encodeBase64((jiraUserName + ":" + jiraPwd).getBytes());
			String authorizationHeader = "Basic " + new String(bytesEncoded);
			JSONObject creatIssueRQ = null;

			creatIssueRQ = new JSONObject();
			JSONObject fields=new JSONObject();
			JSONObject projectDtl=new JSONObject();
			projectDtl.put("key","Q1");

			fields.put("project", projectDtl);
			//fields.put("fields", fields);        			
			fields.put("summary","Automation Bug-"+tcname);			
			fields.put("description",tcError);

			JSONObject issueTypeDtl=new JSONObject();
			issueTypeDtl.put("name","Bug");

			fields.put("issuetype", issueTypeDtl);

			JSONObject priorityDtl=new JSONObject();
			priorityDtl.put("name",priority);

			fields.put("priority", priorityDtl);

			JSONObject updateLinkDtls=new JSONObject();
			JSONObject issueLink=new JSONObject();
			JSONObject[] issuelinks=new JSONObject[1];

			JSONObject issueTypeObj=new JSONObject();
			JSONObject typeObj=new JSONObject();
			JSONObject outwardIssueObj=new JSONObject();

			issueTypeObj.put("name", "Blocks");
			issueTypeObj.put("inward", "is blocked by");
			issueTypeObj.put("outward", "blocks");

			outwardIssueObj.put("key",testKey);

			typeObj.put("type", issueTypeObj);
			typeObj.put("outwardIssue", outwardIssueObj);

			issueLink.put("add", typeObj);
			//issueLink.put("add", outwardIssueObj);

			issuelinks[0]=issueLink;
			updateLinkDtls.put("issuelinks", issuelinks);

			creatIssueRQ.put("fields", fields);        			
			creatIssueRQ.put("update", updateLinkDtls);		

			StringEntity createIssuesJSON = null;
			try {
				createIssuesJSON = new StringEntity(creatIssueRQ.toString());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("POST Body Content---"+creatIssueRQ.toString());

			HttpPost createissuesReq = new HttpPost(uri);
			createissuesReq.addHeader("Content-Type", "application/json");
			createissuesReq.addHeader("Authorization", authorizationHeader);
			createissuesReq.setEntity(createIssuesJSON);


				response = client.execute(createissuesReq);
				if(response!=null && response.getStatusLine().getStatusCode()==201) {
					System.out.println("Issue created and linked!");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		/*CreateAndLinkIssueInJira obj=new CreateAndLinkIssueInJira("https://.atlassian.net", "https://prod-api.zephyr4jiracloud.com/connect",
				"MTJhMjJjOTAtMmVjMy0zZDg1LTk5ZWYtOTQ5ZjE0MjExMWYzIGFkbWluIFpBUElrZXk", "ndGi2UzWRbu9uWLEuLY8h4SFqpDDdYPd0EbVDLnOIVQ",
				"a.faiz@tcs.com","a.faiz@tcs.com","TestProj","V1.0");
		*/
		/*String projId=obj.getProjectID();
		String versionId=obj.getVersionID(projId);*/
	//	obj.createJiraIssue("","");
	}
  
}
