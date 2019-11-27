package Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;


public interface IGitHub {
    @POST("{path}")
    Call<List> post(
      @Path("") String all);

    @GET("/repos/{owner}/{repo}")
    Call<Repo> get(
      @Path("owner") String owner,
      @Path("repo") String repo);
}
