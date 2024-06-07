package com.example.cacareceitaproj.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoonacularApi {

    @GET("recipes/random")
    Call<RecipeResponse> getRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") int number
    );

        @GET("recipes/findByIngredients")
        Call<List<Recipe>> getRecipesByIngredients(
                @Query("ingredients") String ingredients,
                @Query("number") int number,
                @Query("apiKey") String apiKey
        );

}
