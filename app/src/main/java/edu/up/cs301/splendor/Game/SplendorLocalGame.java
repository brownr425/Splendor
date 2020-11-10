package edu.up.cs301.splendor.Game;

import java.io.InputStream;

import edu.up.cs301.splendor.Actions.splCardAction;
import edu.up.cs301.splendor.Actions.splCoinAction;
import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorLocalGame extends LocalGame {
    private SplendorGameState gameState;

    public SplendorLocalGame() {
        this.gameState = new SplendorGameState();
    }

    protected void sendUpdatedStateTo(GamePlayer player){
        player.sendInfo(new SplendorGameState(gameState));
    }

    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getPlayerTurn();
    }

    protected String checkIfGameOver() {return "";}

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof splCoinAction){
            gameState.coinAction();
        }
        else if(action instanceof splCardAction)
        {

        }
        return false;
    }
}
