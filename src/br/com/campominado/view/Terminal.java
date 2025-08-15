package br.com.campominado.view;

import br.com.campominado.exception.ExplosaoException;
import br.com.campominado.exception.SairException;
import br.com.campominado.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Terminal {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public Terminal(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        
        exercutarJogo();
    }

    private void exercutarJogo() {
        try {
            boolean continuar = true;

            while (continuar){
                ciclodoJogo();

                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                }else {
                    tabuleiro.reiniciar();
                }
            }
        }catch (SairException e){
            System.out.println("Tchau!!!!");
        }finally {
            entrada.close();
        }
    }

    private void ciclodoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

                digitado = capturarValorDigitado("1 - Abrir ou 2 (Des)Marcar: ");

                if ("1".equalsIgnoreCase(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equalsIgnoreCase(digitado)) {
                    tabuleiro.alterarmarcacao(xy.next(), xy.next());
                }
            }

            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!");
        }catch (ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("Você perdeu!!!");
        }
    }

    private String capturarValorDigitado(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("Sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }

}
