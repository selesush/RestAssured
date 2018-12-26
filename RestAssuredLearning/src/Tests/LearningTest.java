package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.ReusableMethods;

public class LearningTest {
	
	
	@Test
	public void GetGoogleMapDetails()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res =given().
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("type","restaurant").
				param("keyword","cruise").
				param("key","AIzaSyDPCGp2UlljqibA1Xqjk1iENQZJXspTjPY").
		        when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.and().body("results[0].name",equalTo("Cruise Bar, Restaurant & Events"))
				.and().body("results[0].scope", equalTo("GOOGLE")).and().header("Server", "scaffolding on HTTPServer2")
				.and().body("results[2].types[2]", equalTo("point_of_interest")).
				extract().response();
		JsonPath js =ReusableMethods.rawtoJSON(res);
		int count = js.get("results.size()");
				System.out.println(count);
				
	}

}
