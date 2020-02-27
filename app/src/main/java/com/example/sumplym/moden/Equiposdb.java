package com.example.sumplym.moden;

import java.io.Serializable;

public class Equiposdb implements Serializable {
    private long id;
    private  String nombreE;
    private String modeloE;
    private String tamaño;
    private String procesadorE;


    public Equiposdb(String nombreE, String modeloE, String tamaño, String procesadorE) {
        this.id = -1;
        this.nombreE = nombreE;
        this.modeloE = modeloE;
        this.tamaño = tamaño;
        this.procesadorE = procesadorE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreE() {
        return nombreE;
    }

    public String getModeloE() {
        return modeloE;
    }

    public String getTamaño() {
        return tamaño;
    }

    public String getProcesadorE() {
        return procesadorE;
    }
    public boolean hasBeenSaver(){
        if (id != -1) return false;
        else return true;

    }
}
