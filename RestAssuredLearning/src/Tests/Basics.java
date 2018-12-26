package Tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Basics {
	
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\workspace\\RestAssuredLearning\\src\\files\\env.properties");
		prop.load(fis);
		prop.get("HOST");
	}
	
	
	@Test
	public void AddandDeletePlace()
	{
		//Task 1 - Grab the response
		RestAssured.baseURI=prop.getProperty("HOST");// get the host from properties file
		//RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		queryParam("key",prop.getProperty("KEY")). // key is defined in the properties file
		//queryParam("key","qaclick123").
		body(payload.getHostData()). // Get the body from this method
		when().
		post(resources.placePostdata()). // get the post details from the method defned
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		and().body("status",equalTo("OK")).extract().response();
		String responsestring =res.asString();
		System.out.println(responsestring);
		
		//Task 2 - Grab the place id from response
		
		JsonPath js= new JsonPath(responsestring); // class jsonpath - converts string to json path
		String placeid=js.get("place_id"); //placeid json path
		System.out.println(placeid);
		
		//Task 3 - place the place id in delete request
		
	     given().
	     queryParam("key","AIzaSyDPCGp2UlljqibA1Xqjk1iENQZJXspTjPY").
	     body("{"+"\"place_id\": \""+placeid+"\""+"}").
	     when().
	     post("/maps/api/place/delete/json").
	     then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
			and().body("status",equalTo("OK"));
		
		
		
		
		
	}

}
