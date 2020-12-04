package edu.up.cs301.splendor.Game;


import edu.up.cs301.splendor.Actions.QuitAction;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
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

    public SplendorLocalGame(int num) {
        this.gameState = new SplendorGameState(num);
    }

    protected void sendUpdatedStateTo(GamePlayer player){
        player.sendInfo(new SplendorGameState(gameState));
    }

    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getPlayerTurn();
    }

    protected String checkIfGameOver() {
        for(int i = 0; i < this.gameState.getPlayerList().size(); i++)
        {
            if(this.gameState.getPlayer(i).getPrestigePts() >= 15)
                return "Congratulatons Player " + (this.gameState.getTrueWin(i)+1);
        }
        return null;
    }

    /**
     * makeMove(GameAction action)
     * where players make moves, differentiated actions based on what action the player sends.
     * @param action is the action the player sent, will check if it is instance of any actions we have
     * */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof QuitAction) {

        }
        // if action is player clicking a "take coins button"
        if (action instanceof SplendorCoinAction){
            if(this.gameState.getCoinTracking().isEmpty() || this.gameState.getCoinTracking().size() == 1) // check if selected coin array is empty or one coin
            {
                return false;
            }
            if(this.gameState.getCoinTracking().size() == 2) // check if coin array has only 2 coins that aren't the same
            {
                if(this.gameState.getCoinTracking().get(0) != (this.gameState.getCoinTracking().get(1)))
                {
                    return false;
                }
            }
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++) // go through array and check if any coins equal each other
            {
                if(this.gameState.getCoinTracking().get(i).equals(this.gameState.getCoinTracking().get(i+1)))
                {
                    if (this.gameState.coinAction(this.gameState.getCoinTracking().get(i))) {
                        this.gameState.getCoinTracking().clear(); // need to clear the array once action is made, to get ready for next turn
                        return true;
                    } else  {
                        return false;
                    }
                }
            }
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++) // this is another check to make sure all 3 coins are filled for 3 different coins
            {
                if(this.gameState.getCoinTracking().get(i) == null)
                {
                    return false;
                }
            }

            if (this.gameState.coinAction(this.gameState.getCoinTracking().get(0), // this is to take 3 different coins, clear array when true
                    this.gameState.getCoinTracking().get(1),
                    this.gameState.getCoinTracking().get(2))) {
                this.gameState.getCoinTracking().clear();
                return true;
            } else {
                return false;
            }
            //action was made, return true/valid move
        }
        else if(action instanceof SplendorCardAction) { // this is action to buy card that is in the board array that everyone can access
            if(this.gameState.getSelected() != null)
            {
                this.gameState.cardAction(this.gameState.getSelected(), ((SplendorCardAction) action).getRow(), ((SplendorCardAction) action).getCol());
            }
            else{
                return false;
            }
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorSelectCardAction){ // this is if a player simply presses a card on the board
            if(((SplendorSelectCardAction) action).getCol() == -1) // check if the card selected is a player's reserved card, if it is, then do this
            {
                if(this.gameState.getPlayer(this.gameState.getPlayerTurn()).getPlayerHand().getReserved().size() > ((SplendorSelectCardAction) action).getRow())
                {
                    this.gameState.setSelected(this.gameState.getPlayer(this.gameState.getPlayerTurn()).getPlayerHand().getReserved().get(((SplendorSelectCardAction) action).getRow()));
                    return true;
                }
                return false;
            }
            this.gameState.setSelected(this.gameState.getBoard(((SplendorSelectCardAction) action).getRow(), ((SplendorSelectCardAction) action).getCol()));
            this.gameState.setSelectedRow(((SplendorSelectCardAction) action).getRow());
            this.gameState.setSelectedCol(((SplendorSelectCardAction) action).getCol()); // set the row and column of where the card was selected so game knows what card to replace
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorReserveCardAction){
            this.gameState.reserveAction(this.gameState.getSelected(), this.gameState.getSelectedRow(), this.gameState.getSelectedCol());
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorCoinSelectAction) // this is when a player presses coin buttons, to select them
        {
            if(this.gameState.getCoinTracking().isEmpty()) // this is if the array is empty, add the one chosen coin
            {
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
            if(this.gameState.getCoinTracking().size() == 3) { // if size is 3, that means the array is filled
                for(int i = 0; i < this.gameState.getCoinTracking().size(); i++) // iterate through and if the next chose coin == any coin in array, clear it and add two of same coin
                {
                    if(((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)){
                        this.gameState.getCoinTracking().clear();
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        return true;
                    }
                }
                this.gameState.getCoinTracking().remove(0); // if not, then pop the front of the arrayList then add the chosen coin to back
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
            else {
                for(int i = 0; i < this.gameState.getCoinTracking().size(); i++) // this will do the same thing, except array isn't filled yet
                {
                    if(((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)){
                        this.gameState.getCoinTracking().clear();
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        return true;
                    }
                }
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
        }
        else if(action instanceof SplendorReturnCoinAction)
        {
            for(int i = 0; i < this.gameState.getCoinTracking().size(); i++)
            {
                this.gameState.returnCoins(this.gameState.getCoinTracking().get(i));
            }
            this.gameState.getCoinTracking().clear();
            return true;
        }
        else{
            return false;
        }
    }
}
