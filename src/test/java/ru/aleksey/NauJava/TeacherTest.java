package ru.aleksey.NauJava;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.aleksey.NauJava.objects.Teacher;

import static org.hamcrest.Matchers.equalTo;

public class TeacherTest {
    @Test
    void getTeacherTest() {
        Teacher testTeacher = new Teacher(null, "Иван", "Иванов", null);
        String url = RestAssured.given()
                .auth().basic("admin", "admin")
                .when()
                .body(testTeacher)
                .contentType(ContentType.JSON)
                .post("http://localhost:8082/teachers")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .jsonPath()
                .get("_links.self.href");

        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(url)
                .then()
                .statusCode(200)
                .body("name", equalTo("Иван"))
                .body("surname", equalTo("Иванов"));
    }

    @Test
    void getTeacherIncorrectTest() {
        Teacher testTeacher = new Teacher(null, "Иван", "Иванов", null);
        String url = RestAssured.given()
                .auth().basic("admin", "admin")
                .when()
                .body(testTeacher)
                .contentType(ContentType.JSON)
                .post("http://localhost:8082/teachers")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .jsonPath()
                .get("_links.self.href");

        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(url + "1")
                .then()
                .statusCode(404);
    }
}
