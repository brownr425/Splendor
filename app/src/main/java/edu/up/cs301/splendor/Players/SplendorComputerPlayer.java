package edu.up.cs301.splendor.Players;

import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.splCardAction;
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

        //checks to see if it Computer Players turn
        if (notPlayerTurn()) {
            return;
        }

        //refreshes the local copy of the game state with the new info
        updateGameState(info);

        //traverse through card board and purchases a card if it can
        for (int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                Card card = gameState.getBoard(row, col);
                if (canBuy(card)) {
                    SplendorSelectCardAction select = new SplendorSelectCardAction(null, row, col);
                    this.game.sendAction(select);
                    splCardAction buy = new splCardAction();
                    this.game.sendAction(buy);
                    //not sure if sending a buy action increments player turn counter and breaks here
                }
            }
        }

        //if it cannot purchase a card then it will buy some random coins.
        //TODO: add random coins when we figure out how coin actions will work


    }
    public boolean notPlayerTurn() {
        return this.playerNum != gameState.getPlayerTurn();
    }

    public void updateGameState(SplendorGameState info) {
        this.gameState = new SplendorGameState(info);
    }

    public boolean canBuy(Card card) {
        gameState.cardAction(card);
        return true;
    }




}
