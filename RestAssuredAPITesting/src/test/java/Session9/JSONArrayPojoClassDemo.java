package Session9;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Session8.Employee;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class JSONArrayPojoClassDemo {

	@Test
	public void createEmployeesJSONArray() throws JsonProcessingException
	{
		//Create first employeeObject

		Employee emp1= new Employee();
		emp1.setFirstName("Sharad");
		emp1.setLastname("Khairnar");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(1200.12);



		//Create second emp2oyeeObject

		Employee emp2= new Employee();
		emp2.setFirstName("bharat");
		emp2.setLastname("khairnar");
		emp2.setGender("Male");
		emp2.setAge(40);
		emp2.setSalary(1300.12);

		//Create third emp2oyeeObject

		Employee emp3= new Employee();
		emp3.setFirstName("Dinesh");
		emp3.setLastname("Pawar");
		emp3.setGender("Male");
		emp3.setAge(30);
		emp3.setSalary(1400.12);



		//Create list of Employee

		List<Employee> listOfEmp= new ArrayList<Employee>();
		listOfEmp.add(emp1);
		listOfEmp.add(emp2);
		listOfEmp.add(emp3);

		// covert employee class objects to json array payload as string

		ObjectMapper objMapper = new ObjectMapper();

		String jsonArrayPayload = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfEmp);

		System.out.println("Employee class objects to json array payload");
		System.out.println(jsonArrayPayload);
		
		//create Request specification
		RequestSpecification respec= RestAssured.given();
		
		respec.baseUri("https://reqres.in/api/users");
		respec.contentType(ContentType.JSON);
		respec.body(jsonArrayPayload);
		
		//Perform post request
		Response response=respec.post();
		System.out.println("------ResponseBody----------");
		response.prettyPrint();
		
		//verify the status code
		
		
		Assert.assertEquals(response.statusCode(), 201);
		
		//Convert JsonArray to employee class objects(Deserilization)
		
		ResponseBody<?> responseBody=response.getBody();
		JsonPath jsonPathview=responseBody.jsonPath();
		
		List<Employee> allEmployees=jsonPathview.getList("json",Employee.class);
		
		System.out.println("-----------Employee objects in ResponseBody-------------");
		
		for(Employee emp:allEmployees)
		{
			System.out.println(emp.getFirstName()+ " " +emp.getLastname());
		}
	}

}
