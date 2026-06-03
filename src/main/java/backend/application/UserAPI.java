package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import backend.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPI {

    public Response getCurrentUser() {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .when()
                .get(Routes.CURRENT_USER)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getAddresses() {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .when()
                .get(Routes.ADDRESSES)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateProfile(String firstName, String lastName, String phone) {

        String body = String.format("""
                {
                  "firstName": "%s",
                  "lastName": "%s",
                  "phone": "%s"
                }
                """,
                firstName,
                lastName,
                phone
        );

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenManager.getToken())
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
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer invalidtoken123")
                .body(body)
                .when()
                .put(Routes.UPDATE_PROFILE)
                .then()
                .log().all()
                .extract()
                .response();
    }
}