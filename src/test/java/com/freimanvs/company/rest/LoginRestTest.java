package com.freimanvs.company.rest;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.freimanvs.company.Constants.BASE_URI;
import static com.freimanvs.company.Constants.CONTEXT_PATH;
import static com.freimanvs.company.Constants.PORT;

import static io.restassured.RestAssured.when;

//@Ignore
public class LoginRestTest {

    @BeforeClass
    public static void before() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }

    @Test
    public void principal() {
        when().request("GET", CONTEXT_PATH + "/api/v1/auth/principal").then().statusCode(200);
    }
}