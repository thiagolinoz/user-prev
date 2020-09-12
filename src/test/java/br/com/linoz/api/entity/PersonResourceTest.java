package br.com.linoz.api.entity;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonResourceTest {
	
	private String personMock;
	
	@BeforeEach
	public void mockAPerson() {
		personMock = "{ \"id\" : 1,\"name\" : \"Salah\",\"document\" : \"12345\",\"address\" : \"Avenue\"}";
	}
	
	@Test
	public void personResourceOk() {
		given()
        .when().get("/person")
        .then()
           .statusCode(200);
	}
	
	@Test
	public void createAPerson() {
		given()
			.contentType("application/json")
			.body(personMock)
		.when()
			.post("/person")
		.then()
			.statusCode(200)
			.body(is("The person Salah of document 12345 who lives at Avenue was created"));
	}
	
	@Test
	public void updateAPerson() {
		String updatedPersonMock = "{ \"id\" : 1,\"name\" : \"M. Salah\",\"document\" : \"12345\",\"address\" : \"Avenue\"}";
		
		given()
			.contentType("application/json")
			.body(updatedPersonMock)
		.when()
			.put("/person")
		.then()
			.statusCode(200)
			.body(is("The person M. Salah of document 12345 who lives at Avenue was updated"));
	}
	
	@Test
	public void findPersonWitIdThatDoesNotExist() {
		given()
        .when().get("/person/25")
        .then()
           .statusCode(404);
	}
	
	@Test
	public void deletePersonWitIdThatDoesNotExist() {
		given()
        .when().delete("/person/25")
        .then()
           .statusCode(404);
	}
}
