package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.modelo.Alter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccionAlter extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText etNombreAlter,etEspecieAlter,etGeneroAlter;
    String id;
    Button btnActualizar,btnEliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accion_alter);


        iniciarfirebase();

        Intent i = getIntent();

        Bundle extras = i.getExtras();

        String nombre = extras.getString("nombre");
        String especie = extras.getString("especie");
        String genero = extras.getString("genero");
        id = extras.getString("id");

        Toast.makeText(this, "Volviiii", Toast.LENGTH_SHORT).show();
        etNombreAlter = (EditText) findViewById(R.id.etNombreAlter);
        etNombreAlter.setText(nombre);


        etEspecieAlter = (EditText) findViewById(R.id.etEspecieAlter);
        etEspecieAlter.setText(especie);

        etGeneroAlter = (EditText) findViewById(R.id.etGeneroAlter);
        etGeneroAlter.setText(genero);


        btnActualizar = (Button) findViewById(R.id.btnActualizarAlter);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar(view);
                finish();
            }
        });

        btnEliminar = (Button) findViewById(R.id.btnEliminarAlter);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(view);
            }
        });

    }

    public void eliminar(View v){
        databaseReference.child("Alter").child(id).removeValue();


    }

    public void actualizar(View v){
        Alter alt = new Alter();
        alt.setId(id);
        alt.setNombre(etNombreAlter.getText().toString());
        alt.setEspecie(etEspecieAlter.getText().toString());
        alt.setGenero(etGeneroAlter.getText().toString());

        databaseReference.child("Alter").child(id).setValue(alt);

    }

    public void iniciarfirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


}


