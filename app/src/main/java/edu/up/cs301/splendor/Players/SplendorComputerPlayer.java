package edu.up.cs301.splendor.Players;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReturnCoinAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.State.GameInfo;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorComputerPlayer extends GameComputerPlayer {
    private SplendorGameState gameState;
    private Random randomizer;
    private int totalCoins = 0;

    /**
     * constructor
     *
     * @param name the computer player's name
     */
    public SplendorComputerPlayer(String name) {
        super(name);
        this.randomizer = new Random();
    }

    /**
     * receiveInfo(GameInfo info) get info about the updated GameState
     *
     * AI checks if it can buy a card, if not take coins. return coins if needed
     *
     * @param info the info being sent from Local Game
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof SplendorGameState)) {
            return;
        }
        //refreshes the local copy of the game state with the new info
        this.gameState = new SplendorGameState((SplendorGameState)info);

        //checks to see if it Computer Players turn
        if (this.playerNum != gameState.getPlayerTurn()) {
            return;
        }

        sleep(2);
        //traverse through card board and purchases a card if it can
       // totalCoins = this.gameState.getPlayer(this.gameState.getPlayerTurn()).getTotalCoins();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = gameState.getBoard(row, col);
                SplendorSelectCardAction select = new SplendorSelectCardAction(this, row, col);
                this.game.sendAction(select);
                SplendorCardAction buy = new SplendorCardAction(this, card, row, col);
                this.game.sendAction(buy);
            }
        }
        randomCoinBuy();
        randomReturn();
    }

    /**
     * helper method for receiveInfo()
     *
     * generates a random coin to but, could be 2, could be 3
     *
     */
    public boolean randomCoinBuy() {
        //generates new random object for random coin selection
        boolean success = false;

        int coin1 = randomizer.nextInt(5);
        int coin2 = randomizer.nextInt(5);
        int coin3 = randomizer.nextInt(5);

        //clears the coin tracking array before adding
        this.game.sendAction(new SplendorCoinSelectAction(this, -1));

        //if any of the two coins are the same, do action on the dupe coin pile
        if (coin1 == coin2 || coin1 == coin3) {
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            sleep(0.1);
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            sleep(1);
            this.game.sendAction(new SplendorCoinAction(this));
            totalCoins += 2;
            this.gameState.coinAction(coin1);
        } else if (coin2 == coin3) {
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            sleep(0.1);
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            sleep(1);
            this.game.sendAction(new SplendorCoinAction(this));
            totalCoins += 2;
            this.gameState.coinAction(coin2);
        } else {
            //otherwise, do action on 3 coins
            this.game.sendAction(new SplendorCoinSelectAction(this, coin1));
            sleep(0.1);
            this.game.sendAction(new SplendorCoinSelectAction(this, coin2));
            sleep(0.1);
            this.game.sendAction(new SplendorCoinSelectAction(this, coin3));
            sleep(1);
            this.game.sendAction(new SplendorCoinAction(this));
            totalCoins += 3;
            this.gameState.coinAction(coin1, coin2, coin3);
        }
        return true;
    }

    /**
     * helper to receiveInfo
     *
     * returns 1-3 random coins
     *
     */
    public boolean randomReturn() {
        int coinType = randomizer.nextInt(5);
        boolean flag = false;

        //for numTypes (0-2), return a random coin type if the player has it
        if (!(hasCoin(coinType))) {
            while (!flag) {
                //randomize coin type and change flag
                coinType = randomizer.nextInt(5);
                if (hasCoin(coinType)) {
                    flag = true;
                }
            }
        }
        this.game.sendAction(new SplendorCoinSelectAction(this, coinType));
        this.game.sendAction(new SplendorReturnCoinAction(this));
        totalCoins--;
        return true;
    }

    /**
     * helper to randomReturn
     *
     * checks if the computer player has the selected coins before they return them
     *
     */
    public boolean hasCoin(int coin) {
        boolean flag = false;
        switch (coin) {
            case 0:
                flag = (this.gameState.getPlayerList().get(gameState.getPlayerTurn()).getRubyCoins() != 0);
                break;
            case 1:
                flag = (this.gameState.getPlayerList().get(gameState.getPlayerTurn()).getSapphCoins() != 0);
                break;
            case 2:
                flag = (this.gameState.getPlayerList().get(gameState.getPlayerTurn()).getEmerCoins() != 0);
                break;
            case 3:
                flag = (this.gameState.getPlayerList().get(gameState.getPlayerTurn()).getDiaCoins() != 0);
                break;
            case 4:
                flag = (this.gameState.getPlayerList().get(gameState.getPlayerTurn()).getOnyxCoins() != 0);
                break;
            default:
                break;
        }
        return flag;
    }
}
