package edu.up.cs301.splendor;

import android.graphics.Paint;
import android.support.annotation.VisibleForTesting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class SplendorGameState extends GameState {

//~~~~~~~~~~~~~ player names and IDs ~~~~~~~~~~~ //
    //player display names
    //TODO figure out how to get the player names from super class.
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
    private int p1OnyxPts;

    //player 1 coin values
    private int p1GoldCoins;
    private int p1EmeraldCoins;
    private int p1SapphireCoins;
    private int p1RubyCoins;
    private int p1DiamondCoins;
    private int p1OnyxCoins;

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
    private int p2OnyxPts;

    //player 2 coin values
    private int p2GoldCoins;
    private int p2EmeraldCoins;
    private int p2SapphireCoins;
    private int p2RubyCoins;
    private int p2DiamondCoins;
    private int p2OnyxCoins;

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
    private int p3OnyxPts;

    //player 3 coin values
    private int p3GoldCoins;
    private int p3EmeraldCoins;
    private int p3SapphireCoins;
    private int p3RubyCoins;
    private int p3DiamondCoins;
    private int p3OnyxCoins;

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
    private int p4OnyxPts;

    //player 4 coin values
    private int p4GoldCoins;
    private int p4EmeraldCoins;
    private int p4SapphireCoins;
    private int p4RubyCoins;
    private int p4DiamondCoins;
    private int p4OnyxCoins;

    //reserve card count
    private int p4NumCardsReserved;
    private ArrayList<Card> p4ReserveCards;

//~~~~~~~~~~~~~~~~~~ Deck Information ~~~~~~~~~~~~~~~ //

    private ArrayList<Card> rank1Stack; //ArrayList of rank1 cards
    private ArrayList<Card> rank2Stack; //ArrayList of rank2 cards
    private ArrayList<Card> rank3Stack; //ArrayList of rank3 cards

    private Noble noble1;
    private Noble noble2;
    private Noble noble3;
    private Noble noble4;

    /*
    *New Game Constructor
    * TODO
    *  -figure out  file reading for initializing deck array lists
    *  -possibly add noble/card visibility boolean instance variables
    */
//~~~~~~~~~~~~~~~~~~ Hand Informtion ~~~~~~~~~~~~~ //

    //all players' hands
    private Hand p1Hand;
    private Hand p2Hand;
    private Hand p3Hand;
    private Hand p4Hand;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public SplendorGameState(){
        initializePlayerPointValues();
        initializeDecks(); //unfinished
        initializeHands();
    }

    /*
    *
    * Deep copy constructor
    * TODO add nobles and card visibility
    */
    public SplendorGameState(SplendorGameState newGameState) {

        //deep copy of player 1 points and reserve cards
        newGameState.p1GoldCoins = this.getP1GoldCoins();
        newGameState.p1GoldPts = this.getP1GoldPts();
        newGameState.p1EmeraldCoins = this.getP1EmeraldCoins();
        newGameState.p1EmeraldPts = this.getP1EmeraldPts();
        newGameState.p1SapphireCoins = this.getP1SapphireCoins();
        newGameState.p1SapphirePts = this.getP1SapphirePts();
        newGameState.p1RubyCoins = this.getP1RubyCoins();
        newGameState.p1RubyPts = this.getP1RubyPts();
        newGameState.p1OnyxCoins = this.getP1OnyxCoins();
        newGameState.p1OnyxPts = this.getP1OnyxPts();
        newGameState.p1DiamondCoins = this.getP1DiamondCoins();
        newGameState.p1PrestigePts = this.getP1OnyxPts();
        newGameState.p1NumCardsReserved = this.getP1NumCardsReserved();
        newGameState.p1ReserveCards = new ArrayList<Card>();
        for(Card card: this.p1ReserveCards) {
            newGameState.p1ReserveCards.add(new Card(card)); //uses copy constructor in card
        }

        //deep copy of player 2 points and reserve cards
        newGameState.p2GoldCoins = this.getP2GoldCoins();
        newGameState.p2GoldPts = this.getP2GoldPts();
        newGameState.p2EmeraldCoins = this.getP2EmeraldCoins();
        newGameState.p2EmeraldPts = this.getP2EmeraldPts();
        newGameState.p2SapphireCoins = this.getP2SapphireCoins();
        newGameState.p2SapphirePts = this.getP2SapphirePts();
        newGameState.p2RubyCoins = this.getP2RubyCoins();
        newGameState.p2RubyPts = this.getP2RubyPts();
        newGameState.p2OnyxCoins = this.getP2OnyxCoins();
        newGameState.p2OnyxPts = this.getP2OnyxPts();
        newGameState.p2DiamondCoins = this.getP2DiamondCoins();
        newGameState.p2PrestigePts = this.getP2OnyxPts();
        newGameState.p2NumCardsReserved = this.getP2NumCardsReserved();
        newGameState.p2ReserveCards = new ArrayList<Card>();
        for(Card card: this.p2ReserveCards) {
            newGameState.p2ReserveCards.add(new Card(card)); //uses copy constructor in card
        }

        //deep copy of player 3 points and reserve cards
        newGameState.p3GoldCoins = this.getP3GoldCoins();
        newGameState.p3GoldPts = this.getP3GoldPts();
        newGameState.p3EmeraldCoins = this.getP3EmeraldCoins();
        newGameState.p3EmeraldPts = this.getP3EmeraldPts();
        newGameState.p3SapphireCoins = this.getP3SapphireCoins();
        newGameState.p3SapphirePts = this.getP3SapphirePts();
        newGameState.p3RubyCoins = this.getP3RubyCoins();
        newGameState.p3RubyPts = this.getP3RubyPts();
        newGameState.p3OnyxCoins = this.getP3OnyxCoins();
        newGameState.p3OnyxPts = this.getP3OnyxPts();
        newGameState.p3DiamondCoins = this.getP3DiamondCoins();
        newGameState.p3PrestigePts = this.getP3OnyxPts();
        newGameState.p3NumCardsReserved = this.getP3NumCardsReserved();
        newGameState.p3ReserveCards = new ArrayList<Card>();
        for(Card card: this.p3ReserveCards) {
            newGameState.p3ReserveCards.add(new Card(card)); //uses copy constructor in card
        }

        //deep copy of player 4 points and reserve cards
        newGameState.p4GoldCoins = this.getP4GoldCoins();
        newGameState.p4GoldPts = this.getP4GoldPts();
        newGameState.p4EmeraldCoins = this.getP4EmeraldCoins();
        newGameState.p4EmeraldPts = this.getP4EmeraldPts();
        newGameState.p4SapphireCoins = this.getP4SapphireCoins();
        newGameState.p4SapphirePts = this.getP4SapphirePts();
        newGameState.p4RubyCoins = this.getP4RubyCoins();
        newGameState.p4RubyPts = this.getP4RubyPts();
        newGameState.p4OnyxCoins = this.getP4OnyxCoins();
        newGameState.p4OnyxPts = this.getP4OnyxPts();
        newGameState.p4DiamondCoins = this.getP4DiamondCoins();
        newGameState.p4PrestigePts = this.getP4OnyxPts();
        newGameState.p4NumCardsReserved = this.getP4NumCardsReserved();
        newGameState.p4ReserveCards = new ArrayList<Card>();
        for(Card card: this.p4ReserveCards) {
            newGameState.p4ReserveCards.add(new Card(card)); //uses copy constructor in card
        }

        //deep copies for all 3 card stacks
        newGameState.rank1Stack = new ArrayList<>();
        for(Card rankCard: this.rank1Stack) {
            newGameState.rank1Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        newGameState.rank2Stack = new ArrayList<>();
        for(Card rankCard: this.rank2Stack) {
            newGameState.rank2Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        newGameState.rank3Stack = new ArrayList<>();
        for(Card rankCard: this.rank3Stack) {
            newGameState.rank3Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        //deep copies for each players hand

        //TODO make noble class and copy constructor
        initializeDecks();
        initializeHands();
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
        this.p1OnyxCoins = 0;
        this.p1OnyxPts = 0;
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
        this.p2OnyxCoins = 0;
        this.p2OnyxPts = 0;
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
        this.p3OnyxCoins = 0;
        this.p3OnyxPts = 0;
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
        this.p4OnyxCoins = 0;
        this.p4OnyxPts = 0;
        this.p4DiamondCoins = 0;
        this.p4PrestigePts = 0;
        this.p4NumCardsReserved = 0;
        this.p4ReserveCards = new ArrayList<Card>();
    }

    //TODO file reading from three rank text files
    /*initialize Decks
    * reads input from text files into three array lists then shuffles deck
    *
    */
    public void initializeDecks() {
        this.rank1Stack = new ArrayList<Card>();
        this.rank2Stack = new ArrayList<Card>();
        this.rank3Stack = new ArrayList<Card>();
    }
    public void initializeHands() {
        this.p1Hand = new Hand();
        this.p2Hand = new Hand();
        this.p3Hand = new Hand();
        this.p4Hand = new Hand();
    }
    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public String getPlayer3Name() {
        return player3Name;
    }

    public String getPlayer4Name() {
        return player4Name;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getPLAYER1ID() {
        return PLAYER1ID;
    }

    public int getPLAYER2ID() {
        return PLAYER2ID;
    }

    public int getPLAYER3ID() {
        return PLAYER3ID;
    }

    public int getPLAYER4ID() {
        return PLAYER4ID;
    }

    public int getP1PrestigePts() {
        return p1PrestigePts;
    }

    public int getP1GoldPts() {
        return p1GoldPts;
    }

    public int getP1EmeraldPts() {
        return p1EmeraldPts;
    }

    public int getP1SapphirePts() {
        return p1SapphirePts;
    }

    public int getP1RubyPts() {
        return p1RubyPts;
    }

    public int getP1DiamondPts() {
        return p1DiamondPts;
    }

    public int getP1OnyxPts() {
        return p1OnyxPts;
    }

    public int getP1GoldCoins() {
        return p1GoldCoins;
    }

    public int getP1EmeraldCoins() {
        return p1EmeraldCoins;
    }

    public int getP1SapphireCoins() {
        return p1SapphireCoins;
    }

    public int getP1RubyCoins() {
        return p1RubyCoins;
    }

    public int getP1DiamondCoins() {
        return p1DiamondCoins;
    }

    public int getP1OnyxCoins() {
        return p1OnyxCoins;
    }

    public int getP1NumCardsReserved() {
        return p1NumCardsReserved;
    }

    public ArrayList<Card> getP1ReserveCards() {
        return p1ReserveCards;
    }

    public int getP2PrestigePts() {
        return p2PrestigePts;
    }

    public int getP2GoldPts() {
        return p2GoldPts;
    }

    public int getP2EmeraldPts() {
        return p2EmeraldPts;
    }

    public int getP2SapphirePts() {
        return p2SapphirePts;
    }

    public int getP2RubyPts() {
        return p2RubyPts;
    }

    public int getP2DiamondPts() {
        return p2DiamondPts;
    }

    public int getP2OnyxPts() {
        return p2OnyxPts;
    }

    public int getP2GoldCoins() {
        return p2GoldCoins;
    }

    public int getP2EmeraldCoins() {
        return p2EmeraldCoins;
    }

    public int getP2SapphireCoins() {
        return p2SapphireCoins;
    }

    public int getP2RubyCoins() {
        return p2RubyCoins;
    }

    public int getP2DiamondCoins() {
        return p2DiamondCoins;
    }

    public int getP2OnyxCoins() {
        return p2OnyxCoins;
    }

    public int getP2NumCardsReserved() {
        return p2NumCardsReserved;
    }

    public ArrayList<Card> getP2ReserveCards() {
        return p2ReserveCards;
    }

    public int getP3PrestigePts() {
        return p3PrestigePts;
    }

    public int getP3GoldPts() {
        return p3GoldPts;
    }

    public int getP3EmeraldPts() {
        return p3EmeraldPts;
    }

    public int getP3SapphirePts() {
        return p3SapphirePts;
    }

    public int getP3RubyPts() {
        return p3RubyPts;
    }

    public int getP3DiamondPts() {
        return p3DiamondPts;
    }

    public int getP3OnyxPts() {
        return p3OnyxPts;
    }

    public int getP3GoldCoins() {
        return p3GoldCoins;
    }

    public int getP3EmeraldCoins() {
        return p3EmeraldCoins;
    }

    public int getP3SapphireCoins() {
        return p3SapphireCoins;
    }

    public int getP3RubyCoins() {
        return p3RubyCoins;
    }

    public int getP3DiamondCoins() {
        return p3DiamondCoins;
    }

    public int getP3OnyxCoins() {
        return p3OnyxCoins;
    }

    public int getP3NumCardsReserved() {
        return p3NumCardsReserved;
    }

    public ArrayList<Card> getP3ReserveCards() {
        return p3ReserveCards;
    }

    public int getP4PrestigePts() {
        return p4PrestigePts;
    }

    public int getP4GoldPts() {
        return p4GoldPts;
    }

    public int getP4EmeraldPts() {
        return p4EmeraldPts;
    }

    public int getP4SapphirePts() {
        return p4SapphirePts;
    }

    public int getP4RubyPts() {
        return p4RubyPts;
    }

    public int getP4DiamondPts() {
        return p4DiamondPts;
    }

    public int getP4OnyxPts() {
        return p4OnyxPts;
    }

    public int getP4GoldCoins() {
        return p4GoldCoins;
    }

    public int getP4EmeraldCoins() {
        return p4EmeraldCoins;
    }

    public int getP4SapphireCoins() {
        return p4SapphireCoins;
    }

    public int getP4RubyCoins() {
        return p4RubyCoins;
    }

    public int getP4DiamondCoins() {
        return p4DiamondCoins;
    }

    public int getP4OnyxCoins() {
        return p4OnyxCoins;
    }

    public int getP4NumCardsReserved() {
        return p4NumCardsReserved;
    }

    public ArrayList<Card> getP4ReserveCards() {
        return p4ReserveCards;
    }

    public ArrayList<Card> getRank1Stack() {
        return rank1Stack;
    }

    public ArrayList<Card> getRank2Stack() {
        return rank2Stack;
    }

    public ArrayList<Card> getRank3Stack() {
        return rank3Stack;
    }

    public Noble getNoble1() {
        return noble1;
    }

    public Noble getNoble2() {
        return noble2;
    }

    public Noble getNoble3() {
        return noble3;
    }

    public Noble getNoble4() {
        return noble4;
    }

}
