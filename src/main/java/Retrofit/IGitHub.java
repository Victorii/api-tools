package Retrofit;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;


public interface IGitHub {

    //POST
    @Headers({"Accept: application/json"})
    @POST("/user/repos")
    Call<Repo> post(
    @Header("Authorization") String credentials,
    @Body JsonObject body);


    //GET
    @Headers({"Accept: application/json"})
    @GET("/repos/{owner}/{repo}")
    Call<Repo> get(
      @Path("owner") String owner,
      @Path("repo") String repo);


    //DELETE
    @Headers({"Accept: application/json"})
    @DELETE("/repos/{owner}/{repo}")
    Call<Repo> delete(
            @Header("Authorization") String credentials,
            @Path("owner") String owner,
            @Path("repo") String repo);

    //PUT
    @Headers({"Accept: application/json"})
    @PUT("/user/starred/{owner}/{repo}")
    Call<Repo> put(
            @Header("Authorization") String credentials,
            @Path("owner") String owner,
            @Path("repo") String repo);


    //PATCH
    @Headers({"Accept: application/json"})
    @PATCH("/repos/{owner}/{repo}")
    Call<Repo> patch(
            @Header("Authorization") String credentials,
            @Body JsonObject body,
            @Path("owner") String owner,
            @Path("repo") String repo);
}
