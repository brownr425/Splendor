package edu.up.cs301.splendor.Game;

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
    //card factory constructor
    public Card() {
        this.rPrice = 0;
        this.bPrice = 0;
        this.gPrice = 0;
        this.wPrice = 0;
        this.brPrice = 0;
        this.colorGem = 0;
        this.cardLevel = 0;
        this.prestigePoints = 0;
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

    @Override
    public String toString(){
        String ret;
        ret = "\n----" +
                "\nCard Level: " + cardLevel +
                "\t Gem Color: " + colorGem +
                "\t Prestige Points:  " + prestigePoints +
                "\t Price of Card: " +
                "\t Ruby: " + rPrice +
                "\t Sapphire: " + bPrice +
                "\t Emerald: " + gPrice +
                "\t Diamond: " + wPrice +
                "\t Onyx: " + brPrice;

        return ret;
    }

    public int getrPrice() {
        return rPrice;
    }

    public void setrPrice(int rPrice) {
        this.rPrice = rPrice;
    }

    public int getbPrice() {
        return bPrice;
    }

    public void setbPrice(int bPrice) {
        this.bPrice = bPrice;
    }

    public int getgPrice() {
        return gPrice;
    }

    public void setgPrice(int gPrice) {
        this.gPrice = gPrice;
    }

    public int getwPrice() {
        return wPrice;
    }

    public void setwPrice(int wPrice) {
        this.wPrice = wPrice;
    }

    public int getBrPrice() {
        return brPrice;
    }

    public void setBrPrice(int brPrice) {
        this.brPrice = brPrice;
    }

    public int getColorGem() {
        return colorGem;
    }

    public void setColorGem(int colorGem) {
        this.colorGem = colorGem;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public int getPrestigePoints() {
        return prestigePoints;
    }

    public void setPrestigePoints(int prestigePoints) {
        this.prestigePoints = prestigePoints;
    }
}
