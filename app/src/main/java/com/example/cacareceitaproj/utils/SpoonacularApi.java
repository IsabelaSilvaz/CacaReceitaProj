package com.example.cacareceitaproj.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoonacularApi {

    @GET("recipes/random")
    Call<RecipeResponse> getRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") int number
    );

}
