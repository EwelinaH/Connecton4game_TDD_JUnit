package com.javatdd.connection4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Connection4Spec {

    private Connection4 tested;

    @Before
    public void beforeEachTest(){
        tested = new Connection4();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmty(){
        assertThat(tested.getNumberOfDiscs(), is(0));
    }
}
