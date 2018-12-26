package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Create {
	
	@Test
	public void AddandDeletePlace()
	{
		String b= "{"+
				"\"location\" : {"+
				"\"lat\": -33.8670522,"+
				"\"lng\": 151.1957362,"+
			"},"+
			    "\"accuracy\": 50,"+
			    "\"name\" : \"Google Shoes\","+
			    "\"phone_number\":\"(02) 9374 4000\"."+
			    "\"address\":\"48 Pirrama Road,Pyrmont,NSW 2009,Australia\","+
			    "\"types\":[\"shoe_store\"],"+
			    "\"website\": \"http://www.google.com.au/\","+
			    "\"language\": \"en-AU\""+
			"}";
		//Task 1 - Grab the response
		
		RestAssured.baseURI="https://maps.googleapis.com";
		//RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		queryParam("key","AIzaSyDPCGp2UlljqibA1Xqjk1iENQZJXspTjPY").
		//queryParam("key","qaclick123").
		body(b).
		when().
		post("/maps/api/place/add/json").
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
