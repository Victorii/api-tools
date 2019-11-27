import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TestRestAssured {

    @BeforeTest
    public  void before(){
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.defaultParser = Parser.JSON;
    }


    private String newRepo = "{\n" +
            "  \"auto_init\": true,\n" +
            "  \"description\": \"\",\n" +
            "  \"gitignore_template\": \"\",\n" +
            "  \"has_downloads\": true,\n" +
            "  \"has_issues\": true,\n" +
            "  \"has_wiki\": true,\n" +
            "  \"homepage\": \"\",\n" +
            "  \"name\": \"Test1\",\n" +
            "  \"private\": false,\n" +
            "  \"team_id\": 0\n" +
            "}";


    private String editRepo = "{\n" +
            "  \"description\": \"Description\",\n" +
            "  \"has_downloads\": true,\n" +
            "  \"has_issues\": true,\n" +
            "  \"has_wiki\": true,\n" +
            "  \"homepage\": \"\",\n" +
            "  \"private\": false\n" +
            "}";

    private String repo = "";

    @Test(description = "Add repo", priority = 1)
    public void POST()  {
        repo = request_spec_builder(newRepo, Auth.userName, Auth.password).post("/user/repos").then().
                statusCode(201).and().extract().body().jsonPath().get("full_name");
    }

    @Test(description = "Get repo", priority = 2)
    public void GET()  {
        given().
                accept("application/json").
                when().
                get("/repos/"+repo).
                then().
                statusCode(200);
    }


    @Test(description = "Star a repo", priority = 3)
    public void PUT()  {
        request_spec_builder(Auth.userName, Auth.password).put("/user/starred/"+repo).then().
                statusCode(204);
    }

    @Test(description = "Edit repo", priority = 4)
    public void PATCH()  {
        request_spec_builder(editRepo, Auth.userName, Auth.password).patch("/repos/"+repo).then().
                statusCode(200).extract().asString();
    }

    @Test(description = "Delete repo", priority = 5)
    public void DELETE()  {
        request_spec_builder(Auth.userName, Auth.password).delete("/repos/"+repo).then().
                statusCode(204);
    }


  //  @Test(description = "NOT FOR RUN")
    public void CONNECT()  {
        given().
                when().
                request("CONNECT", "/somewhere").
                then().
                statusCode(200);
    }

    private static RequestSpecification request_spec_builder(String body, String username, String pass){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBody(body);
        builder.setContentType("application/json");
        return authentication(builder, username, pass);
    }

    private static RequestSpecification request_spec_builder(String username, String pass){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType("application/json");
        return authentication(builder, username, pass);
    }

    private static RequestSpecification authentication(RequestSpecBuilder builder, String username, String pass){
        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(username);
        auth.setPassword(pass);
        builder.setAuth(auth);
        RequestSpecification spec = builder.build();
        return RestAssured.given(spec);
    }
}
