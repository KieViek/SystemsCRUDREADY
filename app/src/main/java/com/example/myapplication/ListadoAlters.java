package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.modelo.Alter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListadoAlters extends AppCompatActivity {
    FirebaseDatabase Database;
    DatabaseReference Reference;
    public List<Alter> alters = new ArrayList<Alter>();
    ArrayAdapter<Alter> adAlter;
    ListView lvAlters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alters);
        iniciarFirebase();
        listarAlters();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        Database = FirebaseDatabase.getInstance();
        Reference = Database.getReference();

    }

    public void listarAlters(){
        Reference.child("Alter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("Imprimiendo...");
                alters = new ArrayList<>();
                for (DataSnapshot objeto: snapshot.getChildren()){
                    Alter alt = objeto.getValue(Alter.class);
                    System.out.println(alt);
                    alters.add(alt);
                }

                //Asignando adaptador al list view
                adAlter = new ArrayAdapter<Alter>(getApplicationContext(), android.R.layout.simple_list_item_1, alters);
                //Recuperando la List
                lvAlters = (ListView) findViewById(R.id.lvAlters);
                lvAlters.setAdapter(adAlter);
                lvAlters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Alter alt = (Alter) adapterView.getItemAtPosition(i);
                        Toast.makeText(ListadoAlters.this, "Alter: "+alt, Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(),AccionAlter.class);
                        in.putExtra("nombre",alt.getNombre());
                        in.putExtra("genero",alt.getGenero());
                        in.putExtra("especie",alt.getEspecie());
                        in.putExtra("id",alt.getId());
                        startActivity(in);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}