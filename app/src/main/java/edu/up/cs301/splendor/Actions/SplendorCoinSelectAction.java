package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorCoinSelectAction extends GameAction{

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */

    int chosenCoin = 0;
    public SplendorCoinSelectAction(GamePlayer player, int coin) {
        super(player);
        this.chosenCoin = coin;
    }

    public int getChosenCoin(){
        return this.chosenCoin;
    }
}