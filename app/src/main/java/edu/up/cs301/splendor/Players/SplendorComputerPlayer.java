package edu.up.cs301.splendor.Players;

import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.splendor.Game.Game;
import edu.up.cs301.splendor.Setup.GameMainActivity;
import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.State.GameInfo;
import edu.up.cs301.splendor.State.GameState;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorComputerPlayer extends GameComputerPlayer {
    private Game game;
    private int playerId;
    private String[] playerNames;
    private String name;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public SplendorComputerPlayer(String name) {
        super(name);
    }


    @Override
    protected void receiveInfo(GameInfo info) {

    }

    public void receiveInfo(SplendorGameState info) {
        if (this.playerNum != info.getPlayerTurn()) {
            return;
        }

    }
    public boolean canBuy(Card card) {
        return true;
    }
    public boolean canMove(int playerID) {
        return true;
    }


}
