package edu.up.cs301.splendor.Players;

import edu.up.cs301.counter.CounterState;
import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.splendor.Actions.QuitAction;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.splCardAction;
import edu.up.cs301.splendor.Actions.splCoinAction;
import edu.up.cs301.splendor.Setup.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.GameInfo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.TextView;

import edu.up.cs301.splendor.Game.Hand;
import edu.up.cs301.splendor.State.SplendorGameState;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 *
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class SplendorHumanPlayer extends GameHumanPlayer implements OnClickListener {

    /* instance variables */
    private int prestigePoints;
    private int rubyCurrency;
    private int diamondCurrency;
    private int emeraldCurrency;
    private int goldCurrency;
    private int onyxCurrency;
    private int sapphireCurrency;
    private Hand hand;

    // the most recent game state, as given to us by the CounterLocalGame
    private SplendorGameState state;

    // the android activity that we are running
    private GameMainActivity myActivity;

    //Everything on the GUI that gets updated TODO: RANK card stack for random reserve
    private ImageButton nobleCard1;
    private ImageButton nobleCard2;
    private ImageButton nobleCard3;
    private ImageButton nobleCard4;
    private ImageButton nobleCard5;

    private ImageButton rank3Card1;
    private ImageButton rank3Card2;
    private ImageButton rank3Card3;
    private ImageButton rank3Card4;

    private ImageButton rank2Card1;
    private ImageButton rank2Card2;
    private ImageButton rank2Card3;
    private ImageButton rank2Card4;

    private ImageButton rank1Card1;
    private ImageButton rank1Card2;
    private ImageButton rank1Card3;
    private ImageButton rank1Card4;

    //coins I buttons
    ImageButton emeraldCoin;
    ImageButton diamondCoin;
    ImageButton sapphireCoin;
    ImageButton onyxCoin;
    ImageButton rubyCoin;
    ImageButton goldCoin;

    //Buy button
    Button buyButton;
    //Take Coins
    Button coinButton;

    TextView coinB;



    // infoBox
    private TextView infoBox;

    //player points values
    private TextView p1Emerald;
    private TextView p1Diamond;
    private TextView p1Sapphire;
    private TextView p1Onyx;
    private TextView p1Ruby;
    private TextView p1Gold;
    private TextView p1PrestigePt;

    private TextView p2Emerald;
    private TextView p2Diamond;
    private TextView p2Sapphire;
    private TextView p2Onyx;
    private TextView p2Ruby;
    private TextView p2Gold;
    private TextView p2PrestigePt;

    private TextView p3Emerald;
    private TextView p3Diamond;
    private TextView p3Sapphire;
    private TextView p3Onyx;
    private TextView p3Ruby;
    private TextView p3Gold;
    private TextView p3PrestigePt;

    private TextView p4Emerald;
    private TextView p4Diamond;
    private TextView p4Sapphire;
    private TextView p4Onyx;
    private TextView p4Ruby;
    private TextView p4Gold;
    private TextView p4PrestigePt;

    /**
     * constructor
     * @param name
     * 		the player's name
     */
    public SplendorHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * sets the counter value in the text view
     */
    protected void updateDisplay() {
        // set the text in the appropriate widget
        //counterValueTextView.setText("" + state.getCounter());

        //TODO: update player values. coins


        //for now, card/coin images are default
        rank3Card1.setImageResource(R.drawable.background1);
        rank3Card2.setImageResource(R.drawable.background2);
        rank3Card3.setImageResource(R.drawable.background3);
        rank3Card4.setImageResource(R.drawable.background4);

        rank2Card1.setImageResource(R.drawable.background1);
        rank2Card2.setImageResource(R.drawable.background2);
        rank2Card3.setImageResource(R.drawable.background3);
        rank2Card4.setImageResource(R.drawable.background4);

        rank1Card1.setImageResource(R.drawable.background1);
        rank1Card2.setImageResource(R.drawable.background2);
        rank1Card3.setImageResource(R.drawable.background3);
        rank1Card4.setImageResource(R.drawable.background4);

        nobleCard1.setImageResource(R.drawable.noble1try2);
        nobleCard2.setImageResource(R.drawable.noble2);
        nobleCard3.setImageResource(R.drawable.noble3);
        nobleCard4.setImageResource(R.drawable.noble4);
        nobleCard5.setImageResource(R.drawable.noble5);

        emeraldCoin.setImageResource(R.drawable.emerald);
        diamondCoin.setImageResource(R.drawable.diamond);
        sapphireCoin.setImageResource(R.drawable.sapphire);
        onyxCoin.setImageResource(R.drawable.onyx);
        rubyCoin.setImageResource(R.drawable.ruby);
        goldCoin.setImageResource(R.drawable.gold);


        //player point values
        p1Emerald.setText("" + (state.getSplendorPlayer1().getEmerCoins() + state.getSplendorPlayer1().getEmerPts()));
        p1Diamond.setText("" + (state.getSplendorPlayer1().getDiaCoins()+ state.getSplendorPlayer1().getDiaPts()));
        p1Sapphire.setText(""+ (state.getSplendorPlayer1().getSapphCoins()+ state.getSplendorPlayer1().getSapphPts()));
        p1Onyx.setText("" + (state.getSplendorPlayer1().getOnyxCoins()+ state.getSplendorPlayer1().getOnyxPts()));
        p1Ruby.setText("" + (state.getSplendorPlayer1().getRubyCoins()+ state.getSplendorPlayer1().getRubyPts()));
        p1Gold.setText("" + state.getSplendorPlayer1().getGoldCoins());
        p1PrestigePt.setText(""+ state.getSplendorPlayer1().getPrestigePts());

        p2Emerald.setText("" + (state.getSplendorPlayer2().getEmerCoins() + state.getSplendorPlayer2().getEmerPts()));
        p2Diamond.setText("" + (state.getSplendorPlayer2().getDiaCoins()+ state.getSplendorPlayer2().getDiaPts()));
        p2Sapphire.setText(""+ (state.getSplendorPlayer2().getSapphCoins()+ state.getSplendorPlayer2().getSapphPts()));
        p2Onyx.setText("" + (state.getSplendorPlayer2().getOnyxCoins()+ state.getSplendorPlayer2().getOnyxPts()));
        p2Ruby.setText("" + (state.getSplendorPlayer2().getRubyCoins()+ state.getSplendorPlayer2().getRubyPts()));
        p2Gold.setText("" + state.getSplendorPlayer2().getGoldCoins());
        p2PrestigePt.setText(""+ state.getSplendorPlayer2().getPrestigePts());

        p3Emerald.setText("" + (state.getSplendorPlayer3().getEmerCoins() + state.getSplendorPlayer3().getEmerPts()));
        p3Diamond.setText("" + (state.getSplendorPlayer3().getDiaCoins()+ state.getSplendorPlayer3().getDiaPts()));
        p3Sapphire.setText(""+ (state.getSplendorPlayer3().getSapphCoins()+ state.getSplendorPlayer3().getSapphPts()));
        p3Onyx.setText("" + (state.getSplendorPlayer3().getOnyxCoins()+ state.getSplendorPlayer3().getOnyxPts()));
        p3Ruby.setText("" + (state.getSplendorPlayer3().getRubyCoins()+ state.getSplendorPlayer3().getRubyPts()));
        p3Gold.setText("" + state.getSplendorPlayer3().getGoldCoins());
        p3PrestigePt.setText(""+ state.getSplendorPlayer3().getPrestigePts());

        p4Emerald.setText("" + (state.getSplendorPlayer4().getEmerCoins() + state.getSplendorPlayer4().getEmerPts()));
        p4Diamond.setText("" + (state.getSplendorPlayer4().getDiaCoins()+ state.getSplendorPlayer4().getDiaPts()));
        p4Sapphire.setText(""+ (state.getSplendorPlayer4().getSapphCoins()+ state.getSplendorPlayer4().getSapphPts()));
        p4Onyx.setText("" + (state.getSplendorPlayer4().getOnyxCoins()+ state.getSplendorPlayer4().getOnyxPts()));
        p4Ruby.setText("" + (state.getSplendorPlayer4().getRubyCoins()+ state.getSplendorPlayer4().getRubyPts()));
        p4Gold.setText("" + state.getSplendorPlayer4().getGoldCoins());
        p4PrestigePt.setText(""+ state.getSplendorPlayer4().getPrestigePts());

        coinB.setText("CB: R:" + state.getRubyCoins()+ "B:"+state.getSapphireCoins()+"G:"+state.getEmeraldCoins()+"W:"+state.getDiamondCoins()+"Br:"+state.getOnyxCoins());

        //Update info box to reflect selected card
        if(state.getSelected() != null) {
            String info = state.getSelected().toString();
            infoBox.setText(info);
        } else {
            infoBox.setText("Your card info will be shown here");
        }

    }

    /**
     * this method gets called when the user clicks the '+' or '-' button. It
     * creates a new CounterMoveAction to return to the parent activity.
     *
     * @param button
     * 		the button that was clicked
     */

    //TODO figure how cards can get passed into this method
    //I am thinking that when cards are selected well pass the location in the gameboard array and
    //in its handler ill set a new instance variable Card SelectedCard to the appropriate Card.
    //that way when the actions are called they perform the action on the last selected card.

    public void onClick(View button) {
        // if we are not yet connected to a game, ignore
        if (game == null) return;

        // Construct the action and send it to the game
        GameAction action = null;
        if(button.getId() == R.id.quitButton) {
            action = new QuitAction(this);
        }
        else if (button.getId() == R.id.buyAction) {
            // plus button: create "increment" action
            Log.d("SHP", "BUY");
            action = new splCardAction(this, this.state.getSelected(), this.state.getSelectedRow(), this.state.getSelectedCol());
        }
        else if (button.getId() == R.id.reserveAction) {
            // minus button: create "decrement" action
          //  action = new SplendorReserveCardAction(this, null);
        }
        else if (button.getId() == R.id.coinAction){

          action = new splCoinAction(this);
        }
        else if (button.getId() == R.id.nobleCard1){
            // something else was pressed: ignore
           // action = new SplendorSelectCardAction(this,0,0);
    }
        else if (button.getId() == R.id.nobleCard2){
            // something else was pressed: ignore
            //action = new SplendorSelectCardAction(this,0,1);
        }
        else if (button.getId() == R.id.nobleCard3){
            // something else was pressed: ignore
           // action = new SplendorSelectCardAction(this,0,2);
        }
        else if (button.getId() == R.id.nobleCard4){
            // something else was pressed: ignore
           // action = new SplendorSelectCardAction(this,0,3);
        }
        else if (button.getId() == R.id.rank1Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2,0);
        }
        else if (button.getId() == R.id.rank1Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,1);
        }
        else if (button.getId() == R.id.rank1Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,2);
        }
        else if (button.getId() == R.id.rank1Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,3);
        }
        else if (button.getId() == R.id.rank2Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1,0);
        }
        else if (button.getId() == R.id.rank2Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,1);
        }
        else if (button.getId() == R.id.rank2Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,2);
        }
        else if (button.getId() == R.id.rank2Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,3);
        }
        else if (button.getId() == R.id.rank3Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0,0);
        }
        else if (button.getId() == R.id.rank3Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,1);
        }
        else if (button.getId() == R.id.rank3Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,2);
        }
        else if (button.getId() == R.id.rank3Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,3);
        }
        else if(button.getId() == R.id.rubyCoin){
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 0);
        }
        else if(button.getId() == R.id.sapphireCoin){
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 1);
        }
        else if(button.getId() == R.id.emeraldCoin){
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 2);
        }
        else if(button.getId() == R.id.diamondCoin){
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 3);
        }
        else if(button.getId() == R.id.onyxCoin){
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 4);
        }
        else if(button.getId() == R.id.quitButton){
            System.exit(0);
        }
        game.sendAction(action); // send action to the game
        updateDisplay();
    }// onClick

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a CounterState message
        if (!(info instanceof SplendorGameState)) return;
        this.state = (SplendorGameState) info;

        // update our state; then update the display
        this.state = (SplendorGameState)info;
        updateDisplay();
    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.splendor_human_player);

        //info box for info to be given too
        infoBox = (TextView) activity.findViewById(R.id.infoGiven);

        // make noble cards and set on click listener to display noble card information
        nobleCard1 = (ImageButton) activity.findViewById(R.id.nobleCard1);
        nobleCard1.setOnClickListener(this);
        nobleCard2 = (ImageButton) activity.findViewById(R.id.nobleCard2);
        nobleCard2.setOnClickListener(this);
        nobleCard3 = (ImageButton) activity.findViewById(R.id.nobleCard3);
        nobleCard3.setOnClickListener(this);

        nobleCard4 = (ImageButton) activity.findViewById(R.id.nobleCard4);
        nobleCard4.setOnClickListener(this);
        nobleCard5 = (ImageButton) activity.findViewById(R.id.nobleCard5);
        nobleCard5.setOnClickListener(this);

        //image buttons for all the rank cards 1-3; starting with 3
        rank3Card1 = (ImageButton) activity.findViewById(R.id.rank3Card1);
        rank3Card1.setOnClickListener(this);
        rank3Card2 = (ImageButton) activity.findViewById(R.id.rank3Card2);
        rank3Card2.setOnClickListener(this);
        rank3Card3 = (ImageButton) activity.findViewById(R.id.rank3Card3);
        rank3Card3.setOnClickListener(this);
        rank3Card4 = (ImageButton) activity.findViewById(R.id.rank3Card4);
        rank3Card4.setOnClickListener(this);

        rank2Card1 = (ImageButton) activity.findViewById(R.id.rank2Card1);
        rank2Card1.setOnClickListener(this);
        rank2Card2 = (ImageButton) activity.findViewById(R.id.rank2Card2);
        rank2Card2.setOnClickListener(this);
        rank2Card3 = (ImageButton) activity.findViewById(R.id.rank2Card3);
        rank2Card3.setOnClickListener(this);
        rank2Card4 = (ImageButton) activity.findViewById(R.id.rank2Card4);
        rank2Card4.setOnClickListener(this);

        rank1Card1 = (ImageButton) activity.findViewById(R.id.rank1Card1);
        rank1Card1.setOnClickListener(this);
        rank1Card2 = (ImageButton) activity.findViewById(R.id.rank1Card2);
        rank1Card2.setOnClickListener(this);
        rank1Card3 = (ImageButton) activity.findViewById(R.id.rank1Card3);
        rank1Card3.setOnClickListener(this);
        rank1Card4 = (ImageButton) activity.findViewById(R.id.rank1Card4);
        rank1Card4.setOnClickListener(this);

        // imagebuttons for all coins
        emeraldCoin = (ImageButton) activity.findViewById(R.id.emeraldCoin);
        emeraldCoin.setOnClickListener(this);
        diamondCoin = (ImageButton) activity.findViewById(R.id.diamondCoin);
        diamondCoin.setOnClickListener(this);
        sapphireCoin = (ImageButton) activity.findViewById(R.id.sapphireCoin);
        sapphireCoin.setOnClickListener(this);
        onyxCoin = (ImageButton) activity.findViewById(R.id.onyxCoin);
        onyxCoin.setOnClickListener(this);
        rubyCoin = (ImageButton) activity.findViewById(R.id.rubyCoin);
        rubyCoin.setOnClickListener(this);
        goldCoin = (ImageButton) activity.findViewById(R.id.goldCoin);
        goldCoin.setOnClickListener(this);

        // simple buttons for the reserved cards
        Button reserveCard1 = (Button) activity.findViewById(R.id.reserveSlot1);
        reserveCard1.setOnClickListener(this);
        Button reserveCard2 = (Button) activity.findViewById(R.id.reserveSlot2);
        reserveCard2.setOnClickListener(this);
        Button reserveCard3 = (Button) activity.findViewById(R.id.reserveSlot3);
        reserveCard3.setOnClickListener(this);

        //action buttons for the actions
        buyButton = (Button) activity.findViewById(R.id.buyAction);
        buyButton.setOnClickListener(this);
        Button reserveButton = (Button) activity.findViewById(R.id.reserveAction);
        reserveButton.setOnClickListener(this);
        coinButton = (Button) activity.findViewById(R.id.coinAction);
        coinButton.setOnClickListener(this);

        // will provide info on current player stats if clicked on
        Button currentPlayer = (Button) activity.findViewById(R.id.currentPlayerName);
        currentPlayer.setOnClickListener(this);

        p1Emerald = (TextView) activity.findViewById(R.id.emeraldPoint1);
        p1Diamond= (TextView) activity.findViewById(R.id.diamondPoint1);
        p1Sapphire= (TextView) activity.findViewById(R.id.sapphirePoint1);
        p1Onyx= (TextView) activity.findViewById(R.id.onyxPoint1);
        p1Ruby= (TextView) activity.findViewById(R.id.rubyPoint1);
        p1Gold= (TextView) activity.findViewById(R.id.goldPoint1);
        p1PrestigePt= (TextView) activity.findViewById(R.id.prestigePoint1);

        p2Emerald = (TextView) activity.findViewById(R.id.emeraldPoint2);
        p2Diamond= (TextView) activity.findViewById(R.id.diamondPoint2);
        p2Sapphire= (TextView) activity.findViewById(R.id.sapphirePoint2);
        p2Onyx= (TextView) activity.findViewById(R.id.onyxPoint2);
        p2Ruby= (TextView) activity.findViewById(R.id.rubyPoint2);
        p2Gold= (TextView) activity.findViewById(R.id.goldPoint2);
        p2PrestigePt= (TextView) activity.findViewById(R.id.prestigePoint2);

        p3Emerald = (TextView) activity.findViewById(R.id.emeraldPoint3);
        p3Diamond= (TextView) activity.findViewById(R.id.diamondPoint3);
        p3Sapphire= (TextView) activity.findViewById(R.id.sapphirePoint3);
        p3Onyx= (TextView) activity.findViewById(R.id.onyxPoint3);
        p3Ruby= (TextView) activity.findViewById(R.id.rubyPoint3);
        p3Gold= (TextView) activity.findViewById(R.id.goldPoint3);
        p3PrestigePt= (TextView) activity.findViewById(R.id.prestigePoint3);

        p4Emerald = (TextView) activity.findViewById(R.id.emeraldPoint4);
        p4Diamond= (TextView) activity.findViewById(R.id.diamondPoint4);
        p4Sapphire= (TextView) activity.findViewById(R.id.sapphirePoint4);
        p4Onyx= (TextView) activity.findViewById(R.id.onyxPoint4);
        p4Ruby= (TextView) activity.findViewById(R.id.rubyPoint4);
        p4Gold= (TextView) activity.findViewById(R.id.goldPoint4);
        p4PrestigePt= (TextView) activity.findViewById(R.id.prestigePoint4);


       coinB= (TextView) activity.findViewById(R.id.CB);
        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer

