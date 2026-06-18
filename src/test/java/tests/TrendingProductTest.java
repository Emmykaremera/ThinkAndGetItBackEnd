package tests;


import backend.application.ProductAPI;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.StatusCodes.*;

public class TrendingProductTest extends BaseTest {

    ProductAPI productAPI = new ProductAPI();

    @Test
    public void getTrendingProductsSuccessTest(){
        Response response = productAPI.getTrendingProducts();
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

    }

    @Test
    public void getTrendingProductsShouldReturnBody(){
        Response response = productAPI.getTrendingProducts();
        Assert.assertFalse(response.asString().isEmpty());
    }

    @Test
    public void getTrendingProductsShouldRespondInFiveSeconds(){
        Response response = productAPI.getTrendingProducts();
        Assert.assertTrue(response.time() < 5000);
    }
    
}