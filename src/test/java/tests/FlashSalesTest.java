package tests;

import backend.application.ProductAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static constants.StatusCodes.OK;

public class FlashSalesTest {

    ProductAPI productAPI = new ProductAPI();

    @Test
    public void getFlashSalesProductsSuccessTest() {
        Response response = productAPI.getFlashSalesProduct();
        Assert.assertEquals(response.statusCode(), OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        List<Object> flashSaleFlags =
                response.jsonPath().getList("data.isFlashSale");

        for (Object flag : flashSaleFlags) {
            Assert.assertTrue((Boolean) flag, "Expected all products to be flash sale items");
        }
    }

    @Test
    public void getFlashSalesProductsInvalidApi() {

        Response response = productAPI.getInvalidFlashSalesProduct();

        response.then().log().all();

        Assert.assertTrue(
                response.statusCode() == 400 ||
                        response.statusCode() == 404
        );
    }
}
