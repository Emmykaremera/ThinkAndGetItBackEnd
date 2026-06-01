package tests;

import BackEnd.routes.Routes;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest {

    @Test
    public void regester() {

        String requestBody = """
                {
                  "email": "emmy@example.com",
                  "password": "MyPass@123",
                  "firstName": "John",
                  "lastName": "Doe",
                  "phone": "+250788123456"
                }
                """;

        Response response = request
                .body(requestBody)
                .when()
                .post(Routes.LOGIN);

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void loginSuccessfully() {

        String requestBody = """
                {
                  "email": "emmy@example.com",
                  "password": "MyPass@123"
                }
                """;

        Response response = request
                .log().all()
                .body(requestBody)
                .when()
                .post(Routes.LOGIN);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);

        String token =
                response.jsonPath()
                        .getString("data.token");

        Assert.assertNotNull(token);

        System.out.println("TOKEN: " + token);

    }


    @Test
    public void loginWithInvalidPassword() {

        String requestBody = """
            {
                  "email": "karemera@example.com",
                  "password": "mypass@123"
                }
            """;

        Response response = request
                .log().all()
                .body(requestBody)
                .when()
                .post(Routes.LOGIN);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 401);

    }
}