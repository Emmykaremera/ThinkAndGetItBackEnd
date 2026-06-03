package tests;
import backend.application.CategoryAPI;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCategoriesTest extends BaseTest {

    CategoryAPI categoryAPI = new CategoryAPI();

    @Test
    public void testGetAllCategories() {

        Response response = categoryAPI.getCategories();

        Assert.assertEquals(response.statusCode(), 200);

        response.then().log().all();
    }

    @Test
    public void getAllCategoriesShouldReturnData() {

        Response response = categoryAPI.getCategories();

        response.then().log().all();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected categories response body not to be empty"
        );
    }

    @Test
    public void getAllCategoriesShouldRespondWithinFourSeconds() {

        Response response = categoryAPI.getCategories();

        response.then().log().all();

        Assert.assertTrue(
                response.time() < 4000,
                "Expected categories API response time to be below 4000 ms"
        );
    }

    @Test
    public void getSingleCategoryWithInvalidSlugShouldReturnClientOrNotFoundError() {

        Response response = categoryAPI.getCategoryByInvalidSlug("invalid-category-slug");

        response.then().log().all();

        Assert.assertTrue(
                response.statusCode() == 400 || response.statusCode() == 404,
                "Expected invalid category slug to return 400 or 404"
        );
    }
}