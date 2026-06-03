package backend.spec;

import backend.utils.ConfigReader;
import constants.Headers;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    private RequestSpec(){}

    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder()
                .setBaseUri(
                        ConfigReader.getProperty("base.url")
                )
                .addHeader(
                        Headers.CONTENT_TYPE,
                        Headers.APPLICATION_JSON
                )
                .build();
    }
}