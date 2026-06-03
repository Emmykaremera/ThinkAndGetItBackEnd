package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import backend.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ProfileAPI {

    private String authHeader() {
        return "Bearer " + TokenManager.getToken();
    }

    public Response updateProfile(String firstName, String lastName, String phone) {

        Map<String, String> body = new HashMap<>();

        if (firstName != null) body.put("firstName", firstName);
        if (lastName != null) body.put("lastName", lastName);
        if (phone != null) body.put("phone", phone);

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", authHeader())
                .log().all()
                .body(body)
                .when()
                .put(Routes.UPDATE_PROFILE)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateProfileWithoutToken(String body) {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .body(body)
                .when()
                .put(Routes.UPDATE_PROFILE)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateProfileWithInvalidToken(String body) {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer invalidtoken123")
                .log().all()
                .body(body)
                .when()
                .put(Routes.UPDATE_PROFILE)
                .then()
                .log().all()
                .extract()
                .response();
    }
}