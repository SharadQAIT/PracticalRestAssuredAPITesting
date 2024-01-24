package Session5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//Create JSON Array Using JSON Object and List
//https://youtu.be/FxTC9RCc5Z4?si=tkIRfzRrZPEeoGqt
public class JSONArrayDemo {

	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void createUserUsingJSONObject()
	{
		//Create JSONOBjects for users
		
		JSONObject user1 = new JSONObject();
		user1.put("firstname", "Sharad");
		user1.put("lastname", "Khairnar");
		user1.put("age", 30);
		user1.put("salary", 10000.56);
		
		JSONObject user2 = new JSONObject();
		user2.put("firstname", "Sarthak");
		user2.put("lastname", "Joglekar");
		user2.put("age", 32);
		user2.put("salary", 20000.56);
		
		JSONObject user3 = new JSONObject();
		user3.put("firstname", "Bharat");
		user3.put("lastname", "Khairnar");
		user3.put("age", 34);
		user3.put("salary", 30000.56);
		
		// add JSON Object to JSON Array
		JSONArray userPayLoad= new JSONArray();
		userPayLoad.add(user1);
		userPayLoad.add(user2);
		userPayLoad.add(user3);
		
		//create Request specification
		 
		RequestSpecification reqSpec= RestAssured.given();
		
		//specify URL
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(userPayLoad);
		
		//perform post request
		Response response=reqSpec.post();
		response.prettyPrint();
		
		//validate the status code
		Assert.assertEquals(response.statusCode(), 201);
		
	}
	
	@Test(priority=2)
	@SuppressWarnings("unchecked")
	public void createUserUsingList()
	{
		//Create JSONOBjects for users
		
				JSONObject user4 = new JSONObject();
				user4.put("firstname", "Sharad");
				user4.put("lastname", "Khairnar");
				user4.put("age", 30);
				user4.put("salary", 10000.56);
				
				JSONObject user5 = new JSONObject();
				user5.put("firstname", "Sarthak");
				user5.put("lastname", "Joglekar");
				user5.put("age", 32);
				user5.put("salary", 20000.56);
				
				JSONObject user6 = new JSONObject();
				user6.put("firstname", "Bharat");
				user6.put("lastname", "Khairnar");
				user6.put("age", 34);
				user6.put("salary", 30000.56);
							

				// Create JSON Array using List
				List<Map<String,Object>> jsonArrayListPayload=new ArrayList();
				jsonArrayListPayload.add(user4);
				jsonArrayListPayload.add(user5);
				jsonArrayListPayload.add(user6);
				
				
				//create Request specification
				 
				RequestSpecification reqSpec= RestAssured.given();
				
				//specify URL
				reqSpec.baseUri("https://reqres.in/api/users");
				reqSpec.contentType(ContentType.JSON);
				reqSpec.body(jsonArrayListPayload);
				
				//perform post request
				Response response=reqSpec.post();
				response.prettyPrint();
				
				//validate the status code
				Assert.assertEquals(response.statusCode(), 201);

		
	}
}
