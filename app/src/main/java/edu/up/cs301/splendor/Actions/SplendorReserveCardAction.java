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

    public boolean reserveAction(Card cardToReserve) {
        switch(this.getPlayerTurn()){
            case 1:
                if (!this.p1Hand.canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0) this.p1GoldCoins++; this.goldCoins--;
                    this.p1Hand.addToReserved(cardToReserve);
                }
                break;
            case 2:
                if (!this.p2Hand.canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0) this.p2GoldCoins++; this.goldCoins--;
                    this.p2Hand.addToReserved(cardToReserve);
                }
                break;
            case 3:
                if (!this.p3Hand.canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0) this.p3GoldCoins++; this.goldCoins--;
                    this.p3Hand.addToReserved(cardToReserve);
                }
                break;
            case 4:
                if (!this.p4Hand.canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0) this.p4GoldCoins++; this.goldCoins--;
                    this.p4Hand.addToReserved(cardToReserve);
                }
                break;
        }
        this.nextPlayerTurn();
        return true;
    }


//    public boolean reserveCard() {
//        if (player.hand.canReserve(card)) {
//            player.hand.addToReserve(card);
//            return true;
//        }
//    }
}
