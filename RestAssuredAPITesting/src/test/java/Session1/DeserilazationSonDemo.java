package Session1;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
// Deserialize the JSON Response
// https://youtu.be/6IoMyfEOUco?si=cW4W2Suprk2YkxVE
public class DeserilazationSonDemo {

	@Test
	public void createUser()
	{
		//https://reqres.in/api/users
		//Create Request Specification
		RequestSpecification requestspec= RestAssured.given();
		
		//https://reqres.in/api/users
		//specify URL
		requestspec.baseUri("https://reqres.in");
		requestspec.basePath("/api/users");	
		
		//Create Request Body
		
		JSONObject Jsondata= new JSONObject();
		Jsondata.put("name", "Sharad");
		Jsondata.put("job", "QA" );																
		
		//Perform post request
		
		Response response=requestspec
		.contentType(ContentType.JSON)
		.body(Jsondata.toString())
		.post();
		
		ResponseBody responseBody= response.getBody();
		
		//Deserialize responsbody i.e json response body to class project	
		
		JSONPostRequestResponse responseClass= responseBody.as(JSONPostRequestResponse.class);
		//System.out.println(responseClass);
		Assert.assertEquals(responseClass.name	,"Sharad","check for name");
		Assert.assertEquals(responseClass.job	,"QA","check for job");

	}
	
	
}
