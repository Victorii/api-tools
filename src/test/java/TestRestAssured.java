import RestAssured.RestAssuredWrapper;
import org.testng.annotations.Test;

public class TestRestAssured {
    @Test(description = "Get Repositories")
    public void testGetRepositories()  {
        RestAssuredWrapper restAssuredWrapper = new RestAssuredWrapper();
        restAssuredWrapper.setURL("https://api.github.com/repositories");
        restAssuredWrapper.getResponse();
    }
}
