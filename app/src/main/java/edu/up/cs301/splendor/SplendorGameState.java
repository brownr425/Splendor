package edu.up.cs301.splendor;

import android.graphics.Paint;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class SplendorGameState extends GameState {

//~~~~~~~~~~~~~ player names and IDs ~~~~~~~~~~~ //
    //player display names
    private String player1Name;
    private String player2Name;
    private String player3Name;
    private String player4Name;

    //holds turn value corresponding to player ID below
    private int playerTurn;

    //player values for playerTurn
    private final int PLAYER1ID = 1;
    private final int PLAYER2ID = 2;
    private final int PLAYER3ID = 3;
    private final int PLAYER4ID = 4;


//~~~~~~~~~~~~~~~~ player 1 ~~~~~~~~~~~~~~~~~~ //
    //player 1 prestigePts
    private int p1PrestigePts;

    //player 1 resource point values
    private int p1GoldPts;
    private int p1EmeraldPts;
    private int p1SapphirePts;
    private int p1RubyPts;
    private int p1DiamondPts;
    private int p1OnxyPts;

    //player 1 coin values
    private int p1GoldCoins;
    private int p1EmeraldCoins;
    private int p1SapphireCoins;
    private int p1RubyCoins;
    private int p1DiamondCoins;
    private int p1OnxyCoins;

    //reserve cards count
    private int p1NumCardsReserved;
    private ArrayList<Card> p1ReserveCards;

//~~~~~~~~~~~~~~~~ player 2 ~~~~~~~~~~~~~~~~~~ //

    //player 2 prestigePts
    private int p2PrestigePts;

    //player 2 resource point values
    private int p2GoldPts;
    private int p2EmeraldPts;
    private int p2SapphirePts;
    private int p2RubyPts;
    private int p2DiamondPts;
    private int p2OnxyPts;

    //player 2 coin values
    private int p2GoldCoins;
    private int p2EmeraldCoins;
    private int p2SapphireCoins;
    private int p2RubyCoins;
    private int p2DiamondCoins;
    private int p2OnxyCoins;

    //reserve card count
    private int p2NumCardsReserved;
    private ArrayList<Card> p2ReserveCards;


//~~~~~~~~~~~~~~~~ player 3 ~~~~~~~~~~~~~~~~~~ //

    //player 3 prestigePts
    private int p3PrestigePts;

    //player 3 resource point values
    private int p3GoldPts;
    private int p3EmeraldPts;
    private int p3SapphirePts;
    private int p3RubyPts;
    private int p3DiamondPts;
    private int p3OnxyPts;

    //player 3 coin values
    private int p3GoldCoins;
    private int p3EmeraldCoins;
    private int p3SapphireCoins;
    private int p3RubyCoins;
    private int p3DiamondCoins;
    private int p3OnxyCoins;

    //reserve card count
    private int p3NumCardsReserved;
    private ArrayList<Card> p3ReserveCards;


//~~~~~~~~~~~~~~~~ player 4 ~~~~~~~~~~~~~~~~~~ //

    //player 4 prestigePts
    private int p4PrestigePts;

    //player 4 resource point values
    private int p4GoldPts;
    private int p4EmeraldPts;
    private int p4SapphirePts;
    private int p4RubyPts;
    private int p4DiamondPts;
    private int p4OnxyPts;

    //player 4 coin values
    private int p4GoldCoins;
    private int p4EmeraldCoins;
    private int p4SapphireCoins;
    private int p4RubyCoins;
    private int p4DiamondCoins;
    private int p4OnxyCoins;

    //reserve card count
    private int p4NumCardsReserved;
    private ArrayList<Card> p4ReserveCards;

//~~~~~~~~~~~~~~~~~~ Deck Information ~~~~~~~~~~~~~~~ //

    private ArrayList<Cards> rank1Stack; //ArrayList of rank1 cards
    private ArrayList<Cards> rank2Stack; //ArrayList of rank2 cards
    private ArrayList<Cards> rank3Stack; //ArrayList of rank3 cards

    private Noble noble1;
    private Noble noble2;
    private Noble noble3;
    private Noble noble4;

    public SplendorGameState(){
        initializePlayerPointValues();
        initializeDecks();
    }

    //helper method for constructor setting all point values for player to zero
    public void initializePlayerPointValues() {
        //player one
        this.p1GoldCoins = 0;
        this.p1GoldPts = 0;
        this.p1EmeraldCoins = 0;
        this.p1EmeraldPts = 0;
        this.p1SapphireCoins = 0;
        this.p1SapphirePts = 0;
        this.p1RubyCoins = 0;
        this.p1RubyPts = 0;
        this.p1OnxyCoins = 0;
        this.p1OnxyPts = 0;
        this.p1DiamondCoins = 0;
        this.p1PrestigePts = 0;
        this.p1NumCardsReserved = 0;
        this.p1ReserveCards = new ArrayList<Card>();

        this.p2GoldCoins = 0;
        this.p2GoldPts = 0;
        this.p2EmeraldCoins = 0;
        this.p2EmeraldPts = 0;
        this.p2SapphireCoins = 0;
        this.p2SapphirePts = 0;
        this.p2RubyCoins = 0;
        this.p2RubyPts = 0;
        this.p2OnxyCoins = 0;
        this.p2OnxyPts = 0;
        this.p2DiamondCoins = 0;
        this.p2PrestigePts = 0;
        this.p2NumCardsReserved = 0;
        this.p2ReserveCards = new ArrayList<Card>();

        this.p3GoldCoins = 0;
        this.p3GoldPts = 0;
        this.p3EmeraldCoins = 0;
        this.p3EmeraldPts = 0;
        this.p3SapphireCoins = 0;
        this.p3SapphirePts = 0;
        this.p3RubyCoins = 0;
        this.p3RubyPts = 0;
        this.p3OnxyCoins = 0;
        this.p3OnxyPts = 0;
        this.p3DiamondCoins = 0;
        this.p3PrestigePts = 0;
        this.p3NumCardsReserved = 0;
        this.p3ReserveCards = new ArrayList<Card>();

        this.p4GoldCoins = 0;
        this.p4GoldPts = 0;
        this.p4EmeraldCoins = 0;
        this.p4EmeraldPts = 0;
        this.p4SapphireCoins = 0;
        this.p4SapphirePts = 0;
        this.p4RubyCoins = 0;
        this.p4RubyPts = 0;
        this.p4OnxyCoins = 0;
        this.p4OnxyPts = 0;
        this.p4DiamondCoins = 0;
        this.p4PrestigePts = 0;
        this.p4NumCardsReserved = 0;
        this.p4ReserveCards = new ArrayList<Card>();
    }

    public void initializeDecks() {
        this.rank1Stack = new ArrayList<Card>();
        this.rank2Stack = new ArrayList<Card>();
        this.rank3Stack = new ArrayList<Card>();

        //toString
    }
}
