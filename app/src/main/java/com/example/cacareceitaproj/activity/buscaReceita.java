package com.example.cacareceitaproj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cacareceitaproj.R;
import com.example.cacareceitaproj.classes.Card;
import com.example.cacareceitaproj.classes.CardAdapter;
import com.example.cacareceitaproj.utils.ApiManager;
import com.example.cacareceitaproj.utils.Recipe;
import com.example.cacareceitaproj.utils.RecipeResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class buscaReceita extends AppCompatActivity {

    private TextView nome_ingrediente;
    private Button pesquisarReceita, addIngrediente;
    private EditText inputIngredientes;

    private ApiManager apiManager;
    private List<String> ingredients = new ArrayList<>();
    private ArrayAdapter<String> ingredientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_busca_receita);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.pesquisa);

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

        apiManager = new ApiManager();

        inputIngredientes = findViewById(R.id.inputIngredientes);
        pesquisarReceita = findViewById(R.id.pesquisarReceita);
        addIngrediente = findViewById(R.id.addIngrediente);
        nome_ingrediente = findViewById(R.id.nome_ingrediente);


        addIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ingredient = inputIngredientes.getText().toString().trim();

                if (TextUtils.isEmpty(ingredient)){

                    inputIngredientes.setError("Campo vazio.");
                    return;

                }

                ingredients.add(ingredient);

                nome_ingrediente.setText(String.join(", ", ingredients));

                inputIngredientes.getText().clear();

            }
        });

        pesquisarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar se a lista de ingredientes não está vazia
                if(!ingredients.isEmpty()) {

                    String ingredientsString = String.join(",", ingredients);

                    apiManager.getRecipesByIngredients(ingredientsString, new ApiManager.OnRecipeListener() {
                        @Override
                        public void onSuccess(List<Recipe> recipes) {
                            if (!recipes.isEmpty()) {

                                RecyclerView recyclerView = findViewById(R.id.listaReceitas_resultado);
                                recyclerView.setLayoutManager(new LinearLayoutManager(buscaReceita.this, LinearLayoutManager.HORIZONTAL, false));
                                List<Card> cardList = new ArrayList<>();
                                CardAdapter adapter = new CardAdapter(buscaReceita.this, cardList);
                                recyclerView.setAdapter(adapter);

                                for (int i = 0; i< recipes.size(); i++) {

                                    Recipe recipe = recipes.get(i);

                                    Card card = new Card(recipe.getTitle(), recipe.getImage());
                                    cardList.add(card);

                                    // Usando uma variável final para a receita dentro do loop
                                    final Recipe finalRecipe = recipe;
                                    adapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intent = new Intent(buscaReceita.this, ReceitaActivity.class);
                                            intent.putExtra("titulo_receita", finalRecipe.getTitle());
                                            intent.putExtra("imagem_receita", finalRecipe.getImage());
                                            intent.putExtra("receita_informacoes", finalRecipe.getInstructions());
                                            startActivity(intent);
                                        }
                                    });
                                ingredients.clear();
                                nome_ingrediente.setText("");
                                }

                            } else {
                                Toast.makeText(buscaReceita.this, "Nenhuma receita encontrada", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(String errorMessage) {
                            Toast.makeText(buscaReceita.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    inputIngredientes.setError("Adicione um ingrediente.");
                    return;
                }
            }
        });




//        cardList.add(new Card("Resultado 1", R.drawable.imagem_receita));
//        cardList.add(new Card("Resultado 2", R.drawable.imagem_receita));
//        cardList.add(new Card("Resultado 3", R.drawable.imagem_receita));
//        cardList.add(new Card("Resultado 4", R.drawable.imagem_receita));
//        cardList.add(new Card("Resultado 5", R.drawable.imagem_receita));
//
//        CardAdapter adapter = new CardAdapter(this, cardList);
//        recyclerView.setAdapter(adapter);
    }

}