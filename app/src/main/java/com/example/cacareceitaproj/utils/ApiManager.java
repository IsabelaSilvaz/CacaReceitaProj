package com.example.cacareceitaproj.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {

    private static final String API_KEY = "b102e2965c274e44a06894bc4360ae55";

    private SpoonacularApi service;

    public ApiManager() {
        Retrofit retrofit = ApiClient.getClient();

        service = retrofit.create(SpoonacularApi.class);
    }

    public void getRecipesByIngredients(String ingredients, final OnRecipeListener listener) {
        Call<List<Recipe>> call = service.getRecipesByIngredients(ingredients,5, API_KEY);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                List<Recipe> recipes = response.body();
                listener.onSuccess(recipes);
                } else {
                    listener.onError("Erro na resposta da API");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                listener.onError("Falha na comunicação com o servidor");
            }
        });
    }

    public interface OnRecipeListener {
        void onSuccess(List<Recipe> recipes);
        void onError(String errorMessage);
    }

}
