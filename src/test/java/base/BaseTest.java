package base;

import backend.spec.RequestSpec;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup(){

        request =
                RequestSpec.getRequestSpec();

    }
}
