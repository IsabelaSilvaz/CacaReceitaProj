package com.example.cacareceitaproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cacareceitaproj.activity.CadastroActivity;
import com.example.cacareceitaproj.activity.EsqueciSenha;
import com.example.cacareceitaproj.activity.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cadastrar(View v) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void esqueciSenha(View v) {
        Intent i = new Intent(this, EsqueciSenha.class);
        startActivity(i);
    }

}