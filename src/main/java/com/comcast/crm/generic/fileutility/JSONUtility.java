package com.comcast.crm.generic.fileutility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONUtility {

	public String getDataFromJSONFile(String key) throws Exception
	{	
		FileReader fis = new FileReader("./configAppData/CommanData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fis);
		JSONObject map = (JSONObject) obj;
		String JsonData = map.get(key).toString();

		return JsonData;
	}

}
