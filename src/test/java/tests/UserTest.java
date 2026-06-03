
package tests;

import backend.application.UserAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

    UserAPI userAPI = new UserAPI();

    @Test
    public void getCurrentUser() {

        Response response = userAPI.getCurrentUser();

        Assert.assertEquals(response.statusCode(), 200);

        response.then().log().all();
    }

    @Test
    public void getAddresses() {

        Response response = userAPI.getAddresses();

        response.then().log().all();

        if (response.statusCode() == 429) {
            Assert.fail("Rate limit exceeded. Try again later");
        }

        Assert.assertEquals(response.statusCode(), 200);
    }
}