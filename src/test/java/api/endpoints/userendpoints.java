package api.endpoints;

import api.payloads.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class userendpoints {
	
	public static Response  createuser(user payload){
		System.out.print("Request");
		
	Response response = given().log().body()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		
		.body(payload)
		
		.when()
		.post(Routes.post_url);
	
	return response;
	}

}
