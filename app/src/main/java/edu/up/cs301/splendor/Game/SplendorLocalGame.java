package edu.up.cs301.splendor.Game;

import android.util.Log;


import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorLocalGame extends LocalGame {
    private SplendorGameState gameState;

    public SplendorLocalGame() {
        this.gameState = new SplendorGameState();
    }

    protected void sendUpdatedStateTo(GamePlayer player){
        player.sendInfo(new SplendorGameState(gameState));
    }

    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getPlayerTurn();
    }

    protected String checkIfGameOver() {
        String playerWinner = "";
        if(gameState.getSplendorPlayer1().getPrestigePts() >= 15)
            playerWinner = "Player 1";
        else if(gameState.getSplendorPlayer2().getPrestigePts() >= 15)
            playerWinner = "Player 2";
        else if(gameState.getSplendorPlayer3().getPrestigePts() >= 15)
            playerWinner = "Player 3";
        else if(gameState.getSplendorPlayer4().getPrestigePts() >= 15)
            playerWinner = "Player 4";
        else return null;
        return playerWinner + " wins!";
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof SplendorCoinAction){
            if(this.gameState.getCoinTracking().isEmpty() || this.gameState.getCoinTracking().size() == 1)
            {
                return false;
            }
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
            {
                if(this.gameState.getCoinTracking().get(i).equals(this.gameState.getCoinTracking().get(i+1)))
                {
                    if (this.gameState.coinAction(this.gameState.getCoinTracking().get(i))) {
                        this.gameState.getCoinTracking().clear();
                        return true;
                    } else  {
                        return false;
                    }
                }
            }
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
            {
                if(this.gameState.getCoinTracking().get(i) == null)
                {
                    return false;
                }
            }


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
        else if(action instanceof SplendorCardAction) {
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
        else if(action instanceof SplendorSelectCardAction){
            this.gameState.setSelected(this.gameState.getBoard(((SplendorSelectCardAction) action).getRow(), ((SplendorSelectCardAction) action).getCol()));
            this.gameState.setSelectedRow(((SplendorSelectCardAction) action).getRow());
            this.gameState.setSelectedCol(((SplendorSelectCardAction) action).getCol());
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorReserveCardAction){
            SplendorReserveCardAction sRESca = (SplendorReserveCardAction) action;
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorCoinSelectAction)
        {
            Log.d("but", "works Jacob");
            if(this.gameState.getCoinTracking().isEmpty())
            {
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
            if(this.gameState.getCoinTracking().size() == 3) {
                for(int i = 0; i < this.gameState.getCoinTracking().size(); i++)
                {
                    if(((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)){
                        this.gameState.getCoinTracking().clear();
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        return true;
                    }
                }
                this.gameState.getCoinTracking().remove(0);
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
            else {
                for(int i = 0; i < this.gameState.getCoinTracking().size(); i++)
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
        else{
            return false;
        }
    }
}
