package com.ibm;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CardTransactionEndpointTest {

    static final String PATH = "/api/card-transaction";
    static final String HEADER_NAME = "Content-Type";
    static final String CONTENT_TYPE = "application/json";
    
    
    @Test
    public void testCardTransactionController() throws Exception {
        given()
          .when().get(PATH)
          .then()
             .statusCode(200)
             .body(startsWith("["));
    }

    @Test
    public void testCardTransactionControllerGet() throws Exception {
        given()
          .when().get(PATH + "/9")
          .then()
             .statusCode(200)
             .body(containsString("\"id\":9"));
    }

    @Test
    public void testCardTransactionControllerGetNotFound() throws Exception {
        given()
          .when().get(PATH + "/999")
          .then()
             .statusCode(404)
             .body(containsString("{\"error\":\"999 CardTransaction not found\"}"));
    }

    @Test
    public void testCardTransactionControllerDelete() throws Exception {
        given()
          .when().delete(PATH + "/2")
          .then()
             .statusCode(204);
    }

    @Test
    public void testCardTransactionControllerDeleteUnexistent() throws Exception {
        given()
          .when().delete(PATH + "/999")
          .then()
             .statusCode(404)
             .body(containsString("{\"error\":\"999 CardTransaction not found\"}"));
    }


    @Test
    public void testCardTransactionControllerPost() {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"cardNumber\":\"4312\", \"commerceName\":\"Amazon\", \"amount\":1150.20, \"customerId\":\"f980f7c1-c07a-48d0-b509-23e496e38d1f\", \"accountId\":\"e9233dc4-40e6-4a58-8f7a-4543f6e2cf3f\", \"status\":\"PROCESSED\", \"type\":\"PURCHASE\"}")
          .when().post(PATH)
          .then()
             .statusCode(201);
    }


    @Test
    public void testCardTransactionControllerPostErr() throws Exception {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"id\": 1, \"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\",\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().post(PATH)
          .then()
             .statusCode(400);
    }

    @Test
    public void testCardTransactionControllerPut() throws Exception {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"accountId\": \"e9233dc4-40e6-4a58-8f7a-4543f6e2cf3f\",\"amount\": 150.2,\"cardNumber\": \"1234\",\"commerceName\": \"IBM\",\"customerId\": \"f980f7c1-c07a-48d0-b509-23e496e38d1f\",\"status\": \"PENDING\",\"timestamp\": \"2022-03-03T19:01:39.73Z[UTC]\",\"type\": \"PURCHASE\"}")
          .when().put(PATH + "/3")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCardTransactionControllerPutErr() throws Exception {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{}")
          .when().put(PATH + "/10")
          .then()
             .statusCode(400);
    }

    @Test
    public void testCardTransactionControllerPutUnexistent() throws Exception {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body("{\"accountId\": \"e9233dc4-40e6-4a58-8f7a-4543f6e2cf3f\",\"amount\": 150.2,\"cardNumber\": \"1234\",\"commerceName\": \"IBM\",\"customerId\": \"f980f7c1-c07a-48d0-b509-23e496e38d1f\",\"status\": \"PENDING\",\"timestamp\": \"2022-03-03T19:01:39.73Z[UTC]\",\"type\": \"PURCHASE\"}")
          .when().put(PATH + "/999")
          .then()
             .statusCode(404);
    }
}
