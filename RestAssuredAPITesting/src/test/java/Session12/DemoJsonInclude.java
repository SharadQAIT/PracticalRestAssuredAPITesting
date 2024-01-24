package Session12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DemoJsonInclude {

	@Test
	public void testMethod1() throws JsonProcessingException
	{
		// create payload
		
		employeePojoclass emp=new employeePojoclass();
		emp.setFirstname("sharad");
	//	emp.setLastname("Khairnar");
		emp.setGender("Male");
	//	emp.setAge(30);
		emp.setSalary(10000.5);
	//	emp.setMarried(true);
		
		//Empty array
		String [] hobbies= new String[2];
		hobbies[0]= "Reading";
		hobbies[1]= "Music";
		emp.setHobbies(hobbies);
		
		// Empty list
		List<String> degrees=new ArrayList<String>();
		degrees.add("BCA");
		degrees.add("MCA");
		emp.setDegrees(degrees);
		
		
		//Empty Map
		Map<String,String> familyMembers =new HashMap<String, String>();
		familyMembers.put("1", "Mother");
		familyMembers.put("2", "Mother");
		emp.setFamilymembers(familyMembers);
		
		//convert employee class object to json payload as string
		
		com.fasterxml.jackson.databind.ObjectMapper objectmapper= new com.fasterxml.jackson.databind.ObjectMapper();
		
		String jsonpayload=objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
		System.out.println("jsonpayload:"+jsonpayload);
	
	}
}
