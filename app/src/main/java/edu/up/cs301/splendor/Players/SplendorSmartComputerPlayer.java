package edu.up.cs301.splendor.Players;

import edu.up.cs301.splendor.Game.Card;
import edu.up.cs301.splendor.State.SplendorGameState;

public class SplendorSmartComputerPlayer extends SplendorComputerPlayer {
    private SplendorGameState game;
    private int playerId;
    private String[] playerNames;
    private String name;

    public SplendorSmartComputerPlayer(String name) {
        super(name);
    }

    public void receiveInfo(SplendorGameState info) {}
    public boolean canBuy(Card card) {
        return true;
    }
    public boolean canMove(int playerID) {
        return true;
    }
    public Card seekCard(Card[][] cardsInPlay, int playerId) {
        return null;
    }
    public Card opponentCanBuy(Card[][] cardsInPlay){return null; }
}
