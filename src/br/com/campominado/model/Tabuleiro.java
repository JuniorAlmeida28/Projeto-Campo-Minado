package br.com.campominado.model;

import br.com.campominado.exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampo();
        associarVizinhos();
        sortearMinas();
    }

    public void abrir(int linhas, int colunas){
        try {
            campos.parallelStream()
                    .filter(c -> c.getLinha() == linhas && c .getColuna() == colunas)
                    .findFirst()
                    .ifPresent(c -> c.abrir());
        }catch (ExplosaoException e){
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }

    public void alterarmarcacao(int linhas, int colunas){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linhas && c .getColuna() == colunas)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampo() {
        for (int l = 1; l < linhas+1; l++){
            for (int c = 1; c < colunas+1; c++){
                campos.add(new Campo(l, c));
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos){
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();

        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        }while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int c = 1; c < colunas+1; c++){
            sb.append("  ");
            sb.append(c);
            sb.append(" ");

        }
        sb.append("\n");
        int i = 0;

        for (int l = 1; l < linhas+1; l++){
            sb.append(l);
            for (int c = 1; c < colunas+1; c++){

                sb.append(" | " + campos.get(i));
                i++;
            }
            sb.append(" |");
            sb.append("\n");
        }

        return sb.toString();
    }
}
