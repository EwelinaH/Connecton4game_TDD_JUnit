package com.javatdd.connection4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
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
        exception.expectMessage("Nieprawidłowa kolumna" + column);
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
        exception.expectMessage("Brak miejsca w kolumnie nr " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed(){
        assertThat(tested.getCurrentPlayer(), is("C"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getCurrentPlayer(), is("Z"));
    }

    @Test
    public void whenAskedCurrentPlayerTheOutputNotice(){
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString("Kolej gracza C"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(output.toString(), containsString("| |C| | | | | |"));
    }
    



}
