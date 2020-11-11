package edu.up.cs301.splendor.Game;

public class CoinStack {
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int WHITE = 4;
    public static final int BROWN = 5;
    public static final int GOLD = 6;

    private static int colorCount = 1;

    private int numCoins;

    private int color;

    public CoinStack() {
        if(colorCount == GOLD) this.numCoins = 5;
        else this.numCoins = 7;
        this.color = colorCount;
        this.colorCount++;
    }
}
