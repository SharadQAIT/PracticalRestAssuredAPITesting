package Session3;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

// Send XML Data As Payload In Request | Validate XML response
// https://youtu.be/uEC9NG6_IGA?si=4pVbu4a3sTOLZRRN
public class ValidateXMLResponse {

	
	@Test(priority = 1)
	public void getTraveldata()
	{
		RequestSpecification requestSpec= RestAssured.given();
		
		//specify URI
		requestSpec.baseUri("http://restapi.adequateshop.com");
		requestSpec.basePath("/api/Traveler");
		
		//add query parameter
		requestSpec.queryParam("page", "1");
	
		//Specify header
		requestSpec.header("accept","application/xml");
		
		//perform post request
		Response response=requestSpec.get();
		response.prettyPrint();

		//Approach 1
		//response.then().body("TravelerinformationResponse.travelers.Travelerinformation[3].name", Matchers.equalTo("vano"));
	
		
		//Approach 2
		XmlPath objxml= new XmlPath(response.asString());
		String travellerName=objxml.get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellerName, "Developer","check for traveller name");
	
		//verify total travelers to be 10
		List<String> listofTrvallers=objxml.getList("TravelerinformationResponse.travelers.Travelerinformation");
		int totaltravelerCount=listofTrvallers.size();
		Assert.assertEquals(totaltravelerCount,10,"Check fot total no of travellers node");
	
		//verify for name vano in travelers list
		List<String> listofTrvallersName=objxml.getList("TravelerinformationResponse.travelers.Travelerinformation[2].name");
	
		//print all the names in the travellers list
		
		boolean found = false;
		for (String traveller:listofTrvallersName)
		{		
			System.out.println(traveller);
			
			if(traveller.equals("vano"))
			{
				found = true;
				break;
			}
			Assert.assertEquals(found, true);
		}
	}	
	@Test(enabled=false)
	public void AddPet()
	{
		// create request specification
		
		
		String xmlRequestBody="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<Pet>\r\n"
				+ "	<id>0</id>\r\n"
				+ "	<Category>\r\n"
				+ "		<id>0</id>\r\n"
				+ "		<name>string</name>\r\n"
				+ "	</Category>\r\n"
				+ "	<name>doggie</name>\r\n"
				+ "	<photoUrls>\r\n"
				+ "		<photoUrl>string</photoUrl>\r\n"
				+ "	</photoUrls>\r\n"
				+ "	<tags>\r\n"
				+ "		<Tag>\r\n"
				+ "			<id>0</id>\r\n"
				+ "			<name>string</name>\r\n"
				+ "		</Tag>\r\n"
				+ "	</tags>\r\n"
				+ "	<status>available</status>\r\n"
				+ "</Pet>";
		
		
		String jsonData= "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		
		RequestSpecification requestSpec= RestAssured.given();
		
		//specify URI
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/pet");
		
		//Specify Header
		requestSpec.header("Content-Type","application/xml");
		requestSpec.header("accept","application/xml");
		requestSpec.body(xmlRequestBody);
				
		//perform post request
		Response response=requestSpec.post();
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode()/* Actual status code*/,200/*Expected status code*/,"check for status code");
		
		//verify response matcher of xml data
		response.then().body("pet.name", Matchers.equalTo("doggie"));
	}
}
