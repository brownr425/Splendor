package edu.up.cs301.splendor.Players;

import edu.up.cs301.splendor.Game.Hand;

public class SplendorPlayer {
    private String playerName;
    private int playerID;

    private int prestigePts;

    private int goldPts;
    private int rubyPts;
    private int sapphPts;
    private int emerPts;
    private int diaPts;
    private int onyxPts;

    private int goldCoins;
    private int rubyCoins;
    private int sapphCoins;
    private int emerCoins;
    private int diaCoins;
    private int onyxCoins;

    private Hand playerHand;

    private int numCardsReserved;

    public SplendorPlayer() {
        initializePtsNCoins();
    }

    /**
     * Constructor playerToCopy - deep copy for game state
     * @param playerToCopy - player to send
     */
    public SplendorPlayer(SplendorPlayer playerToCopy) {
        this.playerName = playerToCopy.playerName;
        this.playerID = playerToCopy.playerID;
        this.prestigePts = playerToCopy.prestigePts;

        this.rubyPts = playerToCopy.rubyPts;
        this.sapphPts = playerToCopy.sapphPts;
        this.emerPts = playerToCopy.emerPts;
        this.diaPts = playerToCopy.diaPts;
        this.onyxPts = playerToCopy.onyxPts;

        this.goldCoins = playerToCopy.goldCoins;
        this.rubyCoins = playerToCopy.rubyCoins;
        this.sapphCoins = playerToCopy.sapphCoins;
        this.emerCoins = playerToCopy.emerCoins;
        this.diaCoins = playerToCopy.diaCoins;
        this.onyxCoins = playerToCopy.onyxCoins;

        this.playerHand = new Hand(playerToCopy.playerHand);
    }

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
        this.numCardsReserved = 0;
        this.playerHand = new Hand();
    }

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

    public int getNumCardsReserved() { return numCardsReserved; }

    public void setNumCardsReserved(int numCardsReserved) { this.numCardsReserved = numCardsReserved; }

}
