package httpREquest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {
  @Test
  public void testPath_QueryParam()
  {
	  //https://reqres.in/api/users?page=2&id=5
	   //       domain  /path param? Query param
	  /*
	   * If you see any url with path ad query parameter
	   * the it is good practice to keep them separately.
	   * Query parameters will pass with request but path you need to add in {}
	   */
	  given()
	  	.pathParam("mypath","users")
	  	.queryParam("page",2)
	  	.queryParam("id",5)
	  .when()
	  	.get("https://reqres.in/api/{mypath}")
	  .then()
	  	.statusCode(200)
	  	.log().all();
	  
	  
  }
}
