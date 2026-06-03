package tests;

import backend.application.UserAPI;
import backend.utils.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest {

    UserAPI userAPI = new UserAPI();

    @Test
    public void updateProfileWithAllFields() {

        Response response = userAPI.updateProfile(
                TestData.validFirstName(),
                TestData.validLastName(),
                TestData.validPhone()
        );

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithFirstNameOnly() {

        Response response = userAPI.updateProfile(
                TestData.updatedFirstName(),
                TestData.validLastName(),
                TestData.validPhone()
        );

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithPhoneOnly() {

        Response response = userAPI.updateProfile(
                TestData.validFirstName(),
                TestData.validLastName(),
                TestData.updatedPhone()
        );

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateProfileWithoutToken() {

        Response response = userAPI.updateProfileWithoutToken(
                TestData.profileBody("John", "Doe", "+250789000000")
        );

        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void updateProfileWithInvalidToken() {

        Response response = userAPI.updateProfileWithInvalidToken(
                TestData.profileBody("John", "Doe", "+250789000000")
        );

        Assert.assertEquals(response.statusCode(), 401);
    }
}