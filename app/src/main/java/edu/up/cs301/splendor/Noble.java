package edu.up.cs301.splendor;

public class Noble {
    private int rPrice;
    private int bPrice;
    private int gPrice;
    private int wPrice;
    private int brPrice;

    private int prestigePoints;

    public Noble(int rPrice, int bPrice, int gPrice, int wPrice, int brPrice, int prestigePoints){
        this.rPrice = rPrice;
        this.bPrice = bPrice;
        this.gPrice = gPrice;
        this.wPrice = wPrice;
        this.brPrice = brPrice;
        this.prestigePoints = prestigePoints;
    }

    //COPY CONSTRUCTOR
    public Noble(Noble nobelToCopy){
        this.rPrice = nobelToCopy.rPrice;
        this.bPrice = nobelToCopy.bPrice;
        this.gPrice = nobelToCopy.gPrice;
        this.wPrice = nobelToCopy.wPrice;
        this.brPrice = nobelToCopy.brPrice;
        this.prestigePoints = nobelToCopy.prestigePoints;


    }


}
