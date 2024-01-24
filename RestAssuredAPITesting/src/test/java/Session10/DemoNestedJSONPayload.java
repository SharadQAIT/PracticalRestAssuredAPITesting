package Session10;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoNestedJSONPayload {

	@Test
	public void createUser() throws JsonProcessingException
	{
		
		/*
		{

	"firstName": "Suresh",
	"lastName": "Mehra",
	"gender": "Male",
	"age":35
	"salary":10000.56,
	"Address":{
		"Street": "Parth Avenue",
		"City": "Ahmedabad",
		"State": "Gujarat",
		"pin code": 380008
				}
		}
	        */
		
		EmployeePojoClass emp1= new EmployeePojoClass();
		emp1.setFirstname("sharad");
		emp1.setLastname("Khairnar");
		emp1.setGender("Male");
		emp1.setAge(30);
		emp1.setSalary(10000.56);
		EmployeeAddress emp1address= new EmployeeAddress();
		emp1address.setStreet("Parth Avenue");
		emp1address.setState("Gujarat");
		emp1address.setCity("Ahmedabad");
		emp1address.setPincode(380008);
		emp1.setAddress(emp1address);
		
		//Serialization : convert class object to json object as string
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		String jsonpayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		System.out.println("jsonpayload:"+jsonpayload);
	
		
		RequestSpecification reqSpec= RestAssured.given();
		
		//Specify URL
		reqSpec.baseUri("https://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonpayload);
		
		// perform post request
		Response response=reqSpec.post();
		
		response.prettyPrint();
	}
}
