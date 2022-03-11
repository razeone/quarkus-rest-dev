package com.ibm;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CardTransactionEndpointTest {

    static final String PATH = "/card-transaction";
    static final String HEADER_NAME = "Content-Type";
    static final String CONTENT_TYPE = "application/json";
    
    
    @Test
    public void testCardTransactionResource() {
        given()
          .when().get(PATH)
          .then()
             .statusCode(200)
             .body(containsString("\"commerceName\":\"IBM\""));
    }

    @Test
    public void testCardTransactionResourceGet() {
        given()
          .when().get(PATH + "/1")
          .then()
             .statusCode(200)
             .body(containsString("\"commerceName\":\"IBM\""));
    }

    @Test
    public void testCardTransactionResourceGetNotFound() {
        given()
          .when().get(PATH + "/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testCardTransactionResourceDelete() {
        given()
          .when().delete(PATH + "/1")
          .then()
             .statusCode(204);
    }

    @Test
    public void testCardTransactionResourceDeleteUnexistent() {
        given()
          .when().delete(PATH + "/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testCardTransactionResourcePost() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\",\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().post(PATH)
          .then()
             .statusCode(201);
    }

    @Test
    public void testCardTransactionResourcePostErr() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"id\": 1, \"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\",\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().post(PATH)
          .then()
             .statusCode(422);
    }

    @Test
    public void testCardTransactionResourcePut() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"accountId\": \"e9233dc4-40e6-4a58-8f7a-4543f6e2cf3f\",\"amount\": 150.2,\"cardNumber\": \"1234\",\"commerceName\": \"IBM\",\"customerId\": \"f980f7c1-c07a-48d0-b509-23e496e38d1f\",\"status\": \"PENDING\",\"timestamp\": \"2022-03-03T19:01:39.73Z[UTC]\",\"type\": \"PURCHASE\"}")
          .when().put(PATH + "/1")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCardTransactionResourcePutErr() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"cardNumber\":\"123456789\",\"amount\":\"100\", \"processed\":true,\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().put(PATH + "/1")
          .then()
             .statusCode(422);
    }

    @Test
    public void testCardTransactionResourcePutUnexistent() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"accountId\": \"e9233dc4-40e6-4a58-8f7a-4543f6e2cf3f\",\"amount\": 150.2,\"cardNumber\": \"1234\",\"commerceName\": \"IBM\",\"customerId\": \"f980f7c1-c07a-48d0-b509-23e496e38d1f\",\"status\": \"PENDING\",\"timestamp\": \"2022-03-03T19:01:39.73Z[UTC]\",\"type\": \"PURCHASE\"}")
          .when().put(PATH + "/999")
          .then()
             .statusCode(404);
    }
}
