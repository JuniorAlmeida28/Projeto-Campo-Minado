package br.com.campominado.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

    private Field field;

    @BeforeEach
    void iniciarCampo(){
        field = new Field(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1(){
        Field neighbor = new Field(3, 2);
        boolean resultado = field.addNeighbor(neighbor);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDiagonalDistancia2(){
        Field neighbor = new Field(2, 2);
        boolean resultado = field.addNeighbor(neighbor);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinhoDistancia(){
        Field neighbor = new Field(1, 1);
        boolean resultado = field.addNeighbor(neighbor);
        assertFalse(resultado);
    }
}
