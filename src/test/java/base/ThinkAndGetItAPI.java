package base;

import BackEnd.routes.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

public class ThinkAndGetItAPI {

    public Response getCategories() {

        RestAssured.baseURI =
                ConfigReader.getProperty("base.url");

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .when()
                .get(Routes.GET_CATEGORIES);
    }

}