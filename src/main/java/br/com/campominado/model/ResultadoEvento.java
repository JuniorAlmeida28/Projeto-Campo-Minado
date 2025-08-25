package main.java.br.com.campominado.model;

public class ResultadoEvento {

    private final Boolean ganhou;

    public ResultadoEvento(Boolean ganhou) {
        this.ganhou = ganhou;
    }

    public Boolean isGanhou() {
        return ganhou;
    }
}
