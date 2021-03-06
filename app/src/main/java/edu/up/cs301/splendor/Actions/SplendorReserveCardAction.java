package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorReserveCardAction extends GameAction{
    private Card card;
    private GamePlayer player;
    private int row;
    private int col;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SplendorReserveCardAction(GamePlayer player, int row, int col) {
        super(player);
        this.row = row;
        this.col = col;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }
}
