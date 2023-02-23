package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import javafx.beans.binding.When;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.path.xml.XmlPath.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = RestAssured.given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    // 2) Verify that the store of total is = 1561
    @Test
    public void verifyTotalStores() {
        response.body("total",equalTo(1561));
    }
    @Test
    public void verifyStoresLimit() {
        response.body("limit", equalTo(10));

    }
    @Test
    public void nameArray(){

        response.body("data.name", hasItem("Inver Grove Heights"));
        }
        @Test
    public void multipleNames(){
        response.body("data.name",hasItems("Roseville","Burnsville","Maplewood"));
        }
        @Test
    public void verifyStoreId(){
        response.body("data[2].services[2].storeservices.serviceId",equalTo(3));
        }
        @Test
    public void checkCreatedAt(){
        response.body("data[2].createdAt",equalTo("2016-11-17T17:57:05.853Z"));
    }
    @Test
    public void verifyState(){
        response.body("data[4].state",equalTo("MN"));
    }
    @Test
    public void verifyRochester(){
        response.body("data.name",hasItem("Rochester"));
    }
    @Test
    public void verifyStoreId11(){
        response.body("data[5].id",equalTo(11));
    }
    @Test
    public void verifyServiceId4(){
        response.body("data[6].services[3].id",equalTo(4));
    }


    }





