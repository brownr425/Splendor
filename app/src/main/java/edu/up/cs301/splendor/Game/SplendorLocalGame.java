package edu.up.cs301.splendor.Game;

import edu.up.cs301.splendor.Actions.QuitAction;
import edu.up.cs301.splendor.Actions.SplendorClearSelectedAction;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorNobleSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorReturnCoinAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorLocalGame extends LocalGame {
    private SplendorGameState gameState;



    public SplendorLocalGame(int num, String[] playerNames) {
        this.gameState = new SplendorGameState(num, playerNames);
    }

    /**
     * sendUpdatedStateTo() sends the new updated gamestate to the next player
     *
     * @param player the player doing the action
     */
    protected void sendUpdatedStateTo(GamePlayer player) {
        player.sendInfo(new SplendorGameState(gameState));
    }

    /**
     * @param playerIdx the player's player-number (ID)
     * @return
     */
    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getPlayerTurn();
    }

    /**
     * checkIfGameOver() called after every turn, includes logic for end of game
     * after someone reaches 15 points, every other player gets to play one more turn
     * if anyone gets more points than the player who reached the threshold, the new player wins
     *
     * @return
     */
    protected String checkIfGameOver() {
        for(int i = 0; i < this.gameState.getPlayerList().size(); i++)
        {
            if(this.gameState.getPlayer(i).getPrestigePts() >= 15) return "Congratulations Player " + (i+1) + "! ";
        }
        return null;
    }

    /**
     * makeMove() where players make moves, differentiated actions based on what action the player sends.
     *
     * @param action is the action the player sent, will check if it is instance of any actions we have
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof SplendorCoinAction) {
            // player hits "take coins" button
            return coinActionHandler(action);
        } else if (action instanceof SplendorCardAction) {
            // player hits "buy card" button, buys selected card
            return cardActionHandler(action);
        } else if (action instanceof SplendorSelectCardAction) {
            // player selects a card from the board
            return selectCardHandler(action);
        } else if (action instanceof SplendorReserveCardAction) {
            // player hits "reserve card" button, reserves selected card
            return reserveCardHandler(action);
        } else if (action instanceof SplendorCoinSelectAction) {
            // player selects a coin from the bank
            return coinSelectHandler(action);
        } else if (action instanceof SplendorReturnCoinAction) {
            // player returns coins from their inventory
            for (int i = 0; i < this.gameState.getCoinTracking().size(); i++) {
                this.gameState.returnCoins(this.gameState.getCoinTracking().get(i));
            }
            this.gameState.getCoinTracking().clear();
            return true;
        } else if (action instanceof SplendorNobleSelectAction) {
            if (((SplendorNobleSelectAction) action).getRow() < this.gameState.getNobleBoard().size()) {
                // this is to tell the human player that "hey we're looking at a noble right now!"
                this.gameState.setSelectedCol(-2);
                this.gameState.setSelectedNoble(this.gameState.getNobleBoard()
                        .get(((SplendorNobleSelectAction) action).getRow()));
                return true;
            }
            return false;
        } else if (action instanceof SplendorClearSelectedAction) {
            this.gameState.getCoinTracking().clear();
            return true;
        }
        return false;
    }


    /**
     * coinActionHandler(GameAction action) handles making sure that player coin actions are viable before select coins
     *
     * @param action the action being done
     * @return boolean whether the actions was completed successfully
     */
    protected boolean coinActionHandler(GameAction action) {
        // check if selected coin array is empty or one coin
        if (this.gameState.getCoinTracking().isEmpty() ||
                this.gameState.getCoinTracking().size() == 1) {
            return false;
        }
        // check if coin array has only 2 coins that aren't the same
        if (this.gameState.getCoinTracking().size() == 2) {
            if (!this.gameState.getCoinTracking().get(0).equals(this.gameState.getCoinTracking().get(1))) {
                return false;
            }
        }
        // go through array and check if any coins equal each other
        for (int i = 0; i < this.gameState.getCoinTracking().size() - 1; i++) {
            if (this.gameState.getCoinTracking().get(i).equals(this.gameState.getCoinTracking().get(i + 1))) {
                if (this.gameState.coinAction(this.gameState.getCoinTracking().get(i))) {
                    //clear the array once action is made, get ready for next turn
                    this.gameState.getCoinTracking().clear();
                    return true;
                } else {
                    return false;
                }
            }
        }
        // another check to make sure all 3 coins are filled for 3 different coins
        for (int i = 0; i < this.gameState.getCoinTracking().size() - 1; i++) {
            if (this.gameState.getCoinTracking().get(i) == null) {
                return false;
            }
        }
        // taking 3 different coins
        if (this.gameState.coinAction(this.gameState.getCoinTracking().get(0),
                this.gameState.getCoinTracking().get(1),
                this.gameState.getCoinTracking().get(2))) {
            this.gameState.getCoinTracking().clear();
            return true;
        } else {
            return false;
        }
        //action was made, return true/valid move
    }

    /**
     * cardActionHandler
     * handles the players attempt to purchase a card
     *
     * @param action stores the card to be bought
     * @return boolean whether the action was completed successfully
     */
    public boolean cardActionHandler(GameAction action) {
        if (this.gameState.getSelected() != null) {
            return this.gameState.cardAction(this.gameState.getSelected(),
                    ((SplendorCardAction) action).getRow(),
                    ((SplendorCardAction) action).getCol());
        } else {
            return false;
        }
    }

    /**
     * selectCardHandler() handles selecting a card from the board
     *
     * @param action - stores the card's location on the board
     * @return boolean for if the card was selected propperly
     */
    public boolean selectCardHandler(GameAction action) {
        // check if the card selected is a player's reserved card
        if (((SplendorSelectCardAction) action).getCol() == -1) {
            if (this.gameState.getPlayer(this.gameState.getPlayerTurn()).getPlayerHand().getReserved().size()
                    > ((SplendorSelectCardAction) action).getRow()) {

                this.gameState.setSelected(this.gameState.getPlayer
                        (this.gameState.getPlayerTurn()).getPlayerHand().getReserved()
                        .get(((SplendorSelectCardAction) action).getRow()));

                // set the row and column of where the card was selected so game knows what card to replace
                this.gameState.setSelectedRow(((SplendorSelectCardAction) action).getRow());
                this.gameState.setSelectedCol(((SplendorSelectCardAction) action).getCol());
                this.gameState.setBoughtCard(false); // this resets all the information that action has happened
                this.gameState.setReserveSuccess(false);
                this.gameState.setTakenCoins(false);
                this.gameState.setNobleTaken(false);
                return true;
            }
            return false;
        }
        // if not reserved
        this.gameState.setSelected(this.gameState.getBoard(
                ((SplendorSelectCardAction) action).getRow(),
                ((SplendorSelectCardAction) action).getCol()));

        // set the row and column of where the card was selected so game knows what card to replace
        this.gameState.setSelectedRow(((SplendorSelectCardAction) action).getRow());
        this.gameState.setSelectedCol(((SplendorSelectCardAction) action).getCol());
        this.gameState.setBoughtCard(false); // this resets all the information that action has happened
        this.gameState.setReserveSuccess(false);
        this.gameState.setTakenCoins(false);
        this.gameState.setNobleTaken(false);
        //action was made, return true/valid move
        return true;
    }

    /**
     * reserveCardHandler() Handles reserving a card
     *
     * @param action - action that stores the card to reserve
     * @return boolean whether a card is reserved successfully
     */
    public boolean reserveCardHandler(GameAction action) {
        this.gameState.reserveAction(this.gameState.getSelected(),
                this.gameState.getSelectedRow(),
                this.gameState.getSelectedCol());
        return true;
    }

    /**
     * coinSelectHandler() Handles selecting coins
     *
     * @param action - action done on a coin
     * @return whether a coin is selected successfully
     */
    public boolean coinSelectHandler(GameAction action) {
        // this is if the array is empty, add the one chosen coin
        if (this.gameState.getCoinTracking().isEmpty()) {
            this.gameState.getCoinTracking().add(
                    ((SplendorCoinSelectAction) action).getChosenCoin());
            this.gameState.setBoughtCard(false); // this resets all the information that action has happened
            this.gameState.setReserveSuccess(false);
            this.gameState.setTakenCoins(false);
            this.gameState.setNobleTaken(false);
            return true;
        }
        // if size is 3, that means the array is filled
        if (this.gameState.getCoinTracking().size() == 3) {
            // iterate through and if the next chose coin == any coin in array, clear it and add two of same coin
            for (int i = 0; i < this.gameState.getCoinTracking().size(); i++) {
                if (((SplendorCoinSelectAction) action).getChosenCoin()
                        == this.gameState.getCoinTracking().get(i)) {

                    this.gameState.getCoinTracking().clear();
                    this.gameState.getCoinTracking().add(
                            ((SplendorCoinSelectAction) action).getChosenCoin());
                    this.gameState.getCoinTracking().add(
                            ((SplendorCoinSelectAction) action).getChosenCoin());
                    this.gameState.setBoughtCard(false); // this resets all the information that action has happened
                    this.gameState.setReserveSuccess(false);
                    this.gameState.setTakenCoins(false);
                    this.gameState.setNobleTaken(false);
                    return true;
                }
            }
            // if all different coins, pop the front of the arrayList then add the chosen coin to back
            this.gameState.getCoinTracking().remove(0);
            this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
            this.gameState.setBoughtCard(false); // this resets all the information that action has happened
            this.gameState.setReserveSuccess(false);
            this.gameState.setTakenCoins(false);
            this.gameState.setNobleTaken(false);
            return true;
        } else {
            // add chosen coins to selected, not filled
            for (int i = 0; i < this.gameState.getCoinTracking().size(); i++) {
                if (((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)) {
                    this.gameState.getCoinTracking().clear();
                    this.gameState.getCoinTracking().add(
                            ((SplendorCoinSelectAction) action).getChosenCoin());
                    this.gameState.getCoinTracking().add(
                            ((SplendorCoinSelectAction) action).getChosenCoin());
                    this.gameState.setBoughtCard(false); // this resets all the information that action has happened
                    this.gameState.setReserveSuccess(false);
                    this.gameState.setTakenCoins(false);
                    this.gameState.setNobleTaken(false);
                    return true;
                }
            }
            this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
            this.gameState.setBoughtCard(false); // this resets all the information that action has happened
            this.gameState.setReserveSuccess(false);
            this.gameState.setTakenCoins(false);
            this.gameState.setNobleTaken(false);
            return true;
        }
    }

    /**
     * nextPlayerSim - simulates changing the player's id to the next chronologically
     * used to keep track of who has gone in extraTurn
     *
     * @param playerId the player get the next player of
     * @return
     */
    private int nextPlayerSim(int playerId) {
        return (playerId + 1 % gameState.getPlayerCount());
    }

    /**
     * SplendorGameState() gets the current gamestate
     *
     * @return SplendorGameState the current game state
     */
    public SplendorGameState getLocalGameGameState() {
        return this.gameState;
    }
}
