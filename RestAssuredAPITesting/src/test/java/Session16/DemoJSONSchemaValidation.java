package Session16;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.*;


public class DemoJSONSchemaValidation {
	
	/*
	 
	 https://www.youtube.com/watch?v=b-SRuBQ79zA&ab_channel=CodeStudio - Video URL
	 
	 */
	
	
	@Test
	public void testMethod()
	{
		String requestPayload = "{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		
		RestAssured
			.given()
			.baseUri("https://restful-booker.herokuapp.com/auth")
			.contentType(ContentType.JSON)
			.body(requestPayload)
			
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.body("token",Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
				//.body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\ASUS\\Desktop\\schema.json")));
	}
}
