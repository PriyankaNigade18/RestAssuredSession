package jsonSchemaValidation;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLDataValidation {
  @Test
  public void validateXMLResponseWithoutVariable() 
  {
	  given()
	  .when()
	  	.get("http://restapi.adequateshop.com/api/Traveler/?page=1")
	  	.then()
	  	.statusCode(200)
	  	.header("Content-Type","application/xml; charset=utf-8" )
	  	.body("TravelerinformationResponse.traveler.Travelerinformation[1].id", equalTo(11133));
	  System.out.println("Basic validation is completed!");
	  	
	  	
  }
}
