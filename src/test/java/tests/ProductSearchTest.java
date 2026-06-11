package tests;

import backend.application.ProductSearchAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchTest {

    ProductSearchAPI api = new ProductSearchAPI();

    @Test
    public void searchWithValidKeyword() {

        Response response =
                api.searchProducts("mouse", 1, 20, null, null, null, null);

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertNotNull(
                response.jsonPath().get("data")
        );
    }
}