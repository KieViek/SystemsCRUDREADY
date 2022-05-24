package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private Button btRegistrar;
    private Button btSendToLogin;


    //Variables de los datos que vamos a registrarlkj
    private String name = "";
    private String email = "";
    private String password = "";

    //Firebase
    FirebaseAuth Auth;
    DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        btRegistrar = (Button) findViewById(R.id.btnRegister);
        btSendToLogin = (Button) findViewById(R.id.btnSendToLogin);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                Auth = FirebaseAuth.getInstance();
                Database = FirebaseDatabase.getInstance().getReference();

                //Validacion
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if (password.length() >= 6){
                        registerUser();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Se tiene que añadir la contraseña con minimo de 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }

                else{
                    Toast.makeText(MainActivity.this, "Añade los datos!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btSendToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginPage.class));
            }
        });
    }

    private void registerUser() {
        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);


                    String id = Auth.getCurrentUser().getUid();

                    Database.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                //enviar este usuario a otra pantalla
                                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                                finish();

                            } else {
                                Toast.makeText(MainActivity.this, "NO!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                } else {
                    Toast.makeText(MainActivity.this, "No funciono crack", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }

}
