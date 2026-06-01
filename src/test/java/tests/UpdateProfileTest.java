package tests;

import BackEnd.routes.Routes;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenManager;
import utils.TestData;

public class UpdateProfileTest extends BaseTest {

    @Test
    public void updateProfileWithAllFields() {

        String requestBody = String.format("""
                {
                    "firstName":"%s",
                    "lastName":"%s",
                    "phone":"%s"
                }
                """,
                TestData.validFirstName(),
                TestData.validLastName(),
                TestData.validPhone()
        );

        Response response = request
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(requestBody)
                .when()
                .put(Routes.UPDATE_PROFILE);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithFirstNameOnly() {

        String requestBody = String.format("""
                {
                    "firstName":"%s"
                }
                """,
                TestData.updatedFirstName()
        );

        Response response = request
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(requestBody)
                .when()
                .put(Routes.UPDATE_PROFILE);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithPhoneOnly() {

        String requestBody = String.format("""
                {
                    "phone":"%s"
                }
                """,
                TestData.updatedPhone()
        );

        Response response = request
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(requestBody)
                .when()
                .put(Routes.UPDATE_PROFILE);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithoutToken() {

        String requestBody = """
                {
                    "firstName":"John",
                    "lastName":"Doe",
                    "phone":"+250789000000"
                }
                """;

        Response response = request
                .body(requestBody)
                .when()
                .put(Routes.UPDATE_PROFILE);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void updateProfileWithInvalidToken() {

        String requestBody = """
                {
                    "firstName":"John",
                    "lastName":"Doe",
                    "phone":"+250789000000"
                }
                """;

        Response response = request
                .header("Authorization", "Bearer invalidtoken123")
                .body(requestBody)
                .when()
                .put(Routes.UPDATE_PROFILE);

        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 401);
    }
}