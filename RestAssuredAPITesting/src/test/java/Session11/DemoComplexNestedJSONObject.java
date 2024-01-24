package Session11;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import Session10.EmployeeAddress;
import Session10.EmployeePojoClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoComplexNestedJSONObject {

	/*
	 * "CompanyName":"XYZ Ltd", "		Street": "Arifac Avenue", "City": "Rajkot",
	 * "State": "Gujarat", "pin code": 110066, "BankAccounts": ["Hdfc","SBI" ,
	 * "Axis"]
	 */

	@Test
	public void createUser() throws JsonProcessingException {
		// Create request payload

		NestedJSONPojoClass requestPayload = new NestedJSONPojoClass();
		requestPayload.setCompanyName("XYZ Ltd");
		requestPayload.setStreet("Arifac Avenue");
		requestPayload.setCity("Rajkot");
		requestPayload.setState("Gujarat");
		requestPayload.setPincode(110066);

		List<String> banks = new ArrayList<String>();
		banks.add("HDFC");
		banks.add("SBI");
		banks.add("Axis");
		requestPayload.setBankAccounts(banks);

		EmployeePojoClass emp1 = new EmployeePojoClass();
		EmployeePojoClass emp2 = new EmployeePojoClass();
		EmployeePojoClass emp3 = new EmployeePojoClass();

		emp1.setFirstname("Suresh");
		emp1.setLastname("Mehra");
		emp1.setGender("Male");
		emp1.setAge(35);
		emp1.setSalary(10000.56);

		EmployeeAddress empaddress = new EmployeeAddress();
		empaddress.setStreet("Parth Avenue");
		empaddress.setState("Gujarat");
		empaddress.setCity("Ahmedabad");
		empaddress.setPincode(380008);
		emp1.setAddress(empaddress);

		emp2.setFirstname("Sharad");
		emp2.setLastname("Khairnar");
		emp2.setGender("Male");
		emp2.setAge(30);
		emp2.setSalary(20000.56);

		// EmployeeAddress emp2address= new EmployeeAddress();
		empaddress.setStreet("Krishna Avenue");
		empaddress.setState("Gujarat");
		empaddress.setCity("Vadodara");
		empaddress.setPincode(382451);
		emp2.setAddress(empaddress);

		emp3.setFirstname("Sarthak");
		emp3.setLastname("Joglekar");
		emp3.setGender("Male");
		emp3.setAge(30);
		emp3.setSalary(30000.56);

		empaddress.setStreet("Roller Avenue");
		empaddress.setState("Gujarat");
		empaddress.setCity("Surat");
		empaddress.setPincode(380015);
		emp3.setAddress(empaddress);

		List<EmployeePojoClass> employees = new ArrayList<EmployeePojoClass>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);

		requestPayload.setEmployeelist(employees);
		// Convert class object to JSON object as string
		com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

		String jsonpayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);

		 
		  RequestSpecification reqSpec= RestAssured.given();
		  
		  // Specify URL
		  reqSpec.baseUri("https://httpbin.org/post");
		
		  // Specify content type and request payload
		  reqSpec.contentType(ContentType.JSON); 
		  reqSpec.body(jsonpayload);
		  
		  // perform post request 
		  Response response=reqSpec.post();
		  
		  System.out.println("---------Response body--------");
		  response.prettyPrint();
	
		  //Check the status code
		  Assert.assertEquals(response.statusCode(),200,"Check for stauts code");
	
	}
}
