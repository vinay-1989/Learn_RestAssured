package Validations;


import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.payloads.user;

import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;



public class createUserApiValidator {
	static ObjectMapper mapper;
	static JsonNode rootnode;
	
	
	public static void validateStatusCode(Response response, int expectedStatusCode) {
		System.out.println("Method 1: validating status code");
	 int actualStatuscode=	response.getStatusCode();
		
		if (actualStatuscode != expectedStatusCode) 
            throw new AssertionError("Expected status code " + expectedStatusCode + ", but got " + actualStatuscode + ".");
        
        System.out.println("Status code validation passed: " + actualStatuscode);
		
	}
	
	public static void structurevalidation(Response response) {
		System.out.println("Method 2: Validating response json struture" );
		try {
			String responseBody = response.getBody().asString();
	      mapper = new ObjectMapper();
	
	 rootnode=	mapper.readTree(responseBody);
	
	
	    if(rootnode.has("code") && rootnode.has("type") && rootnode.has("message")) {
	    	System.out.println("Found all the expected key");
	    }
	    
	    else {
	    	throw new AssertionError("missing one of the field from the response body");
	    }
	
	} catch (Exception e) {
	    e.printStackTrace();
	}
		
	
	}
	
	public static void validateJsonSize(Response response) {
		System.out.println("Method 3: validating Response json Size");
		
		if (rootnode.size()==3) {
			System.out.println("response json size have been validated:: passed");
			
		}
		else {
		 throw new AssertionError("Json schema have been modiied");
		}
		
	}
	
	
	
	public static void validateCodeField() {
		System.out.println("Method 4: validating code field");
		if(rootnode.get("code").asInt()==200) {
			Assert.assertTrue(true, "validateCodeField method passed");
		}
		else {
			Assert.assertEquals(rootnode.get("code").asInt(), 200);
			
		}
			
	}
	
	public static void validateTypeFiled() {
		System.out.println("Method 5: validating Type field");
		if(rootnode.get("type").asText().equalsIgnoreCase("unknown")) {
			Assert.assertTrue(true, "validateTypeFiled method passed");
		}
		else {
			Assert.assertEquals(rootnode.get("type").asText(), "unknown");
		}
		
	
	}
	
	public static void validateMessageField() {
		
		System.out.println("Method 6: validating message field");
		
	Object obj=	rootnode.get("message");
	System.out.println(obj.getClass());
		
		if(rootnode.get("message").asInt()==(user.id)) {
			Assert.assertTrue(true, "validateMessageField method passed");
		}
		else {
			Assert.assertEquals(rootnode.get("message").asText(), user.id);
		}
		
		
		
		
	}

}
