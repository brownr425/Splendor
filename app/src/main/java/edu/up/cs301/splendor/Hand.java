package edu.up.cs301.splendor;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private ArrayList<Card> reserved;


    /**
     * constructor Hand
     *
     * creates new hand and reserved hand whenever a new Hand object is created
     */
    public Hand() {
        this.hand = new ArrayList<Card>();
        this.reserved = new ArrayList<Card>();
    }

    /**
     * @param card - to add to player's hand
     */
     protected void addToHand(Card card) {
        hand.add(card);
     }

    /**
     * @param card - to add to player's reserved hand
     */
     protected void addToReserved(Card card) {
        reserved.add(card);
     }

    /**
     * canReserve - detects if player's reserved hand is equal to or greater than 3
     *
     * @return - if player's reserved hand has less than 3, return true
     */
    protected boolean canReserve() { //detects if player's reserved hand is equal to or greater than 3, return condition
        if(reserved.size() >= 3) {
            return false;
        } else
            return true;
    }
}
