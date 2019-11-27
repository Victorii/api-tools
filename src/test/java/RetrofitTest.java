
import com.google.gson.*;
import okhttp3.Credentials;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import Retrofit.*;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class RetrofitTest {

    private IGitHub github;
    private String loginName;
    private String repoName;

    private String newRepo = "{\n" +
            "  \"auto_init\": true,\n" +
            "  \"description\": \"\",\n" +
            "  \"gitignore_template\": \"\",\n" +
            "  \"has_downloads\": true,\n" +
            "  \"has_issues\": true,\n" +
            "  \"has_wiki\": true,\n" +
            "  \"homepage\": \"\",\n" +
            "  \"name\": \"Test2\",\n" +
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

    @BeforeTest
    public  void before(){
        github = createInstanceRetrofit().create(IGitHub.class);
    }

    @Test(description = "Add repo", priority = 1)
    public void POST()  {
        Gson gson = new GsonBuilder().create();
        JsonElement element = gson.fromJson(newRepo, JsonElement.class);
        JsonObject object = element.getAsJsonObject();

        Call<Repo> call = github.post(Credentials.basic(Auth.userName, Auth.password), object);
        Response response = executeCall(call);
        assertEquals(response.code(), 201);
        repoName = ((Repo) response.body()).getName();
        loginName = ((Repo) response.body()).getOwner().getLogin();

    }

    @Test(description = "Get repo", priority = 2)
    public void GET()  {
        Call<Repo> call = github.get(loginName, repoName);
        Response response = executeCall(call);
        assertEquals(response.code(), 200);
    }

    @Test(description = "Star a repo", priority = 3)
    public void PUT()  {
        Call<Repo> call = github.delete(Credentials.basic(Auth.userName, Auth.password),
                loginName, repoName);
        Response response = executeCall(call);
        assertEquals(response.code(), 204);
    }

    @Test(description = "Edit repo", priority = 4)
    public void PATCH()  {
        Gson gson = new GsonBuilder().create();
        JsonElement element = gson.fromJson(editRepo, JsonElement.class);
        JsonObject object = element.getAsJsonObject();

        Call<Repo> call = github.patch(Credentials.basic(Auth.userName, Auth.password), object,
                loginName, repoName);
        Response response = executeCall(call);
        assertEquals(response.code(), 200);
    }


    @Test(description = "Delete repo", priority = 5)
    public void DELETE()  {
        Call<Repo> call = github.delete(Credentials.basic(Auth.userName, Auth.password),
                loginName, repoName);
        Response response = executeCall(call);
        assertEquals(response.code(), 204);
    }


    private Retrofit createInstanceRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Response executeCall(Call<Repo> call){
        Response response = null;
        try {
            response =  call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        call.cancel();
        return response;
    }

}
