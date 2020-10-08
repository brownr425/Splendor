package edu.up.cs301.splendor;

import edu.up.cs301.counter.CounterMoveAction;
import edu.up.cs301.counter.CounterState;
import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

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

    // The TextView the displays the current counter value
    private TextView counterValueTextView;

    // the most recent game state, as given to us by the CounterLocalGame
    private CounterState state;

    // the android activity that we are running
    private GameMainActivity myActivity;

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
        counterValueTextView.setText("" + state.getCounter());
    }

    /**
     * this method gets called when the user clicks the '+' or '-' button. It
     * creates a new CounterMoveAction to return to the parent activity.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        // if we are not yet connected to a game, ignore
        if (game == null) return;

        // Construct the action and send it to the game
        GameAction action = null;
        if (button.getId() == R.id.plusButton) {
            // plus button: create "increment" action
            action = new CounterMoveAction(this, true);
        }
        else if (button.getId() == R.id.minusButton) {
            // minus button: create "decrement" action
            action = new CounterMoveAction(this, false);
        }
        else {
            // something else was pressed: ignore
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
        if (!(info instanceof CounterState)) return;

        // update our state; then update the display
        this.state = (CounterState)info;
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

        // make this object the listener for both the '+' and '-' 'buttons
        /*Button plusButton = (Button) activity.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);
        Button minusButton = (Button) activity.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);*/

        // make noble cards and set on click listener to display noble card information
        ImageButton nobleCard1 = (ImageButton) activity.findViewById(R.id.nobleCard1);
        nobleCard1.setOnClickListener(this);
        ImageButton nobleCard2 = (ImageButton) activity.findViewById(R.id.nobleCard2);
        nobleCard2.setOnClickListener(this);
        ImageButton nobleCard3 = (ImageButton) activity.findViewById(R.id.nobleCard3);
        nobleCard3.setOnClickListener(this);
        ImageButton nobleCard4 = (ImageButton) activity.findViewById(R.id.nobleCard4);
        nobleCard4.setOnClickListener(this);
        ImageButton nobleCard5 = (ImageButton) activity.findViewById(R.id.nobleCard5);
        nobleCard5.setOnClickListener(this);

        //image buttons for all the rank cards 1-3; starting with 3
        ImageButton rank3Card1 = (ImageButton) activity.findViewById(R.id.rank3Card1);
        rank3Card1.setOnClickListener(this);
        ImageButton rank3Card2 = (ImageButton) activity.findViewById(R.id.rank3Card2);
        rank3Card2.setOnClickListener(this);
        ImageButton rank3Card3 = (ImageButton) activity.findViewById(R.id.rank3Card3);
        rank3Card3.setOnClickListener(this);
        ImageButton rank3Card4 = (ImageButton) activity.findViewById(R.id.rank3Card4);
        rank3Card4.setOnClickListener(this);

        ImageButton rank2Card1 = (ImageButton) activity.findViewById(R.id.rank2Card1);
        rank2Card1.setOnClickListener(this);
        ImageButton rank2Card2 = (ImageButton) activity.findViewById(R.id.rank2Card2);
        rank2Card2.setOnClickListener(this);
        ImageButton rank2Card3 = (ImageButton) activity.findViewById(R.id.rank2Card3);
        rank2Card3.setOnClickListener(this);
        ImageButton rank2Card4 = (ImageButton) activity.findViewById(R.id.rank2Card4);
        rank2Card4.setOnClickListener(this);

        ImageButton rank1Card1 = (ImageButton) activity.findViewById(R.id.rank1Card1);
        rank1Card1.setOnClickListener(this);
        ImageButton rank1Card2 = (ImageButton) activity.findViewById(R.id.rank1Card2);
        rank1Card2.setOnClickListener(this);
        ImageButton rank1Card3 = (ImageButton) activity.findViewById(R.id.rank1Card3);
        rank1Card3.setOnClickListener(this);
        ImageButton rank1Card4 = (ImageButton) activity.findViewById(R.id.rank1Card4);
        rank1Card4.setOnClickListener(this);

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
        
        // remember the field that we update to display the counter's value
        this.counterValueTextView =
                (TextView) activity.findViewById(R.id.counterValueTextView);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer

