package edu.up.cs301.splendor.Players;

import edu.up.cs301.splendor.Game.Hand;

public class SplendorPlayer {
    private String playerName;
    private int playerID;

    private int prestigePts;

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
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }
}
