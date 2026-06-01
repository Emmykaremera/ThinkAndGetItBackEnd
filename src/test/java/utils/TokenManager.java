package utils;

import BackEnd.routes.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {

    private static String token;

    public static String getToken() {

        if(token == null){

            String requestBody = """
                    {
                        "email":"emmy@example.com",
                        "password":"MyPass@123"
                    }
                    """;

            Response response = RestAssured
                    .given()
                    .header("Content-Type","application/json")
                    .body(requestBody)
                    .post(Routes.LOGIN);

            token = response
                    .jsonPath()
                    .getString("data.token");
        }

        return token;
    }
}