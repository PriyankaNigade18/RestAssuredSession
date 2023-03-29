package authentication;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Authentications {
  @Test
  public void basicAuthentication() 
  {
	  //Whenever you will provide any authentication its a part of given()
	  
	  given()
 	  	.auth().basic("postman","password")
	  	
	  	.when()
	  		.get("https://postman-echo.com/basic-auth")
	  		
	  	.then()
	  		.statusCode(200)
	  		.body("authenticated", equalTo(true))
	  		.log().all();
	  System.out.println("Basic Authentication Success!");
  }
  
  
  
  
  
  @Test
  public void digestAuthentication() 
  {
	  //Whenever you will provide any authentication its a part of given()
	  
	  given()
 	  	.auth().digest("postman","password")
	  	
	  	.when()
	  		.get("https://postman-echo.com/basic-auth")
	  		
	  	.then()
	  		.statusCode(200)
	  		.body("authenticated", equalTo(true))
	  		.log().all();
	  System.out.println("Digest Authentication Success!");
  }
  
  
  
  
  
  @Test(priority=3)
  public void preemptiveAuthentication() 
  {
	  //Whenever you will provide any authentication its a part of given()
	  
	  given()
 	  	.auth().preemptive().basic("postman", "password")
	  	
	  	.when()
	  		.get("https://postman-echo.com/basic-auth")
	  		
	  	.then()
	  		.statusCode(200)
	  		.body("authenticated", equalTo(true))
	  		.log().all();
	  System.out.println("Preemptive Authentication Success!");
  }
  
  
  
  @Test(priority=4)
  public void bearersToken()
  {
	  
	  given()
	  	.header("Authorization", "Bearer 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")
	  	
	  	.when()
	  		.get("https://api.github.com/user/repos")
	  		
	  		.then()
	  		.statusCode(200)
	  		.log().all();
	  System.out.println("Bearer token based on the application token authenticated!");
  }
  
  
  @Test(priority=5)
  public void OAuth1Authentication()
  {
	  
//	  given()
//	  .auth().oauth("ConsumerKEy", "ConsmerSecret","AccessToken","SecreatToken")
//	  .when()
//	  .get("url").then().statusCode(200)
//	  .log().all();
	  System.out.println("OAuth 1 authentication done!");
  }
  
  
  
  
  
  
  
  @Test(priority=6)
  public void OAuth2Authentication()
  {
	  given()
	  .auth().oauth2("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")
	  .when()
	  	.get("https://api.github.com/user")
	  	
	  	.then()
	  	.statusCode(200);
	  	//.log().body();
	  
	  System.out.println("OAuth 2 authentocation completed !");
  }
}
