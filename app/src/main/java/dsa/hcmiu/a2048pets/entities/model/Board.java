package dsa.hcmiu.a2048pets.entities.model;

import java.util.ArrayList;

import static dsa.hcmiu.a2048pets.entities.handle.HandleGame.arrId;
import static dsa.hcmiu.a2048pets.entities.handle.HandleGame.typePet;


/**
 * Created by Admin on 3/25/2018.
 */

public class Board {
    private int scoreBoard;
    public static int max = 4;
    private ArrayList<Pets> matrix;
    int row,col;

    private ArrayList<Pets> initBoard() {
        ArrayList<Pets> init = new ArrayList<>(max*max -1);
        for(int i = 0; i<max*max; i++) {
            init.add(typePet[0]);
        }
        return init;
    }

    public Board(int scoreBoard, ArrayList<Pets> boardi) {
        this.scoreBoard = scoreBoard;
        matrix = new ArrayList<>();
        this.matrix.addAll(boardi);
    }

    public Board(int scoreBoard) {
        if (matrix == null) matrix = new ArrayList<>();
        if (matrix.isEmpty()) matrix.addAll(initBoard());
        this.scoreBoard = scoreBoard;
    }

    public Board(Board temp) {
        matrix.clear();
        matrix.addAll(temp.matrix);
        scoreBoard = temp.scoreBoard;
    }

    public int getEValue(int rowi, int col) {
        return matrix.get(rowi*4+col).getValue();
    }

    public int getEValue(int index) {
        return matrix.get(index).getValue();
    }

    public Pets getElement(int rowi, int col) {
        return matrix.get(rowi*4+col);
    }

    public Pets getEValement(int index) {
        return matrix.get(index);
    }

    public void setElement(int rowi, int col, int value) {
        matrix.set(rowi*4+col,typePet[arrId[value]]);
    }

    public void setElement(int index, int value) {
        matrix.set(index,typePet[arrId[value]]);
    }

    public int getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(int scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public boolean fullBoard(){
        int pass = 3;
        for(int i=0; i<max*max-1;i++) {
            if (matrix.get(i).getValue()==0) return false;
            if (pass<max*(max-1))
                if (matrix.get(i) == matrix.get(i+4)) return false;
            if (i==pass) {
                pass+=max;
                continue;
            }
            if (matrix.get(i) == matrix.get(i+1)) return false;
        }
        return true;
    }

}
