package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;

public class splCardAction extends GameAction {
    private Card card;
    private GamePlayer player;

    /**
     * Constructor for the splCardAction class
     *
     * @param player - the player making the move
     * @param card
     */
    public splCardAction(GamePlayer player, Card card) {
        super(player);
        this.card = card;
        this.player = player;
    }

}
