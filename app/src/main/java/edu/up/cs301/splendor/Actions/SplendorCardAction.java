package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorCardAction extends GameAction {
    private Card card;
    private GamePlayer player;
    private int row;
    private int col;

    /**
     * Constructor for the splCardAction class
     *
     * @param player - the player making the move
     * @param card
     */
    public SplendorCardAction(GamePlayer player, Card card, int row, int col) {
        super(player);
        this.card = card;
        this.player = player;
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
