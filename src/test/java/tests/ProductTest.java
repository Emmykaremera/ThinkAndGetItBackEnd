package tests;

import backend.application.ProductAPI;
import constants.StatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest {

    ProductAPI productAPI = new ProductAPI();

    @Test
    public void getAllProductsShouldReturnSuccess() {

        Response response =
                productAPI.getAllProducts();

        Assert.assertEquals(
                response.statusCode(),
                StatusCodes.OK
        );
    }

    @Test
    public void getAllProductsShouldReturnResponseBody() {

        Response response =
                productAPI.getAllProducts();

        Assert.assertFalse(
                response.asString().isEmpty()
        );
    }

    @Test
    public void getAllProductsShouldRespondWithinFiveSeconds() {

        Response response =
                productAPI.getAllProducts();

        Assert.assertTrue(
                response.time() < 5000
        );
    }

    @Test
    public void getProductByInvalidSlug() {

        Response response =
                productAPI.getProductBySlug(
                        "invalid-product"
                );

        Assert.assertTrue(
                response.statusCode() == StatusCodes.BAD_REQUEST
                        || response.statusCode() == StatusCodes.NOT_FOUND,
                "Expected status code 400 or 404 but got: "
                + response.statusCode()
        );
    }
}