package serializationandDeserialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationandDeserialization {
  @Test
  public void pojoToJson() throws JsonProcessingException
  {
	  
	  StudentPojo obj=new StudentPojo();
	  obj.setName("Tom");
	 obj.setLocation("France");
	 obj.setPhone("9999999");
	 String arr[]= {"API","Appium"};
	 obj.setCourses(arr);
	 
	 //converting pojo to json is called serialization
	 
	 ObjectMapper objMap=new ObjectMapper();
	 String jsondata=objMap.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	 
	 System.out.println(jsondata);
	 System.out.println("Serialization is completed!");
	  
  
  }
  
  
  
  
  
  
  @Test
  public void jsonToPojo() throws JsonMappingException, JsonProcessingException
  {
	  String jsondata="{\n"
	  		+ "  \"name\" : \"Tom\",\n"
	  		+ "  \"location\" : \"France\",\n"
	  		+ "  \"phone\" : \"9999999\",\n"
	  		+ "  \"courses\" : [ \"API\", \"Appium\" ]\n"
	  		+ "}";
	  
	  ObjectMapper obj=new ObjectMapper();
	 StudentPojo pojo= obj.readValue(jsondata,StudentPojo.class);
	  
	 System.out.println(pojo.getName());
	 System.out.println(pojo.getLocation());
	 System.out.println(pojo.getPhone());
	 System.out.println(pojo.getCourses()[0]);
	 System.out.println(pojo.getCourses()[1]);
	  
	  
	  
	  
	  
	  
	  
  }
}
