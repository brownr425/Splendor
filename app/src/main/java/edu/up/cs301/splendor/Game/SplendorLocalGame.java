package edu.up.cs301.splendor.Game;

import edu.up.cs301.splendor.Actions.SplendorAction;
import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorLocalGame extends LocalGame {
    private SplendorGameState gameState;

    public SplendorLocalGame() {
        super();
    }

    protected void sendUpdatedStateTo(GamePlayer player){}

    protected boolean canMove(int playerIdx) {return true;}

    protected String checkIfGameOver() {return "";}

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }

    protected boolean makeMove(SplendorAction action) {return true;}
}
