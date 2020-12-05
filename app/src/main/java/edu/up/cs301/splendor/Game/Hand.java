package edu.up.cs301.splendor.Game;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private ArrayList<Card> reserved;


    /**
     * Hand() default hand constructor
     */
    public Hand() {
        this.hand = new ArrayList<Card>();
        this.reserved = new ArrayList<Card>();
    }

    /**
     * Hand() copy constructor for Hand
     *
     * @param handToCopy
     */
    public Hand(Hand handToCopy) {

        this.hand = new ArrayList<Card>();
        for (Card card : handToCopy.hand) {
            this.hand.add(new Card(card)); //uses copy constructor in card
        }

        this.reserved = new ArrayList<Card>();
        for (Card card: handToCopy.reserved) {
            this.reserved.add(new Card(card));
        }
    }

    /**
     * addToHand() adds a Card to players hand
     *
     * @param card - to add to player's hand
     */
    public void addToHand(Card card) {
        hand.add(card);
    }

    /**
     * addToReserved() adds a card to reserved cards
     *
     * @param card - to add to player's reserved hand
     */
    public void addToReserved(Card card) {
        reserved.add(card);
    }

    /**
     * removeFromReserved() should remove a card from the reserved hand, in case someone buys it
     *
     * @param idx index in array to remove from
     * */
    public void removeFromReserved(int idx){reserved.remove(idx);}

    /**
     * canReserve() detects if player's reserved hand is equal to or greater than 3
     *
     * @return - if player's reserved hand has less than 3, return true
     */
    public boolean canReserve() { //detects if player's reserved hand is equal to or greater than 3, return condition
        if(reserved.size() >= 3) {
            return false;
        } else
            return true;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public ArrayList<Card> getReserved(){
        return this.reserved;
    }
}