package httpREquest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequestWays
{
	int id;
	//first way using Hashmap
  //@Test(priority=1)
  public void testHashMap() 
  {
	  HashMap data=new HashMap();
	  data.put("name","Jimy");
	  data.put("location","france");
	  data.put("phone","111111");
	  String coursearr[]= {"a","aaa"};
	  data.put("courses",coursearr);
	  
	  
	  given()
	  	.contentType("application/json")
	  	.body(data)
	  	
	  .when()
	  	.post("http://localhost:3000/students")
	  .then()	
	  	.statusCode(201)
	  	.body("name",equalTo("Jimy"))
	  	.body("location",equalTo("france"))
	  	.body("phone",equalTo("111111"))
	  	.body("courses[0]",equalTo("a"))
	  	.body("courses[1]",equalTo("aaa"))
	  	.header("Content-Type","application/json; charset=utf-8")
	  	.log().all();
	   
  }
  
  //2. using org.json library
 //@Test(priority=1)
  public void postUsingJsonLibrary()
  {
	  JSONObject data1=new JSONObject();
	  data1.put("name","Jimy");
	  data1.put("location","france");
	  data1.put("phone","111111");
	  String arr[]={"a","aaa"};
	  data1.put("courses",arr);
	  
	  
	 given()
	  	.contentType("application/json")
	  	.body(data1.toString())
	  	
	  .when()
	  	.post("http://localhost:3000/students")
	  .then()	
	  	.statusCode(201)
	 
	  	.body("name",equalTo("Jimy"))
	  	.body("location",equalTo("france"))
	  	.body("phone",equalTo("111111"))
	  	.body("courses[0]",equalTo("a"))
	  	.body("courses[1]",equalTo("aaa"))
	  	.header("Content-Type","application/json; charset=utf-8")
	  	.log().all();
	  	
	   
	  
  }
  
  //3. Post data body using POJO plain old java object
  //@Test
  public void postUsingPOJO()
  {
	  Pojo_PostRequest data=new Pojo_PostRequest();
	  data.setName("Jimy");
	  data.setLocation("france");
	  data.setPhone("111111");
	  String coursesarr[]= {"a","aaa"};
	  data.setCourses(coursesarr);
	  
	  given()
	  	.contentType("application/json")
	  	.body(data)
	  	
	  .when()
	  	.post("http://localhost:3000/students")
	  .then()	
	  	.statusCode(201)
	 
	  	.body("name",equalTo("Jimy"))
	  	.body("location",equalTo("france"))
	  	.body("phone",equalTo("111111"))
	  	.body("courses[0]",equalTo("a"))
	  	.body("courses[1]",equalTo("aaa"))
	  	.header("Content-Type","application/json; charset=utf-8")
	  	.log().all();
	  
  }
  
  @Test
  public void postUsingFile() throws FileNotFoundException
  {
	  File f1=new File(".\\body.json");
	  FileReader fr=new FileReader(f1);
	  JSONTokener jt=new JSONTokener(fr);
	  JSONObject data=new JSONObject(jt);
	  
	  given()
	  	.contentType("application/json")
	  	.body(data.toString())
	  	
	  .when()
	  	.post("http://localhost:3000/students")
	  .then()	
	  	.statusCode(201)
	 
	  	.body("name",equalTo("Jimy"))
	  	.body("location",equalTo("france"))
	  	.body("phone",equalTo("111111"))
	  	.body("courses[0]",equalTo("a"))
	  	.body("courses[1]",equalTo("aaa"))
	  	.header("Content-Type","application/json; charset=utf-8")
	  	.log().all();
  }
  
  @Test(priority=2)
  public void deleteUser()
  {
	  given()
	  
	  .when()	
	  	.delete("http://localhost:3000/students/4")
	  .then()	
	  .statusCode(200);
  }
  
}
