package com.ibm;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CardTransactionEndpointTest {

    final String path = "/card-transaction";
    
    @Test
    public void testCardTransactionResource() {
        given()
          .when().get(path)
          .then()
             .statusCode(200)
             .body(containsString("\"commerceName\":\"IBM\""));
    }

    @Test
    public void testCardTransactionResourceGet() {
        given()
          .when().get(path + "/1")
          .then()
             .statusCode(200)
             .body(containsString("\"commerceName\":\"IBM\""));
    }

    @Test
    public void testCardTransactionResourceGetNotFound() {
        given()
          .when().get(path + "/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testCardTransactionResourceDelete() {
        given()
          .when().delete(path + "/1")
          .then()
             .statusCode(204);
    }

    @Test
    public void testCardTransactionResourceDeleteUnexistent() {
        given()
          .when().delete(path + "/999")
          .then()
             .statusCode(404);
    }

    @Test
    public void testCardTransactionResourcePost() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\",\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().post(path)
          .then()
             .statusCode(201);
    }

    @Test
    public void testCardTransactionResourcePostErr() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"id\": 1, \"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\",\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().post(path)
          .then()
             .statusCode(422);
    }

    @Test
    public void testCardTransactionResourcePut() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\", \"processed\":true,\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().put(path + "/1")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCardTransactionResourcePutErr() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"cardNumber\":\"123456789\",\"amount\":\"100\", \"processed\":true,\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().put(path + "/1")
          .then()
             .statusCode(422);
    }

    @Test
    public void testCardTransactionResourcePutUnexistent() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"cardNumber\":\"123456789\",\"commerceName\":\"IBM\",\"amount\":\"100\", \"processed\":true,\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
          .when().put(path + "/999")
          .then()
             .statusCode(404);
    }
}
