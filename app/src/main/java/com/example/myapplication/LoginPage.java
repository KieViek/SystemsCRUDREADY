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
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private EditText etCorreo;
    private EditText etContraseña;
    private Button btn_Login;


    private String email;
    private String password;


    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Auth = FirebaseAuth.getInstance();
        etContraseña= (EditText) findViewById(R.id.editTextPassword);
        etCorreo = (EditText) findViewById(R.id.editTextEmail);
        btn_Login = (Button) findViewById(R.id.btnLogin);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etCorreo.getText().toString();
                password = etContraseña.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }
                else{
                    Toast.makeText(LoginPage.this, "Completen los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        }

        private void loginUser(){
        Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginPage.this,MainActivity2.class));
                    finish();

                }
                else{
                    Toast.makeText(LoginPage.this, "Lo lamentamos mucho, puedes volver a intentar?", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
}