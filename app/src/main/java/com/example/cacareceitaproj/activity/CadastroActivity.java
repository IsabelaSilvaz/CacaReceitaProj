package com.example.cacareceitaproj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cacareceitaproj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText inputCadastro_email, inputCadastro_nomeUsuario, inputCadastro_senha, inputCadastro_confirmaSenha;
    private Button botao_confirmarCadastro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputCadastro_email = findViewById(R.id.inputCadastro_email);
        inputCadastro_nomeUsuario = findViewById(R.id.inputCadastro_nomeUsuario);
        inputCadastro_senha = findViewById(R.id.inputCadastro_senha);
        inputCadastro_confirmaSenha = findViewById(R.id.inputCadastro_confirmaSenha);

        botao_confirmarCadastro = findViewById(R.id.botao_confirmarCadastro);

        mAuth = FirebaseAuth.getInstance();

        botao_confirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        String email = inputCadastro_email.getText().toString().trim();
        String usuario = inputCadastro_nomeUsuario.getText().toString().trim();
        String senha = inputCadastro_senha.getText().toString().trim();
        String confirmarSenha = inputCadastro_confirmaSenha.getText().toString().trim();


        if(TextUtils.isEmpty(email)) {
            inputCadastro_email.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(usuario)) {
            inputCadastro_nomeUsuario.setError("User is required.");
            return;
        }

        if(TextUtils.isEmpty(senha)){
            inputCadastro_senha.setError("Password is required.");
            return;
        }

        if (TextUtils.isEmpty(confirmarSenha)) {
            inputCadastro_confirmaSenha.setError("password confirmation is required.");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            inputCadastro_senha.setError("passwords do not match.");
            inputCadastro_confirmaSenha.setError("passwords do not match.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }else {
                            Toast.makeText(CadastroActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

}