package Session13;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DemoJSONIgnore {

	@Test
	public void testMethod1() throws JsonProcessingException
	{
		employeePojoClass emp=new employeePojoClass();
		emp.setFirstname("sharad");
		emp.setLastname("Khairnar");
		emp.setGender("Male");
		emp.setAge(30);
		emp.setSalary(10000.5);
		emp.setMarried(true);
		emp.setFullName("Sharad Khairnar");
		
	// serialization: covert employee class object to json payload
		com.fasterxml.jackson.databind.ObjectMapper objectmapper= new com.fasterxml.jackson.databind.ObjectMapper();
		
		
		String jsonpayload=objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
		System.out.println("------print after covert employee class object to json payload");

		System.out.println("jsonpayload:"+jsonpayload);
	
		//Deserialization: Payload string to employee class object
	
		
		String payload= "{\r\n"
				+"\"firstname\" : \"sharad\",\r\n"
				+ "  \"lastname\" : \"Khairnar\",\r\n"
				+ "  \"gender\" : \"Male\",\r\n"
				+ "  \"age\" : 30,\r\n"
				+ "  \"salary\" : 10000.5,\r\n"
				+ "  \"fullName\" : \"Sharad Khairnar\",\r\n"
				+ "  \"married\" : true\r\n"
				+ "}";
		
		employeePojoClass emp2= objectmapper.readValue(payload, employeePojoClass.class);
		System.out.println("------print after covert JSON object to class object");
		System.out.println("FirstName:"+emp2.getFirstname());
		System.out.println("LastName:"+emp2.getLastname());
		System.out.println("Gender:"+emp2.getGender());
		System.out.println("Age:"+emp2.getAge());
		System.out.println("Salary:"+emp2.getSalary());
		System.out.println("Full Name:"+emp2.getFullName());


		}
}
