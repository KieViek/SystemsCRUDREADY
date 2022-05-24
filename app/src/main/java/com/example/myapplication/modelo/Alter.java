package com.example.myapplication.modelo;

public class Alter {
    private String id;
    private String nombre;
    private String especie;
    private String genero;

    public Alter(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Nombre: "+nombre+"\n"+
                "Especie: " + especie +"\n"+
                "Genero: " + genero + "\n";
    }

}
