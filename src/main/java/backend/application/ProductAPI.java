package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPI {

    public Response getAllProducts() {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.PRODUCTS)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getProductBySlug(String slug) {

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.productBySlug(slug))
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getFlashSalesProduct(){
        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.FLASH_SALES_PRODUCTS)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getInvalidFlashSalesProduct(){
        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .log().all()
                .when()
                .get(Routes.INVALID_FLASH_SALES)
                .then()
                .log().all()
                .extract()
                .response();
    }


}