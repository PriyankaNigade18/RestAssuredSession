package httpREquest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class GETRequest 
{
	int id;
	
  @Test(priority=1)
  public void getUser() 
  {
	  given()
	  
	  .when()
	  	.get("https://reqres.in/api/users/2")
	  .then()
	  	.statusCode(200)
	  	.body("data.first_name", equalTo("Janet"))
	  	.log().all();
	
	  
  }
  
  
  
  /*
   * let us create one user you will get id.
   * same id we can update and delete so Create id as global variable
   * so that we can use it in throughout the test case.
   */
  @Test(priority=2)
  public void createUser()
  {
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("name","Priyanka");
	  data.put("job","trainer");
	 
	  id=given()
	   	.contentType("application/json")
	   	.body(data)
	  .when()
	  	.post("https://reqres.in/api/users")                                         //once you pass this request you will get response
	  	.jsonPath().getInt("id");                                                     //same id from response we can capture from jasonPath()

	  System.out.println(id);
  }
  
  @Test(priority=3,dependsOnMethods = "createUser")
  public void updateUSer()
  {
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("name","Sarang");
	  data.put("job","QA");
	  
	  
	   given()
	   	.contentType("application/json")
	   	.body(data)
	  .when()
	  	.put("https://reqres.in/api/users/"+id)
	  .then()
	  	.statusCode(200)
	  	.log().all();
	   System.out.println("same user is updated!");
  }
  
  @Test(priority=4)
  public void deleteUser()
  {
	  given()
	  .when()
	  	.delete("https://reqres.in/api/users/"+id)
	  .then()
	  	.statusCode(204)
	  	.log().all();
  }
  
}
