package com.upiiz.examen_3.models;

public class CategoriaModel {
    private Long id;
    private String nombre;

    public CategoriaModel() {}
    public CategoriaModel(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
