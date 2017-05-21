/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srbarrigajavaapplication;

/**
 *
 * @author Juliano
 */
public class Cilindro {
    private int altura;
    private int raio;
    private Cores cor;
    private int opt;

    public Cilindro(int altura, int raio, Cores cor) {
        this.altura = altura;
        this.raio = raio;
        this.cor = cor;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public Cores getCor() {
        return cor;
    }

    public void setCor(Cores cor) {
        this.cor = cor;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }
    
    
}
