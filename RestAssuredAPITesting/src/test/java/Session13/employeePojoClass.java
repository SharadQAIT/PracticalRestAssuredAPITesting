package Session13;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value= {"fullName","gender"})
public class employeePojoClass {

	/*
	{
	"firstname": "Suresh",
	"lastName" : "Mehra",
	"gender": "Male",
	"age": 35
	salary:100000
	IsMarried: true
	} 
	 */
	
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	
//	@JsonProperty(access= JsonProperty.Access.READ_ONLY) // Only salary field use in serialization
//	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY) // Only salary field use in diserialization
	@JsonProperty(access= JsonProperty.Access.READ_WRITE) // salary field use in both serialization and diserialization
	
	private double salary;
	private boolean isMarried;
	
//	@JsonIgnore // Ignore particular fields
	private String fullName;

	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean isMarried() {
		return isMarried;
	}
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
	
}
