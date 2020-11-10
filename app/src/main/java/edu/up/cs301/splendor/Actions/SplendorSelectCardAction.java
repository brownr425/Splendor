package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorSelectCardAction extends GameAction {

   private int row, col;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SplendorSelectCardAction(GamePlayer player, int row, int col) {
        super(player);
        this.row = row;
        this.col = col;
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
