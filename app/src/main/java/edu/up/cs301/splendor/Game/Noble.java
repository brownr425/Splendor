package edu.up.cs301.splendor.Game;

public class Noble {
    private int rPrice;
    private int bPrice;
    private int gPrice;
    private int wPrice;
    private int brPrice;

    private final int PRESTIGE_POINTS = 3;

    public Noble(int rPrice, int bPrice, int gPrice, int wPrice, int brPrice){
        this.rPrice = rPrice;
        this.bPrice = bPrice;
        this.gPrice = gPrice;
        this.wPrice = wPrice;
        this.brPrice = brPrice;
    }

    public Noble() {
        this.rPrice = -1;
        this.bPrice = -1;
        this.gPrice = -1;
        this.wPrice = -1;
        this.brPrice = -1;
    }

    //COPY CONSTRUCTOR
    public Noble(Noble nobelToCopy){
        this.rPrice = nobelToCopy.rPrice;
        this.bPrice = nobelToCopy.bPrice;
        this.gPrice = nobelToCopy.gPrice;
        this.wPrice = nobelToCopy.wPrice;
        this.brPrice = nobelToCopy.brPrice;
    }

    @Override
    public String toString(){
        String ret;
        ret = "\nPrice of Noble: " +
                "\nRuby: " + rPrice +
                "\nSapphire: " + bPrice +
                "\nEmerald: " + gPrice +
                "\nDiamond: " + wPrice +
                "\nOnyx: " + brPrice;
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

    public int getPrestigePoints() {
        return PRESTIGE_POINTS;
    }
}
