package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.modelo.Alter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

//List View
public class Alters extends AppCompatActivity {

    //Strings
    private EditText Nombre_Alter;
    private EditText Genero_Alter;
    private EditText Especie_Alter;

    //Firebase
    DatabaseReference Database;
    FirebaseDatabase FireBaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alters);
        inciarFirebase();
    }

    public void registrarAlter(View v) {
        Nombre_Alter = (EditText) findViewById(R.id.etNombreAlter);
        Genero_Alter = (EditText) findViewById(R.id.etGeneroAlter);
        Especie_Alter = (EditText) findViewById(R.id.etEspecieAlter);

        String nombreAlter = Nombre_Alter.getText().toString();
        String generoAlter = Genero_Alter.getText().toString();
        String especieAlter = Especie_Alter.getText().toString();

        //Generamos Id porque esto no funciona como deberia
        String id = UUID.randomUUID().toString();

        //Crear Objeto
        Alter alt = new Alter();
        alt.setId(id);
        alt.setNombre(nombreAlter);
        alt.setEspecie(especieAlter);
        alt.setGenero(generoAlter);

        //Insertar en Firebase
        Database.child("Alter").child(id).setValue(alt);
    }

    public void inciarFirebase(){
        FirebaseApp.initializeApp(this);
        FireBaseDatabase = FirebaseDatabase.getInstance();
        Database = FireBaseDatabase.getReference();
    }

    public void goListado(View v){
        Intent i = new Intent(getApplicationContext(),ListadoAlters.class);
        startActivity(i);
    }


}