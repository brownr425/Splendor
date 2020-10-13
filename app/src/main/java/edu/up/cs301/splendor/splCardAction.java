package edu.up.cs301.splendor;

import java.util.ArrayList;
import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

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

    public boolean buyCard() {
        //pseudocode
        if(player.hasResources(card)) {
            player.hand.addToHand(card);
            return true;
        } else {
            return false;
        }
    }

    public boolean reserveCard() {
        if(player.hand.canReserve(card)) {
            player.hand.addToReserve(card);
            return true;
        }
    }


}
