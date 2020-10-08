package edu.up.cs301.splendor;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class splCardAction extends GameAction {
    private ArrayList<Card> cards;

    /**
     * Constructor for the splCardAction class
     *
     * @param player - the player making the move
     * @param cards
     */
    public splCardAction(GamePlayer player, ArrayList<Card> cards) {
        super(player);
        this.cards = cards;
    }

    public boolean canBuy() {
        if(player) {

        }
    }


}
