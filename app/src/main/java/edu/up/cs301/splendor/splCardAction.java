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

    //TODO: ?
    public boolean buyCard() {
      return false;
    }

    //TODO: ?
    public boolean reserveCard() {
        return false;
    }


}
