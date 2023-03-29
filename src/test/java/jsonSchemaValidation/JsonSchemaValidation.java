package jsonSchemaValidation;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
public class JsonSchemaValidation {
  @Test
  public void validateSchema() 
    {
	  given()
	  .when()
	  .get("http://localhost:3000/store")
	  .then()
	  .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storejsonschema.json"));
	  
	  System.out.println("Json schema validation is done!");
	  
	  
  }
  
  
  @Test
  public void validatexmlSchema() 
    {
	  given()
	  .when()
	  .get("http://restapi.adequateshop.com/api/Traveler/?page=1")
	  .then()
	  .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
	  
	
	  System.out.println("XML SChema validation is done!");
	  
  }
  
}
