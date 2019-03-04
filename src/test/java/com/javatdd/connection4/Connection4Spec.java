package com.javatdd.connection4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Connection4Spec {

    private Connection4 tested;
    private OutputStream output;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();
        tested = new Connection4(new PrintStream(output));
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty(){
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenDiscOutsideThenRuntimeException(){
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstDiscInColumnThenPositionIsZero(){
        int column = 1;
        assertThat(tested.putDiscInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInColumnThenPositionISOne(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscIncreases(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void whenNoMoreRoomInColumnThenRunTimeException(){
        int column = 1;
        int maxDiscsInColumn = 6;
        for (int times = 0; times < maxDiscsInColumn; times++){
            tested.putDiscInColumn(column);
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("No more room in column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed(){
        assertThat(tested.getCurrentPlayer(), is("R"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }

    @Test
    public void whenAskedCurrentPlayerTheOutputNotice(){
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString("Player R turn"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(output.toString(), containsString("| |R| | | | | |"));
    }

    @Test
    public void whenTheGameStartsItIsNotFinished(){
        assertFalse("The game must not be finished", tested.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int row = 0; row < 6; row++)
            for (int column = 0; column < 7; column++)
                tested.putDiscInColumn(column);
        assertTrue("The game must be finished", tested.isFinished());
    }

    @Test
    public void when4VerticalDiscsAreConnectedThenPlayerWins(){
        for(int row = 0; row < 3; row++){
            tested.putDiscInColumn(1);
            tested.putDiscInColumn(2);
        }
        assertThat(tested.getWinner(), isEmptyString());
        tested.putDiscInColumn(1);
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4HorizontalDiscsAreConnectedThenPlayerWins(){
        int column;
        for (column = 0; column < 3; column++){
            tested.putDiscInColumn(column);
            tested.putDiscInColumn(column);
        }
        assertThat(tested.getWinner(), isEmptyString());
        tested.putDiscInColumn(column);
        assertThat(tested.getWinner(), is("R"));
    }


    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins(){
        int[] gameplay = new int[]{1,2,2,3,4,3,3,4,4,5,4};
        for (int column : gameplay){
            tested.putDiscInColumn(column);
        }
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins(){
        int[] gameplay = new int[]{3,4,2,3,2,2,1,1,1,1};
        for (int column : gameplay){
            tested.putDiscInColumn(column);
        }
        assertThat(tested.getWinner(), is("G"));

    }


}
