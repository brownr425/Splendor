package edu.up.cs301.splendor.Game;



import org.junit.Test;

import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorReturnCoinAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Players.SplendorHumanPlayer;
import edu.up.cs301.splendor.State.SplendorGameState;

import static org.junit.Assert.*;

public class SplendorLocalGameTest {

    @Test
    public void canMove() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        local1.getLocalGameGameState().setPlayerTurn(3);

        //check p1 can't move, p4 can, increment turn, check again reversed with values
        assertFalse(local1.canMove(0));
        assert (local1.canMove(3));
        local1.getLocalGameGameState().nextPlayerTurn();
        assertFalse(local1.canMove(3));
        assert (local1.canMove(0));
    }

    @Test
    public void checkIfGameOver() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        local1.getLocalGameGameState().getPlayer(1).setPrestigePts(20);
        String str = local1.checkIfGameOver();
        assertEquals("Congratulatons Player 2", str);
    }

    @Test
    public void SplendorSelectCardAction() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name1");


        //SplendorSelectCardAction
        //initial case
        assert( -1 == local1.getLocalGameGameState().getSelectedCol());
        assert( -1 == local1.getLocalGameGameState().getSelectedRow());

        //make action and call
        GameAction action = new SplendorSelectCardAction(human1,1,2);
        local1.makeMove(action);
        assert( 2 == local1.getLocalGameGameState().getSelectedCol());
        assert( 1 == local1.getLocalGameGameState().getSelectedRow());
    }
    @Test
    public void SplendorCardAction() {
        //SplendorCardAction
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name2");

        //set turn and make sure player has enough points
        local1.getLocalGameGameState().setPlayerTurn(1);
        local1.getLocalGameGameState().getPlayer(1).setDiaPts(70);
        local1.getLocalGameGameState().getPlayer(1).setEmerPts(70);
        local1.getLocalGameGameState().getPlayer(1).setOnyxPts(70);
        local1.getLocalGameGameState().getPlayer(1).setSapphPts(70);
        local1.getLocalGameGameState().getPlayer(1).setRubyPts(70);

        Card testCard = local1.getLocalGameGameState().getBoard(0,0);
        local1.getLocalGameGameState().setSelected(testCard);

        GameAction action = new SplendorCardAction(human1, testCard, 0, 0);
        local1.makeMove(action);
        //should be next player's turn
        assertEquals(2, local1.getLocalGameGameState().getPlayerTurn());
        Card inHand = local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getHand().get(0);
        assertEquals(testCard, inHand);
    }

    @Test
    public void SplendorReserveCardAction() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name1");

        local1.getLocalGameGameState().setPlayerTurn(1);
        Card testCard1 = local1.getLocalGameGameState().getBoard(2,0);
        local1.getLocalGameGameState().setSelectedRow(0);
        local1.getLocalGameGameState().setSelectedCol(0);
        GameAction action = new SplendorReserveCardAction(human1,0,0);
        local1.makeMove(action);
        //Check next turn and gold coins updated
        assertEquals(2, local1.getLocalGameGameState().getPlayerTurn());
        assert (1 == local1.getLocalGameGameState().getPlayer(1).getGoldCoins());
        //check that res card is in array now
        assert (testCard1 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(0));

        //reserve 2 more cards
        local1.getLocalGameGameState().setPlayerTurn(1);
        Card testCard2 = local1.getLocalGameGameState().getBoard(2,0);
        local1.getLocalGameGameState().setSelectedRow(0);
        local1.getLocalGameGameState().setSelectedCol(0);
        local1.makeMove(action);
        local1.getLocalGameGameState().setPlayerTurn(1);
        Card testCard3 = local1.getLocalGameGameState().getBoard(2,0);
        local1.getLocalGameGameState().setSelectedRow(0);
        local1.getLocalGameGameState().setSelectedCol(0);
        local1.makeMove(action);
        assert (testCard1 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(0));
        assert (testCard2 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(1));
        assert (testCard3 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(2));


        //make illegal reserve
        local1.getLocalGameGameState().setPlayerTurn(1);
        Card testCard4 = local1.getLocalGameGameState().getBoard(2,0);
        local1.getLocalGameGameState().setSelectedRow(0);
        local1.getLocalGameGameState().setSelectedCol(0);
        local1.makeMove(action);

        //these should be the same
        assert (testCard1 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(0));
        assert (testCard2 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(1));
        assert (testCard3 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(2));
        //should still be p1 turn
        assertEquals(1, local1.getLocalGameGameState().getPlayerTurn());

        //make sure player can buy card
        local1.getLocalGameGameState().getPlayer(1).setDiaPts(70);
        local1.getLocalGameGameState().getPlayer(1).setEmerPts(70);
        local1.getLocalGameGameState().getPlayer(1).setOnyxPts(70);
        local1.getLocalGameGameState().getPlayer(1).setSapphPts(70);
        local1.getLocalGameGameState().getPlayer(1).setRubyPts(70);

        //buy reserved card 1
        GameAction buyaction = new SplendorCardAction(human1, testCard1, 0, -1);
        local1.makeMove(buyaction);
        assert (testCard2 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(0));
        assert (testCard3 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(1));

        //Checkt that we can reserve again
        local1.getLocalGameGameState().setPlayerTurn(1);
        local1.getLocalGameGameState().setSelectedRow(0);
        local1.getLocalGameGameState().setSelectedCol(0);
        local1.makeMove(action);
        assert (testCard4 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(0));
        assert (testCard2 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(1));
        assert (testCard3 == local1.getLocalGameGameState().getPlayer(1).getPlayerHand().getReserved().get(2));

    }

    @Test
    public void SplendorCoinAction() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name2");
        GameAction action1 = new SplendorCoinSelectAction(human1, 0);
        GameAction action2 = new SplendorCoinSelectAction(human1, 1);
        GameAction action3 = new SplendorCoinSelectAction(human1, 2);
        GameAction action4 = new SplendorCoinSelectAction(human1, 3);
        GameAction coinAction = new SplendorCoinAction(human1);

        //CASE 1, THREE DIFFERENT SELECTED COINS
        SplendorLocalGame local1 = new SplendorLocalGame(4,arr);
        local1.getLocalGameGameState().setPlayerTurn(1);
        local1.makeMove(action1);
        local1.makeMove(action2);
        local1.makeMove(action3);
        local1.makeMove(coinAction);

        assert(0 == local1.getLocalGameGameState().getPlayer(1).getDiaCoins());
        assert(0 == local1.getLocalGameGameState().getPlayer(1).getOnyxCoins());
        assert(1 == local1.getLocalGameGameState().getPlayer(1).getRubyCoins());
        assert(1 == local1.getLocalGameGameState().getPlayer(1).getSapphCoins());
        assert(1 == local1.getLocalGameGameState().getPlayer(1).getEmerCoins());

        //TWO SELECTED COINS
        SplendorLocalGame local2 = new SplendorLocalGame(4, arr);
        local2.getLocalGameGameState().setPlayerTurn(1);
        local2.makeMove(action1);
        local2.makeMove(action1);
        local2.makeMove(coinAction);
        assert(0 == local2.getLocalGameGameState().getPlayer(1).getDiaCoins());
        assert(0 == local2.getLocalGameGameState().getPlayer(1).getOnyxCoins());
        assert(2 == local2.getLocalGameGameState().getPlayer(1).getRubyCoins());
        assert(0 == local2.getLocalGameGameState().getPlayer(1).getSapphCoins());
        assert(0 == local2.getLocalGameGameState().getPlayer(1).getEmerCoins());

        //EDGE CASES

    }

    @Test
    public void SplendorCoinSelectAction() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name2");

        //CASE THREE SELECTED COINS
        GameAction action1 = new SplendorCoinSelectAction(human1, 1);
        GameAction action2 = new SplendorCoinSelectAction(human1, 0);
        GameAction action3 = new SplendorCoinSelectAction(human1, 2);
        GameAction action4 = new SplendorCoinSelectAction(human1, 3);

        local1.makeMove(action1);
        local1.makeMove(action2);
        local1.makeMove(action3);

        assert((1 == local1.getLocalGameGameState().getCoinTracking().get(0)));
        assert((0 == local1.getLocalGameGameState().getCoinTracking().get(1)));
        assert((2 == local1.getLocalGameGameState().getCoinTracking().get(2)));

        //CASE 4 COINS SELECTED
        local1.makeMove(action4);
        assert((0 == local1.getLocalGameGameState().getCoinTracking().get(0)));
        assert((2 == local1.getLocalGameGameState().getCoinTracking().get(1)));
        assert((3 == local1.getLocalGameGameState().getCoinTracking().get(2)));

    }

    @Test
    public void SplendorReturnCoinAction() {
        String[] arr = {"p1", "p2", "p3", "p4"};
        SplendorLocalGame local1 = new SplendorLocalGame(4, arr);
        SplendorHumanPlayer human1 = new SplendorHumanPlayer("name2");
        local1.getLocalGameGameState().setPlayerTurn(1);
        //total coins == 12
        local1.getLocalGameGameState().getPlayer(1).setDiaCoins(3);
        local1.getLocalGameGameState().getPlayer(1).setEmerCoins(3);
        local1.getLocalGameGameState().getPlayer(1).setSapphCoins(3);
        local1.getLocalGameGameState().getPlayer(1).setRubyCoins(3);
        local1.getLocalGameGameState().getCoinTracking().add(1);
        local1.getLocalGameGameState().getCoinTracking().add(2);
        local1.getLocalGameGameState().getCoinTracking().add(0);

       // GameAction actiontake = new SplendorCoinAction(human1);
        //local1.makeMove(actiontake);
        local1.getLocalGameGameState().getCoinTracking().add(1);
        local1.getLocalGameGameState().getCoinTracking().add(2);
        local1.getLocalGameGameState().getCoinTracking().add(0);
        GameAction actionreturn = new SplendorReturnCoinAction(human1);
        local1.makeMove(actionreturn);

        assert(3 == local1.getLocalGameGameState().getPlayer(1).getRubyCoins());
        assert(3 == local1.getLocalGameGameState().getPlayer(1).getEmerCoins());
        assert(3 == local1.getLocalGameGameState().getPlayer(1).getSapphCoins());

    }

}