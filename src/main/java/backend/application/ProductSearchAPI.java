package backend.application;

import backend.routes.Routes;
import backend.spec.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductSearchAPI {

    public Response searchProducts(
            String query,
            Integer page,
            Integer limit,
            String category,
            Double minPrice,
            Double maxPrice,
            String sort
    ) {

        StringBuilder endpoint =
                new StringBuilder(Routes.SEARCH_PRODUCTS + "?q=" + query);

        if (page != null) endpoint.append("&page=").append(page);
        if (limit != null) endpoint.append("&limit=").append(limit);
        if (category != null) endpoint.append("&category=").append(category);
        if (minPrice != null) endpoint.append("&minPrice=").append(minPrice);
        if (maxPrice != null) endpoint.append("&maxPrice=").append(maxPrice);
        if (sort != null) endpoint.append("&sort=").append(sort);

        return RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get(endpoint.toString())
                .then()
                .extract()
                .response();

    }


}