package com.example.cacareceitaproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import com.example.cacareceitaproj.activity.CadastroActivity;
import com.example.cacareceitaproj.activity.EsqueciSenha;
import com.example.cacareceitaproj.activity.HomeActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.cacareceitaproj.activity.ReceitaActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText input_usuario, input_senha;
    private Button botao_entrar;
    private FirebaseAuth mAuth;

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

        input_usuario = findViewById(R.id.input_usuario);
        input_senha = findViewById(R.id.input_senha);
        botao_entrar = findViewById(R.id.botao_entrar);

        mAuth = FirebaseAuth.getInstance();

        botao_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void loginUser(){
        String usuario = input_usuario.getText().toString();
        String senha = input_senha.getText().toString();

        if(TextUtils.isEmpty(usuario)) {
            input_usuario.setError("Email is required.");
            return;
        }

        if(TextUtils.isEmpty(senha)) {
            input_senha.setError("Password is required.");
            return;
        }

        mAuth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void cadastrar(View v) {
        Intent i = new Intent(this, CadastroActivity.class);
        startActivity(i);
    }

    public void esqueciSenha(View v) {
        Intent i = new Intent(this, EsqueciSenha.class);
        startActivity(i);
    }
}