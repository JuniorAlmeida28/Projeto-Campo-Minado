package br.com.campominado;

import br.com.campominado.model.Tabuleiro;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

        tabuleiro.abrir(3,3);
        tabuleiro.alterarmarcacao(4,4);
        tabuleiro.alterarmarcacao(4,5);

        System.out.println(tabuleiro);
    }
}
