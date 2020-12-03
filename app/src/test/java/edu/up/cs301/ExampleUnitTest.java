package edu.up.cs301;

import org.junit.Test;
import java.io.InputStream;


import edu.up.cs301.splendor.State.SplendorGameState;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    SplendorGameState state;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

    }
    @Test
    public void coins_are_initialized(){
        state = new SplendorGameState(4 );
        assertEquals(state.getRubyCoins(), 70);
    }


}
