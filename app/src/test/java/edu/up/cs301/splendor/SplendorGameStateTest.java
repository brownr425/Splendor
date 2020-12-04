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
        SplendorGameState state1 = new SplendorGameState(playerSize);

        //player array size is correct, values initialized to zero
        assert(state1.getPlayerList().size() == (playerSize));
        for(int i = 0; i< playerSize; i++){
            assertEquals(zero, state1.getPlayer(i).getPrestigePts());
        }

    }

    @Test
    public void initializeDecks() {
        SplendorGameState state1 = new SplendorGameState(4);
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
        SplendorGameState state1 = new SplendorGameState(4);
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
        SplendorGameState state1 = new SplendorGameState(4);
        state1.setPlayerTurn(3);
        state1.nextPlayerTurn();
        assertEquals(0,state1.getPlayerTurn());
        //single check normal increment
        state1.nextPlayerTurn();
        assertEquals(1, state1.getPlayerTurn());

        //check that player turn updates correctly with 3 players
        SplendorGameState state2 = new SplendorGameState(3);
        state2.setPlayerTurn(2);
        state2.nextPlayerTurn();
        assertEquals(0,state2.getPlayerTurn());

        //check that player turn updates correctly with 2 players
        SplendorGameState state3 = new SplendorGameState(2);
        state3.setPlayerTurn(1);
        state3.nextPlayerTurn();
        assertEquals(0,state3.getPlayerTurn());

    }

    @Test
    public void getP1PrestigePts() {
    }

    @Test
    public void getP1GoldPts() {
    }

    @Test
    public void getP1EmeraldPts() {
    }

    @Test
    public void getP1SapphirePts() {
    }

    @Test
    public void getP1RubyPts() {
    }

    @Test
    public void getP1DiamondPts() {
    }

    @Test
    public void getP1OnyxPts() {
    }

    @Test
    public void getP1GoldCoins() {
    }

    @Test
    public void getP1EmeraldCoins() {
    }

    @Test
    public void getP1SapphireCoins() {
    }

    @Test
    public void getP1RubyCoins() {
    }

    @Test
    public void getP1DiamondCoins() {
    }

    @Test
    public void getP1OnyxCoins() {
    }

    @Test
    public void getP1NumCardsReserved() {
    }

    @Test
    public void getP1ReserveCards() {
    }

    @Test
    public void getP2PrestigePts() {
    }

    @Test
    public void getP2GoldPts() {
    }

    @Test
    public void getP2EmeraldPts() {
    }

    @Test
    public void getP2SapphirePts() {
    }

    @Test
    public void getP2RubyPts() {
    }

    @Test
    public void getP2DiamondPts() {
    }

    @Test
    public void getP2OnyxPts() {
    }

    @Test
    public void getP2GoldCoins() {
    }

    @Test
    public void getP2EmeraldCoins() {
    }

    @Test
    public void getP2SapphireCoins() {
    }

    @Test
    public void getP2RubyCoins() {
    }

    @Test
    public void getP2DiamondCoins() {
    }

    @Test
    public void getP2OnyxCoins() {
    }

    @Test
    public void getP2NumCardsReserved() {
    }

    @Test
    public void getP2ReserveCards() {
    }

    @Test
    public void getP3PrestigePts() {
    }

    @Test
    public void getP3GoldPts() {
    }

    @Test
    public void getP3EmeraldPts() {
    }

    @Test
    public void getP3SapphirePts() {
    }

    @Test
    public void getP3RubyPts() {
    }

    @Test
    public void getP3DiamondPts() {
    }

    @Test
    public void getP3OnyxPts() {
    }

    @Test
    public void getP3GoldCoins() {
    }

    @Test
    public void getP3EmeraldCoins() {
    }

    @Test
    public void getP3SapphireCoins() {
    }

    @Test
    public void getP3RubyCoins() {
    }

    @Test
    public void getP3DiamondCoins() {
    }

    @Test
    public void getP3OnyxCoins() {
    }

    @Test
    public void getP3NumCardsReserved() {
    }

    @Test
    public void getP3ReserveCards() {
    }

    @Test
    public void getP4PrestigePts() {
    }

    @Test
    public void getP4GoldPts() {
    }

    @Test
    public void getP4EmeraldPts() {
    }

    @Test
    public void getP4SapphirePts() {
    }

    @Test
    public void getP4RubyPts() {
    }

    @Test
    public void getP4DiamondPts() {
    }

    @Test
    public void getP4OnyxPts() {
    }

    @Test
    public void getP4GoldCoins() {
    }

    @Test
    public void getP4EmeraldCoins() {
    }

    @Test
    public void getP4SapphireCoins() {
    }

    @Test
    public void getP4RubyCoins() {
    }

    @Test
    public void getP4DiamondCoins() {
    }

    @Test
    public void getP4OnyxCoins() {
    }

    @Test
    public void getP4NumCardsReserved() {
    }

    @Test
    public void getP4ReserveCards() {
    }

    @Test
    public void getRank1Stack() {
    }

    @Test
    public void getRank2Stack() {
    }

    @Test
    public void getRank3Stack() {
    }

    @Test
    public void getNoble1() {
    }

    @Test
    public void getNoble2() {
    }

    @Test
    public void getNoble3() {
    }

    @Test
    public void getNoble4() {
    }
}