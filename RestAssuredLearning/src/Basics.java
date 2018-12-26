import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Basics {

	public static void main(String[] args)
	{
		RestAssured.baseURI="http://www.google.com";
		
		given().
				param("weather","Mumbai").
				when().
				get("/ig/api").
				then().assertThat().statusCode(200);
				
		
		
	}

	
}
