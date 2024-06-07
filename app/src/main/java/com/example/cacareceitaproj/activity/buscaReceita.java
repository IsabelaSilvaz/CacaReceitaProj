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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cacareceitaproj.R;
import com.example.cacareceitaproj.classes.Card;
import com.example.cacareceitaproj.classes.CardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class buscaReceita extends AppCompatActivity {

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

        RecyclerView recyclerView = findViewById(R.id.listaReceitas_resultado);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card("Resultado 1", R.drawable.imagem_receita));
        cardList.add(new Card("Resultado 2", R.drawable.imagem_receita));
        cardList.add(new Card("Resultado 3", R.drawable.imagem_receita));
        cardList.add(new Card("Resultado 4", R.drawable.imagem_receita));
        cardList.add(new Card("Resultado 5", R.drawable.imagem_receita));

        CardAdapter adapter = new CardAdapter(this, cardList);
        recyclerView.setAdapter(adapter);
    }

}