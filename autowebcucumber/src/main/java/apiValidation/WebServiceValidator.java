package apiValidation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

//import cucumber.runtime.junit.Assertions;
import dataProvider.ConfigFileReader;
//import managers.JsonDataReader;
import managers.JsonDataReader;

public class WebServiceValidator {

	ConfigFileReader configReader;
	boolean isProxyEnabled = false;
	String proxyHost;
	int proxyPort;
	HashMap<String, String> headerMap;
	String postReqData="",headerContent="";
	
	public WebServiceValidator() {
		configReader = new ConfigFileReader();
	}
	
	private HashMap<String, String> createHeaderMap(JSONObject headerContent)
	{
		System.out.println("Creating Header Map for:"+headerContent);
		if(headerContent!=null) {
		headerMap = new Gson().fromJson(headerContent.toString(), HashMap.class);
		}
		//headerMap.put("Content-Type","application/json");
		return headerMap;
	}
	
	/*private String readHeaderContent(String filePath)
	{
		System.out.println("Reading Header Content from:"+filePath);
		File reqData;
		try {
			//D:\SeleniumX_Important\Retail\postBodyContent.txt
			reqData=new File(filePath);
			if(reqData.exists()) {
			//reads the data of the whole file in one go
			headerContent = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8).trim();
			System.out.println("Header Content:"+headerContent);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File of Header Content  doesn't exist");
			e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return headerContent;
	}*/
	
	private String readPostBodyContent(String filePath)
	{
		System.out.println("Reading POST Body Content from:"+filePath);
		File reqData;
		try {
			//D:\SeleniumX_Important\Retail\postBodyContent.txt
			reqData=new File(filePath);
			if(reqData.exists()) {
			//reads the data of the whole file in one go
			postReqData = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8).trim();
			System.out.println("POST Body Content:"+postReqData);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File of Body Content doesn't exist");
			e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postReqData;
	}
	
	private CloseableHttpResponse getWebServiceResponse(String requestURI, String method, HashMap<String, String> headerMap,JSONObject postBodyContent) {
		
		System.out.println("Accessing Web Service at:----"+requestURI);
		HttpHost proxy;
		CloseableHttpClient httpClient;
		RequestConfig config = null;
		CloseableHttpResponse response=null;
		try {
		httpClient = HttpClients.createDefault();
		// for allowing connection through proxy server
		if (configReader.isProxyEnabled()) {
			isProxyEnabled = true;
			proxyHost = configReader.getProxyHost();
			proxyPort = configReader.getProxyPort();
			proxy = new HttpHost(proxyHost, proxyPort,"http");
			config = RequestConfig.custom().setProxy(proxy).build();
		}
		
		// for processing GET request
		if (method.equalsIgnoreCase("GET")) {
			HttpGet httpReq = new HttpGet(requestURI);
			
			if(config!=null) {
				httpReq.setConfig(config);
			}
				// setting the header request parameters
			if(headerMap!=null) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				System.out.println("Header = " + entry.getKey() + ", Value = " + entry.getValue());
				httpReq.addHeader(entry.getKey(), entry.getValue());
			}
			}
				response = httpClient.execute(httpReq);
			}
		// for processing POST request
		else if (method.equalsIgnoreCase("POST")) {
			HttpPost httpReq = new HttpPost(requestURI);
			if(config!=null) {
				httpReq.setConfig(config);
			}
			// setting the header request parameters
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				System.out.println("Header = " + entry.getKey() + ", Value = " + entry.getValue());
				httpReq.addHeader(entry.getKey(), entry.getValue());
			}
			// setting the POST Body content
			httpReq.setEntity(new StringEntity(postBodyContent.toJSONString()));
			response = httpClient.execute(httpReq);
		}
		// for processing PUT request
		else if (method.equalsIgnoreCase("PUT")) {
			HttpPut httpReq = new HttpPut(requestURI);
			if(config!=null) {
				httpReq.setConfig(config);
			}
			// setting the header request parameters
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				System.out.println("Header = " + entry.getKey() + ", Value = " + entry.getValue());
				httpReq.addHeader(entry.getKey(), entry.getValue());
			}
			// setting the PUT Body content
			httpReq.setEntity(new StringEntity(postBodyContent.toJSONString()));
			response = httpClient.execute(httpReq);
		}
		// for processing PATCH request
		else if (method.equalsIgnoreCase("PATCH")) {
			HttpPatch httpReq = new HttpPatch(requestURI);
			if(config!=null) {
				httpReq.setConfig(config);
			}
			// setting the header request parameters
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				System.out.println("Header = " + entry.getKey() + ", Value = " + entry.getValue());
				httpReq.addHeader(entry.getKey(), entry.getValue());
			}
			// setting the PATCH Body content
			httpReq.setEntity(new StringEntity(postBodyContent.toJSONString()));
			response = httpClient.execute(httpReq);
		
		}
		
		else if (method.equalsIgnoreCase("DELETE")) {

		}
		
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return response;
	}

	private void validateWebServiceResponse(int expectedResponse, int actualResponse) {
		Assert.assertEquals("Response code did not match",expectedResponse, actualResponse);
	
	}
	
	/*public static void main(String[] args) {
		JsonDataReader reader=new JsonDataReader();
		List<APIRequestDetail> apisList=reader.getApiDetailsList();
		WebServiceValidator webserviceVldn=new WebServiceValidator();
		CloseableHttpResponse response=null; 
		String uri,methodName;
		JSONObject headerContent,bodyContent;
		try {
			
			for(APIRequestDetail apiDtl:apisList) {
				uri=apiDtl.getApiUri();
				methodName=apiDtl.getMethod();
				headerContent=apiDtl.getHeaderContent();
				bodyContent=apiDtl.getBodyContent();
				response=webserviceVldn.getWebServiceResponse
						(uri,methodName,webserviceVldn.createHeaderMap(headerContent),bodyContent);
				if(response!=null) {
					System.out.println("Response Code:"+response.getStatusLine().getStatusCode());
					String responseBody = EntityUtils.toString(response.getEntity());
					System.out.println("Response Body:"+responseBody);
					}
				webserviceVldn.validateWebServiceResponse(200,response.getStatusLine().getStatusCode());
			}
				
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	public CloseableHttpResponse accessAndValidateApiResponse(String apiIdentifier,JSONObject customBodyContent) {
		int actualResponseCode=400;
		JsonDataReader reader=new JsonDataReader();
		List<APIRequestDetail> apisList=reader.getApiDetailsList();
		WebServiceValidator webserviceVldn=new WebServiceValidator();
		CloseableHttpResponse response=null; 
		String uri,methodName;
		int expectedResponseCode;
		JSONObject headerContent,bodyContent;
		try {
			for(APIRequestDetail apiDtl:apisList) {
				//some identifier is specified by user else by default all APIs will be validated
				if(apiIdentifier.equalsIgnoreCase("") ||
						apiIdentifier.equalsIgnoreCase(apiDtl.getApiIdentifier())) {
				uri=apiDtl.getApiUri();
				methodName=apiDtl.getMethod();
				headerContent=apiDtl.getHeaderContent();
				expectedResponseCode=Integer.parseInt(apiDtl.getExpectedResponseCode());
				bodyContent=(customBodyContent!=null?customBodyContent:apiDtl.getBodyContent());
				response=webserviceVldn.getWebServiceResponse
						(uri,methodName,webserviceVldn.createHeaderMap(headerContent),bodyContent);
				if(response!=null) {
					System.out.println("Response Code:"+response.getStatusLine().getStatusCode());
					}
				actualResponseCode=response.getStatusLine().getStatusCode();
				webserviceVldn.validateWebServiceResponse(expectedResponseCode,actualResponseCode);
				}}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return response;
	}

}
