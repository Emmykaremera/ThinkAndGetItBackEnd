package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import backend.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CartAPI {

    public Response getCart() {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header(
                        "Authorization",
                        "Bearer " + TokenManager.getToken()
                )
                .log().all()
                .when()
                .get(Routes.GET_CART)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getCartAsGuest(String sessionId){

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header(
                        "x-session-id",
                        sessionId
                )
                .log().all()
                .when()
                .get(Routes.GET_CART)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getCartWithoutAuth(){

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.GET_CART)
                .then()
                .log().all()
                .extract()
                .response();
    }
}