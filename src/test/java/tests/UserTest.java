package tests;

import BackEnd.routes.Routes;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenManager;

public class UserTest extends BaseTest {

    @Test
    public void getCurrentUser() {

        Response response = request
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .when()
                .get(Routes.CURRENT_USER);

        response.then().log().all();

        Assert.assertEquals(
                response.statusCode(),
                200,
                "Expected successful response"
        );
    }

    @Test
    public void getAddresses() {

        Response response = request
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .when()
                .get(Routes.ADDRESSES);

        response.then().log().all();

        if (response.statusCode() == 429) {
            Assert.fail("Rate limit exceeded. Try again later");
        }

        response.then().statusCode(200);
    }
}