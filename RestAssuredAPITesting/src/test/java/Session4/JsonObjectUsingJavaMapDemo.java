package Session4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
// What is JSON | Create JSON Object Using Java Map
// https://youtu.be/DW0spzG2ONw?si=e4RloswoDIfxTLnM

public class JsonObjectUsingJavaMapDemo {

	@Test (enabled=false)
	public void createAuthToken() {
		// create JSON Object using Java map
		/*
		 * { "username" : "admin", 
		 * "password" : "password123" 
		 * }
		 */

		Map<String,String> authToken=new HashMap<String,String>();
		authToken.put("username", "admin");
		authToken.put("password", "password123");
		
		Response response=RestAssured.given().baseUri("https://restful-booker.herokuapp.com/auth")
		.contentType(ContentType.JSON)
		.body(authToken)
		.post();
		
		response.prettyPrint();
		
		//verify status code
		Assert.assertEquals(response.statusCode(), 200,"check for status code");
	}
	
	@Test
	public void createUser()
	{
              /*{
				"firstname":"Amod",
				"lastname":"Mahajan",
				"age":28,
				"salary":10000.56,
				"IsMarried":true,
				"Hobbies":["Music","Computer", "Games"],
				"Teckskills":{
						"Programming language":"JAVA",
						"WebAutomation": "Selenium",
						"API Testing" : "Rest assured"
					} */
		
			// why used <String,Object> - because object included all kind of data types.
		
			HashMap<String,Object> Userdata=new HashMap<String, Object>();
			Userdata.put("firstname", "Amod");
			Userdata.put("lastname", "Mahajan");
			Userdata.put("age", 28);
			Userdata.put("salary", 10000.56);
			Userdata.put("IsMarried", true);
			
			ArrayList<String> hobbies= new ArrayList<String>();
			hobbies.add("Music");
			hobbies.add("Computer");
			hobbies.add("Games");
			Userdata.put("Hobbies", hobbies);
			
			HashMap<String,String> TechSkill=new HashMap<String, String>();
			TechSkill.put("Programming language", "JAVA");
			TechSkill.put("WebAutomation", "Selenium");
			TechSkill.put("API Testing", "Rest assured");
			Userdata.put("Teckskills", TechSkill);
			
			Response response=RestAssured.given().baseUri("https://reqres.in/api/users")
					.contentType(ContentType.JSON)
					.body(Userdata)
					.post();
					
					response.prettyPrint();
					
					//verify status code
					Assert.assertEquals(response.statusCode(), 201,"check for status code");
				
		}		
}

