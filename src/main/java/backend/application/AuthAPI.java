package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AuthAPI {


    public Response register(String email, String password, String firstName, String lastName, String phone) {

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("phone", phone);

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .body(body)
                .when()
                .post(Routes.REGISTER)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response login(String email, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .body(body)
                .when()
                .post(Routes.LOGIN)
                .then()
                .log().all()
                .extract()
                .response();
    }

}