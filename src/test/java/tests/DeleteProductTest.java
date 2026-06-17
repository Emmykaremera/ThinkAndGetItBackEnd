package tests;

import backend.application.AuthAPI;
import backend.application.ProductAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.StatusCodes.OK;

public class DeleteProductTest {

    ProductAPI productAPI =
            new ProductAPI();


    @Test
    public void deleteProductWithoutPermission() {

        String productId = productAPI.getFirstProductId();

        Response response = productAPI.deleteProduct(productId);

        Assert.assertEquals(response.statusCode(),
                403);

        Assert.assertEquals(
                response.jsonPath().getString("message"),
                "You do not have permission for this action");
    }


//    @Test
//    public void deleteProductSuccessTest() {
//
//        AuthAPI.login(
//                "emmy@example.com",
//                "MyPass@123"
//        );
//
//        String productId = productAPI.getFirstProductId();
//
//        Response response = productAPI.deleteProduct(productId);
//
//        response.then().log().all();
//
//        Assert.assertEquals(response.statusCode(), OK);
//    }

}