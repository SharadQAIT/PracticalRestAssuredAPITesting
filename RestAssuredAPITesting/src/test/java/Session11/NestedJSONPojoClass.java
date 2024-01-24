package Session11;

import java.util.List;

import Session10.EmployeePojoClass;

public class NestedJSONPojoClass {

/*
 "CompanyName":"XYZ Ltd",
	"Street": "Arifac Avenue",
	"City": "Rajkot",
	"State": "Gujarat",
	"pin code": 110066,
	"BankAccounts": ["Hdfc","SBI" , "Axis"]
 
 */
	
	private String CompanyName;
	private String Street;
	private String City;
	private String State;
	private int pincode;
	private List<String> BankAccounts;
	
	// Use employeepojoclass fields in nestedjosnprojo class
	private List<EmployeePojoClass> employeelist;

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public List<String> getBankAccounts() {
		return BankAccounts;
	}

	public void setBankAccounts(List<String> bankAccounts) {
		BankAccounts = bankAccounts;
	}

	public List<EmployeePojoClass> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(List<EmployeePojoClass> employeelist) {
		this.employeelist = employeelist;
	}
	
}
