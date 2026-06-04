package tests;

import backend.application.CartAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class GetCartTest {

    CartAPI cartAPI = new CartAPI();

    @Test
    public void getCartWithValidToken(){

        Response response =
                cartAPI.getCart();

        Assert.assertEquals(
                response.statusCode(),
                200
        );

        Assert.assertNotNull(
                response.jsonPath()
                        .get("data")
        );
    }

    @Test
    public void getCartAsGuest(){

        String sessionId =
                UUID.randomUUID().toString();

        Response response =
                cartAPI.getCartAsGuest(
                        sessionId
                );

        Assert.assertTrue(
                response.statusCode()==200
                        || response.statusCode()==404
        );
    }

    @Test
    public void getCartWithoutAuth(){

        Response response =
                cartAPI.getCartWithoutAuth();

        response.then().log().all();

        Assert.assertEquals(
                response.statusCode(),
                200
        );

        Assert.assertTrue(
                response.jsonPath()
                        .getList("data.items")
                        .isEmpty()
        );
    }
}