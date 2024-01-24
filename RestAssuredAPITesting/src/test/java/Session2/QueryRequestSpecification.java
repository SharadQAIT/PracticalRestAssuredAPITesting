package Session2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
//How To Retrieve/Query Request Specification
//https://youtu.be/CxT3rfmQs6g?si=9xWb9iCfkWbjPqIh
public class QueryRequestSpecification {

	
	@Test
	public void createUser()
	{
		
		//Create Json Object
		JSONObject jsondata=new JSONObject();
		jsondata.put("email", "Sarthak12@gmail.com");
		jsondata.put("name", "Sarthak");
		jsondata.put("gender", "male");
		jsondata.put("status", "active");
		
		
		//Create request specification
		RequestSpecification respec= RestAssured.given();
		
		//specify URL
		respec.baseUri("https://gorest.co.in");
		respec.basePath("v2/users");	
		respec.contentType(ContentType.JSON)
		.body(jsondata.toString());
	
		//query details from request specification
		QueryableRequestSpecification queryRequest=SpecificationQuerier.query(respec);
		
		//get base URI
		
		String retrieveBaseURI= queryRequest.getBaseUri();
		System.out.println("Base URI:"+retrieveBaseURI);
		
		//get base path
		String retrieveBasepath= queryRequest.getBasePath();
		System.out.println("Base URI:"+retrieveBasepath);
	
	
		//get body
		
		String retrievebody= queryRequest.getBody();
		System.out.println("Body :"+retrievebody);
	
		// get header
		Headers allheaders=queryRequest.getHeaders();
		System.out.println("\n-----------------------Request Header----------------------\n");
		for(Header h:allheaders)
		{
			System.out.println("Header name:"+h.getName()+"Header value"+h.getValue());
		}
	}
	
}