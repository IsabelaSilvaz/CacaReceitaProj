package com.example.cacareceitaproj.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cacareceitaproj.R;
import com.example.cacareceitaproj.classes.Card;
import com.example.cacareceitaproj.classes.CardAdapter;
import com.example.cacareceitaproj.utils.ApiClient;
import com.example.cacareceitaproj.utils.Recipe;
import com.example.cacareceitaproj.utils.RecipeResponse;
import com.example.cacareceitaproj.utils.SpoonacularApi;
import com.google.android.gms.common.api.Api;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String API_KEY = "6bdc6f038dd6410a8379bb1c79ec41da";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                }

                if (id == R.id.pesquisa) {
                    startActivity(new Intent(getApplicationContext(), buscaReceita.class));
                    return true;
                }

                if (id == R.id.perfil) {
                    startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                    return true;
                }

                if (id == R.id.assinatura) {
                    startActivity(new Intent(getApplicationContext(), AssinaturaActivity.class));
                    return true;
                }

                if (id == R.id.livro) {
                    startActivity(new Intent(getApplicationContext(), LivrosMenu.class));
                    return true;
                }

                return false;
            }
        });

        getSpoonacularRecipe();

        // Segundo RecyclerView


    }

    private void getSpoonacularRecipe() {
        SpoonacularApi apiService = ApiClient.getClient().create(SpoonacularApi.class);
        Call<RecipeResponse> call1 = apiService.getRandomRecipe(API_KEY, 5);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Card> cardList = new ArrayList<>();
        CardAdapter adapter = new CardAdapter(this, cardList);
        recyclerView.setAdapter(adapter);

        call1.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response1) {
                if(response1.isSuccessful()) {
                    RecipeResponse recipeResponse = response1.body();
                    List<Recipe> recipes = recipeResponse.getRecipes();

                    for (int i = 0; i < recipes.size(); i++){
                        Recipe recipe = recipes.get(i);
                        cardList.add(new Card(recipe.getTitle(), recipe.getImage()));


                    }

                    adapter.notifyDataSetChanged(); // Notifica o adaptador sobre as alterações na lista
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Falha na solicitação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        SpoonacularApi apiService2 = ApiClient.getClient().create(SpoonacularApi.class);
        Call<RecipeResponse> call2 = apiService2.getRandomRecipe(API_KEY, 5);

        RecyclerView recyclerView2 = findViewById(R.id.recycler_view2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Card> cardList2 = new ArrayList<>();
        CardAdapter adapter2 = new CardAdapter(this, cardList);
        recyclerView2.setAdapter(adapter);

        call2.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if(response.isSuccessful()) {
                    RecipeResponse recipeResponse = response.body();
                    List<Recipe> recipes = recipeResponse.getRecipes();

                    for (int i = 0; i < recipes.size(); i++){
                        Recipe recipe = recipes.get(i);
                        cardList2.add(new Card(recipe.getTitle(), recipe.getImage()));
                    }

                    adapter2.notifyDataSetChanged(); // Notifica o adaptador sobre as alterações na lista
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Falha na solicitação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });


    }
}