package edu.up.cs301.splendor.State;

import edu.up.cs301.splendor.Game.Hand;

public class SplendorPlayer {
    private String playerName;
    private int playerID;

    private int prestigePts;

    //point values for each player
    private int goldPts;
    private int rubyPts;
    private int sapphPts;
    private int emerPts;
    private int diaPts;
    private int onyxPts;

    //coin counts for each player
    private int goldCoins;
    private int rubyCoins;
    private int sapphCoins;
    private int emerCoins;
    private int diaCoins;
    private int onyxCoins;

    //player's hand
    private Hand playerHand;

    public SplendorPlayer(){
        initializePtsNCoins();
    }
    /**
     * constructor SplendorPlayer() initialize all values for new object
     *
     * @param playerID - player's id, 1-4
     *
     */
    public SplendorPlayer(int playerID) {
        initializePtsNCoins();
    }

    /**
     * Constructor playerToCopy() deep copy for game state
     *
     * @param playerToCopy - player to copy to instance
     */
    public SplendorPlayer(SplendorPlayer playerToCopy) {
        this.playerName = playerToCopy.playerName;
        this.playerID = playerToCopy.getPlayerID();
        this.prestigePts = playerToCopy.getPrestigePts();

        this.rubyPts = playerToCopy.getRubyPts();
        this.sapphPts = playerToCopy.getSapphPts();
        this.emerPts = playerToCopy.getEmerPts();
        this.diaPts = playerToCopy.getDiaPts();
        this.onyxPts = playerToCopy.getOnyxPts();

        this.goldCoins = playerToCopy.getGoldCoins();
        this.rubyCoins = playerToCopy.getRubyCoins();
        this.sapphCoins = playerToCopy.getSapphCoins();
        this.emerCoins = playerToCopy.getEmerCoins();
        this.diaCoins = playerToCopy.getDiaCoins();
        this.onyxCoins = playerToCopy.getOnyxCoins();

        this.playerHand = new Hand(playerToCopy.getPlayerHand());
    }

    /**
     * initializePtsNCoins() sets coin hands to have zero coins
     */
    private void initializePtsNCoins() {
        this.goldCoins = 0;
        this.rubyCoins = 0;
        this.rubyPts = 0;
        this.sapphCoins = 0;
        this.sapphPts = 0;
        this.emerCoins = 0;
        this.emerPts = 0;
        this.diaCoins = 0;
        this.diaPts = 0;
        this.onyxCoins = 0;
        this.onyxPts = 0;
        this.prestigePts = 0;
        this.playerHand = new Hand();
    }

    public Hand getPlayerHand() { return playerHand; }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public int getPlayerID() { return playerID; }

    public void setPlayerID(int playerID) { this.playerID = playerID; }

    public int getPrestigePts() { return prestigePts; }

    public void setPrestigePts(int prestigePts) { this.prestigePts = prestigePts; }

    public int getGoldPts() { return goldPts; }

    public void setGoldPts(int goldPts) { this.goldPts = goldPts;}

    public int getRubyPts() { return rubyPts; }

    public void setRubyPts(int rubyPts) { this.rubyPts = rubyPts; }

    public int getSapphPts() { return sapphPts; }

    public void setSapphPts(int sapphPts) { this.sapphPts = sapphPts; }

    public int getEmerPts() { return emerPts; }

    public void setEmerPts(int emerPts) { this.emerPts = emerPts; }

    public int getDiaPts() { return diaPts; }

    public void setDiaPts(int diaPts) { this.diaPts = diaPts; }

    public int getOnyxPts() { return onyxPts; }

    public void setOnyxPts(int onyxPts) { this.onyxPts = onyxPts; }

    public int getGoldCoins() { return goldCoins; }

    public void setGoldCoins(int goldCoins) { this.goldCoins = goldCoins; }

    public int getRubyCoins() { return rubyCoins; }

    public void setRubyCoins(int rubyCoins) { this.rubyCoins = rubyCoins; }

    public int getSapphCoins() { return sapphCoins; }

    public void setSapphCoins(int sapphCoins) { this.sapphCoins = sapphCoins; }

    public int getEmerCoins() { return emerCoins; }

    public void setEmerCoins(int emerCoins) { this.emerCoins = emerCoins; }

    public int getDiaCoins() { return diaCoins; }

    public void setDiaCoins(int diaCoins) { this.diaCoins = diaCoins; }

    public int getOnyxCoins() { return onyxCoins; }

    public void setOnyxCoins(int onyxCoins) { this.onyxCoins = onyxCoins; }

    public int getTotalCoins() {return this.rubyCoins+this.sapphCoins+this.emerCoins+this.diaCoins+this.onyxCoins+this.goldCoins;}
}
