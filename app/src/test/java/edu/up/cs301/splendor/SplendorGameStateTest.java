package edu.up.cs301.splendor;



import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import edu.up.cs301.splendor.State.SplendorGameState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SplendorGameStateTest {


    @Test
    public void testPlayerValues() {
        int zero = 0;
        int playerSize = 4;
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorGameState state1 = new SplendorGameState(playerSize, arr);

        //player array size is correct, values initialized to zero
        assert(state1.getPlayerList().size() == (playerSize));
        for(int i = 0; i< playerSize; i++){
            assertEquals(zero, state1.getPlayer(i).getPrestigePts());
        }
    }

    @Test
    public void initializeDecks() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorGameState state1 = new SplendorGameState(4, arr);
        //check that card in deck got initialized
       for(int i = 0; i < state1.getRank1Stack().size(); i++) {
           assertFalse(state1.getRank1Stack().get(i) == null);
       }
        for(int i = 0; i < state1.getRank2Stack().size(); i++) {
            assertFalse(state1.getRank2Stack().get(i) == null);
        }
        for(int i = 0; i < state1.getRank3Stack().size(); i++) {
            assertFalse(state1.getRank3Stack().get(i) == null);
        }
    }

    @Test
    public void copyConst() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorGameState state1 = new SplendorGameState(4, arr);
        state1.getPlayer(0).setPrestigePts(10);
        state1.getPlayer(1).setPrestigePts(5);
        SplendorGameState state2 = new SplendorGameState(state1);
        state2.getPlayer(1).setPrestigePts(14);

        //check that state2 copied state1 and change it's own value without altering state 1
        assertEquals(10, state1.getPlayer(0).getPrestigePts());
        assertEquals(5, state1.getPlayer(1).getPrestigePts());
        assertEquals(10, state2.getPlayer(0).getPrestigePts());
        assertEquals(14, state2.getPlayer(1).getPrestigePts());
    }


    @Test
    public void playerTurn() {
        //check that player turn updates correctly with 4 players
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorGameState state1 = new SplendorGameState(4, arr);
        state1.setPlayerTurn(3);
        state1.nextPlayerTurn();
        assertEquals(0,state1.getPlayerTurn());
        //single check normal increment
        state1.nextPlayerTurn();
        assertEquals(1, state1.getPlayerTurn());

        //check that player turn updates correctly with 3 players
        SplendorGameState state2 = new SplendorGameState(3, arr);
        state2.setPlayerTurn(2);
        state2.nextPlayerTurn();
        assertEquals(0,state2.getPlayerTurn());

        //check that player turn updates correctly with 2 players
        SplendorGameState state3 = new SplendorGameState(2, arr);
        state3.setPlayerTurn(1);
        state3.nextPlayerTurn();
        assertEquals(0,state3.getPlayerTurn());

    }

}