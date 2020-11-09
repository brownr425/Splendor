package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorSelectCardAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SplendorSelectCardAction(GamePlayer player, int row, int col) {
        super(player);
    }
}
