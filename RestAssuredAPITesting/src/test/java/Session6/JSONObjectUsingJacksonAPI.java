package Session6;

import java.util.Iterator;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//How To Create A JSON Object Using Jackson API
//https://youtu.be/KYGV3oXwxsw?si=YGTIrrTBJcxubzyL
public class JSONObjectUsingJacksonAPI {

	@Test
	public void createUser()
	{
		//https://reqres.in/api/users

		/*{ Nested Json Object
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
					}
			}*/

		//Create ObjectMapper class instance

		ObjectMapper objectMapper = new ObjectMapper();


		//Create object node i.e json node
		ObjectNode userDetails=objectMapper.createObjectNode();

		userDetails.put("firstname", "siddhant");
		userDetails.put("lastname", "Joglekar");
		userDetails.put("age", "28");
		userDetails.put("salary", 10000.56);
		userDetails.put("IsMarried", false);
		//userDetails.s("Hobbies", objectMapper.convertValue(Arrays.asList("Cooking", "Music"),JsonNode.class));

		ObjectNode techSkills=objectMapper.createObjectNode();
		techSkills.put("Programming language", "JAVA");
		techSkills.put("WebAutomation", "Selenium");
		techSkills.put("API Testing", "Rest assured");

		userDetails.set("TechSkill", techSkills);

		//print userDetails JSON Object

		try
		{
			String UserDetailsAsString= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);
			System.out.println("Created JSON Node is:" + UserDetailsAsString);
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
		}

		//Retrieve field value from JSON Object or ObjectNode
		String firstname=userDetails.get("firstname").asText();
		String lastname=userDetails.get("lastname").asText();
		Integer age=userDetails.get("age").asInt();
		Double salary=userDetails.get("salary").asDouble();
		Boolean ismarried=userDetails.get("IsMarried").asBoolean();
		System.out.println("Firstname:"+ firstname+"Lastname:"+lastname+"Age:"+age+"Salary:"+salary+"IsMarried:"+ismarried);

		String WebAutomation=techSkills.get("WebAutomation").asText();
		System.out.println("WebAutomation Technology: "+WebAutomation);


		// Retrieve all key-value pair from JSON Object or ObjectNode

		System.out.println("------------print all fields name-----------\n");

		java.util.Iterator<String> fieldNameIterator=userDetails.fieldNames();

		while(fieldNameIterator.hasNext())
		{
			System.out.println(fieldNameIterator.next());
		}

		System.out.println("------------Print all fields values----------\n");

		Iterator<com.fasterxml.jackson.databind.JsonNode> fieldvaluesterator= userDetails.elements();

		while(fieldvaluesterator.hasNext())
		{
			System.out.println(fieldvaluesterator.next());
		}

		System.out.println("------------Print all field name and values both----------\n");

		Iterator<Entry<String, com.fasterxml.jackson.databind.JsonNode>> keyvalueEntries= userDetails.fields();

		while(keyvalueEntries.hasNext())
		{
			Entry<String, com.fasterxml.jackson.databind.JsonNode> node=keyvalueEntries.next();

			node.getKey();
			System.out.println("Key:"+ node.getKey()+","+"value"+node.getValue());
		}

		// Remove field from json object or object node

		/*
		 * String removedValued= userDetails.remove("firstname").asText();
		 * System.out.println("Removed firstname value is:" + removedValued);
		 */
		
		//update json object or object node
				userDetails.put("lastName", "Sharma");
				techSkills.put("Programming language", "C#");
				userDetails.set("TechSkill", techSkills);
				//print userDetails JSON Object

				try {
					String UserDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);

					System.out.println("JSON Node After Remove method:" + UserDetailsAsString);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//create Request Specification
				RequestSpecification reqSpec = RestAssured.given();
				
				//specify URL
				reqSpec.baseUri("https://reqres.in/api/users");
				reqSpec.contentType(ContentType.JSON);
				reqSpec.body(userDetails);

				//perform post request
				Response response = reqSpec.post();

				System.out.println("-------------Print Http response body-----------------------------");
				response.prettyPrint();

				//Validate the status code
				Assert.assertEquals(response.statusCode(), 201,"Check for status code.");
	}

}
