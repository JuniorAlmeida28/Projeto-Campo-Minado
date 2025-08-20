package br.com.campominado.view;

import br.com.campominado.model.Campo;
import br.com.campominado.model.CampoEvento;
import br.com.campominado.model.CampoObservador;

import javax.swing.*;
import java.awt.*;

public class BotaoCampo extends JButton implements CampoObservador {

    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);

    private Campo campo;

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        campo.registrarObservador(this);
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento){
            case ABRIR:
                applicarEstiloAbir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                applicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloPadrao() {
    }

    private void applicarEstiloExplodir() {
        
    }

    private void aplicarEstiloMarcar() {

    }

    private void applicarEstiloAbir() {
    }
}
