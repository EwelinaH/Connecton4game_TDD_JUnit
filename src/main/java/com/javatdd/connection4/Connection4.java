package com.javatdd.connection4;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Connection4 {

    private static int ROWS = 6;
    private static int COLUMNS = 7;
    private static final String PUSTE = " ";

    private String[][] board = new String[ROWS][COLUMNS];

    public Connection4(){
        for (String[]row : board){
            Arrays.fill(row, PUSTE);
        }
    }

    public int getNumberOfDiscs(){
        return IntStream.range(0, COLUMNS).map(this::getNumberOfDiscInColumn).sum();
    }

    private  int getNumberOfDiscInColumn(int column){
        return  (int) IntStream.range(0, ROWS).filter(row -> !PUSTE.equals(board[row][column])).count();
    }

    public int putDiscInColumn(int column){
        checkColumn(column);
        int row = getNumberOfDiscInColumn(column);
        checkPositionTOInsert(row, column);
        board[row][column] = "X";
        return row;
    }

    private void checkColumn(int column){
        if(column < 0 || column >= COLUMNS)
        throw new RuntimeException("Nieprawidłowa kolumna" + column);
    }

    private void checkPositionTOInsert(int row, int column){
        if (row == ROWS)
            throw new RuntimeException("Brak miejsca w kolumnie nr " + column);
    }
}
