import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {
    @Test
    void getListUserTest() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .body("page", is(2))
                .body("data.first_name", hasItem("Michael"))
                .body("data.last_name", hasItem("Lawson"))
                .body("data.avatar", hasItem("https://reqres.in/img/faces/7-image.jpg"));

    }
    @Test
    void postCreateUserTest() {
        String body = "{\"name\": \"morpheus\",     \"job\": \"leader\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void putUpdateUserTest() {
        String body = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteUserTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    void postRegisterSuccesfullTest() {
        String body = "{\"email\": \"sydney@fife\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("error",is("Missing password"));
    }

}
