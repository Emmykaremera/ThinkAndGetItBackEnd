package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CategoryAPI {

    public Response getCategories() {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.GET_CATEGORIES)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getCategoryByInvalidSlug(String slug) {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.GET_CATEGORIES + "/" + slug)
                .then()
                .log().all()
                .extract()
                .response();
    }
}