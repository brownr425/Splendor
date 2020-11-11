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

    protected String checkIfGameOver() {return "";}

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof splCoinAction){
            splCoinAction sCOINa = (splCoinAction) action;

           // gameState.coinAction();
            //action was made, return true/valid move
            return true;
        }
        else if(action instanceof splCardAction)
        {
            splCardAction sCARDa = (splCardAction) action;
            //gameState.
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
                this.gameState.getCoinTracking().remove(0);
                this.gameState.getCoinTracking().set(0, this.gameState.getCoinTracking().get(1));
            }
            else {
                for(int i = 0; i < this.gameState.getCoinTracking().size(); i++)
                {
                    if(((SplendorCoinSelectAction) action).getChosenCoin() == this.gameState.getCoinTracking().get(i)){
                        this.gameState.getCoinTracking().clear();
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                    }
                    else{
                        this.gameState.getCoinTracking().add(((SplendorCoinSelectAction) action).getChosenCoin());
                    }
                }
            }
        }
        else{

            return false;
        }
        return false;
    }


}
