import RestAssured.RestAssuredResponseData;
import RestAssured.RestAssuredWrapper;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.stream.JsonParser;


public class TestRestAssured {


    @Test(description = "Get Repositories")
    public void getRepositories()  {
        RestAssuredWrapper restAssuredWrapper = new RestAssuredWrapper();
        restAssuredWrapper.setURL("https://api.github.com/repositories");
        restAssuredWrapper.getResponse();

        JsonParser object = Json.createParser(RestAssuredResponseData.getData().getResponseBody().asInputStream());

    }
}
