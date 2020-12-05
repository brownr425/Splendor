package edu.up.cs301.splendor.Players;

import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.splendor.Actions.SplendorClearSelectedAction;
import edu.up.cs301.splendor.Actions.SplendorCoinSelectAction;
import edu.up.cs301.splendor.Actions.SplendorNobleSelectAction;
import edu.up.cs301.splendor.Actions.SplendorReserveCardAction;
import edu.up.cs301.splendor.Actions.SplendorSelectCardAction;
import edu.up.cs301.splendor.Actions.SplendorCardAction;
import edu.up.cs301.splendor.Actions.SplendorCoinAction;
import edu.up.cs301.splendor.Setup.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.splendor.Actions.GameAction;
import edu.up.cs301.splendor.State.GameInfo;

import java.util.Random;

import android.graphics.Color;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
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
    // the most recent game state, as given to us by the SplendorLocalGame
    private SplendorGameState state;

    // the android activity that we are running + most Outer linear layout
    private GameMainActivity myActivity;
    private LinearLayout mainLayout;

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

    private Button reserveCard1;
    private Button reserveCard2;
    private Button reserveCard3;

    //coins I buttons
    private ImageButton emeraldCoin;
    private ImageButton diamondCoin;
    private ImageButton sapphireCoin;
    private ImageButton onyxCoin;
    private ImageButton rubyCoin;
    private Button clearCoins;

    //player area
    private Button buyButton;
    private Button reserveButton;
    private Button coinButton;
    private TextView coinB;
    private Button currentPlayer;
    private Button restartButton;
    private Button returnCoinButton;

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

    private MediaPlayer buttonSound;
    private MediaPlayer errorSound;
    private MediaPlayer coinJingle;
    private MediaPlayer cardSound;
    private MediaPlayer coinSelectSound;

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

        //SELECTED COIN HIGHLIGHT
        updateSelectedCoins();
        updatePlayerTurnColor();
        updatePlayerTurnColor();
        updatePlayerInfoVisabilty();
        updatePlayerScores();
        updateInfoBox();
        updateBuyableCards(); //TODO needs some fixing
        updateSelectedCards();
        coinB.setText("Coin Bank:\n Ruby: " + state.getRubyCoins() +
                "\nSapphire: " + state.getSapphireCoins() +
                "\nEmerald: " + state.getEmeraldCoins() +
                "\nDiamond: " + state.getDiamondCoins() +
                "\nOnyx: " + state.getOnyxCoins());

        //Update info box to reflect selected card

        mainLayout.setBackgroundResource(R.drawable.wood_grain_background_1);
    }

    public void setUpGUIImages() {
        rank3Card1.setImageResource(randomImage());
        rank3Card2.setImageResource(randomImage());
        rank3Card3.setImageResource(randomImage());
        rank3Card4.setImageResource(randomImage());

        rank2Card1.setImageResource(randomImage());
        rank2Card2.setImageResource(randomImage());
        rank2Card3.setImageResource(randomImage());
        rank2Card4.setImageResource(randomImage());

        rank1Card1.setImageResource(randomImage());
        rank1Card2.setImageResource(randomImage());
        rank1Card3.setImageResource(randomImage());
        rank1Card4.setImageResource(randomImage());
    }

    public int randomImage() {
        Random rand = new Random();
        int newRandomNumber = rand.nextInt(8);
        int drawableId = 0;
        switch (newRandomNumber) {
            case 0:
                drawableId = R.drawable.background1;
                break;
            case 1:
                drawableId = R.drawable.background2;
                break;
            case 2:
                drawableId = R.drawable.background3;
                break;
            case 3:
                drawableId = R.drawable.background4;
                break;
            case 4:
                drawableId = R.drawable.background5;
                break;
            case 5:
                drawableId = R.drawable.background6;
                break;
            case 6:
                drawableId = R.drawable.background7;
                break;
            case 7:
                drawableId = R.drawable.background8;
                break;
        }
        return drawableId;
    }

    /**
     * This methods sets the color behind the current players name to be green
     */
    public void updatePlayerTurnColor() {
        switch (this.state.getPlayerTurn()) {
            case 0:
                p1Name.setBackgroundResource(R.color.green);
                p2Name.setBackgroundResource(R.color.grey);
                p3Name.setBackgroundResource(R.color.grey);
                p4Name.setBackgroundResource(R.color.grey);
                break;
            case 1:
                p1Name.setBackgroundResource(R.color.grey);
                p2Name.setBackgroundResource(R.color.green);
                p3Name.setBackgroundResource(R.color.grey);
                p4Name.setBackgroundResource(R.color.grey);
                break;
            case 2:
                p1Name.setBackgroundResource(R.color.grey);
                p2Name.setBackgroundResource(R.color.grey);
                p3Name.setBackgroundResource(R.color.green);
                p4Name.setBackgroundResource(R.color.grey);
                break;
            case 3:
        }
    }

    /**
     * This method changes which players point boxes are visable based on the number of players
     */
    public void updatePlayerInfoVisabilty() {
        if (this.state.getPlayerCount() == 2) {
            p3Emerald.setVisibility(View.GONE);
            p3Diamond.setVisibility(View.GONE);
            p3Sapphire.setVisibility(View.GONE);
            p3Onyx.setVisibility(View.GONE);
            p3Ruby.setVisibility(View.GONE);
            p3Gold.setVisibility(View.GONE);
            p3PrestigePt.setVisibility(View.GONE);
            p3Name.setVisibility(View.GONE);
            p3Box.setVisibility(View.GONE);

            p4Emerald.setVisibility(View.GONE);
            p4Diamond.setVisibility(View.GONE);
            p4Sapphire.setVisibility(View.GONE);
            p4Onyx.setVisibility(View.GONE);
            p4Ruby.setVisibility(View.GONE);
            p4Gold.setVisibility(View.GONE);
            p4PrestigePt.setVisibility(View.GONE);
            p4Box.setVisibility(View.GONE);

            nobleCard4.setVisibility(View.GONE);
            nobleCard5.setVisibility(View.GONE);

            p1Name.setText(state.getPlayer1Name());
            p2Name.setText(state.getPlayer2Name());
            p4Name.setVisibility(View.GONE);
        } else if (this.state.getPlayerCount() == 3) {
            p4Emerald.setVisibility(View.GONE);
            p4Diamond.setVisibility(View.GONE);
            p4Sapphire.setVisibility(View.GONE);
            p4Onyx.setVisibility(View.GONE);
            p4Ruby.setVisibility(View.GONE);
            p4Gold.setVisibility(View.GONE);
            p4PrestigePt.setVisibility(View.GONE);
            p4Box.setVisibility(View.GONE);

            nobleCard5.setVisibility(View.GONE);

            p1Name.setText(state.getPlayer1Name());
            p2Name.setText(state.getPlayer2Name());
            p3Name.setText(state.getPlayer3Name());
        } else if (this.state.getPlayerCount() == 4) {
            p1Name.setText(state.getPlayer1Name());
            p2Name.setText(state.getPlayer2Name());
            p3Name.setText(state.getPlayer3Name());
            p4Name.setText(state.getPlayer4Name());
        }
    }

    /**
     * This method updates point boxes at the top of the screen to current player scores
     */
    public void updatePlayerScores() {
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

        if (state.getPlayerCount() >= 3) {
            p3Emerald.setText("" + state.getPlayer(2).getEmerCoins() +
                    " + " + state.getPlayer(2).getEmerPts());
            p3Diamond.setText("" + state.getPlayer(2).getDiaCoins() +
                    " + " + state.getPlayer(2).getDiaPts());
            p3Sapphire.setText("" + state.getPlayer(2).getSapphCoins() +
                    " + " + state.getPlayer(2).getSapphPts());
            p3Onyx.setText("" + state.getPlayer(2).getOnyxCoins() +
                    " + " + state.getPlayer(2).getOnyxPts());
            p3Ruby.setText("" + state.getPlayer(2).getRubyCoins() +
                    " + " + state.getPlayer(2).getRubyPts());
            p3Gold.setText("" + state.getPlayer(2).getGoldCoins());
            p3PrestigePt.setText("" + state.getPlayer(2).getPrestigePts());

            if (state.getPlayerCount() == 4) {
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
    }

    /**
     * This method writes the card information of the currently selected card to the info box
     */
    public void updateInfoBox() {
        if (this.state.getSelectedCol() == -2) {
            String info = state.getSelectedNoble().toString();
            infoBox.setText(info);
        } else if (state.getSelected() != null) {
            String info = state.getSelected().toString();
            infoBox.setText(info);
        } else {
            String playerCoins = "Player's Coins\n" +
                    "\nRuby:\n" + state.getPlayer(state.getPlayerTurn()).getRubyCoins() +
                    "\n\nSapphire:\n" + state.getPlayer(state.getPlayerTurn()).getSapphCoins() +
                    "\n\nEmerald:\n" + state.getPlayer(state.getPlayerTurn()).getEmerCoins() +
                    "\n\nDiamond:\n" + state.getPlayer(state.getPlayerTurn()).getDiaCoins() +
                    "\n\nOnyx:\n" + state.getPlayer(state.getPlayerTurn()).getOnyxCoins() +
                    "\n\nGold:\n" + state.getPlayer(state.getPlayerTurn()).getGoldCoins();
            infoBox.setText(playerCoins);
        }
    }

    public void updateSelectedCards() {
        switch (this.state.getSelectedCol()) {
            case 0:
                switch (this.state.getSelectedRow()) {
                    case 0:
                        rank3Card1.setBackgroundResource(R.color.cyan);
                        break;
                    case 1:
                        rank2Card1.setBackgroundResource(R.color.cyan);
                        break;
                    case 2:
                        rank1Card1.setBackgroundResource(R.color.cyan);
                        break;
                }
                break;
            case 1:
                switch (this.state.getSelectedRow()) {
                    case 0:
                        rank3Card2.setBackgroundResource(R.color.cyan);
                        break;
                    case 1:
                        rank2Card2.setBackgroundResource(R.color.cyan);
                        break;
                    case 2:
                        rank1Card2.setBackgroundResource(R.color.cyan);
                        break;
                }
                break;
            case 2:
                switch (this.state.getSelectedRow()) {
                    case 0:
                        rank3Card3.setBackgroundResource(R.color.cyan);
                        break;
                    case 1:
                        rank2Card3.setBackgroundResource(R.color.cyan);
                        break;
                    case 2:
                        rank1Card3.setBackgroundResource(R.color.cyan);
                        break;
                }
                break;
            case 3:
                switch (this.state.getSelectedRow()) {
                    case 0:
                        rank3Card4.setBackgroundResource(R.color.cyan);
                        break;
                    case 1:
                        rank2Card4.setBackgroundResource(R.color.cyan);
                        break;
                    case 2:
                        rank1Card4.setBackgroundResource(R.color.cyan);
                        break;
                }
                break;
        }
    }

    /**
     * This method updates coin images to reflect which coins are currently chosen
     */
    public void updateSelectedCoins() {
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
    }

    /**
     * This method sets the background of cards to green if the player can purchase them
     */
    public void updateBuyableCards() {
        rank3Card1.setBackgroundResource(R.color.grey);
        rank3Card2.setBackgroundResource(R.color.grey);
        rank3Card3.setBackgroundResource(R.color.grey);
        rank3Card4.setBackgroundResource(R.color.grey);
        rank2Card1.setBackgroundResource(R.color.grey);
        rank2Card2.setBackgroundResource(R.color.grey);
        rank2Card3.setBackgroundResource(R.color.grey);
        rank2Card4.setBackgroundResource(R.color.grey);
        rank1Card1.setBackgroundResource(R.color.grey);
        rank1Card2.setBackgroundResource(R.color.grey);
        rank1Card3.setBackgroundResource(R.color.grey);
        rank1Card4.setBackgroundResource(R.color.grey);

        reserveCard1.setBackgroundResource(R.color.grey);
        reserveCard2.setBackgroundResource(R.color.grey);
        reserveCard3.setBackgroundResource(R.color.grey);


        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(0, 0))) {
            rank3Card1.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(0, 1))) {
            rank3Card2.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(0, 2))) {
            rank3Card3.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(0, 3))) {
            rank3Card4.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(1, 0))) {
            rank2Card1.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(1, 1))) {
            rank2Card2.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(1, 2))) {
            rank2Card3.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(1, 3))) {
            rank2Card4.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(2, 0))) {
            rank1Card1.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(2, 1))) {
            rank1Card2.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(2, 2))) {
            rank1Card3.setBackgroundResource(R.color.green);
        }
        if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()), state.getBoard(2, 3))) {
            rank1Card4.setBackgroundResource(R.color.green);
        }
        if (state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().size() == 1) {
            if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()),
                    state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().get(0))) {
                reserveCard1.setBackgroundResource(R.color.green);
            }
        }
        if (state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().size() == 2) {
            if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()),
                    state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().get(1))) {
                reserveCard2.setBackgroundResource(R.color.green);
            }
        }
        if (state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().size() == 3) {
            if (state.canBuyCard(state.getPlayer(state.getPlayerTurn()),
                    state.getPlayer(state.getPlayerTurn()).getPlayerHand().getReserved().get(2))) {
                reserveCard3.setBackgroundResource(R.color.green);
            }
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
        if (button.equals(restartButton)) {
            myActivity.recreate();
        } else if (button.equals(buyButton)) {
            Log.d("SHP", "BUY");
            action = new SplendorCardAction(this, this.state.getSelected(),
                    this.state.getSelectedRow(), this.state.getSelectedCol());
            cardSound.start();
        } else if (button.equals(reserveButton)) {
            Log.d("SHP", "RSRV");
            cardSound.start();
            action = new SplendorReserveCardAction(this,
                    this.state.getSelectedRow(), this.state.getSelectedCol());
        } else if (button.equals(coinButton)) {
            action = new SplendorCoinAction(this);
            coinJingle.start();
            Log.d("SHP", "COIN");
        } else if (button.equals(returnCoinButton)) {
            action = new SplendorReturnCoinAction(this);
            coinJingle.start();
            Log.d("SHP", "RETURNC");
        } else if (button.equals(currentPlayer)) {
            state.setSelected(null);
            Log.d("SHP", "PINFO");
        } else if (button.equals(nobleCard1)) {
            action = new SplendorNobleSelectAction(this, 0);
            buttonSound.start();
        } else if (button.equals(nobleCard2)) {
            action = new SplendorNobleSelectAction(this, 1);
            buttonSound.start();
        } else if (button.equals(nobleCard3)) {
            action = new SplendorNobleSelectAction(this, 2);
            buttonSound.start();
        } else if (button.equals(nobleCard4)) {
            action = new SplendorNobleSelectAction(this, 3);
            buttonSound.start();
        } else if (button.equals(nobleCard5)) {
            action = new SplendorNobleSelectAction(this, 4);
            buttonSound.start();
        } else if (button.equals(rank1Card1)) {
            action = new SplendorSelectCardAction(this, 2, 0);
            buttonSound.start();
        } else if (button.equals(rank1Card2)) {
            action = new SplendorSelectCardAction(this, 2, 1);
            buttonSound.start();
        } else if (button.equals(rank1Card3)) {
            action = new SplendorSelectCardAction(this, 2, 2);
            buttonSound.start();
        } else if (button.equals(rank1Card4)) {
            action = new SplendorSelectCardAction(this, 2, 3);
            buttonSound.start();
        } else if (button.equals(rank2Card1)) {
            action = new SplendorSelectCardAction(this, 1, 0);
            buttonSound.start();
        } else if (button.equals(rank2Card2)) {
            action = new SplendorSelectCardAction(this, 1, 1);
            buttonSound.start();
        } else if (button.equals(rank2Card3)) {
            action = new SplendorSelectCardAction(this, 1, 2);
            buttonSound.start();
        } else if (button.equals(rank2Card4)) {
            action = new SplendorSelectCardAction(this, 1, 3);
            buttonSound.start();
        } else if (button.equals(rank3Card1)) {
            action = new SplendorSelectCardAction(this, 0, 0);
            buttonSound.start();
        } else if (button.equals(rank3Card2)) {
            action = new SplendorSelectCardAction(this, 0, 1);
            buttonSound.start();
        } else if (button.equals(rank3Card3)) {
            action = new SplendorSelectCardAction(this, 0, 2);
            buttonSound.start();
        } else if (button.equals(rank3Card4)) {
            action = new SplendorSelectCardAction(this, 0, 3);
            buttonSound.start();
        } else if (button.equals(rubyCoin)) {
            action = new SplendorCoinSelectAction(this, 0);
            coinSelectSound.start();
        } else if (button.equals(sapphireCoin)) {
            action = new SplendorCoinSelectAction(this, 1);
            coinSelectSound.start();
        } else if (button.equals(emeraldCoin)) {
            action = new SplendorCoinSelectAction(this, 2);
            coinSelectSound.start();
        } else if (button.equals(diamondCoin)) {
            action = new SplendorCoinSelectAction(this, 3);
            coinSelectSound.start();
        } else if (button.equals(onyxCoin)) {
            action = new SplendorCoinSelectAction(this, 4);
            coinSelectSound.start();
        } else if (button.equals(clearCoins)) {
            action = new SplendorClearSelectedAction(this);
        } else if (button.equals(reserveCard1)) {
            action = new SplendorSelectCardAction(this, 0, -1);
            // column is -1 to signify it is not in the board, it is the reserved hand
        } else if (button.equals(reserveCard2)) {
            action = new SplendorSelectCardAction(this, 1, -1);
        } else if (button.equals(reserveCard3)) {
            action = new SplendorSelectCardAction(this, 2, -1);
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
        if ((info instanceof SplendorGameState)) {
            this.state = (SplendorGameState) info;

            // update our state; then update the display
            this.state = (SplendorGameState) info;
        } else {
            errorSound.start();
        }
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
        mainLayout = myActivity.findViewById(R.id.top_gui_layout);

        //~~~~~~~~~~~~Buttons and Listeners~~~~~~~~~~~~~~//
        // make noble cards and set on click listener to display noble card information
        nobleCard1 = (ImageButton) activity.findViewById(R.id.nobleCard1);
        nobleCard2 = (ImageButton) activity.findViewById(R.id.nobleCard2);
        nobleCard3 = (ImageButton) activity.findViewById(R.id.nobleCard3);
        nobleCard4 = (ImageButton) activity.findViewById(R.id.nobleCard4);
        nobleCard5 = (ImageButton) activity.findViewById(R.id.nobleCard5);
        nobleCard1.setOnClickListener(this);
        nobleCard2.setOnClickListener(this);
        nobleCard3.setOnClickListener(this);
        nobleCard4.setOnClickListener(this);
        nobleCard5.setOnClickListener(this);

        //image buttons for all the rank cards 1-3; starting with 3
        rank3Card1 = (ImageButton) activity.findViewById(R.id.rank3Card1);
        rank3Card2 = (ImageButton) activity.findViewById(R.id.rank3Card2);
        rank3Card3 = (ImageButton) activity.findViewById(R.id.rank3Card3);
        rank3Card4 = (ImageButton) activity.findViewById(R.id.rank3Card4);
        rank2Card1 = (ImageButton) activity.findViewById(R.id.rank2Card1);
        rank2Card2 = (ImageButton) activity.findViewById(R.id.rank2Card2);
        rank2Card3 = (ImageButton) activity.findViewById(R.id.rank2Card3);
        rank2Card4 = (ImageButton) activity.findViewById(R.id.rank2Card4);
        rank1Card1 = (ImageButton) activity.findViewById(R.id.rank1Card1);
        rank1Card2 = (ImageButton) activity.findViewById(R.id.rank1Card2);
        rank1Card3 = (ImageButton) activity.findViewById(R.id.rank1Card3);
        rank1Card4 = (ImageButton) activity.findViewById(R.id.rank1Card4);
        rank3Card1.setOnClickListener(this);
        rank3Card2.setOnClickListener(this);
        rank3Card3.setOnClickListener(this);
        rank3Card4.setOnClickListener(this);
        rank2Card1.setOnClickListener(this);
        rank2Card2.setOnClickListener(this);
        rank2Card3.setOnClickListener(this);
        rank2Card4.setOnClickListener(this);
        rank1Card1.setOnClickListener(this);
        rank1Card2.setOnClickListener(this);
        rank1Card3.setOnClickListener(this);
        rank1Card4.setOnClickListener(this);

        // imagebuttons for all coins
        rubyCoin = (ImageButton) activity.findViewById(R.id.rubyCoin);
        sapphireCoin = (ImageButton) activity.findViewById(R.id.sapphireCoin);
        emeraldCoin = (ImageButton) activity.findViewById(R.id.emeraldCoin);
        diamondCoin = (ImageButton) activity.findViewById(R.id.diamondCoin);
        onyxCoin = (ImageButton) activity.findViewById(R.id.onyxCoin);
        clearCoins = (Button) activity.findViewById(R.id.clearCoins);
        rubyCoin.setOnClickListener(this);
        sapphireCoin.setOnClickListener(this);
        emeraldCoin.setOnClickListener(this);
        diamondCoin.setOnClickListener(this);
        onyxCoin.setOnClickListener(this);
        clearCoins.setOnClickListener(this);

        // simple buttons for the reserved cards
        reserveCard1 = (Button) activity.findViewById(R.id.reserveSlot1);
        reserveCard2 = (Button) activity.findViewById(R.id.reserveSlot2);
        reserveCard3 = (Button) activity.findViewById(R.id.reserveSlot3);
        reserveCard1.setOnClickListener(this);
        reserveCard2.setOnClickListener(this);
        reserveCard3.setOnClickListener(this);

        //action buttons for the actions
        buyButton = (Button) activity.findViewById(R.id.buyAction);
        reserveButton = (Button) activity.findViewById(R.id.reserveAction);
        coinButton = (Button) activity.findViewById(R.id.coinAction);
        returnCoinButton = (Button) activity.findViewById(R.id.returnCoins);
        buyButton.setOnClickListener(this);
        reserveButton.setOnClickListener(this);
        coinButton.setOnClickListener(this);
        returnCoinButton.setOnClickListener(this);

        // will provide info on current player stats if clicked on
        currentPlayer = (Button) activity.findViewById(R.id.currentPlayerInfo);
        currentPlayer.setOnClickListener(this);

        restartButton = (Button) activity.findViewById(R.id.restartButton);
        restartButton.setOnClickListener(this);

        //~~~~~~~~~~~~~TextViews and Player Boxes~~~~~~~~~~~~~//
        //player point values
        p1Emerald = (TextView) activity.findViewById(R.id.emeraldPoint1);
        p1Diamond = (TextView) activity.findViewById(R.id.diamondPoint1);
        p1Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint1);
        p1Onyx = (TextView) activity.findViewById(R.id.onyxPoint1);
        p1Ruby = (TextView) activity.findViewById(R.id.rubyPoint1);
        p1Gold = (TextView) activity.findViewById(R.id.goldPoint1);
        p1PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint1);
        p1Name = (TextView) activity.findViewById(R.id.player1Name);
        //player 1 box not needed because it always exists

        p2Emerald = (TextView) activity.findViewById(R.id.emeraldPoint2);
        p2Diamond = (TextView) activity.findViewById(R.id.diamondPoint2);
        p2Sapphire = (TextView) activity.findViewById(R.id.sapphirePoint2);
        p2Onyx = (TextView) activity.findViewById(R.id.onyxPoint2);
        p2Ruby = (TextView) activity.findViewById(R.id.rubyPoint2);
        p2Gold = (TextView) activity.findViewById(R.id.goldPoint2);
        p2PrestigePt = (TextView) activity.findViewById(R.id.prestigePoint2);
        p2Name = (TextView) activity.findViewById(R.id.player2Name);
        //player 2 box not needed because it always exists

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
        infoBox = (TextView) activity.findViewById(R.id.infoGiven);

        buttonSound = MediaPlayer.create(activity, R.raw.button_click);
        errorSound = MediaPlayer.create(activity, R.raw.error);
        coinJingle = MediaPlayer.create(activity, R.raw.buy_coin_sound);
        cardSound = MediaPlayer.create(activity, R.raw.buy_card_sound);
        coinSelectSound = MediaPlayer.create(activity, R.raw.coin_select);

        setUpGUIImages();

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer

