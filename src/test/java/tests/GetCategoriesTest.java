package tests;

import BackEnd.routes.Routes;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetCategoriesTest extends BaseTest {

    @Test
    public void testGetAllCategories() {

        Response response = request
                .when()
                .get(Routes.GET_CATEGORIES);

        response.then().log().all();

        assertEquals(response.statusCode(), 200);
    }
}