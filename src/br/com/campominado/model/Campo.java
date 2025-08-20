package br.com.campominado.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<>();
    private List<CampoObservador> observadores = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }

    private void notificarObservador(CampoEvento evento){
        observadores.stream()
                .forEach(obs -> obs.eventoOcorreu(this, evento));
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaOutra = linha != vizinho.linha;
        boolean colunaOutra = coluna != vizinho.coluna;
        boolean diagonal = linhaOutra && colunaOutra;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao(){
        if (!aberto){
            marcado = !marcado;

            if (marcado){
                notificarObservador(CampoEvento.MARCAR);
            }else {
                notificarObservador(CampoEvento.DESMARCAR);
            }
        }
    }

    boolean abrir(){

        if (!aberto && !marcado){
            if (minado){
                notificarObservador(CampoEvento.EXPLODIR);
                return true;
            }

            setAberto(true);

            if (vizinhacaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }else {
            return false;
        }
    }

    boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    void minar(){
        minado = true;
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;

        if (aberto){
            notificarObservador(CampoEvento.ABRIR);
        }
    }

    public boolean isAberto() {
        return aberto;
    }
    public boolean isFechado() {
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasNaVizinhaca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

}
