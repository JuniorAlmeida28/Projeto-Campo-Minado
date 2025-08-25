package main.java.br.com.campominado.view;

import main.java.br.com.campominado.model.Campo;
import main.java.br.com.campominado.model.CampoEvento;
import main.java.br.com.campominado.model.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);

    private Campo campo;
    private ImageIcon bombaIcone;

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        campo.registrarObservador(this);

        URL url = getClass().getResource("/main/resources/imagens/bomba.png");
        ImageIcon iconeOriginal = new ImageIcon(url);
        Image imagemOriginal = iconeOriginal.getImage();
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        this.bombaIcone = new ImageIcon(imagemRedimensionada);
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
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setIcon(null);
        setText("");
    }

    private void applicarEstiloExplodir() {
        setBackground(BG_EXPLODIR);
        setIcon(this.bombaIcone);
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_PADRAO);
        setText("🚩");
        setFont(new Font("Noto Color Emoji", Font.PLAIN, 18));
        setForeground(Color.RED);
        setIcon(null);
    }

    private void applicarEstiloAbir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if (campo.isMinado()){
            setIcon(this.bombaIcone);
            return;
        }
        setBackground(BG_PADRAO);


        switch (campo.minasNaVizinhaca()){
            case 1:
                setForeground(Color.blue);
                break;
            case 2:
                setForeground(TEXTO_VERDE);
                break;
            case 3:
                setForeground(Color.RED);
                break;
            case 4:
                setForeground(Color.CYAN);
                break;
            case 5:
                setForeground(Color.PINK);
                break;
            case 6:
            default:
                setForeground(Color.PINK);
        }

        String valor = !campo.vizinhacaSegura() ? campo.minasNaVizinhaca() + "" : "";
        setText(valor);
    }


    // Interface dos eventos do Mouse
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            campo.abrir();
        }else {
            campo.alternarMarcacao();
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

}
