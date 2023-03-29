package jsonSchemaValidation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class JSonDataVAlidation {
  @Test
  public void dataValidation() 
  {
	  //Approach1: To validate title of the book
	  given()
	  	.contentType(ContentType.JSON).
	  when()
	  	.get("http://localhost:3000/store").
	  	
	  then()
	  	.statusCode(200)
	  	.body("book[3].title", equalTo("Python for beginners"))
	  	.header("Content-Type","application/json; charset=utf-8")
	  	.log().all();
	  
	  
  }



//@Test
public void dataValidationWithResponse()
{
	
	//Response is a class
	Response res=given()
		.contentType(ContentType.JSON).
	when()
		.get("http://localhost:3000/store");
	
	/*
	 * res is of type Response so we can apply assertion on the same
	 */
	//validate status code
	Assert.assertEquals(res.getStatusCode(),200);
	System.out.println("Status code validation completed!");
	//validate header
	Assert.assertEquals(res.getHeader("Content-Type"),"application/json; charset=utf-8");
	System.out.println("Header validation completed!");
	
	//to validate any author name
	String bookAuthor=res.jsonPath().get("book[0].author").toString();
	Assert.assertEquals(bookAuthor,"Dennis Rtiche");
	System.out.println("Author name matched!");
	
}




//Approach where Json file is Dynamic

@Test
public void totalBooks()
{
	Response res1=given()
				.contentType(ContentType.JSON).
				when()
				.get("http://localhost:3000/store");
		
	/*
	 * res is of type Response so convert it into Object type 
	 * Using JSONObject class and for validation convert it into String
	 * using toString() method
	 * but 
	 * getBody() is user to extract response object as string so use 
	 * res.getBody().asString
	 * 
	 */
		
	JSONObject jo=new JSONObject(res1.getBody().asString());
	
	
	System.out.println("Total Books are: "+jo.getJSONArray("book").length());
		
	//To get all titles from book store
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		
		System.out.println("Available Book title is: "+booktitle);
	}
	System.out.println("************************");
	
	//search for a title-"Java Fundamentals"
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		
		if(booktitle.contains("Java Fundamentals"))
		{
			System.out.println("Match Found!");
			break;
		}
		
		System.out.println("Java Fundamentals title book is available!");
		
	}
	
	//Search for all book title and book price
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		//to get title
		String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		
		System.out.println(booktitle+" : "+price);
		
		
		
		
	}
	System.out.println("**********************************");
	
	//To calculate total price of all books
	int totalPrice=0;
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		totalPrice=totalPrice+Integer.parseInt(price);
		
	}
	
	System.out.println("Total Price of all Books is: "+totalPrice);
	
	
	
	
	
}











}