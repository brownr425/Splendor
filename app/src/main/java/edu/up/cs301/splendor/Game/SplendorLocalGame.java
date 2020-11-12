package edu.up.cs301.splendor.Game;

import java.io.InputStream;


import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.splCardAction;
import edu.up.cs301.splendor.Actions.splCoinAction;
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
        if (this.gameState.getSplendorPlayer1().getPrestigePts() == 15 ||
                this.gameState.getSplendorPlayer2().getPrestigePts() == 15 ||
                this.gameState.getSplendorPlayer3().getPrestigePts() == 15 ||
                this.gameState.getSplendorPlayer4().getPrestigePts() == 15) {
            return "";
        }
        else {
            return null;
        }
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof splCoinAction){
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
            {
                if(this.gameState.getCoinTracking().get(i).equals(this.gameState.getCoinTracking().get(i+1)))
                {
                    return this.gameState.coinAction(this.gameState.getCoinTracking().get(i));
                }
            }
            for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
            {
                if(this.gameState.getCoinTracking().get(i) == null)
                {
                    return false;
                }
            }
            return this.gameState.coinAction(this.gameState.getCoinTracking().get(0),
                    this.gameState.getCoinTracking().get(1),
                    this.gameState.getCoinTracking().get(2));
            //action was made, return true/valid move
        }
        else if(action instanceof splCardAction)
        {
            if(this.gameState.getSelected() != null)
            {
                this.gameState.cardAction(this.gameState.getSelected());
            }
            else{
                return false;
            }
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof SplendorSelectCardAction){
            this.gameState.setSelected(this.gameState.getBoard(((SplendorSelectCardAction) action).getRow(), ((SplendorSelectCardAction) action).getCol()));
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
            if(this.gameState.getCoinTracking().size() == 3) {
                for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
                {
                    if(((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)){
                        this.gameState.getCoinTracking().clear();
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        return true;
                    }
                }
                this.gameState.getCoinTracking().remove(0);
                this.gameState.getCoinTracking().set(0, this.gameState.getCoinTracking().get(1));
                this.gameState.getCoinTracking().remove(1);
                this.gameState.getCoinTracking().set(0, this.gameState.getCoinTracking().get(2));
                this.gameState.getCoinTracking().remove(2);
                this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                return true;
            }
            else {
                for(int i = 0; i < this.gameState.getCoinTracking().size()-1; i++)
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
