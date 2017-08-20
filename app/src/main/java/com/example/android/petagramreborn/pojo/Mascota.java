package com.example.android.petagramreborn.pojo;

/**
 * Created by DOMINIC on 8/20/2017.
 */

public class Mascota {

    private int imgFoto;
    private String nombre;
    private int numBones;

    public Mascota(int imgFoto, String nombre, int numBones) {
        this.imgFoto = imgFoto;
        this.nombre = nombre;
        this.numBones = numBones;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumBones() {
        return numBones;
    }

    public void setNumBones(int numBones) {
        this.numBones = numBones;
    }

}
