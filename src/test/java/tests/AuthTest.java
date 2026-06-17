package tests;
import backend.application.AuthAPI;
import backend.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.UUID;

public class AuthTest {

    AuthAPI authAPI = new AuthAPI();
    String email = "emmy+" + UUID.randomUUID() + "@example.com";
    String password = "MyPass@123";

    @Test(priority = 1)
    public void registerSuccessfully() {

        Response response = authAPI.register(
                email,
                password,
                "John",
                "Doe",
                "+250788123456"
        );

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 2)
    public void loginSuccessfully() {

        Response response = authAPI.login(
                ConfigReader.getProperty("user.email"),
                ConfigReader.getProperty("user.password")
        );

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);

        String token = response.jsonPath().getString("data.token");

        Assert.assertNotNull(token);

        System.out.println("TOKEN: " + token);
    }

    @Test(priority = 3)
    public void loginWithInvalidPassword() {

        Response response = authAPI.login(
                ConfigReader.getProperty("user.email"),
                "wrongPassword123"
        );

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 401);
    }

    public static Response login(String email, String password) {

        return RestAssured
                .given()
                .body(Map.of(
                        "email", email,
                        "password", password
                ))
                .post("/auth/login");
    }
}