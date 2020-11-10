package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorReserveCardAction extends GameAction{
    private Card card;
    private GamePlayer player;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SplendorReserveCardAction(GamePlayer player) {
        super(player);
    }

    public void ReserveCardAction(GamePlayer player, Card card) {
        this.card = card;
        this.player = player;
    }


//    public boolean reserveCard() {
//        if (player.hand.canReserve(card)) {
//            player.hand.addToReserve(card);
//            return true;
//        }
//    }
}
