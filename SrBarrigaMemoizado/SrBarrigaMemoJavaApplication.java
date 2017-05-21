/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srbarrigamemojavaapplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Juliano
 */
public class SrBarrigaMemoJavaApplication {
    public static int contador = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*    Scanner scanf = new Scanner(System.in);
        //selecionar numero de cilindros
        System.out.println("Quantos cilindros deseja inserir?");
        int i = scanf.nextInt();
        //vetor de cilindros que serão usados no jogo 
        Cilindro[] cilindros = new Cilindro[i];
        //inserir características do cilindro :altura, raio e cor
        for (int j = 0; j < cilindros.length; j++) {
            System.out.println("Qual a altura do cilindro?");
            int h = scanf.nextInt();
            System.out.println("Qual o raio do cilindro?");
            int r = scanf.nextInt();
            int cor = 0;
            do {
                System.out.println("Qual a cor do cilindro?");
                System.out.println("1 para Vermelho");
                System.out.println("2 para Laranja");
                System.out.println("3 para Verde");
                System.out.println("4 para azul");
                cor = scanf.nextInt();
            } while ((cor != 1) && (cor != 2) && (cor != 3) && (cor != 4));
            Cilindro cilindro = null;
            switch (cor) {
                case 1:
                    cilindro = new Cilindro(h, r, Cores.VERMELHO);
                    break;
                case 2:
                    cilindro = new Cilindro(h, r, Cores.LARANJA);
                    break;
                case 3:
                    cilindro = new Cilindro(h, r, Cores.VERDE);
                    break;
                case 4:
                    cilindro = new Cilindro(h, r, Cores.AZUL);
                    break;
            }
            cilindros[j] = cilindro;
        }*/

        String endereco = "C:\\Users\\Juliano\\Desktop\\PAA\\patologico.txt";
        FileReader arq = new FileReader(endereco);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine(); //lê a primeira linha

        //vetor de cilindros que serão usados no jogo 
        Cilindro[] cilindros = new Cilindro[8500];
        int indice = 0;
        //inserir características do cilindro :altura, raio e cor
        while (linha != null) {
            int h = Integer.parseInt(linha);
            linha = lerArq.readLine();
            int r = Integer.parseInt(linha);
            linha = lerArq.readLine();
            int cor = Integer.parseInt(linha);
            Cilindro cilindro = null;
            switch (cor) {
                case 1:
                    cilindro = new Cilindro(h, r, Cores.VERMELHO);
                    break;
                case 2:
                    cilindro = new Cilindro(h, r, Cores.LARANJA);
                    break;
                case 3:
                    cilindro = new Cilindro(h, r, Cores.VERDE);
                    break;
                case 4:
                    cilindro = new Cilindro(h, r, Cores.AZUL);
                    break;
            }
            linha = lerArq.readLine();
            cilindros[indice] = cilindro;
            indice++;
        }
        //ordena em ordem decrescente pelo raio ( Bubble Sort)
        for (int j = 0; j <= cilindros.length; j++) {
            for (int k = 1; k < cilindros.length - j; k++) {
                if (cilindros[k - 1].getRaio() < cilindros[k].getRaio()) {
                    Cilindro aux = cilindros[k - 1];
                    cilindros[k - 1] = cilindros[k];
                    cilindros[k] = aux;
                }
            }
        }
        //preenche vetor opt
        for (int j = 0; j < cilindros.length; j++) {
            boolean sai = true;
            int controle = j + 1;
            while (sai) {
                if (controle >= cilindros.length) {
                    cilindros[j].setOpt(-1);
                    sai = false;
                } else if (null != cilindros[j].getCor()) {
                    switch (cilindros[j].getCor()) {
                        case VERMELHO:
                            if (cilindros[controle].getCor() == Cores.VERDE) {
                                controle = controle + 1;
                            } else {
                                cilindros[j].setOpt(controle);
                                sai = false;
                            }
                            break;
                        case LARANJA:
                            if (cilindros[controle].getCor() == Cores.VERMELHO) {
                                controle = controle + 1;
                            } else {
                                cilindros[j].setOpt(controle);
                                sai = false;
                            }

                            break;
                        case AZUL:
                            if (cilindros[controle].getCor() == Cores.LARANJA) {
                                controle = controle + 1;
                            } else {
                                cilindros[j].setOpt(controle);
                                sai = false;
                            }

                            break;
                        case VERDE:
                            if (cilindros[controle].getCor() == Cores.AZUL) {
                                controle = controle + 1;
                            } else {
                                cilindros[j].setOpt(controle);
                                sai = false;
                            }

                            break;
                    }
                }
            }
        }
        for (Cilindro cilindro : cilindros) {
            System.out.println(cilindro.getOpt());
        }
        
        int[] memo = new int[cilindros.length];
        memo = inicializaMemo(memo);
        int alturaTotal;
        alturaTotal = construirPiramide(cilindros, 0, memo);
        System.out.println(alturaTotal);
    }

    private static int[] inicializaMemo(int[] memo) {
        for (int j = 0; j < memo.length; j++) {
            memo[j] = -1;
        }
        return memo;
    }

    private static int construirPiramide(Cilindro[] cilindros, int n, int[] memo) {
        int opt = 0;
        if (n == -1) {
            return opt;
        } else if (memo[n] != -1) {
            opt = memo[n];
        } else {
            contador = contador +1;
            System.out.println(contador);
            opt = Math.max(construirPiramide(cilindros, cilindros[n].getOpt(), memo), cilindros[n].getAltura() + construirPiramide(cilindros, cilindros[n].getOpt(), memo));
            memo[n] = opt;
        }
        return opt;
    }

}
