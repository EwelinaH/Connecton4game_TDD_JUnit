package com.javatdd.connection4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Connection4 {

    private static int ROWS = 6;
    private static int COLUMNS = 7;
    private static final String PUSTE = " ";
    private static final String CZER = "C";
    private static final String ZIEL = "Z";
    private String currentPlayer = CZER;
    private static final String DELIMITER = "|";
    private PrintStream outputChannel;


    private String[][] board = new String[ROWS][COLUMNS];

    public Connection4(PrintStream out){
        outputChannel = out;
        for (String[]row : board){
            Arrays.fill(row, PUSTE);
        }
    }

    public String getCurrentPlayer(){
        outputChannel.printf("Kolej gracza %s %n", currentPlayer);
        return currentPlayer;
    }

    private void printBoard(){
        for (int row =ROWS -1; row >= 0; row--){
            StringJoiner stringJoiner = new StringJoiner(DELIMITER, DELIMITER, DELIMITER);
            Stream.of(board[row]).forEachOrdered(stringJoiner::add);
            outputChannel.println(stringJoiner.toString());
        }
    }

    private void switchPlayer(){
        if(CZER.equals(currentPlayer))
            currentPlayer = ZIEL;
        else currentPlayer = CZER;
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
        board[row][column] = currentPlayer;
        printBoard();
        switchPlayer();
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
