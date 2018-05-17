package com.freimanvs.company.testing;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.freimanvs.company.Constants.BASE_URI;
import static com.freimanvs.company.Constants.CONTEXT_PATH;
import static com.freimanvs.company.Constants.PORT;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

//@Ignore
public class RestAssuredTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }

    @Test
    public void whenRequestGet_thenOK(){
//        given().log().all(). - log request for debugging
        when().request("GET", "/company/api/v1/employees").then()
//        .log().body() - log response for debugging
        .statusCode(200);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("/company/api/v1/employees").then().time(lessThan(5L), TimeUnit.SECONDS);
    }
}
