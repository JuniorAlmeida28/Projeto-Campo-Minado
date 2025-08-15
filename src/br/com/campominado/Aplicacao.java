package br.com.campominado;

import br.com.campominado.model.Tabuleiro;
import br.com.campominado.view.Terminal;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new Terminal(tabuleiro);
    }
}
