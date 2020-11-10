package edu.up.cs301.splendor.State;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Game.Hand;
import edu.up.cs301.splendor.Game.Noble;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;


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

//~~~~~~~~~~~~~~~~~~ Deck and Coin Information ~~~~~~~~~~~~~~~ //

    private ArrayList<Card> rank1Stack; //ArrayList of rank1 cards
    private ArrayList<Card> rank2Stack; //ArrayList of rank2 cards
    private ArrayList<Card> rank3Stack; //ArrayList of rank3 cards
    private ArrayList<Noble> nobleStack;

    private final int RANKS = 3;
    private final int CARDS_PER_RANK = 4;

    // some could be unused, dependent on num players
    private Noble noble1; //= new Noble(4,0,4,0,0,3);;
    private Noble noble2; //= new Noble(3,0,0,3,3,3);;
    private Noble noble3; //= new Noble(4,0,0,0,4,3);;
    private Noble noble4 ;//= new Noble(0,3,3,3,0,3);;

    private int rubyCoins;
    private int sapphireCoins;
    private int emeraldCoins;
    private int diamondCoins;
    private int onyxCoins;
    private int goldCoins;

    /*
     *New Game Constructor
     */
//~~~~~~~~~~~~~~~~~~ Hand Information ~~~~~~~~~~~~~ //

    //all players' hands
    private Hand p1Hand;
    private Hand p2Hand;
    private Hand p3Hand;
    private Hand p4Hand;

//~~~~~~~~~~~~~~~~~~~~~ Game State Specific Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    private int stack1Iterator = 0;
    private int stack2Iterator = 0;
    private int stack3Iterator = 0;

    private Card board[][] = new Card[RANKS][CARDS_PER_RANK];
    private Noble nobles[];
    private Card selected = null;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public SplendorGameState() { /*InputStream rank1, InputStream rank2, InputStream rank3*/ //
        initializePlayerPointValues();
        //initializeDecks(); //unfinished
        //initializeHands();
        initializeCoins();
        //initializeNobles();

        initializeDecks(); //unfinished: rank1, rank2, rank3
        initializeHands();
        Collections.shuffle(this.rank1Stack);
        Collections.shuffle(this.rank2Stack);
        Collections.shuffle(this.rank3Stack);
        initializeBoard(this.rank1Stack, this.rank2Stack, this.rank3Stack);
    }

    /*
     *
     * Deep copy constructor
     *
     */
    public SplendorGameState(SplendorGameState stateToCopy) {

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                this.board[i][j] = stateToCopy.getBoard(i, j);
            }
        }

        //creates deeps copy of each players hands
        this.p1Hand = new Hand(stateToCopy.p1Hand);
        this.p2Hand = new Hand(stateToCopy.p2Hand);
        this.p3Hand = new Hand(stateToCopy.p3Hand);
        this.p4Hand = new Hand(stateToCopy.p4Hand);

        //deep copy of player 1 points and reserve cards
        this.p1GoldCoins = stateToCopy.getP1GoldCoins();
        this.p1GoldPts = stateToCopy.getP1GoldPts();
        this.p1EmeraldCoins = stateToCopy.getP1EmeraldCoins();
        this.p1EmeraldPts = stateToCopy.getP1EmeraldPts();
        this.p1SapphireCoins = stateToCopy.getP1SapphireCoins();
        this.p1SapphirePts = stateToCopy.getP1SapphirePts();
        this.p1RubyCoins = stateToCopy.getP1RubyCoins();
        this.p1RubyPts = stateToCopy.getP1RubyPts();
        this.p1OnyxCoins = stateToCopy.getP1OnyxCoins();
        this.p1OnyxPts = stateToCopy.getP1OnyxPts();
        this.p1DiamondPts = stateToCopy.getP1DiamondPts();
        this.p1DiamondCoins = stateToCopy.getP1DiamondCoins();
        this.p1PrestigePts = stateToCopy.getP1OnyxPts();
        this.p1NumCardsReserved = stateToCopy.getP1NumCardsReserved();

        //deep copy of player 2 points and reserve cards
        this.p2GoldCoins = stateToCopy.getP2GoldCoins();
        this.p2GoldPts = stateToCopy.getP2GoldPts();
        this.p2EmeraldCoins = stateToCopy.getP2EmeraldCoins();
        this.p2EmeraldPts = stateToCopy.getP2EmeraldPts();
        this.p2SapphireCoins = stateToCopy.getP2SapphireCoins();
        this.p2SapphirePts = stateToCopy.getP2SapphirePts();
        this.p2RubyCoins = stateToCopy.getP2RubyCoins();
        this.p2RubyPts = stateToCopy.getP2RubyPts();
        this.p2OnyxCoins = stateToCopy.getP2OnyxCoins();
        this.p2OnyxPts = stateToCopy.getP2OnyxPts();
        this.p2DiamondPts = stateToCopy.getP2DiamondPts();
        this.p2DiamondCoins = stateToCopy.getP2DiamondCoins();
        this.p2PrestigePts = stateToCopy.getP2OnyxPts();
        this.p2NumCardsReserved = stateToCopy.getP2NumCardsReserved();

        //deep copy of player 3 points and reserve cards
        this.p3GoldCoins = stateToCopy.getP3GoldCoins();
        this.p3GoldPts = stateToCopy.getP3GoldPts();
        this.p3EmeraldCoins = stateToCopy.getP3EmeraldCoins();
        this.p3EmeraldPts = stateToCopy.getP3EmeraldPts();
        this.p3SapphireCoins = stateToCopy.getP3SapphireCoins();
        this.p3SapphirePts = stateToCopy.getP3SapphirePts();
        this.p3RubyCoins = stateToCopy.getP3RubyCoins();
        this.p3RubyPts = stateToCopy.getP3RubyPts();
        this.p3OnyxCoins = stateToCopy.getP3OnyxCoins();
        this.p3OnyxPts = stateToCopy.getP3OnyxPts();
        this.p3DiamondPts = stateToCopy.getP3DiamondPts();
        this.p3DiamondCoins = stateToCopy.getP3DiamondCoins();
        this.p3PrestigePts = stateToCopy.getP3OnyxPts();
        this.p3NumCardsReserved = stateToCopy.getP3NumCardsReserved();

        //deep copy of player 4 points and reserve cards
        this.p4GoldCoins = stateToCopy.getP4GoldCoins();
        this.p4GoldPts = stateToCopy.getP4GoldPts();
        this.p4EmeraldCoins = stateToCopy.getP4EmeraldCoins();
        this.p4EmeraldPts = stateToCopy.getP4EmeraldPts();
        this.p4SapphireCoins = stateToCopy.getP4SapphireCoins();
        this.p4SapphirePts = stateToCopy.getP4SapphirePts();
        this.p4RubyCoins = stateToCopy.getP4RubyCoins();
        this.p4RubyPts = stateToCopy.getP4RubyPts();
        this.p4OnyxCoins = stateToCopy.getP4OnyxCoins();
        this.p4OnyxPts = stateToCopy.getP4OnyxPts();
        this.p4DiamondPts = stateToCopy.getP4DiamondPts();
        this.p4DiamondCoins = stateToCopy.getP4DiamondCoins();
        this.p4PrestigePts = stateToCopy.getP4OnyxPts();
        this.p4NumCardsReserved = stateToCopy.getP4NumCardsReserved();

        //deep copies for all 3 card stacks
        this.rank1Stack = new ArrayList<>();
        for (Card rankCard : stateToCopy.rank1Stack) {
            this.rank1Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        this.rank2Stack = new ArrayList<>();
        for (Card rankCard : stateToCopy.rank2Stack) {
            this.rank2Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        this.rank3Stack = new ArrayList<>();
        for (Card rankCard : stateToCopy.rank3Stack) {
            this.rank3Stack.add(new Card(rankCard)); //uses copy constructor in card
        }

        this.noble1 = stateToCopy.getNoble1();
        this.noble2 = stateToCopy.getNoble2();
        this.noble3 = stateToCopy.getNoble3();
        this.noble4 = stateToCopy.getNoble4();
    }

    //helper method for constructor setting all point values for player to zero
    public void initializePlayerPointValues() {
        this.playerTurn = 1;
        //player one
        this.p1GoldCoins = 0;
        this.p1GoldPts = 0;
        this.p1EmeraldCoins = 0;
        this.p1EmeraldPts = 4;
        this.p1SapphireCoins = 0;
        this.p1SapphirePts = 4;
        this.p1RubyCoins = 0;
        this.p1RubyPts = 4;
        this.p1OnyxCoins = 0;
        this.p1OnyxPts = 4;
        this.p1DiamondCoins = 0;
        this.p1DiamondPts = 4;
        this.p1PrestigePts = 0;
        this.p1NumCardsReserved = 0;

        this.p2GoldCoins = 0;
        this.p2GoldPts = 0;
        this.p2EmeraldCoins = 0;
        this.p2EmeraldPts = 4;
        this.p2SapphireCoins = 0;
        this.p2SapphirePts = 4;
        this.p2RubyCoins = 4;
        this.p2RubyPts = 4;
        this.p2OnyxCoins = 0;
        this.p2OnyxPts = 4;
        this.p2DiamondCoins = 0;
        this.p2DiamondPts = 4;
        this.p2PrestigePts = 0;
        this.p2NumCardsReserved = 0;

        this.p3GoldCoins = 0;
        this.p3GoldPts = 0;
        this.p3EmeraldCoins = 0;
        this.p3EmeraldPts = 4;
        this.p3SapphireCoins = 0;
        this.p3SapphirePts = 4;
        this.p3RubyCoins = 0;
        this.p3RubyPts = 4;
        this.p3OnyxCoins = 0;
        this.p3OnyxPts = 4;
        this.p3DiamondCoins = 0;
        this.p3DiamondPts = 4;
        this.p3PrestigePts = 0;
        this.p3NumCardsReserved = 0;

        this.p4GoldCoins = 0;
        this.p4GoldPts = 0;
        this.p4EmeraldCoins = 0;
        this.p4EmeraldPts = 4;
        this.p4SapphireCoins = 0;
        this.p4SapphirePts = 4;
        this.p4RubyCoins = 0;
        this.p4RubyPts = 4;
        this.p4OnyxCoins = 0;
        this.p4OnyxPts = 4;
        this.p4DiamondCoins = 0;
        this.p4DiamondPts = 4;
        this.p4PrestigePts = 0;
        this.p4NumCardsReserved = 0;
    }

    public void initializeBoard(ArrayList<Card> rank1, ArrayList<Card> rank2, ArrayList<Card> rank3){
        this.board[2][0] = rank1.remove(0);
        this.board[2][1] = rank1.remove(0);
        this.board[2][2] = rank1.remove(0);
        this.board[2][3] = rank1.remove(0);
        this.board[1][0] = rank2.remove(0);
        this.board[1][1] = rank2.remove(0);
        this.board[1][2] = rank2.remove(0);
        this.board[1][3] = rank2.remove(0);
        this.board[0][0] = rank3.remove(0);
        this.board[0][1] = rank3.remove(0);
        this.board[0][2] = rank3.remove(0);
        this.board[0][3] = rank3.remove(0);
    }

    /*initialize Decks
     * reads input from text files into three array lists then shuffles deck
     *
     */
    public void initializeDecks(/*InputStream rank1, InputStream rank2, InputStream rank3, InputStream nobles*/) {

        String rank1File = "res/raw/rank1.csv";
        InputStream rank1 = this.getClass().getClassLoader().getResourceAsStream(rank1File);
        String rank2File = "res/raw/rank2.csv";
        InputStream rank2 = this.getClass().getClassLoader().getResourceAsStream(rank2File);
        String rank3File = "res/raw/rank3.csv";
        InputStream rank3 = this.getClass().getClassLoader().getResourceAsStream(rank3File);
        String nobleFile = "res/raw/nobles.csv";
        InputStream nobles = this.getClass().getClassLoader().getResourceAsStream(nobleFile);

        this.rank1Stack = new ArrayList<Card>();
        this.rank2Stack = new ArrayList<Card>();
        this.rank3Stack = new ArrayList<Card>();
        this.nobleStack = new ArrayList<Noble>();

        //reading data for rank 1
        BufferedReader rank1Reader = new BufferedReader(
                new InputStreamReader(rank1, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while((line = rank1Reader.readLine()) != null) {
                //split by ,
                String[] tokens = line.split(",");
                Card card = new Card();
                card.setColorGem(Integer.parseInt(tokens[0]));
                card.setPrestigePoints(Integer.parseInt(tokens[1]));
                card.setwPrice(Integer.parseInt(tokens[2]));
                card.setbPrice(Integer.parseInt(tokens[3]));
                card.setgPrice(Integer.parseInt(tokens[4]));
                card.setrPrice(Integer.parseInt(tokens[5]));
                card.setBrPrice(Integer.parseInt(tokens[6]));
                card.setCardLevel(1);
                this.rank1Stack.add(card);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file " + line, e);
        }

        //reading data for rank 2
        BufferedReader rank2Reader = new BufferedReader(
                new InputStreamReader(rank2, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while((line = rank2Reader.readLine()) != null) {
                //split by ,
                String[] tokens = line.split(",");
                Card card = new Card();
                card.setPrestigePoints(Integer.parseInt(tokens[0]));
                card.setColorGem(Integer.parseInt(tokens[1]));
                card.setwPrice(Integer.parseInt(tokens[2]));
                card.setbPrice(Integer.parseInt(tokens[3]));
                card.setgPrice(Integer.parseInt(tokens[4]));
                card.setrPrice(Integer.parseInt(tokens[5]));
                card.setBrPrice(Integer.parseInt(tokens[6]));
                card.setCardLevel(2);
                this.rank2Stack.add(card);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file " + line, e);
        }

        //reading data for rank 3
        BufferedReader rank3Reader = new BufferedReader(
                new InputStreamReader(rank3, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while((line = rank3Reader.readLine()) != null) {
                //split by ,
                String[] tokens = line.split(",");
                Card card = new Card();
                card.setPrestigePoints(Integer.parseInt(tokens[0]));
                card.setColorGem(Integer.parseInt(tokens[1]));
                card.setwPrice(Integer.parseInt(tokens[2]));
                card.setbPrice(Integer.parseInt(tokens[3]));
                card.setgPrice(Integer.parseInt(tokens[4]));
                card.setrPrice(Integer.parseInt(tokens[5]));
                card.setBrPrice(Integer.parseInt(tokens[6]));
                card.setCardLevel(3);
                this.rank3Stack.add(card);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file " + line, e);
        }
        //reading data for rank 3
        BufferedReader nobleReader = new BufferedReader(
                new InputStreamReader(nobles, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while((line = nobleReader.readLine()) != null) {
                //split by ,
                String[] tokens = line.split(",");
                Noble noble = new Noble();
                noble.setwPrice(Integer.parseInt(tokens[0]));
                noble.setbPrice(Integer.parseInt(tokens[1]));
                noble.setgPrice(Integer.parseInt(tokens[2]));
                noble.setrPrice(Integer.parseInt(tokens[3]));
                noble.setBrPrice(Integer.parseInt(tokens[4]));
                this.nobleStack.add(noble);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file " + line, e);
        }
    }

    public void initializeHands() {
        this.p1Hand = new Hand();
        this.p2Hand = new Hand();
        this.p3Hand = new Hand();
        this.p4Hand = new Hand();
    }

    public void initializeCoins() {
        this.rubyCoins = 7;
        this.sapphireCoins = 7;
        this.emeraldCoins = 7;
        this.diamondCoins = 7;
        this.onyxCoins = 7;
        this.goldCoins = 5;
    }
    //this will eventually initialize 4 random nobles from a set of 10, for now we have chosen 4
    public void initializeNobles(){
        this.noble1 = new Noble(4,0,4,0,0);
        this.noble2 = new Noble(3,0,0,3,3);
        this.noble3 = new Noble(4,0,0,0,4);
        this.noble4 = new Noble(0,3,3,3,0);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~toString~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    //Returns a String to be printed by TextView
    /* Includes :
    p1,p2,p3,p4  ARRAY: reserve cards
    nobles 1-4
    Board: rank 1-3
    All Cards
    p1-4 hands  ARRAY
    coins in bank

    * */
    @Override
    public String toString(){
        //p refers to player, n refers to noble, TS refers to ToString
        String p1, p2, p3, p4, playerTS, n1, n2, n3, n4, nobleTS, coinToString, returnString, newGame, GameInfoTS, allCardsTS;
        String p1_Reserve = "";
        String p2_Reserve = "";
        String p3_Reserve = "";
        String p4_Reserve = "";
        String p1HandTS = "";
        String p2HandTS = "";
        String p3HandTS = "";
        String p4HandTS = "";
        String rank1Cards = "";
        String rank2Cards = "";
        String rank3Cards = "";
        String currBoardTS = "";

        newGame = "\n~~~~~~~~~~~~~~~New Game Instance~~~~~~~~~~~~~~~";

        p1 = "\n\nPlayer 1 name: " + player1Name +
                "\nPlayer 1 turn ID: " + PLAYER1ID +
                "\nPlayer 1 Prestige Points: " + p1PrestigePts +
                "\nPlayer 1 Resource Point values: " +
                "\nGold: "+ p1GoldPts +
                "\nEmerald: " + p1EmeraldPts +
                "\nSapphire: " + p1SapphirePts +
                "\nRuby: " + p1RubyPts +
                "\nDiamond: " + p1DiamondPts +
                "\nOnyx: " + p1OnyxPts +
                "\nPlayer 1 Coin Values: " +
                "\nGold: "+ p1GoldCoins +
                "\nEmerald: " + p1EmeraldCoins +
                "\nSapphire: " + p1SapphireCoins +
                "\nRuby: " + p1RubyCoins +
                "\nDiamond: " + p1DiamondCoins +
                "\nOnyx: " + p1OnyxCoins +
                "\nPlayer 1 number of Cards reserved: " + p1NumCardsReserved+
                "\nPlayer 1 Cards in reserve: ";

        //loop through reserve card array, append to string
        for (Card card: p1Hand.getReserved() ) {
            p1_Reserve = p1_Reserve + card.toString();
        }
        //null message
        if(p1_Reserve ==null){p1_Reserve = "\nNo cards in reserve";}
        //add reserve to player for clarity
        p1 = p1 + p1_Reserve;

        p2 = "\n\nPlayer 2 name: " + player2Name +
                "\nPlayer 2 turn ID: " + PLAYER2ID +
                "\nPlayer 2 Prestige Points: " + p2PrestigePts +
                "\nPlayer 2 Resource Point values: " +
                "\nGold: "+ p2GoldPts +
                "\nEmerald: " + p2EmeraldPts +
                "\nSapphire: " + p2SapphirePts +
                "\nRuby: " + p2RubyPts +
                "\nDiamond: " + p2DiamondPts +
                "\nOnyx: " + p2OnyxPts +
                "\nPlayer 2 Coin Values: " +
                "\nGold: "+ p2GoldCoins +
                "\nEmerald: " + p2EmeraldCoins +
                "\nSapphire: " + p2SapphireCoins +
                "\nRuby: " + p2RubyCoins +
                "\nDiamond: " + p2DiamondCoins +
                "\nOnyx: " + p2OnyxCoins +
                "\nPlayer 2 number of Cards reserved: " + p2NumCardsReserved+
                "\nPlayer 2 Cards in reserve: ";

        //loop through reserve card array, append to string
        for (Card card: p2Hand.getReserved() ) {
            p2_Reserve = p2_Reserve + card.toString();
        }
        //null message
        if(p2_Reserve ==null){p2_Reserve = "\nNo cards in reserve";}
        //add reserve to player for clarity
        p2 = p2 + p2_Reserve;

        p3 = "\n\nPlayer 3 name: " + player3Name +
                "\nPlayer 3 turn ID: " + PLAYER3ID +
                "\nPlayer 3 Prestige Points: " + p3PrestigePts +
                "\nPlayer 3 Resource Point values: " +
                "\nGold: "+ p3GoldPts +
                "\nEmerald: " + p3EmeraldPts +
                "\nSapphire: " + p3SapphirePts +
                "\nRuby: " + p3RubyPts +
                "\nDiamond: " + p3DiamondPts +
                "\nOnyx: " + p3OnyxPts +
                "\nPlayer 3 Coin Values: " +
                "\nGold: "+ p3GoldCoins +
                "\nEmerald: " + p3EmeraldCoins +
                "\nSapphire: " + p3SapphireCoins +
                "\nRuby: " + p3RubyCoins +
                "\nDiamond: " + p3DiamondCoins +
                "\nOnyx: " + p3OnyxCoins +
                "\nPlayer 3 number of Cards reserved: " + p3NumCardsReserved+
                "\nPlayer 3 Cards in reserve: ";;

        //loop through reserve card array, append to string
        for (Card card: p3Hand.getReserved() ) {
            p3_Reserve = p3_Reserve + card.toString();
        }
        //null message
        if(p3_Reserve ==null){p3_Reserve = "\nNo cards in reserve";}
        //add reserve to player for clarity
        p3 = p3 + p3_Reserve;


        p4 = "\n\nPlayer 4 name: " + player4Name +
                "\nPlayer 4 turn ID: " + PLAYER4ID +
                "\nPlayer 4 Prestige Points: " + p4PrestigePts +
                "\nPlayer 4 Resource Point values: " +
                "\nGold: "+ p4GoldPts +
                "\nEmerald: " + p4EmeraldPts +
                "\nSapphire: " + p4SapphirePts +
                "\nRuby: " + p4RubyPts +
                "\nDiamond: " + p4DiamondPts +
                "\nOnyx: " + p4OnyxPts +
                "\nPlayer 4 Coin Values: " +
                "\nGold: "+ p4GoldCoins +
                "\nEmerald: " + p4EmeraldCoins +
                "\nSapphire: " + p4SapphireCoins +
                "\nRuby: " + p4RubyCoins +
                "\nDiamond: " + p4DiamondCoins +
                "\nOnyx: " + p4OnyxCoins +
                "\nPlayer 4 number of Cards reserved: " + p4NumCardsReserved+
                "\nPlayer 4 Cards in reserve: ";;

        //loop through reserve card array, append to string
        for (Card card: p4Hand.getReserved() ) {
            p4_Reserve = p4_Reserve + card.toString();
        }
        //null message
        if(p4_Reserve ==null){p4_Reserve = "\nNo cards in reserve";}
        //add reserve to player for clarity
        p4 = p4 + p4_Reserve;

        //add players together for clarity
        playerTS = p1 + p2 + p3 + p4;

        n1 = "\n\nNoble 1: " + noble1.toString();
        n2 = "\n\nNoble 2: " + noble2.toString();
        n3 = "\n\nNoble 2: " + noble3.toString();
        n4 = "\n\nNoble 2: " + noble4.toString();

        //add nobles together for clarity
        nobleTS = "\n\n---The Nobles this Game---" + n1 + n2 + n3 + n4;

        coinToString = "\n\n---Coins in the Bank--- " +
                "\nGold: "+ goldCoins +
                "\nEmerald: " + emeraldCoins +
                "\nSapphire: " + sapphireCoins +
                "\nRuby: " + rubyCoins +
                "\nDiamond: " + diamondCoins +
                "\nOnyx: " + onyxCoins + "\n";


        for (Card rank1: rank1Stack ) {
            rank1Cards = rank1Cards + rank1.toString();
        }
        for (Card rank2: rank2Stack ) {
            rank2Cards = rank2Cards + rank2.toString();
        }
        for (Card rank3: rank3Stack ) {
            rank3Cards = rank3Cards + rank3.toString();
        }
        allCardsTS = "\nRank 1 cards: " + rank1Cards + "\nRank 2 cards: " + rank2Cards + "\nRank 3 cards: " + rank3Cards;

        //prints boards
        for(int i = 0; i < RANKS; i++){
            for(int j = 0; j < CARDS_PER_RANK; j++){
                currBoardTS = currBoardTS + board[i][j].toString();
            }

        }
        //get hands for each player
        for (Card hand: p1Hand.getHand() ) {
            p1HandTS = p1HandTS + hand.toString();
        }
        for (Card hand: p2Hand.getHand() ) {
            p2HandTS = p2HandTS + hand.toString();
        }
        for (Card hand: p3Hand.getHand() ) {
            p3HandTS = p3HandTS + hand.toString();
        }
        for (Card hand: p4Hand.getHand() ) {
            p4HandTS = p4HandTS + hand.toString();
        }

        GameInfoTS = "\nInfo about the Game: " +
                "\nPlayer turn: " + playerTurn +
                "\n\n----Player hands----" +
                "\nPlayer 1 hand: " + p1HandTS +
                "\nPlayer 2 hand: " + p2HandTS +
                "\nPlayer 3 hand: " + p3HandTS +
                "\nPlayer 4 hand: " + p4HandTS +
                "\n----The current board----\n" + currBoardTS +
                "\n\n------All Cards------" + allCardsTS;



        returnString = newGame + playerTS + nobleTS + coinToString + GameInfoTS;

        return returnString;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~actions for #d~~~~~~~~~~~~~~~~~~~*/

    //TODO: Move actions and helpers?F


    private void nextPlayerTurn() {
        if(getPlayerTurn() == 4) {
            setPlayerTurn(1);
        } else setPlayerTurn(getPlayerTurn()+1);
    }

    public void setPlayerTurn(int playerID) {
        this.playerTurn = playerID;
    }


    public boolean coinAction(int coinColor1, int coinColor2, int coinColor3) {
        if(coinCheck(coinColor1, coinColor2, coinColor3)) {
            individualCoinAction(coinColor1);
            individualCoinAction(coinColor2);
            individualCoinAction(coinColor3);
            nextPlayerTurn();
            return true;
        }
        return false;
    }

    public boolean coinAction(int coinColor)
    {
        if (coinCheckDoubles(coinColor)){
            individualCoinAction(coinColor);
            individualCoinAction(coinColor);
            nextPlayerTurn();
            return true;
        }
        return false;
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

    /*~~~~~~~~~~~~~~~~~~~~~helper methods~~~~~~~~~~~~~~~~~~~*/


    private boolean coinCheck(int coinColor, int coinColor2, int coinColor3) { //checks if current player can

        boolean flag1 = coinPileCheck()[coinColor];
        boolean flag2 = coinPileCheck()[coinColor2];
        boolean flag3 = coinPileCheck()[coinColor3];

        switch (this.getPlayerTurn()) {
            case 1:
                if(p1coinCountBool() && flag1 && flag2 && flag3) {
                    return true;
                }
                break;
            case 2:
                if(p2coinCountBool() && flag1 && flag2 && flag3) {
                    return true;
                }
                break;
            case 3:
                if(p3coinCountBool() && flag1 && flag2 && flag3) {
                    return true;
                }
                break;
            case 4:
                if(p4coinCountBool() && flag1 && flag2 && flag3) {
                    return true;
                }
                break;
        }
        return false;
    }

    private boolean coinCheckDoubles(int coinColor) // checks if current player can take 2 coins of same color
    {
        boolean flag = coinPileCheckDoubles()[coinColor];
        switch (this.getPlayerTurn()){
            case 1:
                if(p1coinCountBool() && flag) return true;
                break;
            case 2:
                if(p2coinCountBool() && flag) return true;
                break;
            case 3:
                if(p3coinCountBool() && flag) return true;
                break;
            case 4:
                if(p4coinCountBool() && flag) return true;
                break;
        }
        return false;
    }

    private boolean p1coinCountBool() {
        if(this.p1DiamondCoins+this.p1EmeraldCoins+this.p1OnyxCoins+this.p1RubyCoins+this.p1SapphireCoins+this.p1GoldCoins >= 10)
            return false;
        return true;
    }
    private boolean p2coinCountBool() {
        if(this.p2DiamondCoins+this.p2EmeraldCoins+this.p2OnyxCoins+this.p2RubyCoins+this.p2SapphireCoins+this.p2GoldCoins >= 10)
            return false;
        return true;
    }

    private boolean p3coinCountBool() {
        if(this.p3DiamondCoins+this.p3EmeraldCoins+this.p3OnyxCoins+this.p3RubyCoins+this.p3SapphireCoins+this.p3GoldCoins >= 10)
            return false;
        return true;
    }

    private boolean p4coinCountBool() {
        if(this.p4DiamondCoins+this.p4EmeraldCoins+this.p4OnyxCoins+this.p4RubyCoins+this.p4SapphireCoins+this.p4GoldCoins >= 10)
            return false;
        return true;
    }

    private boolean[] coinPileCheckDoubles() {
        boolean[] coinPilesDoubles = {(this.rubyCoins >=4), (this.sapphireCoins >= 4), this.emeraldCoins >= 4, this.diamondCoins >= 4, this.onyxCoins >= 4};
        return coinPilesDoubles;
    }

    private boolean[] coinPileCheck() {
        boolean[] coinPiles = {(this.rubyCoins > 0), (this.sapphireCoins > 0), this.emeraldCoins > 0, this.diamondCoins > 0, this.onyxCoins > 0};
        return coinPiles;
    }

    private void individualCoinAction(int coinColor) {
        switch(coinColor) {
            case 0:
                this.rubyCoins--;
                switch(this.getPlayerTurn()) {
                    case 1:
                        this.p1RubyCoins++;
                        break;
                    case 2:
                        this.p2RubyCoins++;
                        break;
                    case 3:
                        this.p3RubyCoins++;
                        break;
                    case 4:
                        this.p4RubyCoins++;
                        break;
                }
                break;
            case 1:
                this.sapphireCoins--;
                switch(this.getPlayerTurn()) {
                    case 1:
                        this.p1SapphireCoins++;
                        break;
                    case 2:
                        this.p2SapphireCoins++;
                        break;
                    case 3:
                        this.p3SapphireCoins++;
                        break;
                    case 4:
                        this.p4SapphireCoins++;
                        break;
                }
                break;
            case 2:
                this.emeraldCoins--;
                switch(this.getPlayerTurn()) {
                    case 1:
                        this.p1EmeraldCoins++;
                        break;
                    case 2:
                        this.p2EmeraldCoins++;
                        break;
                    case 3:
                        this.p3EmeraldCoins++;
                        break;
                    case 4:
                        this.p4EmeraldCoins++;
                        break;
                }
                break;
            case 3:
                this.diamondCoins--;
                switch(this.getPlayerTurn()) {
                    case 1:
                        this.p1DiamondCoins++;
                        break;
                    case 2:
                        this.p2DiamondCoins++;
                        break;
                    case 3:
                        this.p3DiamondCoins++;
                        break;
                    case 4:
                        this.p4DiamondCoins++;
                        break;
                }
                break;
            case 4:
                this.onyxCoins--;
                switch(this.getPlayerTurn()) {
                    case 1:
                        this.p1OnyxCoins++;
                        break;
                    case 2:
                        this.p2OnyxCoins++;
                        break;
                    case 3:
                        this.p3OnyxCoins++;
                        break;
                    case 4:
                        this.p4OnyxCoins++;
                        break;
                }
                break;
        }
    }

    //Getter methods

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

    public Card getBoard(int row, int col){
        return this.board[row][col];
    }

    public Card getSelected() {return this.selected;}

    public void setSelected(Card card){this.selected = card;}
}

