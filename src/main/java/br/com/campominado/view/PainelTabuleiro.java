package main.java.br.com.campominado.view;

import main.java.br.com.campominado.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro) {
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
        tabuleiro.registarObservador(e -> {

            SwingUtilities.invokeLater(() -> {
                if (e.isGanhou()){
                    JOptionPane.showMessageDialog(this, "Parabéns Você ganhou!!!" );
                }else {
                    JOptionPane.showMessageDialog(this, "GameOver!!!" );
                }

                tabuleiro.reiniciar();
            });
        });
    }
}
