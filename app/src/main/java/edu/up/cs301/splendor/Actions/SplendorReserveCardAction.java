package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;

public class SplendorReserveCardAction {
    private Card card;
    private GamePlayer player;

    public void ReserveCardAction(GamePlayer player, Card card) {
        super(player);
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
