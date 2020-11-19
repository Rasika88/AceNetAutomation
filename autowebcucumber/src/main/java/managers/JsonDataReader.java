package managers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import apiValidation.APIRequestDetail;
import dataProvider.ConfigFileReader;

public class JsonDataReader {
/*	private final String filePath = FileReaderManager.getInstance().getConfigReader().getAPIDetailsPath()
			+ "APIDetails.json";
	*/
	private String filePath; 
	ConfigFileReader configReader=new ConfigFileReader();
	
	
	private List<APIRequestDetail> apiDetailsList;

	public JsonDataReader() {
		filePath = System.getProperty("user.dir")+configReader.getAPIDetailsPath();
		apiDetailsList = getAPIDetails();
		setApiDetailsList(apiDetailsList);
	}
	
	private List<APIRequestDetail> getAPIDetails() {
		JSONParser parser = new JSONParser();
		apiDetailsList=new ArrayList<APIRequestDetail>();
		JSONObject currentJsonObj;
		try {
		Object apiDtlsObj = parser.parse(new FileReader(filePath));
        JSONArray apiDtlsArray = (JSONArray) apiDtlsObj;
        System.out.println("JSON Array:"+apiDtlsArray);
        
        for(int cnt=0;cnt<apiDtlsArray.size();cnt++)
        {
        	currentJsonObj=(JSONObject) apiDtlsArray.get(cnt);
        	APIRequestDetail reqDtl=new APIRequestDetail();
        	reqDtl.setApiIdentifier(currentJsonObj.get("apiIdentifier")!=null?
        			currentJsonObj.get("apiIdentifier").toString():"");
        	reqDtl.setApiUri(currentJsonObj.get("apiUri")!=null?
        			currentJsonObj.get("apiUri").toString():"");
        	reqDtl.setMethod(currentJsonObj.get("method")!=null?
        			currentJsonObj.get("method").toString():"");
        	reqDtl.setBodyContent((JSONObject)currentJsonObj.get("bodyContent"));
        	reqDtl.setHeaderContent((JSONObject)currentJsonObj.get("headerContent"));
        	reqDtl.setExpectedResponseCode(currentJsonObj.get("expectedResponseCode").toString());
        	apiDetailsList.add(reqDtl);
        }
  
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return apiDetailsList;
	}

	public List<APIRequestDetail> getApiDetailsList() {
		return apiDetailsList;
	}

	public void setApiDetailsList(List<APIRequestDetail> apiDetailsList) {
		this.apiDetailsList = apiDetailsList;
	}
	
	
}