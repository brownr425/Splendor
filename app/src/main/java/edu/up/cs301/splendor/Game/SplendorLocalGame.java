package edu.up.cs301.splendor.Game;


import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorLocalGame extends LocalGame {
    private SplendorGameState gameState;

    public SplendorLocalGame() {
        super();
    }

    protected void sendUpdatedStateTo(GamePlayer player){}

    protected boolean canMove(int playerIdx) {return true;}

    //TODO: check for point threshold, create and change search iteration to numplayers
    protected String checkIfGameOver() {
        int i, playerLeadID = 0;
        if(gameState.getP1PrestigePts() > playerLeadID) {
            gameState.getPLAYER1ID();
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
