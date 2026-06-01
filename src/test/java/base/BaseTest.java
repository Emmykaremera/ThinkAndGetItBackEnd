package base;

import utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup() {

        String baseUrl =
                ConfigReader.getProperty("base.url");

        RestAssured.baseURI = baseUrl;

        request = RestAssured
                .given()
                .header("Content-Type", "application/json");

    }
}