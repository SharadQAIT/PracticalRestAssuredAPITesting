package Session14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 
 Headers are metadata(Data about Data) of request and response of an API
 
 
 Interfaces RequestSpecification and ResponseSpecification have multiple overloaded methods named "headers()" and "header()".
 
 RequestSpecification
 ------------------------
 We can use headers() or header() methods of requestspecification to send headers with a request to an API
 
 OverLoaded Methods
 -------------------------
 
 1. RequestSpecification headers (String firstHeaderName,Object firstheaderValue,Object...headerNameValuePairs);
 2. RequestSpecification headers (Map<String, ?>headers);
 3. RequestSpecification headers (Headers, headers);
 4. RequestSpecification header (String headerName,Object headerValue,Object...additionalHeaderValues);
 5. RequestSpecification header(Header header);
 
 Example:
 Key: "Header1"
 Value : "Value1"
 
 ResponseSpecification
 -------------------------------------------------------------
use headers() and header() method of ResponseSpecification to put assertions on headers received with response
 
 
 
 */
public class AddHeaderRequestDemo {

	@Test
	public void testMethod1() {

		// Multiple headers call
		Map<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("Header1", "Value1");
		requestHeader.put("Header2", "Value2");

		Header header1 = new Header("Header1", "Value1");
		Header header2 = new Header("Header1", "Value1");
		Header header3 = new Header("Header1", "Value1");

		List<Header> headerlist = new ArrayList<Header>();
		headerlist.add(header1);
		headerlist.add(header2);
		headerlist.add(header3);

		Headers headerObj = new Headers(headerlist);

		/*
		 * RequestSpecification requestSpec = RestAssured.given();
		 * 
		 * // Single Add Header // requestSpec.header("Header1", "Value1");
		 * 
		 * // Multiple headers call // requestSpec.headers(requestHeader);
		 * 
		 * requestSpec.headers(headerObj);
		 * 
		 * requestSpec.log().headers(); // Specify URL
		 * requestSpec.baseUri("https://reqres.in/api/users?page=1");
		 * 
		 * // Perform get request requestSpec.get();
		 * 
		 * // validate response code
		 * 
		 * Response response = requestSpec.get();
		 * 
		 * Assert.assertEquals(response.statusCode(), 200);
		 * 
		 * // https://reqres.in/api/users?page=1
		 * 
		 */

		// BDD Style (Given,when,then)

		RestAssured
		.given()
		.headers(headerObj)
		.log().headers()

		.when()
		.get("https://reqres.in/api/users?page=1")

		.then()
		.log().body();

	}

}
