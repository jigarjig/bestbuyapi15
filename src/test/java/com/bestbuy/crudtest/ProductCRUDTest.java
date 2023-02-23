package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest {
    static  int idNumber;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
    @Test // get all list
    public void test001() {

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }
    @Test // post new and retrive id
    public void test002() {

        ProductPojo pojo = new ProductPojo();
        pojo.setName("Surajhua");
        pojo.setType("patel");
        pojo.setPrice(5000);
        pojo.setShipping(10);
        pojo.setUpc("123456789");
        pojo.setDescription("compatible");
        pojo.setManufacturer("bmw");
        pojo.setModel("x6");
        pojo.setUrl("abcd");
        pojo.setImage("jhkjb");


        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        idNumber = response.then().extract().path("id");

        System.out.println(idNumber);
    }
    @Test //update id
    public void test003() {
        ProductPojo pojo = new ProductPojo();
        pojo.setPrice(33000);
        pojo.setShipping(300);
        pojo.setManufacturer("nissan");
        pojo.setModel("micra");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);
    }
    @Test// delete it
    public void test004() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test// retrive id and validate
    public void test005() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}
