package com.bestbuy.crudtest;

//import com.studentapp.model.StudentPojo;
//import com.studentapp.testbase.TestBase;
import com.bestbuy.model.StudentPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class NewStudentCRUDTest {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/student";
    }
    static int idNumber;
    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");

        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void createStudent() {

        List<String> courses= new ArrayList<>();
        courses.add("selenium");
        courses.add("postman");
        courses.add("restassured");

        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName("sosunnyhot");
        pojo.setLastName("patel");
        pojo.setEmail("aacccyyyxx@gmail.com");
        pojo.setProgramme("automation testing");
        pojo.setCourses(courses);

        Response response =given()
                .header("Content-Type","application/json")
                .body(pojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();
    }
    @Test //update id
    public void test003() {
        StudentPojo pojo = new StudentPojo();
        List<String> courses= new ArrayList<>();
        pojo.setFirstName("superwinter");
        pojo.setLastName("patel");
        pojo.setEmail("aaabbbyyxx@gmail.com");
        pojo.setProgramme("API junit testing");
        pojo.setCourses(courses);

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
        response.then().statusCode(204);



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
