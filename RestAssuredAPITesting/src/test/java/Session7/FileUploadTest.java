package Session7;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.scribejava.core.model.Response;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
// How To Upload File In Rest Assured
// https://youtu.be/fzDBx4kmBPg?si=3sQwJYJhuLxEvuTl
public class FileUploadTest {
	
	@Test
	public void uploadFile()
	{
		//Create file object
		
		File testFileUpload= new File("C:/Users/Sharad Khairnar/Desktop/FileUploadTest.txt");
		File testFileUpload2= new File("C:/Users/Sharad Khairnar/Desktop/FileUploadTest2.txt");

		
		//create Request specification
		
		RequestSpecification requestspec= RestAssured.given();
		
		//Specify URL
		requestspec.baseUri("https://httpbin.org/post");
		requestspec.multiPart("file",testFileUpload);
		requestspec.multiPart("file",testFileUpload2);

		requestspec.contentType("multipart/form-data");
		
		//perform post request
		io.restassured.response.Response response=requestspec.post();
		
		//print response body
		response.prettyPrint();
		
		//validate status code
		Assert.assertEquals(response.statusCode(),200,"Check for status code");
	}
	
	@Test
	public void uploadfile2()
	{
		File testFileUpload= new File("C:/Users/Sharad Khairnar/Desktop/Screenshot_2.png");
		//create Request specification
		
				RequestSpecification requestspec= RestAssured.given();
				
				//Specify URL
				requestspec.baseUri("https://petstore.swagger.io/v2/pet/1/uploadImage");
				requestspec.multiPart("file",testFileUpload);

				requestspec.contentType("multipart/form-data");
				
				//perform post request
				io.restassured.response.Response response=requestspec.post();
				
				//print response body
				response.prettyPrint();
				
				//validate status code
				Assert.assertEquals(response.statusCode(),200,"Check for status code");
			
	}

}
