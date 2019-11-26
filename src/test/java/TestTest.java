import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TestTest {

    @BeforeTest
    public  void before(){
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(description = "Get repo")
    public void getRepo()  {
        given().
                accept("application/json").
                when().
                get("/repos/Victorii/SimpleProject").
                then().
                statusCode(200).
                assertThat().
                body(containsString("Java"));
    }

    private static String newRepo = "{\n" +
            "  \"auto_init\": true,\n" +
            "  \"description\": \"\",\n" +
            "  \"gitignore_template\": \"\",\n" +
            "  \"has_downloads\": true,\n" +
            "  \"has_issues\": true,\n" +
            "  \"has_wiki\": true,\n" +
            "  \"homepage\": \"\",\n" +
            "  \"name\": \"Test1\",\n" +
            "  \"private\": true,\n" +
            "  \"team_id\": 0\n" +
            "}";

    @Test(description = "Add repo")
    public void addRepo()  {
        request_spec_builder_POST(newRepo, Auth.userName, Auth.password).post("/user/repos").then().
                statusCode(201);
    }

    private static RequestSpecification request_spec_builder_POST(String body, String _username, String _pass){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBody(body);

        builder.setContentType("application/json");
        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(_username);
        auth.setPassword(_pass);
        builder.setAuth(auth);
        RequestSpecification _spec = builder.build();
        return RestAssured.given(_spec);
    }
}
