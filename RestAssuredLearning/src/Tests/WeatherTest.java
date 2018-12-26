package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherTest {
	
@Test
	public void GetWeatherDetails()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httprequest=RestAssured.given();
		Response response=httprequest.request(Method.GET , "/Hyderabad");
		String responsebody=response.getBody().asString();
		int statuscode=response.getStatusCode();
		String contenttype=response.getContentType().toString();
		String header=response.getHeaders().toString();
		//System.out.println("Response body is "  +responsebody);
		//System.out.println("StatusCode of the response is" +statuscode);
		//System.out.println("Content Type is " +contenttype);
		System.out.println("Content Type is " +header);

		
		Assert.assertEquals(statuscode /*actual value*/,200 /*expected value*/, "Correct status code returned");
	    //Assert.assertEquals(statuscode, 200  );
	}

}
