/**
 * @authors Ryan Brown, Noah LaFave, Jacob Noble, Aidan Day
 * @version 1.0
 *
 */

package edu.up.cs301.splendor.State;
import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.Game.Noble;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;


public class SplendorGameState extends GameState {

//~~~~~~~~~~~~~ player names and IDs ~~~~~~~~~~~ //

    //holds turn value corresponding to player ID below
    private int playerTurn;

    private String player1Name;
    private String player2Name;
    private String player3Name;
    private String player4Name;


    //player values for playerTurn
    private final int PLAYER1ID = 0;
    private final int PLAYER2ID = 1;
    private final int PLAYER3ID = 2;
    private final int PLAYER4ID = 3;

//~~~~~~~~~~~~~~~~ players ~~~~~~~~~~~~~~~~~~~ //

    private int playerCount;

    //create list of all players
    private ArrayList<SplendorPlayer> playerList = new ArrayList<>();

//~~~~~~~~~~~~~~~~~~ Deck and Coin Information ~~~~~~~~~~~~~~~ //

    private ArrayList<Card> rank1Stack; //ArrayList of rank1 cards
    private ArrayList<Card> rank2Stack; //ArrayList of rank2 cards
    private ArrayList<Card> rank3Stack; //ArrayList of rank3 cards
    private ArrayList<Noble> nobleStack;

    private final int RANKS = 3;
    private final int CARDS_PER_RANK = 4;

    //coin instance variables for stacks
    private int rubyCoins;
    private int sapphireCoins;
    private int emeraldCoins;
    private int diamondCoins;
    private int onyxCoins;
    private int goldCoins;

    private boolean moreThanTenCoins = false; // check if current player has moreThanTenCoins, this doesn't need to be in copy constructor
    // BECAUSE we ONLY want this to be false at the start of every turn, and it won't move onto the next turn until this is false.


//~~~~~~~~~~~~~~~~~~~~~ Game State Specific Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //game board of active cards that players can select
    private Card board[][] = new Card[RANKS][CARDS_PER_RANK];

    //nobles displayed on board
    private ArrayList<Noble> nobleBoard = new ArrayList<>();

    //this holds the last card that the user has selected
    private Card selected = null;
    private Noble selectedNoble = null;
    private ArrayList<Integer> coinTracking = new ArrayList<>();
    private int selectedRow = -1;
    private int selectedCol = -1;

    // these are checkers to see if an action is successful because some classes can't determine that through the actions like human player
    private boolean boughtCard = false;
    private boolean reserveSuccess = false;
    private boolean takenCoins = false;
    private boolean nobleTaken = false;

//~~~~~~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public SplendorGameState(int num, String[] playerNames) {
        initializePlayers(num, playerNames);
        initializeCoins();

        this.selected = new Card();
        this.selectedNoble = new Noble();

        initializeDecks();
        Collections.shuffle(this.rank1Stack);
        Collections.shuffle(this.rank2Stack);
        Collections.shuffle(this.rank3Stack);
        Collections.shuffle(this.nobleStack);
        initializeBoard(this.rank1Stack, this.rank2Stack, this.rank3Stack);
        initializeNobles();
        this.selected = board[2][0];
    }

    /**
     * SplendorGameState() Deep copy constructor
     *
     * @param stateToCopy :SplendorGameState that needs to be copied
     */
    public SplendorGameState(SplendorGameState stateToCopy) {
        this.playerCount = stateToCopy.playerCount;

        //runs through board deep copy cards onto new instances board
        for (int row = 0; row < 3; row++) //
        {
            for (int col = 0; col < 4; col++) {
                Card card = new Card(stateToCopy.getBoard(row, col));
                this.board[row][col] = card;
            }
        }

        //makes copy of card that is currently selected
        this.selected = new Card(stateToCopy.getSelected());

        this.moreThanTenCoins = stateToCopy.getMoreThanTenCoins();

        //makes copy of Noble currently selected
        this.selectedNoble = new Noble(stateToCopy.getSelectedNoble());

        this.playerTurn = stateToCopy.getPlayerTurn();
        for (SplendorPlayer player : stateToCopy.getPlayerList()) {
            this.playerList.add(new SplendorPlayer(player));
        }

        //deep copies for all 3 card stacks + nobleStack
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

        this.nobleStack = new ArrayList<Noble>();
        for(Noble nobleCard : stateToCopy.getNobleBoard())
        {
            this.nobleStack.add(new Noble(nobleCard));
        }

        //deep copy for noble cards

        for(Noble noblesToCopy : stateToCopy.nobleBoard)
        {
            this.nobleBoard.add(new Noble(noblesToCopy));
        }

        this.coinTracking = new ArrayList<>();
        for (int coin : stateToCopy.getCoinTracking()) {
            this.coinTracking.add(coin);
        }

        this.selectedRow = stateToCopy.getSelectedRow();
        this.selectedCol = stateToCopy.getSelectedCol();

        this.rubyCoins = stateToCopy.getRubyCoins();
        this.sapphireCoins = stateToCopy.getSapphireCoins();
        this.emeraldCoins = stateToCopy.getEmeraldCoins();
        this.diamondCoins = stateToCopy.getDiamondCoins();
        this.onyxCoins = stateToCopy.getOnyxCoins();
        this.goldCoins = stateToCopy.getGoldCoins();

        this.player1Name = stateToCopy.getPlayer1Name();
        this.player2Name = stateToCopy.getPlayer2Name();
        this.player3Name = stateToCopy.getPlayer3Name();
        this.player4Name = stateToCopy.getPlayer4Name();

        // this is to see if someone bought a card!
        this.boughtCard = stateToCopy.getBoughtCard();
        this.reserveSuccess = stateToCopy.getReserveSuccess();
        this.takenCoins = stateToCopy.getTakenCoins();
        this.nobleTaken = stateToCopy.getNobleTaken();
    }

    //helper method for constructor setting all point values for player to zero
    public void initializePlayers(int num, String[] playerNames) {
        //set turn to be first
        this.playerCount = num;
        this.playerTurn = PLAYER1ID;


        switch (num) {
            case 2:
                this.player1Name = playerNames[0];
                this.player2Name = playerNames[1];
                break;
            case 3:
                this.player1Name = playerNames[0];
                this.player2Name = playerNames[1];
                this.player3Name = playerNames[2];
                break;
            case 4:
                this.player1Name = playerNames[0];
                this.player2Name = playerNames[1];
                this.player3Name = playerNames[2];
                this.player4Name = playerNames[3];
                break;
            default:
                this.player1Name = "";
                this.player2Name = "";
                this.player3Name = "";
                this.player4Name = "";


        }

        // make SplendorPlayers based on how many are needed
        for (int i = 0; i < num; i++) {
            this.playerList.add(new SplendorPlayer(i));
            this.playerList.get(i).setPlayerID(i);
        }
    }

    public void initializeBoard(ArrayList<Card> rank1, ArrayList<Card> rank2, ArrayList<Card> rank3) {
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

    /* initialize Decks
     * reads input from text files into three array lists then shuffles deck
     *
     */

    public void initializeDecks() {
        String rank1File = "res/raw/rank1.csv";
        InputStream rank1 = this.getClass().getClassLoader().getResourceAsStream(rank1File);
        String rank2File = "res/raw/rank2.csv";
        InputStream rank2 = this.getClass().getClassLoader().getResourceAsStream(rank2File);
        String rank3File = "res/raw/rank3.csv";
        InputStream rank3 = this.getClass().getClassLoader().getResourceAsStream(rank3File);
        String nobleFile = "res/raw/noble";
        InputStream nobles = this.getClass().getClassLoader().getResourceAsStream(nobleFile);

        /**
         * External Citation
         * Date: 12/3/2020
         * Problem: files not read in junit testing
         * Solution: jury-rigged path with trial and error
         * Contributors: Dr. Nuxoll and Noah LaFave
         */
        //test case to make sure files load correctly
        if (rank1 == null) {
            //this must be the unit test
            String filepath1 = System.getProperty("user.dir") + "/src/main/" + rank1File;
            try {
                rank1 = new FileInputStream(filepath1);
            } catch(FileNotFoundException fnfe) {
                //do nothing for now
                System.out.println(filepath1);
            }
        }
        if (rank2 == null) {
            //this must be the unit test
            String filepath2 = System.getProperty("user.dir") + "/src/main/" + rank2File;
            try {
                rank2 = new FileInputStream(filepath2);
            } catch(FileNotFoundException fnfe) {
                //do nothing for now
                System.out.println(filepath2);
            }
        }
        if (rank3 == null) {
            //this must be the unit test
            String filepath3 = System.getProperty("user.dir") + "/src/main/" + rank3File;
            try {
                rank3 = new FileInputStream(filepath3);
            } catch(FileNotFoundException fnfe) {
                //do nothing for now
                System.out.println(filepath3);
            }
        }
        if (nobles == null) {
            //this must be the unit test
            String filepathNoble = System.getProperty("user.dir") + "/src/main/" + nobleFile;
            try {
                nobles = new FileInputStream(filepathNoble);
            } catch(FileNotFoundException fnfe) {
                //do nothing for now
                System.out.println(filepathNoble);
            }
        }
        //End Unit test appendage

        //initialize card stacks
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
            while ((line = rank1Reader.readLine()) != null) {
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
            Log.wtf("MyActivity", "Error reading data file " + line, e);
        }

        //reading data for rank 2
        BufferedReader rank2Reader = new BufferedReader(
                new InputStreamReader(rank2, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while ((line = rank2Reader.readLine()) != null) {
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
            Log.wtf("MyActivity", "Error reading data file " + line, e);
        }

        //reading data for rank 3
        BufferedReader rank3Reader = new BufferedReader(
                new InputStreamReader(rank3, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while ((line = rank3Reader.readLine()) != null) {
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
            Log.wtf("MyActivity", "Error reading data file " + line, e);
        }
        //reading data for rank 3
        BufferedReader nobleReader = new BufferedReader(
                new InputStreamReader(nobles, Charset.forName("UTF-8"))
        );

        line = "";
        try {
            while ((line = nobleReader.readLine()) != null) {
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
            Log.wtf("MyActivity", "Error reading data file " + line, e);
        }
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
        /*this.noble1 = new Noble(4,0,4,0,0);
        this.noble2 = new Noble(3,0,0,3,3);
        this.noble3 = new Noble(4,0,0,0,4);
        this.noble4 = new Noble(0,3,3,3,0);*/
        for(int i = 0; i <= playerList.size(); i++)
        {
            this.nobleBoard.add(this.nobleStack.remove(0));
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~action methods~~~~~~~~~~~~~~~~~~~*/

    //increments to next players turn
    public void nextPlayerTurn() {
        if(moreThanTenCoins) // will check if player has more than 10 coins
        {
            return;
        }
        nobleReqChecker();
        setPlayerTurn((getPlayerTurn()+1) % playerList.size());
    }

    //sets turn to exact value
    public void setPlayerTurn(int playerID) {
        this.playerTurn = playerID;
    }

    public boolean returnCoins(int coinColor)
    {
        if(this.moreThanTenCoins) // check if player has more than 10 coins, if they do, then disable all actions except returnCoins until they have less
        {
            for(SplendorPlayer player : this.playerList)
            {
                if(player.getPlayerID() == this.playerTurn)
                {
                    if(hasCoin(coinColor))
                    {
                        individualCoinReturn(coinColor);
                        this.moreThanTenCoins = coinsGreaterThanTen(player); // check if the player still has more than 10 coins
                        if(!this.moreThanTenCoins) {
                            nextPlayerTurn(); // if they don't have more than 10 coins, then we move to the nextPlayerTurn
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //player chooses three coins to purchase action
    public boolean coinAction(int coinColor1, int coinColor2, int coinColor3) {
        if (coinCheck(coinColor1, coinColor2, coinColor3)) {
            if (!moreThanTenCoins) // check if player has more than 10 coins, if they do, then disable all actions except returnCoins until they have less
            {
                individualCoinAction(coinColor1); // call individualCoinaction three times, for three different coins
                individualCoinAction(coinColor2);
                individualCoinAction(coinColor3);
                for (SplendorPlayer player : this.playerList) // iterate through player list, and check if current player has more than 10 coins
                {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (coinsGreaterThanTen(player)) {
                            moreThanTenCoins = true;
                        }
                    }
                }
                this.takenCoins = true;
                nextPlayerTurn();
                return true;
            }
        }
        return false;
    }

    //player chooses two of one type to purchase
    public boolean coinAction(int coinColor) {
        if (!moreThanTenCoins) // check if player has more than 10 coins, if they do, then disable all actions except returnCoins until they have less
        {
            if (coinCheckDoubles(coinColor)) {
                individualCoinAction(coinColor);
                individualCoinAction(coinColor);
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (coinsGreaterThanTen(player)) {
                            moreThanTenCoins = true;
                        }
                    }
                }
                this.takenCoins = true;
                nextPlayerTurn();
                return true;
            }
        }
        return false;
    }

    /**
     * reserveAction()
     * <p>
     * iterate through playerlist, and make sure it is the player's turn and they don't have more than 10 coins
     * let player reserve if they don't have more than 10 coins, and there are gold coins available
     * The card that is reserved gets moved into the player's reserved hand, and gets replaced on board
     *
     * @param cardToReserve card that player wants to reserve (cannot be a reserved card already)
     * @param row           row in the board or reserved hand list where the card is located
     * @param col           col in board or if it is in the reserved hand list where the card is located
     */
    public boolean reserveAction(Card cardToReserve, int row, int col) {
        for (SplendorPlayer player : playerList) {
            if (player.getPlayerID() == this.playerTurn && !moreThanTenCoins) // check if player has more than 10 coins, if they do, then disable all actions except returnCoins until they have less
            {
                if(player.getPlayerHand().canReserve() && col != -1){ // col != -1 makes sure that the selected card ISN'T a reserved card already
                    if(this.goldCoins > 0){
                        player.setGoldCoins(player.getGoldCoins()+1); this.goldCoins--;
                        if(coinsGreaterThanTen(player))
                        {
                            moreThanTenCoins = true;
                        }
                        player.getPlayerHand().addToReserved(cardToReserve);
                        switch (row) {
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
                        this.reserveSuccess = true;
                        this.nextPlayerTurn();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //if player has sufficient coins the cards points are added to players resources and coins are placed back into the coin bank
    public boolean cardAction(Card cardToBuy, int row, int col) {
        for (SplendorPlayer player : this.playerList) {
            if (player.getPlayerID() == this.playerTurn && !moreThanTenCoins) { // check if player has more than 10 coins, if they do, then disable all actions except returnCoins until they have less
                if (canBuyCard(player, cardToBuy)) {
                    buyCardLogic(player, cardToBuy);
                    player.getPlayerHand().addToHand(cardToBuy);
                    addToCoinPoints(player, cardToBuy);
                    player.setPrestigePts(player.getPrestigePts() + cardToBuy.getPrestigePoints());
                    if (col == -1) // this is to check if the card being bought is a reserved card or not; if it is, then don't remove from board, remove from reserved hand
                    {
                        player.getPlayerHand().removeFromReserved(row);
                    } else {
                        switch (row) {
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

                    }
                    this.boughtCard = true;
                    this.nextPlayerTurn();
                    return true;
                }
            }
        }
        return false;
    }

    public int getTrueVictor(int firstToThreshold) {
        int playerVictor = firstToThreshold, maxPoints = 0;
        if(this.getPlayer(0).getPrestigePts() > maxPoints) {
            maxPoints = getPlayer(0).getPrestigePts();
            playerVictor = 0;
        }
        if(this.getPlayer(1).getPrestigePts() > maxPoints) {
            maxPoints = getPlayer(1).getPrestigePts();
            playerVictor = 1;
        }
        if(this.playerCount > 2) {
            if(getPlayer(2).getPrestigePts() > maxPoints) {
                maxPoints = getPlayer(2).getPrestigePts();
                playerVictor = 2;
            }
            if(this.playerCount > 3) {
                if(getPlayer(3).getPrestigePts() > maxPoints) {
                    maxPoints = getPlayer(3).getPrestigePts();
                    playerVictor = 3;
                }
            }
        }
        return playerVictor;
    }

    /*~~~~~~~~~~~~~~~~~~~~~helper methods~~~~~~~~~~~~~~~~~~~*/

    public boolean hasCoin(int coin) {
        boolean flag = false;
        switch(coin) {
            case 0:
                flag = (this.playerList.get(this.playerTurn).getRubyCoins() != 0);
                break;
            case 1:
                flag = (this.playerList.get(this.playerTurn).getSapphCoins() != 0);
                break;
            case 2:
                flag = (this.playerList.get(this.playerTurn).getEmerCoins() != 0);
                break;
            case 3:
                flag = (this.playerList.get(this.playerTurn).getDiaCoins() != 0);
                break;
            case 4:
                flag = (this.playerList.get(this.playerTurn).getOnyxCoins() != 0);
                break;
            default:
                break;
        }
        return flag;
    }

    /** canBuyCard()
     * The helper method for cardAction, will check if a player can buy a card.
     *
     * @param player    What player should we look at?
     * @param cardToBuy What card is player trying to buy?
     */
    public boolean canBuyCard(SplendorPlayer player, Card cardToBuy) {
        if (!(cardToBuy.getrPrice() <= player.getRubyCoins() + player.getRubyPts() &&
                cardToBuy.getbPrice() <= player.getSapphPts() + player.getSapphCoins() &&
                cardToBuy.getBrPrice() <= player.getOnyxCoins() + player.getOnyxPts() &&
                cardToBuy.getwPrice() <= player.getDiaCoins() + player.getDiaPts() &&
                cardToBuy.getgPrice() <= player.getEmerCoins() + player.getEmerPts())) {
            return goldCoinChecker(player, cardToBuy);
        }
        return true;
    }

    /**
     * goldCoinCheck()
     *
     * helper method for canBuyCard method. This is the second check to see if user can buy a card with gold coins
     * function will keep track of however many gold coins have been used so far while checking each color value
     * outer while loop will break when gold > player's gold coins, meaning they need more gold coins than they have, so they can't buy
     * inner while loops will break when it has determined that a player can buy a card with gold coins
     * iterator == how many gold coins would the player need for that specific coin type. This is reset to 0 between each while loop so
     * that its ready for each coin.
     * gold == int incremented every time the player needs an extra gold coin for each card type
     * will break the outer loop when it is greater than the gold coins the player has
     */
    public boolean goldCoinChecker(SplendorPlayer player, Card cardToBuy) {
        int gold = 0;
        int iterator = 0;
        boolean goldWorks = false; // initially false when we don't know if they can buy with gold or not

        while (cardToBuy.getrPrice() > player.getRubyCoins() + player.getRubyPts() + iterator) {
            gold++;
            iterator++;
        }
        iterator = 0;

        while (cardToBuy.getbPrice() > player.getSapphPts() + player.getSapphCoins() + iterator) {
            gold++;
            iterator++;
        }
        iterator = 0;

        while (cardToBuy.getBrPrice() > player.getOnyxCoins() + player.getOnyxPts() + iterator) {
            gold++;
            iterator++;
        }
        iterator = 0;

        while (cardToBuy.getwPrice() > player.getDiaCoins() + player.getDiaPts() + iterator) {
            gold++;
            iterator++;
        }
        iterator = 0;

        while (cardToBuy.getgPrice() > player.getEmerCoins() + player.getEmerPts() + iterator) {
            gold++;
            iterator++;
        }

        if(gold > player.getGoldCoins()) {
            return false;
        } else {
            return true;
        }


    }

    /**
     * buyCardLogic()
     * the logic used to accurately decrease the player's currency (including gold coins) and also add the coins back to the coin bank.
     * Will first check if player can buy the card with only currency points, then will check if they need gold coins, then will adjust the player's
     * currency and the coin bank after those checks are made.
     *
     * @param cardToBuy card that the player wishes to buy
     * @param player    player that wants the card
     */
    public void buyCardLogic(SplendorPlayer player, Card cardToBuy) {
        if (cardToBuy.getrPrice() - player.getRubyPts() >= 0) { // this first checks if player can buy card with only pts
            if (player.getRubyCoins() - (cardToBuy.getrPrice() - player.getRubyPts()) < 0) //this will check if player needs gold coins to buy
            {
                while (player.getRubyCoins() - (cardToBuy.getrPrice() - player.getRubyPts()) < 0) // subtract one gold coin until they can buy this coin type
                {
                    cardToBuy.setrPrice(cardToBuy.getrPrice() - 1);
                    player.setGoldCoins(player.getGoldCoins() - 1);
                    this.goldCoins++; // here, we decrease the card's cost by 1 according to how many gold coins are needed
                }
            }
            player.setRubyCoins(player.getRubyCoins() - (cardToBuy.getrPrice() - player.getRubyPts()));
            this.rubyCoins += (cardToBuy.getrPrice() - player.getRubyPts());
        }

        if (cardToBuy.getbPrice() - player.getSapphPts() >= 0) {
            if (player.getSapphCoins() - (cardToBuy.getbPrice() - player.getSapphPts()) < 0) //this will check if player needs gold coins to buy
            {
                while (player.getSapphCoins() - (cardToBuy.getbPrice() - player.getSapphPts()) < 0) // subtract one gold coin until they can buy this coin type
                {
                    cardToBuy.setbPrice(cardToBuy.getbPrice() - 1);
                    player.setGoldCoins(player.getGoldCoins() - 1);
                    this.goldCoins++;
                }
            }
            player.setSapphCoins(player.getSapphCoins() - (cardToBuy.getbPrice() - player.getSapphPts()));
            this.sapphireCoins += (cardToBuy.getbPrice() - player.getSapphPts());
        }

        if (cardToBuy.getBrPrice() - player.getOnyxPts() >= 0) {
            if (player.getOnyxCoins() - (cardToBuy.getBrPrice() - player.getOnyxPts()) < 0) //this will check if player needs gold coins to buy
            {
                while (player.getOnyxCoins() - (cardToBuy.getBrPrice() - player.getOnyxPts()) < 0) // subtract one gold coin until they can buy this coin type
                {
                    cardToBuy.setBrPrice(cardToBuy.getBrPrice() - 1);
                    player.setGoldCoins(player.getGoldCoins() - 1);
                    this.goldCoins++;
                }
            }
            player.setOnyxCoins(player.getOnyxCoins() - (cardToBuy.getBrPrice() - player.getOnyxPts()));
            this.onyxCoins += (cardToBuy.getBrPrice() - player.getOnyxPts());
        }

        if (cardToBuy.getwPrice() - player.getDiaPts() >= 0) {
            if (player.getDiaCoins() - (cardToBuy.getwPrice() - player.getDiaPts()) < 0) //this will check if player needs gold coins to buy
            {
                while (player.getDiaCoins() - (cardToBuy.getwPrice() - player.getDiaPts()) < 0) // subtract one gold coin until they can buy this coin type
                {
                    cardToBuy.setwPrice(cardToBuy.getwPrice() - 1);
                    player.setGoldCoins(player.getGoldCoins() - 1);
                    this.goldCoins++;
                }
            }
            player.setDiaCoins(player.getDiaCoins() - (cardToBuy.getwPrice() - player.getDiaPts()));
            this.diamondCoins += (cardToBuy.getwPrice() - player.getDiaPts());
        }
        if (cardToBuy.getgPrice() - player.getEmerPts() >= 0) {
            if (player.getEmerCoins() - (cardToBuy.getgPrice() - player.getEmerPts()) < 0) //this will check if player needs gold coins to buy
            {
                while (player.getEmerCoins() - (cardToBuy.getgPrice() - player.getEmerPts()) < 0) // subtract one gold coin until they can buy this coin type
                {
                    cardToBuy.setgPrice(cardToBuy.getgPrice() - 1);
                    player.setGoldCoins(player.getGoldCoins() - 1);
                    this.goldCoins++;
                }
            }
            player.setEmerCoins(player.getEmerCoins() - (cardToBuy.getgPrice() - player.getEmerPts()));
            this.emeraldCoins += (cardToBuy.getgPrice() - player.getEmerPts());
        }
    }

    /**
     * addToCoinPoints()
     * The helper method for cardAction, will add to a player's pts currency based on what color is on the card.
     * 1 == Ruby
     * 2 == Sapphire
     * 3 == Emerald
     * 4 == Diamond
     * 5 == Onyx
     *
     * @param player    What player should we look at?
     * @param cardToBuy What card is player trying to buy?
     */
    public void addToCoinPoints(SplendorPlayer player, Card cardToBuy) {
        switch (cardToBuy.getColorGem()) {
            case 1:
                player.setRubyPts(player.getRubyPts() + 1);
                break;
            case 2:
                player.setSapphPts(player.getSapphPts() + 1);
                break;
            case 3:
                player.setEmerPts(player.getEmerPts() + 1);
                break;
            case 4:
                player.setDiaPts(player.getDiaPts() + 1);
                break;
            case 5:
                player.setOnyxPts(player.getOnyxPts() + 1);
                break;
        }
    }

    /**
     * coinCheck()
     * check to see if the 3 stacks the player would like to take from are not empty
     *
     * @param coinColor  first coin color stack
     * @param coinColor2 second coins color stack
     * @param coinColor3 third coins color stack
     */
    private boolean coinCheck(int coinColor, int coinColor2, int coinColor3) {

        boolean stackOneNotEmpty = coinPileCheck()[coinColor];
        boolean stackTwoNotEmpty = coinPileCheck()[coinColor2];
        boolean stackThreeNotEmpty = coinPileCheck()[coinColor3];
        for (SplendorPlayer player : this.playerList) {
            if (player.getPlayerID() == this.playerTurn) {
                return stackOneNotEmpty && stackTwoNotEmpty && stackThreeNotEmpty;
            }
        }
        return false;
    }

    /**
     * coinCheckDoubles()
     * check to see if a coin stack that a player wants to take two coins from has four coins in it
     *
     * @param coinColor the coin color that the player wants to take from
     */
    private boolean coinCheckDoubles(int coinColor) // checks if current player can take 2 coins of same color
    {
        boolean stackAtLeastFour = coinPileCheckDoubles()[coinColor];
        for (SplendorPlayer player : this.playerList) {
            if (player.getPlayerID() == this.playerTurn) {
                return (stackAtLeastFour);
            }
        }
        return false;
    }

    //checks that player has not exceeded max coin count
    private boolean coinCountBool(SplendorPlayer splendorPlayer) {
        if (coinsGreaterThanTen(splendorPlayer)) {
            return false;
        }
        return true;
    }

    //helper method for coinCount
    public boolean coinsGreaterThanTen(SplendorPlayer splendorPlayer) {
        return splendorPlayer.getDiaCoins() + splendorPlayer.getEmerCoins() + splendorPlayer.getOnyxCoins() +
                splendorPlayer.getRubyCoins() + splendorPlayer.getSapphCoins() + splendorPlayer.getGoldCoins() > 10;
    }

    //returns array with true values for coin stacks that 2 coins of the same type can be taken
    private boolean[] coinPileCheckDoubles() {
        boolean[] coinPilesDoubles = {(this.rubyCoins >= 4), (this.sapphireCoins >= 4), this.emeraldCoins >= 4, this.diamondCoins >= 4, this.onyxCoins >= 4};
        return coinPilesDoubles;
    }

    //returns array with true values for coin stacks that have at least one coin
    private boolean[] coinPileCheck() {
        boolean[] coinPiles = {(this.rubyCoins > 0), (this.sapphireCoins > 0), this.emeraldCoins > 0, this.diamondCoins > 0, this.onyxCoins > 0};
        return coinPiles;
    }


    /**
     * individualCoinReturn()
     * where the actual action of an individual coin is taken. The method will determine which coin stack it is taking from
     * then subtract one coin from that coin stack, and add that coin to the player's inventory.
     *
     * @param coinColor coin color stack the player wants to take from.
     */
    private void individualCoinAction(int coinColor) {
        switch (coinColor) {
            case 0:
                this.rubyCoins--;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn)
                        player.setRubyCoins(player.getRubyCoins() + 1);
                }
                break;
            case 1:
                this.sapphireCoins--;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn)
                        player.setSapphCoins(player.getSapphCoins() + 1);
                }
                break;
            case 2:
                this.emeraldCoins--;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn)
                        player.setEmerCoins(player.getEmerCoins() + 1);
                }
                break;
            case 3:
                this.diamondCoins--;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn)
                        player.setDiaCoins(player.getDiaCoins() + 1);
                }
                break;
            case 4:
                this.onyxCoins--;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn)
                        player.setOnyxCoins(player.getOnyxCoins() + 1);
                }
                break;
        }
    }

    /**
     * individualCoinReturn()
     * where the actual action of an individual coin being returned. Same algorithm as indivualCoinAction, but the roles between
     * player and coin bank are reversed
     *
     * @param coinColor coin color stack the player wants to return to.
     */
    private void individualCoinReturn(int coinColor) {
        switch (coinColor) {
            case 0:
                this.rubyCoins++;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (player.getRubyCoins() > 0)
                            player.setRubyCoins(player.getRubyCoins() - 1);
                    }
                }
                break;
            case 1:
                this.sapphireCoins++;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (player.getSapphCoins() > 0)
                            player.setSapphCoins(player.getSapphCoins() - 1);
                    }
                }
                break;
            case 2:
                this.emeraldCoins++;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (player.getEmerCoins() > 0)
                            player.setEmerCoins(player.getEmerCoins() - 1);
                    }
                }
                break;
            case 3:
                this.diamondCoins++;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (player.getDiaCoins() > 0) player.setDiaCoins(player.getDiaCoins() - 1);
                    }
                }
                break;
            case 4:
                this.onyxCoins++;
                for (SplendorPlayer player : this.playerList) {
                    if (player.getPlayerID() == this.playerTurn) {
                        if (player.getOnyxCoins() > 0)
                            player.setOnyxCoins(player.getOnyxCoins() - 1);
                    }
                }
                break;
        }
    }

    /**
     * nobleReqChecker()
     *      checks if current player can take a noble, looking at the player's pts across all currencies
     *
     * */
    public boolean nobleReqChecker()
    {
        for (SplendorPlayer player : this.playerList)
        {
            if (player.getPlayerID() == this.playerTurn)
            {
                for(Noble noble : this.nobleBoard)
                {
                    if(noblePriceChecker(player, noble))
                    {
                        this.nobleBoard.remove(noble);
                        player.setPrestigePts(player.getPrestigePts()+3);
                        this.nobleTaken = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * noblePriceChecker()
     *      checks if current player can take a noble, looking at the player's pts across all currencies; helper method for above method
     *
     * @param player  player to check
     * @param noble noble to check
     * */
    public boolean noblePriceChecker(SplendorPlayer player, Noble noble)
    {
        return player.getRubyPts() >= noble.getrPrice() &&
                player.getDiaPts() >= noble.getwPrice() &&
                player.getEmerPts() >= noble.getgPrice() &&
                player.getOnyxPts() >= noble.getBrPrice() &&
                player.getSapphPts() >= noble.getbPrice();
    }

    //Getter methods

    public Card getSelected() {
        return this.selected;
    }

    public void setSelected(Card card) {
        this.selected = card;
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

    public ArrayList<Card> getRank1Stack() {
        return rank1Stack;
    }

    public ArrayList<Card> getRank2Stack() {
        return rank2Stack;
    }

    public ArrayList<Card> getRank3Stack() {
        return rank3Stack;
    }

    public Card getBoard(int row, int col){
        return this.board[row][col];
    }

    public SplendorPlayer getPlayer(int num) {
        return this.playerList.get(num);
    }

    public int getRubyCoins() {
        return rubyCoins;
    }

    public int getSapphireCoins() {
        return sapphireCoins;
    }

    public int getEmeraldCoins() {
        return emeraldCoins;
    }

    public int getDiamondCoins() {
        return diamondCoins;
    }

    public int getOnyxCoins() {
        return onyxCoins;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public int[] getAllCoins()
    {
        int allCoins[] = {this.rubyCoins, this.sapphireCoins, this.emeraldCoins, this.diamondCoins, this.onyxCoins};
        return allCoins;
    }

    public ArrayList<Integer> getCoinTracking(){
        return this.coinTracking;
    }

    public void setCoinTracking(ArrayList<Integer> toChange) {
        this.coinTracking = toChange;
    }

    public void setSelectedRow(int row) {
        this.selectedRow = row;
    }

    public int getSelectedRow() {
        return this.selectedRow;
    }

    public void setSelectedCol(int col) {
        this.selectedCol = col;
    }

    public int getSelectedCol() {
        return this.selectedCol;
    }

    public ArrayList<SplendorPlayer> getPlayerList() {
        return this.playerList;
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


    public int getPlayerCount() {
        return playerCount;
    }

    public ArrayList<Noble> getNobleBoard() { return this.nobleBoard; }

    public ArrayList<Noble> getNobleStack() { return this.nobleStack; }

    public Noble getSelectedNoble() { return this.selectedNoble; }

    public void setSelectedNoble(Noble selected) { this.selectedNoble = selected; }

    public boolean getMoreThanTenCoins() { return this.moreThanTenCoins; }

    public boolean getBoughtCard() { return this.boughtCard; }

    public void setBoughtCard(boolean bought) { this.boughtCard = bought; }

    public boolean getReserveSuccess() { return this.reserveSuccess; }

    public void setReserveSuccess(boolean success) { this.reserveSuccess = success; }

    public boolean getTakenCoins() { return this.takenCoins; }

    public void setTakenCoins(boolean taken) { this.takenCoins = taken; }

    public boolean getNobleTaken() { return this.nobleTaken; }

    public void setNobleTaken(boolean taken) { this.nobleTaken = taken; }
}