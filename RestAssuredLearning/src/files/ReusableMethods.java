package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static XmlPath rawtoXML(Response r)
	{
		String responsedata = r.asString();
		XmlPath x = new XmlPath(responsedata);
		return x;
		
	}
	
	public static JsonPath rawtoJSON(Response r)
	{
		String responsedata =r.asString();
		JsonPath x =new JsonPath(responsedata);
		return x;
		
	}

}
