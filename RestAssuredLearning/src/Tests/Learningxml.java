package Tests;

import org.testng.annotations.Test;
import files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Learningxml {

	@Test
	public void Test2() throws IOException
	{
		String postdata = GenerateStringFromResource("C:\\Users\\sushman\\Desktop\\rest\\postdata.xml"); // method defined below
		RestAssured.baseURI="https://maps.googleapis.com";
		//RestAssured.baseURI="http://216.10.245.166";
		Response res =given().
		queryParam("key","AIzaSyDPCGp2UlljqibA1Xqjk1iENQZJXspTjPY").
		//queryParam("key","qaclick123").
		body(postdata). // postdata has the xml body
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
		XmlPath x =ReusableMethods.rawtoXML(res);   // method to convert the raw data to xml 
		String traversepath=x.get("PlaceAddResponse.place_id"); // path here the xml path traverse from parent to child in the response we got 
		System.out.println(traversepath);
	

}
	// Reads the xml file and  Convert to String & return string
	public static String GenerateStringFromResource(String Path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(Path)));
		
	}
}
