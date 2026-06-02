package tests;

import BackEnd.routes.Routes;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
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

    @Test
    public void getAllCategoriesShouldReturnData() {
        Response response =
                request
                        .when()
                        .get("/categories")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected categories response body not to be empty"
        );
    }

    @Test
    public void getAllCategoriesShouldRespondWithinFourSeconds() {
        Response response =
                request
                        .when()
                        .get("/categories")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.time() < 4000,
                "Expected categories API response time to be below 5000 ms"
        );
    }

    @Test
    public void getSingleCategoryWithInvalidSlugShouldReturnClientOrNotFoundError() {
        Response response =
                request
                        .when()
                        .get("/categories/invalid-category-slug")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() == 400 || response.statusCode() == 404,
                "Expected invalid category slug to return 400 or 404"
        );
    }
}