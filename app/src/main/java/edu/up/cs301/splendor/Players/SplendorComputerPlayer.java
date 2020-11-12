package edu.up.cs301.splendor.Players;

import java.util.Random;

import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.splCardAction;
import edu.up.cs301.splendor.Actions.splCoinAction;
import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.State.GameInfo;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorComputerPlayer extends GameComputerPlayer {
    private SplendorGameState gameState;
    private Random randomizer;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public SplendorComputerPlayer(String name) {
        super(name);
    }

    @Override
    public void receiveInfo(GameInfo info) {

        if (!(info instanceof SplendorGameState)) {
            return;
        }
        //refreshes the local copy of the game state with the new info
        SplendorGameState gameState = new SplendorGameState((SplendorGameState)info);

        //checks to see if it Computer Players turn
        if (this.playerNum != gameState.getPlayerTurn()) {
            return;
        }
        //traverse through card board and purchases a card if it can
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = gameState.getBoard(row, col);
                if (canBuy(card, row, col)) {
                    SplendorSelectCardAction select = new SplendorSelectCardAction(this, row, col);
                    this.game.sendAction(select);
                    splCardAction buy = new splCardAction(this, card, row, col);
                    this.game.sendAction(buy);
                    break;
                }
            }
        }
        randomCoinBuy();
    }

    public boolean randomCoinBuy() {
        //generates new random object for random coin selection
        this.randomizer = new Random();
        boolean success = false;
        int coin1 = randomizer.nextInt(5);
        int coin2 = randomizer.nextInt(5);
        int coin3 = randomizer.nextInt(5);
        //if any of the two coins are the same, do action on the dupe coin pile
        if (coin1 == coin2 || coin1 == coin3) {
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            this.game.sendAction(new splCoinAction(this));
        } else if (coin2 == coin3) {
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            this.game.sendAction(new splCoinAction(this));
        } else {
            //otherwise, do action on 3 coins
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            this.game.sendAction(new SplendorCoinSelectAction(this, coin3));
            this.game.sendAction(new splCoinAction(this));
        }

        return true;
    }

    public boolean notPlayerTurn() {
        return this.playerNum != gameState.getPlayerTurn();
    }

    public boolean canBuy(Card card, int row, int col) {
        return this.gameState.cardAction(card, row, col);
    }
}
