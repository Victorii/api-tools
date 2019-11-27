
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import Retrofit.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitTest {

    @BeforeTest
    public  void before(){
        createInstanceRetrofit();
    }

    @Test(description = "Add repo", priority = 1)
    public void POST()  {
        IGitHub github = createInstanceRetrofit().create(IGitHub.class);
        Call<Repo> call = github.get("Victorii", "SimpleProject");
        try {
            System.out.println(call.execute().body().getFullName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Retrofit createInstanceRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
