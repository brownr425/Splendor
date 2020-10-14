package edu.up.cs301.splendor;

public class Card {
    private int rPrice;
    private int bPrice;

    private int gPrice;
    private int wPrice;
    private int brPrice;

    private int colorGem;
    private int cardLevel;
    private int prestigePoints;


    public Card(int rPrice, int bPrice, int gPrice, int wPrice, int brPrice, int colorGem, int cardLevel, int prestigePoints) {
        this.rPrice = rPrice;
        this.bPrice = bPrice;
        this.gPrice = gPrice;
        this.wPrice = wPrice;
        this.brPrice = brPrice;
        this.colorGem = colorGem;
        this.cardLevel = cardLevel;
        this.prestigePoints = prestigePoints;
    }
    
    //Copy Constructor 
    public Card(Card cardToCopy) {
        this.rPrice = cardToCopy.rPrice;
        this.bPrice = cardToCopy.bPrice;
        this.gPrice = cardToCopy.gPrice;
        this.wPrice = cardToCopy.wPrice;
        this.brPrice = cardToCopy.brPrice;
        this.colorGem = cardToCopy.colorGem;
        this.cardLevel = cardToCopy.cardLevel;
        this.prestigePoints = cardToCopy.prestigePoints;
    }

}
