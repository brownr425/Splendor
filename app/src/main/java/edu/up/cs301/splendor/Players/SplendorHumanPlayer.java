package edu.up.cs301.splendor.Players;

import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Setup.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.GameInfo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.up.cs301.splendor.Actions.SplendorReturnCoinAction;
import edu.up.cs301.splendor.State.SplendorGameState;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * <p>
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class SplendorHumanPlayer extends GameHumanPlayer implements OnClickListener {
    // the most recent game state, as given to us by the CounterLocalGame
    private SplendorGameState state;

    // the android activity that we are running
    private GameMainActivity myActivity;

    //Everything on the GUI that gets updated
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
    private ImageButton emeraldCoin;
    private ImageButton diamondCoin;
    private ImageButton sapphireCoin;
    private ImageButton onyxCoin;
    private ImageButton rubyCoin;
    private ImageButton goldCoin;

    //player area
    private Button buyButton;
    private Button reserveButton;
    private Button coinButton;
    private TextView coinB;
    private Button reserveSlot1;
    private Button reserveSlot2;
    private Button reserveSlot3;
    private Button currentPlayer;

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
    private TextView p1Name;

    private TextView p2Emerald;
    private TextView p2Diamond;
    private TextView p2Sapphire;
    private TextView p2Onyx;
    private TextView p2Ruby;
    private TextView p2Gold;
    private TextView p2PrestigePt;
    private TextView p2Name;

    private TextView p3Emerald;
    private TextView p3Diamond;
    private TextView p3Sapphire;
    private TextView p3Onyx;
    private TextView p3Ruby;
    private TextView p3Gold;
    private TextView p3PrestigePt;
    private TextView p3Name;
    private LinearLayout p3Box;

    private TextView p4Emerald;
    private TextView p4Diamond;
    private TextView p4Sapphire;
    private TextView p4Onyx;
    private TextView p4Ruby;
    private TextView p4Gold;
    private TextView p4PrestigePt;
    private TextView p4Name;
    private LinearLayout p4Box;

    /**
     * constructor
     *
     * @param name the player's name
     */
    public SplendorHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * updates the buttons and text views to match with the updated gamestate.
     */
    protected void updateDisplay() {
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

        //SELECTED COIN HIGHLIGHT
        for (int coin : this.state.getCoinTracking()) {

            if (coin == 0) {
                rubyCoin.setImageResource(R.drawable.ruby_selected);
            } else if (coin == 1) {
                sapphireCoin.setImageResource(R.drawable.sapphire_selected);
            } else if (coin == 2) {
                emeraldCoin.setImageResource(R.drawable.emerald_selected);
            } else if (coin == 3) {
                diamondCoin.setImageResource(R.drawable.diamond_selected);
            } else if (coin == 4) {
                onyxCoin.setImageResource(R.drawable.onyx_selected);
            }
        }
        Log.d("SHP", "PC" + this.state.getPlayerCount());
        if (this.state.getPlayerCount() < 3) {
            Log.d("SHP", "PC" + this.state.getPlayerCount());
            p3Box.setVisibility(View.GONE);
            p3Emerald.setVisibility(View.GONE);
            p3Diamond.setVisibility(View.GONE);
            p3Sapphire.setVisibility(View.GONE);
            p3Onyx.setVisibility(View.GONE);
            p3Ruby.setVisibility(View.GONE);
            p3Gold.setVisibility(View.GONE);
            p3PrestigePt.setVisibility(View.GONE);
            p3Name.setVisibility(View.GONE);
        }
        if (this.state.getPlayerCount() < 4) {
            p4Box.setVisibility(View.GONE);
            p4Emerald.setVisibility(View.GONE);
            p4Diamond.setVisibility(View.GONE);
            p4Sapphire.setVisibility(View.GONE);
            p4Onyx.setVisibility(View.GONE);
            p4Ruby.setVisibility(View.GONE);
            p4Gold.setVisibility(View.GONE);
            p4PrestigePt.setVisibility(View.GONE);
            p4Name.setVisibility(View.GONE);
        }

        p1Emerald.setText("" + state.getPlayer(0).getEmerCoins() +
                " + " + state.getPlayer(0).getEmerPts());
        p1Diamond.setText("" + state.getPlayer(0).getDiaCoins() +
                " + " + state.getPlayer(0).getDiaPts());
        p1Sapphire.setText("" + state.getPlayer(0).getSapphCoins() +
                " + " + state.getPlayer(0).getSapphPts());
        p1Onyx.setText("" + state.getPlayer(0).getOnyxCoins() +
                " + " + state.getPlayer(0).getOnyxPts());
        p1Ruby.setText("" + state.getPlayer(0).getRubyCoins() +
                " + " + state.getPlayer(0).getRubyPts());
        p1Gold.setText("" + state.getPlayer(0).getGoldCoins());
        p1PrestigePt.setText("" + state.getPlayer(0).getPrestigePts());

        p2Emerald.setText("" + state.getPlayer(1).getEmerCoins() +
                " + " + state.getPlayer(1).getEmerPts());
        p2Diamond.setText("" + state.getPlayer(1).getDiaCoins() +
                " + " + state.getPlayer(1).getDiaPts());
        p2Sapphire.setText("" + state.getPlayer(1).getSapphCoins() +
                " + " + state.getPlayer(1).getSapphPts());
        p2Onyx.setText("" + state.getPlayer(1).getOnyxCoins() +
                " + " + state.getPlayer(1).getOnyxPts());
        p2Ruby.setText("" + state.getPlayer(1).getRubyCoins() +
                " + " + state.getPlayer(1).getRubyPts());
        p2Gold.setText("" + state.getPlayer(1).getGoldCoins());
        p2PrestigePt.setText("" + state.getPlayer(1).getPrestigePts());

        if(state.getPlayerCount() >= 3) {
            p3Emerald.setText("" + state.getPlayer(2).getEmerCoins() +
                    " + " + state.getPlayer(2).getEmerPts());
            p3Diamond.setText("" + state.getPlayer(2).getDiaCoins() +
                    " + " + state.getPlayer(2).getDiaPts());
            p3Sapphire.setText("" + state.getPlayer(2).getSapphCoins() +
                    " + " + state.getPlayer(2).getSapphPts());
            p3Onyx.setText("" + state.getPlayer(2).getOnyxCoins() +
                    " + " + state.getPlayer(2).getOnyxPts());
            p3Ruby.setText("" +state.getPlayer(2).getRubyCoins() +
                    " + " + state.getPlayer(2).getRubyPts());
            p3Gold.setText("" + state.getPlayer(2).getGoldCoins());
            p3PrestigePt.setText("" + state.getPlayer(2).getPrestigePts());

            if(state.getPlayerCount() == 4) {
                p4Emerald.setText("" + state.getPlayer(3).getEmerCoins() +
                        " + " + state.getPlayer(3).getEmerPts());
                p4Diamond.setText("" + state.getPlayer(3).getDiaCoins() +
                        " + " + state.getPlayer(3).getDiaPts());
                p4Sapphire.setText("" + state.getPlayer(3).getSapphCoins() +
                        " + " + state.getPlayer(3).getSapphPts());
                p4Onyx.setText("" + state.getPlayer(3).getOnyxCoins() +
                        " + " + state.getPlayer(3).getOnyxPts());
                p4Ruby.setText("" + state.getPlayer(3).getRubyCoins() +
                        " + " + state.getPlayer(3).getRubyPts());
                p4Gold.setText("" + state.getPlayer(3).getGoldCoins());
                p4PrestigePt.setText("" + state.getPlayer(3).getPrestigePts());
            }
        }

        switch(state.getPlayer(1).getPlayerHand().getReserved().size()) {
            case 1:
                reserveSlot1.setText("Reserved Card");
                reserveSlot2.setText("Reserved Slot");
                reserveSlot3.setText("Reserved Slot");
                break;
            case 2:
                reserveSlot1.setText("Reserved Card");
                reserveSlot2.setText("Reserved Card");
                reserveSlot3.setText("Reserved Slot");
                break;
            case 3:
                reserveSlot1.setText("Reserved Card");
                reserveSlot2.setText("Reserved Card");
                reserveSlot3.setText("Reserved Card");
                break;
            default:
                reserveSlot1.setText("Reserved Slot");
                reserveSlot2.setText("Reserved Slot");
                reserveSlot3.setText("Reserved Slot");
                break;

        }

        coinB.setText("Coin Bank:" +
                "\nRuby: " + state.getRubyCoins() +
                "\nSapphire: " + state.getSapphireCoins() +
                "\nEmerald: " + state.getEmeraldCoins() +
                "\nDiamond: " + state.getDiamondCoins() +
                "\nOnyx: " + state.getOnyxCoins() +
                "\nGold: " + state.getGoldCoins());

        //Update info box to reflect selected card
        if (state.getSelected() != null) {
            String info = state.getSelected().toString();
            infoBox.setText(info);
        } else {
            String playerCoins = "Player's Coins\n" +
                    "\nRuby:\n" + state.getPlayer(0).getRubyCoins() +
                    "\n\nSapphire:\n" + state.getPlayer(0).getSapphCoins() +
                    "\n\nEmerald:\n" + state.getPlayer(0).getEmerCoins() +
                    "\n\nDiamond:\n" + state.getPlayer(0).getDiaCoins() +
                    "\n\nOnyx:\n" + state.getPlayer(0).getOnyxCoins() +
                    "\n\nGold:\n" + state.getPlayer(0).getGoldCoins();
            infoBox.setText(playerCoins);
        }
    }

    /**
     * this method gets called whenever the user clicks any of the buttons on screen.
     * It creates a corresponding action based on the button that was clicked.
     *
     * @param button the button that was clicked
     */
    public void onClick(View button) {
        // if we are not yet connected to a game, ignore
        if (game == null) return;

        // Construct the action and send it to the game
        GameAction action = null;
        if (button.getId() == R.id.quitButton) {
            myActivity.restartGame();
        } else if (button.getId() == R.id.buyAction) {
            // plus button: create "increment" action
            Log.d("SHP", "BUY");
            action = new SplendorCardAction(this, this.state.getSelected(),
                    this.state.getSelectedRow(), this.state.getSelectedCol());
        } else if (button.getId() == R.id.reserveAction) {
            // minus button: create "decrement" action
            action = new SplendorReserveCardAction(this, this.state.getSelectedRow(), this.state.getSelectedCol());
        } else if (button.getId() == R.id.coinAction) {
            action = new SplendorCoinAction(this);
            Log.d("SHP", "COIN");
        } else if (button.getId() == R.id.returnCoins) {
            action = new SplendorReturnCoinAction(this);
            Log.d("SHP", "RETURNC");
        } else if (button.getId() == R.id.currentPlayerInfo) {
            state.setSelected(null);
        } else if (button.getId() == R.id.rank1Card1) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2, 0);
        } else if (button.getId() == R.id.rank1Card2) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2, 1);
        } else if (button.getId() == R.id.rank1Card3) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2, 2);
        } else if (button.getId() == R.id.rank1Card4) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 2, 3);
        } else if (button.getId() == R.id.rank2Card1) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1, 0);
        } else if (button.getId() == R.id.rank2Card2) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1, 1);
        } else if (button.getId() == R.id.rank2Card3) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1, 2);
        } else if (button.getId() == R.id.rank2Card4) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 1, 3);
        } else if (button.getId() == R.id.rank3Card1) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0, 0);
        } else if (button.getId() == R.id.rank3Card2) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0, 1);
        } else if (button.getId() == R.id.rank3Card3) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0, 2);
        } else if (button.getId() == R.id.rank3Card4) {
            // something else was pressed: ignore
            action = new SplendorSelectCardAction(this, 0, 3);
        } else if (button.getId() == R.id.rubyCoin) {
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 0);
        } else if (button.getId() == R.id.sapphireCoin) {
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 1);
        } else if (button.getId() == R.id.emeraldCoin) {
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 2);
        } else if (button.getId() == R.id.diamondCoin) {
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 3);
        } else if (button.getId() == R.id.onyxCoin) {
            // something else was pressed: ignore
            action = new SplendorCoinSelectAction(this, 4);
        } else if (button.getId() == R.id.reserveSlot1) {
            action = new SplendorSelectCardAction(this, 0, -1); // column are -1 to signify it is not in the board, it is the reserved hand
        } else if (button.getId() == R.id.reserveSlot2) {
            action = new SplendorSelectCardAction(this, 1, -1);
        } else if (button.getId() == R.id.reserveSlot3) {
            action = new SplendorSelectCardAction(this, 2, -1);
        } else if (button.getId() == R.id.quitButton) {
            System.exit(0);
        }
        game.sendAction(action); // send action to the game
        updateDisplay();
    }// onClick

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a CounterState message
        if (!(info instanceof SplendorGameState)) return;
        this.state = (SplendorGameState) info;

        // update our state; then update the display
        this.state = (SplendorGameState) info;
        updateDisplay();
    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity the activity under which we are running
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
        reserveSlot1 = (Button) activity.findViewById(R.id.reserveSlot1);
        reserveSlot1.setOnClickListener(this);
        reserveSlot2 = (Button) activity.findViewById(R.id.reserveSlot2);
        reserveSlot2.setOnClickListener(this);
        reserveSlot3 = (Button) activity.findViewById(R.id.reserveSlot3);
        reserveSlot3.setOnClickListener(this);

        //action buttons for the actions
        buyButton = (Button) activity.findViewById(R.id.buyAction);
        buyButton.setOnClickListener(this);
        reserveButton = (Button) activity.findViewById(R.id.reserveAction);
        reserveButton.setOnClickListener(this);
        coinButton = (Button) activity.findViewById(R.id.coinAction);
        coinButton.setOnClickListener(this);

        // will provide info on current player stats if clicked on
        currentPlayer = (Button) activity.findViewById(R.id.currentPlayerInfo);
        currentPlayer.setOnClickListener(this);

        //player point values
        p1Emerald = (TextView) activity.findViewById(R.id.emeraldPoint1);
        p1Diamond = (TextView) activity.findViewById(R.id.diamondPoint1);
        p1Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint1);
        p1Onyx = (TextView) activity.findViewById(R.id.onyxPoint1);
        p1Ruby = (TextView) activity.findViewById(R.id.rubyPoint1);
        p1Gold = (TextView) activity.findViewById(R.id.goldPoint1);
        p1PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint1);
        p1Name = (TextView) activity.findViewById(R.id.player1Name);

        p2Emerald = (TextView) activity.findViewById(R.id.emeraldPoint2);
        p2Diamond = (TextView) activity.findViewById(R.id.diamondPoint2);
        p2Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint2);
        p2Onyx = (TextView) activity.findViewById(R.id.onyxPoint2);
        p2Ruby = (TextView) activity.findViewById(R.id.rubyPoint2);
        p2Gold = (TextView) activity.findViewById(R.id.goldPoint2);
        p2PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint2);
        p2Name = (TextView) activity.findViewById(R.id.player2Name);

        p3Emerald = (TextView) activity.findViewById(R.id.emeraldPoint3);
        p3Diamond = (TextView) activity.findViewById(R.id.diamondPoint3);
        p3Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint3);
        p3Onyx = (TextView) activity.findViewById(R.id.onyxPoint3);
        p3Ruby = (TextView) activity.findViewById(R.id.rubyPoint3);
        p3Gold = (TextView) activity.findViewById(R.id.goldPoint3);
        p3PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint3);
        p3Name = (TextView) activity.findViewById(R.id.player3Name);
        p3Box = (LinearLayout) activity.findViewById((R.id.player3Box));

        p4Emerald = (TextView) activity.findViewById(R.id.emeraldPoint4);
        p4Diamond = (TextView) activity.findViewById(R.id.diamondPoint4);
        p4Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint4);
        p4Onyx = (TextView) activity.findViewById(R.id.onyxPoint4);
        p4Ruby = (TextView) activity.findViewById(R.id.rubyPoint4);
        p4Gold = (TextView) activity.findViewById(R.id.goldPoint4);
        p4PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint4);
        p4Name = (TextView) activity.findViewById(R.id.player4Name);
        p4Box = (LinearLayout) activity.findViewById(R.id.player4Box);

        coinB = (TextView) activity.findViewById(R.id.CB);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer

