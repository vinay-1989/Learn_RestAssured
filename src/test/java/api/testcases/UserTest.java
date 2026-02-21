package api.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Validations.createUserApiValidator;
import api.endpoints.userendpoints;
import api.payloads.user;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	user userPayload;
	
	@BeforeClass
	public void generateTestData() {
		System.out.println("test generate method");
		faker=new Faker();
		userPayload=new user();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	
	}
	
	@Test
	public void testCreateuser() {
		System.out.println("test method");
		Response response=userendpoints.createuser(userPayload);
		System.out.println("Response body::::::::");
		response.then().log().body();
		createUserApiValidator.validateStatusCode(response, 200);
		createUserApiValidator.structurevalidation(response);
		createUserApiValidator.validateJsonSize(response);
		createUserApiValidator.validateCodeField();
		createUserApiValidator.validateTypeFiled();
	    createUserApiValidator.validateMessageField();
	}
}

