package edu.up.cs301.splendor.Actions;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Players.GamePlayer;
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
    public boolean cardAction(Card cardToBuy) {
        switch(this.getPlayerTurn()){
            case 1:
                if (cardToBuy.getrPrice() <= p1RubyCoins+p1RubyPts &&
                        cardToBuy.getbPrice() <= p1SapphireCoins+p1SapphirePts &&
                        cardToBuy.getBrPrice() <= p1OnyxCoins+p1OnyxPts &&
                        cardToBuy.getwPrice() <= p1DiamondCoins+p1DiamondPts &&
                        cardToBuy.getgPrice() <= p1EmeraldCoins+p1EmeraldPts)
                {
                    if(cardToBuy.getrPrice()-p1RubyPts >= 0) p1RubyCoins = cardToBuy.getrPrice()-p1RubyPts-p1RubyCoins;
                    if(cardToBuy.getbPrice()-p1SapphirePts >= 0) p1SapphireCoins = cardToBuy.getbPrice()-p1SapphirePts-p1SapphireCoins;
                    if(cardToBuy.getBrPrice()-p1OnyxPts >= 0) p1OnyxCoins = cardToBuy.getBrPrice()-p1OnyxPts-p1OnyxCoins;
                    if(cardToBuy.getwPrice()-p1DiamondPts >= 0) p1DiamondCoins = cardToBuy.getwPrice()-p1DiamondPts-p1DiamondCoins;
                    if(cardToBuy.getgPrice()-p1EmeraldPts >= 0) p1EmeraldCoins = cardToBuy.getgPrice()-p1EmeraldPts-p1EmeraldCoins;
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.p1Hand.addToHand(cardToBuy);
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 2:
                if (cardToBuy.getrPrice() <= p2RubyCoins+p2RubyPts &&
                        cardToBuy.getbPrice() <= p2SapphireCoins+p2SapphirePts &&
                        cardToBuy.getBrPrice() <= p2OnyxCoins+p2OnyxPts &&
                        cardToBuy.getwPrice() <= p2DiamondCoins+p2DiamondPts &&
                        cardToBuy.getgPrice() <= p2EmeraldCoins+p2EmeraldPts)
                {
                    if(cardToBuy.getrPrice()-p2RubyPts >= 0) p2RubyCoins = cardToBuy.getrPrice()-p2RubyPts-p2RubyCoins;
                    if(cardToBuy.getbPrice()-p2SapphirePts >= 0) p2SapphireCoins = cardToBuy.getbPrice()-p2SapphirePts-p2SapphireCoins;
                    if(cardToBuy.getBrPrice()-p2OnyxPts >= 0) p2OnyxCoins = cardToBuy.getBrPrice()-p2OnyxPts-p2OnyxCoins;
                    if(cardToBuy.getwPrice()-p2DiamondPts >= 0) p2DiamondCoins = cardToBuy.getwPrice()-p2DiamondPts-p2DiamondCoins;
                    if(cardToBuy.getgPrice()-p2EmeraldPts >= 0) p2EmeraldCoins = cardToBuy.getgPrice()-p2EmeraldPts-p2EmeraldCoins;
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.p2Hand.addToHand(cardToBuy);
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 3:
                if (cardToBuy.getrPrice() <= p3RubyCoins+p3RubyPts &&
                        cardToBuy.getbPrice() <= p3SapphireCoins+p3SapphirePts &&
                        cardToBuy.getBrPrice() <= p3OnyxCoins+p3OnyxPts &&
                        cardToBuy.getwPrice() <= p3DiamondCoins+p3DiamondPts &&
                        cardToBuy.getgPrice() <= p3EmeraldCoins+p3EmeraldPts)
                {
                    if(cardToBuy.getrPrice()-p3RubyPts >= 0) p3RubyCoins = cardToBuy.getrPrice()-p3RubyPts-p3RubyCoins;
                    if(cardToBuy.getbPrice()-p3SapphirePts >= 0) p3SapphireCoins = cardToBuy.getbPrice()-p3SapphirePts-p3SapphireCoins;
                    if(cardToBuy.getBrPrice()-p3OnyxPts >= 0) p3OnyxCoins = cardToBuy.getBrPrice()-p3OnyxPts-p3OnyxCoins;
                    if(cardToBuy.getwPrice()-p3DiamondPts >= 0) p3DiamondCoins = cardToBuy.getwPrice()-p3DiamondPts-p3DiamondCoins;
                    if(cardToBuy.getgPrice()-p3EmeraldPts >= 0) p3EmeraldCoins = cardToBuy.getgPrice()-p3EmeraldPts-p3EmeraldCoins;
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.p3Hand.addToHand(cardToBuy);
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 4:
                if (cardToBuy.getrPrice() <= p4RubyCoins+p4RubyPts &&
                        cardToBuy.getbPrice() <= p4SapphireCoins+p4SapphirePts &&
                        cardToBuy.getBrPrice() <= p4OnyxCoins+p4OnyxPts &&
                        cardToBuy.getwPrice() <= p4DiamondCoins+p4DiamondPts &&
                        cardToBuy.getgPrice() <= p4EmeraldCoins+p4EmeraldPts)
                {
                    if(cardToBuy.getrPrice()-p4RubyPts >= 0) p4RubyCoins = cardToBuy.getrPrice()-p4RubyPts-p4RubyCoins;
                    if(cardToBuy.getbPrice()-p4SapphirePts >= 0) p4SapphireCoins = cardToBuy.getbPrice()-p4SapphirePts-p4SapphireCoins;
                    if(cardToBuy.getBrPrice()-p4OnyxPts >= 0) p4OnyxCoins = cardToBuy.getBrPrice()-p4OnyxPts-p4OnyxCoins;
                    if(cardToBuy.getwPrice()-p4DiamondPts >= 0) p4DiamondCoins = cardToBuy.getwPrice()-p4DiamondPts-p4DiamondCoins;
                    if(cardToBuy.getgPrice()-p4EmeraldPts >= 0) p4EmeraldCoins = cardToBuy.getgPrice()-p4EmeraldPts-p4EmeraldCoins;
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.p4Hand.addToHand(cardToBuy);
                    this.nextPlayerTurn();
                    return true;
                }
                break;
        }
        return false;
    }



}
