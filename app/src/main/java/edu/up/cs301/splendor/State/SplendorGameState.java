/*
@authors Ryan Brown, Noah LaFave, Jacob Noble, Aidan Day
@version Alpha

 */

package edu.up.cs301.splendor.State;
import edu.up.cs301.splendor.Game.Card;
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

    //holds turn value corresponding to player ID below
    private int playerTurn;

    //player values for playerTurn
    private final int PLAYER1ID = 0;
    private final int PLAYER2ID = 1;
    private final int PLAYER3ID = 2;
    private final int PLAYER4ID = 3;

    //create list of all players
    public ArrayList<SplendorPlayer> playerList = new ArrayList<>();

//~~~~~~~~~~~~~~~~ players ~~~~~~~~~~~~~~~~~~~ //

    //SplendorPlayer objects contain all player point values and cards
    private SplendorPlayer splendorPlayer1;
    private SplendorPlayer splendorPlayer2;
    private SplendorPlayer splendorPlayer3;
    private SplendorPlayer splendorPlayer4;

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

    //coin instance variables for stacks
    private int rubyCoins;
    private int sapphireCoins;
    private int emeraldCoins;
    private int diamondCoins;
    private int onyxCoins;
    private int goldCoins;


//~~~~~~~~~~~~~~~~~~~~~ Game State Specific Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //game board of active cards that players can select
    private Card board[][] = new Card[RANKS][CARDS_PER_RANK];

    //nobles displayed on board
    private Noble nobles[];

    //this holds the last card that the user has selected
    private Card selected;
    private ArrayList<Integer> coinTracking = new ArrayList<>();
    private int selectedRow;
    private int selectedCol;

//~~~~~~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public SplendorGameState() {
        //initialize play
        initializePlayers();
        initializeCoins();
        initializeSelected();
        //create deck and shuffle them
        initializeNobles();
        initializeDecks();
        Collections.shuffle(this.rank1Stack);
        Collections.shuffle(this.rank2Stack);
        Collections.shuffle(this.rank3Stack);
        //build board and set default selected
        initializeBoard(this.rank1Stack, this.rank2Stack, this.rank3Stack);
        this.selected = board[2][0];
    }

    /**
     * initializeSelected - initializes all values for selected variables
     */
    public void initializeSelected() {
        this.selected = new Card();
        this.selectedRow = -1;
        this.selectedCol = -1;
    }

    /*
     *
     * Deep copy constructor
     *
     */
    public SplendorGameState(SplendorGameState stateToCopy) {

        //runs through board deep copy cards onto new instances board
        for(int row = 0; row < 3; row++) //
        {
            for(int col = 0; col < 4; col++)
            {
                Card card = new Card(stateToCopy.getBoard(row,col));
                this.board[row][col] = card;
            }
        }

        //makes copy of card that is currently selected if it exists
        if(stateToCopy.getSelected() != null) {
            this.selected = new Card(stateToCopy.getSelected());
        }
        this.playerTurn = stateToCopy.playerTurn;
        this.splendorPlayer1 = new SplendorPlayer(stateToCopy.splendorPlayer1);
        this.splendorPlayer2 = new SplendorPlayer(stateToCopy.splendorPlayer2);
        this.splendorPlayer3 = new SplendorPlayer(stateToCopy.splendorPlayer3);
        this.splendorPlayer4 = new SplendorPlayer(stateToCopy.splendorPlayer4);

        this.playerTurn = stateToCopy.getPlayerTurn();

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

        //deep copy for noble cards
        this.noble1 = new Noble(stateToCopy.getNoble1());
        this.noble2 = new Noble(stateToCopy.getNoble2());
        this.noble3 = new Noble(stateToCopy.getNoble3());
        this.noble4 = new Noble(stateToCopy.getNoble4());

        //copy of which coin is selected
        this.coinTracking = new ArrayList<>();
        for (int coin : stateToCopy.coinTracking) {
            this.coinTracking.add(coin); //uses copy constructor in card
        }

        this.selectedRow = stateToCopy.getSelectedRow();
        this.selectedCol = stateToCopy.getSelectedCol();

        this.rubyCoins = stateToCopy.rubyCoins;
        this.sapphireCoins = stateToCopy.sapphireCoins;
        this.emeraldCoins = stateToCopy.emeraldCoins;
        this.diamondCoins = stateToCopy.diamondCoins;
        this.onyxCoins = stateToCopy.onyxCoins;
        this.goldCoins = stateToCopy.goldCoins;
    }

    //helper method for constructor setting all point values for player to zero
    public void initializePlayers() {
        //set turn to be first
        this.playerTurn = PLAYER1ID;

        this.splendorPlayer1 = new SplendorPlayer(PLAYER1ID);
        this.splendorPlayer2 = new SplendorPlayer(PLAYER2ID);
        this.splendorPlayer3 = new SplendorPlayer(PLAYER3ID);
        this.splendorPlayer4 = new SplendorPlayer(PLAYER4ID);

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

    public void initializeDecks() {

        String rank1File = "res/raw/rank1.csv";
        InputStream rank1 =
                this.getClass().getClassLoader().getResourceAsStream(rank1File);
        String rank2File = "res/raw/rank2.csv";
        InputStream rank2 =
                this.getClass().getClassLoader().getResourceAsStream(rank2File);
        String rank3File = "res/raw/rank3.csv";
        InputStream rank3 =
                this.getClass().getClassLoader().getResourceAsStream(rank3File);
        String nobleFile = "res/raw/noble";
        InputStream nobles =
                this.getClass().getClassLoader().getResourceAsStream(nobleFile);

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
                card.setColorGem(Integer.parseInt(tokens[0]));
                card.setPrestigePoints(Integer.parseInt(tokens[1]));
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
                card.setColorGem(Integer.parseInt(tokens[0]));
                card.setPrestigePoints(Integer.parseInt(tokens[1]));
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

    public void initializeCoins() {
        this.rubyCoins = 70;
        this.sapphireCoins = 70;
        this.emeraldCoins = 70;
        this.diamondCoins = 70;
        this.onyxCoins = 70;
        this.goldCoins = 5;
    }
    //this will eventually initialize 4 random nobles from a set of 10, for now we have chosen 4
    public void initializeNobles(){
        this.noble1 = new Noble(4,0,4,0,0);
        this.noble2 = new Noble(3,0,0,3,3);
        this.noble3 = new Noble(4,0,0,0,4);
        this.noble4 = new Noble(0,3,3,3,0);
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~action methods~~~~~~~~~~~~~~~~~~~*/


    private void nextPlayerTurn() {
        setPlayerTurn((getPlayerTurn()+1) % 4);
    }

    //sets turn to exact value
    public void setPlayerTurn(int playerID) {
        this.playerTurn = playerID;
    }

    /**
     *
     * @param coinColor1 - color of first coin to be taken
     * @param coinColor2 - color of second coin to be taken
     * @param coinColor3 - color of third coin to be taken
     * @return - if action was carried out
     */
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

    //player chooses two of one type to purchase
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

    //adds selected card into the reserve card slot and gives player a gold coin
    public boolean reserveAction(Card cardToReserve) {
        switch(this.getPlayerTurn()){
            case 0:
                if (this.splendorPlayer1.getPlayerHand().canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0)
                        this.splendorPlayer1.setGoldCoins(this.splendorPlayer1.getGoldCoins()+1);
                    this.goldCoins--;
                    this.splendorPlayer1.getPlayerHand().addToReserved(cardToReserve);
                }
                break;
            case 1:
                if (!this.splendorPlayer2.getPlayerHand().canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0)
                        this.splendorPlayer2.setGoldCoins(this.splendorPlayer2.getGoldCoins()+1);
                    this.goldCoins--;
                    this.splendorPlayer2.getPlayerHand().addToReserved(cardToReserve);
                }
                break;
            case 2:
                if (!this.splendorPlayer3.getPlayerHand().canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0)
                        this.splendorPlayer3.setGoldCoins(this.splendorPlayer3.getGoldCoins()+1);
                    this.goldCoins--;
                    this.splendorPlayer3.getPlayerHand().addToReserved(cardToReserve);
                }
                break;
            case 3:
                if (!this.splendorPlayer4.getPlayerHand().canReserve()) {
                    return false;
                }
                else {
                    if (this.goldCoins > 0)
                        this.splendorPlayer4.setGoldCoins(this.splendorPlayer4.getGoldCoins()+1);
                    this.goldCoins--;
                    this.splendorPlayer4.getPlayerHand().addToReserved(cardToReserve);
                }
                break;
        }
        this.nextPlayerTurn();
        return true;
    }

    /**
     * cardAction - if player has sufficient coins, the cards and resource points are given to the player
     *            - coins are placed back into coin bank
     *
     * @param cardToBuy - card on the board to be bought
     * @param row - row of card on board
     * @param col - column of card on board
     */
    public boolean cardAction(Card cardToBuy, int row, int col) {
        switch(this.getPlayerTurn()){
            case 0:
                if (cardToBuy.getrPrice() <= splendorPlayer1.getRubyCoins()+splendorPlayer1.getRubyPts() &&
                        cardToBuy.getbPrice() <= splendorPlayer1.getSapphCoins()+splendorPlayer1.getSapphPts() &&
                        cardToBuy.getBrPrice() <= splendorPlayer1.getOnyxCoins()+splendorPlayer1.getOnyxPts() &&
                        cardToBuy.getwPrice() <= splendorPlayer1.getDiaCoins()+splendorPlayer1.getDiaCoins() &&
                        cardToBuy.getgPrice() <= splendorPlayer1.getEmerCoins()+splendorPlayer1.getEmerPts())
                {
                    if(cardToBuy.getrPrice()-splendorPlayer1.getRubyPts() >= 0) {
                        splendorPlayer1.setRubyCoins(splendorPlayer1.getRubyCoins() -
                                (cardToBuy.getrPrice() - splendorPlayer1.getRubyPts()));
                        this.rubyCoins += (cardToBuy.getrPrice() - splendorPlayer1.getRubyPts());
                    }
                    if(cardToBuy.getbPrice()-splendorPlayer1.getSapphPts() >= 0) {
                        splendorPlayer1.setSapphCoins(splendorPlayer1.getSapphCoins() -
                                (cardToBuy.getbPrice() - splendorPlayer1.getSapphPts()));
                        this.sapphireCoins += (cardToBuy.getbPrice() - splendorPlayer1.getSapphPts());
                    }
                    if(cardToBuy.getBrPrice()-splendorPlayer1.getEmerPts() >= 0) {
                        splendorPlayer1.setOnyxCoins(splendorPlayer1.getOnyxCoins() -
                                (cardToBuy.getBrPrice() - splendorPlayer1.getOnyxPts()));
                        this.onyxCoins += (cardToBuy.getBrPrice() - splendorPlayer1.getOnyxPts());
                    }
                    if(cardToBuy.getwPrice()-splendorPlayer1.getDiaPts() >= 0) {
                        splendorPlayer1.setDiaCoins(splendorPlayer1.getDiaCoins() -
                                (cardToBuy.getwPrice() - splendorPlayer1.getDiaPts()));
                        this.diamondCoins += (cardToBuy.getwPrice() - splendorPlayer1.getDiaPts());
                    }
                    if(cardToBuy.getgPrice()-splendorPlayer1.getEmerPts() >= 0) {
                        splendorPlayer1.setEmerCoins(splendorPlayer1.getEmerCoins() -
                                (cardToBuy.getgPrice() - splendorPlayer1.getEmerPts()));
                        this.emeraldCoins += (cardToBuy.getgPrice() - splendorPlayer1.getEmerPts());
                    }
                    this.splendorPlayer1.getPlayerHand().addToHand(cardToBuy);
                    this.splendorPlayer1.setPrestigePts(splendorPlayer1.getPrestigePts()+cardToBuy.getPrestigePoints());
                    switch(row)
                    {
                        case 0:
                            this.board[row][col] = this.rank3Stack.remove(0);
                            break;
                        case 1:
                            this.board[row][col] = this.rank2Stack.remove(0);
                            break;
                        case 2:
                            this.board[row][col] = this.rank1Stack.remove(0);
                            break;
                    }
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 1:
                if (cardToBuy.getrPrice() <= splendorPlayer2.getRubyCoins()+splendorPlayer2.getRubyPts() &&
                        cardToBuy.getbPrice() <= splendorPlayer2.getSapphCoins()+splendorPlayer2.getSapphPts() &&
                        cardToBuy.getBrPrice() <= splendorPlayer2.getOnyxCoins()+splendorPlayer2.getOnyxPts() &&
                        cardToBuy.getwPrice() <= splendorPlayer2.getDiaCoins()+splendorPlayer2.getDiaCoins() &&
                        cardToBuy.getgPrice() <= splendorPlayer2.getEmerCoins()+splendorPlayer2.getEmerPts())
                {
                    if(cardToBuy.getrPrice()-splendorPlayer2.getRubyPts() >= 0) {
                        splendorPlayer2.setRubyCoins(splendorPlayer2.getRubyCoins() -
                                (cardToBuy.getrPrice() - splendorPlayer2.getRubyPts()));
                        this.rubyCoins += (cardToBuy.getrPrice() - splendorPlayer2.getRubyPts());
                    }
                    if(cardToBuy.getbPrice()-splendorPlayer2.getSapphPts() >= 0) {
                        splendorPlayer2.setSapphCoins(splendorPlayer2.getSapphCoins() -
                                (cardToBuy.getbPrice() - splendorPlayer2.getSapphPts()));
                        this.sapphireCoins += (cardToBuy.getbPrice() - splendorPlayer2.getSapphPts());
                    }
                    if(cardToBuy.getBrPrice()-splendorPlayer2.getOnyxPts() >= 0) {
                        splendorPlayer2.setOnyxCoins(splendorPlayer2.getOnyxCoins() -
                                (cardToBuy.getBrPrice() - splendorPlayer2.getOnyxPts()));
                        this.onyxCoins += (cardToBuy.getBrPrice() - splendorPlayer2.getOnyxPts());
                    }
                    if(cardToBuy.getwPrice()-splendorPlayer2.getDiaPts() >= 0) {
                        splendorPlayer2.setDiaCoins(splendorPlayer2.getDiaCoins() -
                                (cardToBuy.getwPrice() - splendorPlayer2.getDiaPts()));
                        this.diamondCoins += (cardToBuy.getwPrice() - splendorPlayer2.getDiaPts());
                    }
                    if(cardToBuy.getgPrice()-splendorPlayer2.getEmerPts() >= 0) {
                        splendorPlayer2.setEmerCoins(splendorPlayer2.getEmerCoins() -
                                (cardToBuy.getgPrice() - splendorPlayer2.getEmerPts()));
                        this.emeraldCoins += (cardToBuy.getgPrice() - splendorPlayer2.getEmerPts());
                    }
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.splendorPlayer2.getPlayerHand().addToHand(cardToBuy);
                    this.splendorPlayer2.setPrestigePts(splendorPlayer2.getPrestigePts()+cardToBuy.getPrestigePoints());
                    switch(row)
                    {
                        case 0:
                            this.board[row][col] = this.rank3Stack.remove(0);
                            break;
                        case 1:
                            this.board[row][col] = this.rank2Stack.remove(0);
                            break;
                        case 2:
                            this.board[row][col] = this.rank1Stack.remove(0);
                            break;
                    }
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 2:
                if (cardToBuy.getrPrice() <= splendorPlayer3.getRubyCoins()+splendorPlayer3.getRubyPts() &&
                        cardToBuy.getbPrice() <= splendorPlayer3.getSapphCoins()+splendorPlayer3.getSapphPts() &&
                        cardToBuy.getBrPrice() <= splendorPlayer3.getOnyxPts()+splendorPlayer3.getOnyxCoins() &&
                        cardToBuy.getwPrice() <= splendorPlayer3.getDiaCoins()+splendorPlayer3.getDiaCoins() &&
                        cardToBuy.getgPrice() <= splendorPlayer3.getEmerCoins()+splendorPlayer3.getEmerPts())
                {
                    if(cardToBuy.getrPrice()-splendorPlayer3.getRubyPts() >= 0) {
                        splendorPlayer3.setRubyCoins(splendorPlayer3.getRubyCoins() -
                                (cardToBuy.getrPrice() - splendorPlayer3.getRubyPts()));
                        this.rubyCoins += (cardToBuy.getrPrice() - splendorPlayer3.getRubyPts());
                    }
                    if(cardToBuy.getbPrice()-splendorPlayer3.getSapphPts() >= 0) {
                        splendorPlayer3.setSapphCoins(splendorPlayer3.getSapphCoins() -
                                (cardToBuy.getbPrice() - splendorPlayer3.getSapphPts()));
                        this.sapphireCoins += (cardToBuy.getbPrice() - splendorPlayer3.getSapphPts());
                    }
                    if(cardToBuy.getBrPrice()-splendorPlayer3.getOnyxPts() >= 0) {
                        splendorPlayer3.setOnyxCoins(splendorPlayer3.getOnyxCoins() -
                                (cardToBuy.getBrPrice() - splendorPlayer3.getOnyxPts()));
                        this.onyxCoins += (cardToBuy.getBrPrice() - splendorPlayer3.getOnyxPts());
                    }
                    if(cardToBuy.getwPrice()-splendorPlayer3.getDiaPts() >= 0) {
                        splendorPlayer3.setDiaCoins(splendorPlayer3.getDiaCoins() -
                                (cardToBuy.getwPrice() - splendorPlayer3.getDiaPts()));
                        this.diamondCoins += (cardToBuy.getwPrice() - splendorPlayer3.getDiaPts());
                    }
                    if(cardToBuy.getgPrice()-splendorPlayer3.getEmerPts() >= 0) {
                        splendorPlayer3.setEmerCoins(splendorPlayer3.getEmerCoins() -
                                (cardToBuy.getgPrice() - splendorPlayer3.getEmerPts()));
                        this.emeraldCoins += (cardToBuy.getgPrice() - splendorPlayer3.getEmerPts());
                    }
                    this.splendorPlayer3.getPlayerHand().addToHand(cardToBuy);
                    this.splendorPlayer3.setPrestigePts(splendorPlayer3.getPrestigePts()+cardToBuy.getPrestigePoints());
                    switch(row)
                    {
                        case 0:
                            this.board[row][col] = this.rank3Stack.remove(0);
                            break;
                        case 1:
                            this.board[row][col] = this.rank2Stack.remove(0);
                            break;
                        case 2:
                            this.board[row][col] = this.rank1Stack.remove(0);
                            break;
                    }
                    this.nextPlayerTurn();
                    return true;
                }
                break;
            case 3:
                if (cardToBuy.getrPrice() <= splendorPlayer4.getRubyCoins()+splendorPlayer4.getRubyPts() &&
                        cardToBuy.getbPrice() <= splendorPlayer4.getSapphCoins()+splendorPlayer4.getSapphPts() &&
                        cardToBuy.getBrPrice() <= splendorPlayer4.getOnyxCoins()+splendorPlayer4.getOnyxPts() &&
                        cardToBuy.getwPrice() <= splendorPlayer4.getDiaCoins()+splendorPlayer4.getDiaCoins() &&
                        cardToBuy.getgPrice() <= splendorPlayer4.getEmerCoins()+splendorPlayer4.getEmerPts())
                {
                    if(cardToBuy.getrPrice()-splendorPlayer4.getRubyPts() >= 0) {
                        splendorPlayer4.setRubyCoins(splendorPlayer4.getRubyCoins() -
                                (cardToBuy.getrPrice() - splendorPlayer4.getRubyPts()));
                        this.rubyCoins += (cardToBuy.getrPrice() - splendorPlayer4.getRubyPts());
                    }
                    if(cardToBuy.getbPrice()-splendorPlayer4.getSapphPts() >= 0) {
                        splendorPlayer4.setSapphCoins(splendorPlayer4.getSapphCoins() -
                                (cardToBuy.getbPrice() - splendorPlayer4.getSapphPts()));
                        this.sapphireCoins += (cardToBuy.getbPrice() - splendorPlayer4.getSapphPts());
                    }
                    if(cardToBuy.getBrPrice()-splendorPlayer4.getOnyxPts() >= 0) {
                        splendorPlayer4.setOnyxCoins(splendorPlayer4.getOnyxCoins() -
                                (cardToBuy.getBrPrice() - splendorPlayer4.getOnyxPts()));
                        this.onyxCoins += (cardToBuy.getBrPrice() - splendorPlayer4.getOnyxPts());
                    }
                    if(cardToBuy.getwPrice()-splendorPlayer4.getDiaPts() >= 0) {
                        splendorPlayer4.setDiaCoins(splendorPlayer4.getDiaCoins() -
                                (cardToBuy.getwPrice() - splendorPlayer4.getDiaPts()));
                        this.diamondCoins += (cardToBuy.getwPrice() - splendorPlayer4.getDiaPts());
                    }
                    if(cardToBuy.getgPrice()-splendorPlayer4.getEmerPts() >= 0) {
                        splendorPlayer4.setEmerCoins(splendorPlayer4.getEmerCoins() -
                                (cardToBuy.getgPrice() - splendorPlayer4.getEmerPts()));
                        this.emeraldCoins += (cardToBuy.getgPrice() - splendorPlayer4.getEmerPts());
                    }
                    //add card to hand -> maybe fill new card in place of the bought card?
                    this.splendorPlayer4.getPlayerHand().addToHand(cardToBuy);
                    this.splendorPlayer4.setPrestigePts(splendorPlayer4.getPrestigePts()+cardToBuy.getPrestigePoints());
                    switch(row)
                    {
                        case 0:
                            this.board[row][col] = this.rank3Stack.remove(0);
                            break;
                        case 1:
                            this.board[row][col] = this.rank2Stack.remove(0);
                            break;
                        case 2:
                            this.board[row][col] = this.rank1Stack.remove(0);
                            break;
                    }
                    this.nextPlayerTurn();
                    return true;
                }
                break;
        }
        return false;
    }

    /*~~~~~~~~~~~~~~~~~~~~~helper methods~~~~~~~~~~~~~~~~~~~*/

    /**
     * coinCheck - helper method for coin action, detects if player can take
     *
     * @param coinColor - color of first coin chosen
     * @param coinColor2 - color of second coin chosen
     * @param coinColor3 - color of third coin chosen
     */
    private boolean coinCheck(int coinColor, int coinColor2, int coinColor3) {
        boolean stackOneNotEmpty = coinPileCheck()[coinColor];
        boolean stackTwoNotEmpty = coinPileCheck()[coinColor2];
        boolean stackThreeNotEmpty = coinPileCheck()[coinColor3];
        switch (this.getPlayerTurn()) {
            case 0:
                if(coinCountBool(splendorPlayer1) && stackOneNotEmpty &&
                        stackTwoNotEmpty && stackThreeNotEmpty) {
                    return true;
                }
                break;
            case 1:
                if(coinCountBool(splendorPlayer2) && stackOneNotEmpty &&
                        stackTwoNotEmpty && stackThreeNotEmpty) {
                    return true;
                }
                break;
            case 2:
                if(coinCountBool(splendorPlayer3) && stackOneNotEmpty &&
                        stackTwoNotEmpty && stackThreeNotEmpty) {
                    return true;
                }
                break;
            case 3:
                if(coinCountBool(splendorPlayer4) && stackOneNotEmpty &&
                        stackTwoNotEmpty && stackThreeNotEmpty) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * coinCheckDoubles - helper method for coin action, detects if player can take
     *
     * @param coinColor - color ID of coins to do actions on
     */
    private boolean coinCheckDoubles(int coinColor) // checks if current player can take 2 coins of same color
    {
        boolean stackAtLeastFour = coinPileCheckDoubles()[coinColor];
        switch (this.getPlayerTurn()){
            case 0:
                if(coinCountBool(splendorPlayer1) && stackAtLeastFour) return true;
                break;
            case 1:
                if(coinCountBool(splendorPlayer2) && stackAtLeastFour) return true;
                break;
            case 2:
                if(coinCountBool(splendorPlayer3) && stackAtLeastFour) return true;
                break;
            case 3:
                if(coinCountBool(splendorPlayer4) && stackAtLeastFour) return true;
                break;
        }
        return false;
    }

    //checks that player has not exceeded max coin count
    private boolean coinCountBool(SplendorPlayer splendorPlayer) {
        if(coinsGreaterThanTen(splendorPlayer)) {
            return false;
        }
        return true;
    }

    //helper method for coinCount
    private boolean coinsGreaterThanTen(SplendorPlayer splendorPlayer) {
        return splendorPlayer.getDiaCoins()+splendorPlayer.getEmerCoins()+splendorPlayer.getOnyxCoins()+
                splendorPlayer.getRubyCoins()+splendorPlayer.getSapphCoins()+splendorPlayer.getGoldCoins() >= 100;
    }

    //returns array with true values for coin stacks that 2 coins of the same type can be taken
    private boolean[] coinPileCheckDoubles() {
        boolean[] coinPilesDoubles = {this.rubyCoins >= 4, this.sapphireCoins >= 4, this.emeraldCoins >= 4,
                this.diamondCoins >= 4, this.onyxCoins >= 4};
        return coinPilesDoubles;
    }

    //returns array with true values for coin stacks that have at least one coin
    private boolean[] coinPileCheck() {
        boolean[] coinPiles = {this.rubyCoins > 0, this.sapphireCoins > 0, this.emeraldCoins > 0,
                this.diamondCoins > 0, this.onyxCoins > 0};
        return coinPiles;
    }

    //action done on a single coin
    private void individualCoinAction(int coinColor) {
        switch(coinColor) {
            case 0:
                this.rubyCoins--;
                switch(this.getPlayerTurn()) {
                    case 0:
                        splendorPlayer1.setRubyCoins(splendorPlayer1.getRubyCoins()+1);
                        break;
                    case 1:
                        splendorPlayer2.setRubyCoins(splendorPlayer2.getRubyCoins()+1);
                        break;
                    case 2:
                        splendorPlayer3.setRubyCoins(splendorPlayer3.getRubyCoins()+1);
                        break;
                    case 3:
                        splendorPlayer4.setRubyCoins(splendorPlayer4.getRubyCoins()+1);
                        break;
                }
                break;
            case 1:
                this.sapphireCoins--;
                switch(this.getPlayerTurn()) {
                    case 0:
                        splendorPlayer1.setSapphCoins(splendorPlayer1.getSapphCoins()+1);
                        break;
                    case 1:
                        splendorPlayer2.setSapphCoins(splendorPlayer2.getSapphCoins()+1);
                        break;
                    case 2:
                        splendorPlayer3.setSapphCoins(splendorPlayer3.getSapphCoins()+1);
                        break;
                    case 3:
                        splendorPlayer4.setSapphCoins(splendorPlayer4.getSapphCoins()+1);
                        break;
                }
                break;
            case 2:
                this.emeraldCoins--;
                switch(this.getPlayerTurn()) {
                    case 0:
                        splendorPlayer1.setEmerCoins(splendorPlayer1.getEmerCoins()+1);
                        break;
                    case 1:
                        splendorPlayer2.setEmerCoins(splendorPlayer2.getEmerCoins()+1);
                        break;
                    case 2:
                        splendorPlayer3.setEmerCoins(splendorPlayer3.getEmerCoins()+1);
                        break;
                    case 3:
                        splendorPlayer4.setEmerCoins(splendorPlayer4.getEmerCoins()+1);
                        break;
                }
                break;
            case 3:
                this.diamondCoins--;
                switch(this.getPlayerTurn()) {
                    case 0:
                        splendorPlayer1.setDiaCoins(splendorPlayer1.getDiaCoins()+1);
                        break;
                    case 1:
                        splendorPlayer2.setDiaCoins(splendorPlayer2.getDiaCoins()+1);
                        break;
                    case 2:
                        splendorPlayer3.setDiaCoins(splendorPlayer3.getDiaCoins()+1);
                        break;
                    case 3:
                        splendorPlayer4.setDiaCoins(splendorPlayer4.getDiaCoins()+1);
                        break;
                }
                break;
            case 4:
                this.onyxCoins--;
                switch(this.getPlayerTurn()) {
                    case 0:
                        splendorPlayer1.setOnyxCoins(splendorPlayer1.getOnyxCoins()+1);
                        break;
                    case 1:
                        splendorPlayer2.setOnyxCoins(splendorPlayer2.getOnyxCoins()+1);
                        break;
                    case 2:
                        splendorPlayer3.setOnyxCoins(splendorPlayer3.getOnyxCoins()+1);
                        break;
                    case 3:
                        splendorPlayer4.setOnyxCoins(splendorPlayer4.getOnyxCoins()+1);
                        break;
                }
                break;
        }
    }
//~~~~~~~~~~~~~~~~~~Getters~~~~~~~~~~~~~~~~~~~~~~//
    public Card getSelected(){ return this.selected; }

    public void setSelected(Card card) { this.selected = card; }

    public int getPlayerTurn() { return playerTurn; }

    public int getPLAYER1ID() { return PLAYER1ID; }

    public int getPLAYER2ID() { return PLAYER2ID; }

    public int getPLAYER3ID() { return PLAYER3ID; }

    public int getPLAYER4ID() { return PLAYER4ID; }

    public ArrayList<Card> getRank1Stack() { return rank1Stack; }

    public ArrayList<Card> getRank2Stack() { return rank2Stack; }

    public ArrayList<Card> getRank3Stack() { return rank3Stack; }

    public Noble getNoble1() { return noble1; }

    public Noble getNoble2() { return noble2; }

    public Noble getNoble3() { return noble3; }

    public Noble getNoble4() { return noble4; }

    public Card getBoard(int row, int col){ return this.board[row][col]; }

    public SplendorPlayer getSplendorPlayer1() { return splendorPlayer1; }

    public void setSplendorPlayer1(SplendorPlayer splendorPlayer1) { this.splendorPlayer1 = splendorPlayer1; }

    public SplendorPlayer getSplendorPlayer2() { return splendorPlayer2; }

    public void setSplendorPlayer2(SplendorPlayer splendorPlayer2) { this.splendorPlayer2 = splendorPlayer2; }

    public SplendorPlayer getSplendorPlayer3() { return splendorPlayer3; }

    public void setSplendorPlayer3(SplendorPlayer splendorPlayer3) { this.splendorPlayer3 = splendorPlayer3; }

    public SplendorPlayer getSplendorPlayer4() { return splendorPlayer4; }

    public void setSplendorPlayer4(SplendorPlayer splendorPlayer4) { this.splendorPlayer4 = splendorPlayer4; }

    public int getRubyCoins() { return rubyCoins; }

    public int getSapphireCoins() { return sapphireCoins; }

    public int getEmeraldCoins() { return emeraldCoins; }

    public int getDiamondCoins() { return diamondCoins; }

    public int getOnyxCoins() { return onyxCoins; }

    public int getGoldCoins() { return goldCoins; }

    public ArrayList<Integer> getCoinTracking(){ return this.coinTracking; }

    public void setCoinTracking(ArrayList<Integer> toChange){ this.coinTracking = toChange; }

    public void setSelectedRow(int row) { this.selectedRow = row; }

    public int getSelectedRow() { return this.selectedRow; }

    public void setSelectedCol(int col) { this.selectedCol = col; }

    public int getSelectedCol() { return this.selectedCol; }
}

