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
public class RoleRestTest {

    @BeforeClass
    public static void before() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }

    @Test
    public void getALL() {
        when().request("GET", CONTEXT_PATH + "/api/v1/roles").then().statusCode(200);
    }

    @Test
    public void getById() {
        when().request("GET", CONTEXT_PATH + "/api/v1/roles").then().statusCode(200);
    }
}