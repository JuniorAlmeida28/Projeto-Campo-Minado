package br.com.campominado.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int line;
    private final int column;

    private boolean open;
    private boolean mined;
    private boolean reserved;

    private List<Field> neighbors = new ArrayList<>();

    Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    boolean addNeighbor(Field neighbor){
        boolean lineOther = line != neighbor.line;
        boolean columnOther = column != neighbor.column;
        boolean diagonal = lineOther && columnOther;

        int deltaLine = Math.abs(line - neighbor.line);
        int deltaColumn = Math.abs(column - neighbor.column);
        int generalDelta = deltaLine + deltaColumn;

        if (generalDelta == 1 && !diagonal){
            neighbors.add(neighbor);
            return true;
        } else if (generalDelta == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        }else {
            return false;
        }
    }
}
