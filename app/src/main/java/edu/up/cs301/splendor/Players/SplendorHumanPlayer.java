package edu.up.cs301.splendor.Players;

import edu.up.cs301.counter.CounterState;
import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.splCardAction;
import edu.up.cs301.splendor.Setup.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.GameInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

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
        if (button.getId() == R.id.buyAction) {
            // plus button: create "increment" action
            action = new splCardAction(this, null);
        }
        else if (button.getId() == R.id.reserveAction) {
            // minus button: create "decrement" action
          //  action = new SplendorReserveCardAction(this, null);
        }
        else if (button.getId() == R.id.coinAction){
            // something else was pressed: ignore
          //  action = new SplendorReserveCardAction(this, null);
        }
        else if (button.getId() == R.id.nobleCard1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0,0);
    }
        else if (button.getId() == R.id.nobleCard2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,1);
        }
        else if (button.getId() == R.id.nobleCard3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,2);
        }
        else if (button.getId() == R.id.nobleCard4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,0,3);
        }
        else if (button.getId() == R.id.rank1Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1,0);
        }
        else if (button.getId() == R.id.rank1Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,1);
        }
        else if (button.getId() == R.id.rank1Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,2);
        }
        else if (button.getId() == R.id.rank1Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,1,3);
        }
        else if (button.getId() == R.id.rank2Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2,0);
        }
        else if (button.getId() == R.id.rank2Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,1);
        }
        else if (button.getId() == R.id.rank2Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,2);
        }
        else if (button.getId() == R.id.rank2Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,2,3);
        }
        else if (button.getId() == R.id.rank3Card1){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 3,0);
        }
        else if (button.getId() == R.id.rank3Card2){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,3,1);
        }
        else if (button.getId() == R.id.rank3Card3){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,3,2);
        }
        else if (button.getId() == R.id.rank3Card4){
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this,3,3);
        }
        else {
            return;
        }
        game.sendAction(action); // send action to the game
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

        // update our state; then update the display
        // this.state = (CounterState)info;
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
        ImageButton emeraldCoin = (ImageButton) activity.findViewById(R.id.emeraldCoin);
        emeraldCoin.setOnClickListener(this);
        ImageButton diamondCoin = (ImageButton) activity.findViewById(R.id.diamondCoin);
        diamondCoin.setOnClickListener(this);
        ImageButton sapphireCoin = (ImageButton) activity.findViewById(R.id.sapphireCoin);
        sapphireCoin.setOnClickListener(this);
        ImageButton onyxCoin = (ImageButton) activity.findViewById(R.id.onyxCoin);
        onyxCoin.setOnClickListener(this);
        ImageButton rubyCoin = (ImageButton) activity.findViewById(R.id.rubyCoin);
        rubyCoin.setOnClickListener(this);
        ImageButton goldCoin = (ImageButton) activity.findViewById(R.id.goldCoin);
        goldCoin.setOnClickListener(this);

        // simple buttons for the reserved cards
        Button reserveCard1 = (Button) activity.findViewById(R.id.reserveSlot1);
        reserveCard1.setOnClickListener(this);
        Button reserveCard2 = (Button) activity.findViewById(R.id.reserveSlot2);
        reserveCard2.setOnClickListener(this);
        Button reserveCard3 = (Button) activity.findViewById(R.id.reserveSlot3);
        reserveCard3.setOnClickListener(this);

        //action buttons for the actions
        Button buyButton = (Button) activity.findViewById(R.id.buyAction);
        buyButton.setOnClickListener(this);
        Button reserveButton = (Button) activity.findViewById(R.id.reserveAction);
        reserveButton.setOnClickListener(this);
        Button coinButton = (Button) activity.findViewById(R.id.coinAction);
        coinButton.setOnClickListener(this);

        // will provide info on current player stats if clicked on
        Button currentPlayer = (Button) activity.findViewById(R.id.currentPlayerName);
        currentPlayer.setOnClickListener(this);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer

