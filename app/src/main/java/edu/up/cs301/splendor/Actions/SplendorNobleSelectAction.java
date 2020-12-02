package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorNobleSelectAction extends GameAction {

    private int row;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SplendorNobleSelectAction(GamePlayer player, int row) {
        super(player);
        this.row = row;
    }

    public int getRow() {
        return row;
    }
}
