package com.example.enviardatos2.modelos;

import java.io.Serializable;

public class Bici implements Serializable {
    private String marca;
    private int pulgadas;

    public Bici(String marca, int pulgadas) {
        this.marca = marca;
        this.pulgadas = pulgadas;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getPulgadas() { return pulgadas; }
    public void setPulgadas(int pulgadas) { this.pulgadas = pulgadas; }

    @Override
    public String toString() {
        return "Bici{marca='" + marca + "', pulgadas=" + pulgadas + "}";
    }
}