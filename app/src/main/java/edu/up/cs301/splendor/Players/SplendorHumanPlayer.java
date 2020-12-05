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
 * A GUI of a splendor-player. The GUI displays the splendor board as given by the Local Game, GUI has multiple parts:
 * Info: Info banner on top of screen that displays points, Info box that displays runtime information
 * Board: The board is a 3x4 board with 3 ranks, all are selectable buttons
 * Coins: the Far right of the board has 6 coin selection buttons
 * Action buttons: The bottom of the board is reserved for action buttons namely:
 *      Take coin, Buy Card, Reserve Card, Return Coins
 *
 *  This GUI worked off the framework given by Dr. Nuxoll and Dr. Vegdahl and their counter game
 *
 * @version December 2020
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

    private ImageButton emeraldCoin;
    private ImageButton diamondCoin;
    private ImageButton sapphireCoin;
    private ImageButton onyxCoin;
    private ImageButton rubyCoin;
    private Button clearCoins;

    private Button buyButton;
    private Button reserveButton;
    private Button coinButton;
    private TextView coinB;
    private Button currentPlayer;
    private Button restartButton;
    private Button returnCoinButton;

    private TextView infoBox;

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

        setUpNoblesandCoins();
        updateSelectedCoins();
        updatePlayerTurnColor();
        updatePlayerTurnColor();
        updatePlayerInfoVisabilty();
        updatePlayerScores();
        updateInfoBox();
        updateBuyableCards();
        updateSelectedCards();
        updateCoinBank();

        //Update info box to reflect selected card
        mainLayout.setBackgroundResource(R.drawable.wood_grain_background_1);
    }

    /**
     * updateCoinBank()
     *
     * helper method for updateDisplay()
     * sets text for coin bank message
     * */
    public void updateCoinBank(){
        String message = "Coin Bank:\n Ruby: " + state.getRubyCoins() +
                "\nSapphire: " + state.getSapphireCoins() +
                "\nEmerald: " + state.getEmeraldCoins() +
                "\nDiamond: " + state.getDiamondCoins() +
                "\nOnyx: " + state.getOnyxCoins();
        coinB.setText(message);
    }

    /**
     * setUpNoblesandCoins()
     *
     * helper method for updateDisplay()
     * sets all imageResources of nobles and coins, before any selections
     * */
    public void setUpNoblesandCoins()
    {
        nobleCard1.setImageResource(R.drawable.noble1try2);
        nobleCard2.setImageResource(R.drawable.noble2);
        nobleCard3.setImageResource(R.drawable.noble3);
        nobleCard4.setImageResource(R.drawable.noble4);
        nobleCard5.setImageResource(R.drawable.noble5);

        emeraldCoin.setImageResource(R.drawable.emerald_hr);
        diamondCoin.setImageResource(R.drawable.diamond_hrhr);
        sapphireCoin.setImageResource(R.drawable.sapphire_hr);
        onyxCoin.setImageResource(R.drawable.onyx_hr);
        rubyCoin.setImageResource(R.drawable.ruby_hr);

        nobleCard1.setVisibility(View.GONE);
        nobleCard2.setVisibility(View.GONE);
        nobleCard3.setVisibility(View.GONE);
        nobleCard4.setVisibility(View.GONE);
        nobleCard5.setVisibility(View.GONE);

        for(int i = 0; i < this.state.getNobleBoard().size(); i++)
        {
            switch(i)
            {
                case 0: nobleCard1.setVisibility(View.VISIBLE);
                case 1: nobleCard2.setVisibility(View.VISIBLE);
                case 2: nobleCard3.setVisibility(View.VISIBLE);
                case 3: nobleCard4.setVisibility(View.VISIBLE);
                case 4: nobleCard5.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * setUpGUIImages()
     *
     * helper method for setAsGUI
     * sets initial random board config images
     * */
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

    /**
     * randomImage()
     *
     * helper method for setUpGUIImages
     * creates random drawable object
     * */
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
     * updatePlayerTurnColor()
     *
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
     * updatePlayerInfoVisabilty()
     *
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
     * updatePlayerScores()
     *
     * This method updates point boxes at the top of the screen to current player scores
     */
    public void updatePlayerScores() {
        String p1E_message ="" + state.getPlayer(0).getEmerCoins() +
                " + " + state.getPlayer(0).getEmerPts();
        String p1D_message ="" + state.getPlayer(0).getDiaCoins() +
                " + " + state.getPlayer(0).getDiaPts();
        String p1S_message ="" + state.getPlayer(0).getSapphCoins() +
                        " + " + state.getPlayer(0).getSapphPts();
        String p1O_message ="" + state.getPlayer(0).getOnyxCoins() +
                        " + " + state.getPlayer(0).getOnyxPts();
        String p1R_message ="" + state.getPlayer(0).getRubyCoins() +
                        " + " + state.getPlayer(0).getRubyPts();
        String p1G_message = "" + state.getPlayer(0).getGoldCoins();
        String p1PP_message ="Prestige Points:" + state.getPlayer(0).getPrestigePts();
        p1Emerald.setText(p1E_message);
        p1Diamond.setText(p1D_message);
        p1Sapphire.setText(p1S_message);
        p1Onyx.setText(p1O_message);
        p1Ruby.setText(p1R_message);
        p1Gold.setText(p1G_message);
        p1PrestigePt.setText(p1PP_message);

        String p2E_message ="" + state.getPlayer(1).getEmerCoins() +
                " + " + state.getPlayer(1).getEmerPts();
        String p2D_message ="" + state.getPlayer(1).getDiaCoins() +
                " + " + state.getPlayer(1).getDiaPts();
        String p2S_message ="" + state.getPlayer(1).getSapphCoins() +
                " + " + state.getPlayer(1).getSapphPts();
        String p2O_message ="" + state.getPlayer(1).getOnyxCoins() +
                " + " + state.getPlayer(1).getOnyxPts();
        String p2R_message ="" + state.getPlayer(1).getRubyCoins() +
                " + " + state.getPlayer(1).getRubyPts();
        String p2G_message = "" + state.getPlayer(1).getGoldCoins();
        String p2PP_message ="Prestige Points:" + state.getPlayer(1).getPrestigePts();
        p2Emerald.setText(p2E_message);
        p2Diamond.setText(p2D_message);
        p2Sapphire.setText(p2S_message);
        p2Onyx.setText(p2O_message);
        p2Ruby.setText(p2R_message);
        p2Gold.setText(p2G_message);
        p2PrestigePt.setText(p2PP_message);

        if (state.getPlayerCount() >= 3) {
            String p3E_message ="" + state.getPlayer(2).getEmerCoins() +
                    " + " + state.getPlayer(2).getEmerPts();
            String p3D_message ="" + state.getPlayer(2).getDiaCoins() +
                    " + " + state.getPlayer(2).getDiaPts();
            String p3S_message ="" + state.getPlayer(2).getSapphCoins() +
                    " + " + state.getPlayer(2).getSapphPts();
            String p3O_message ="" + state.getPlayer(2).getOnyxCoins() +
                    " + " + state.getPlayer(2).getOnyxPts();
            String p3R_message ="" + state.getPlayer(2).getRubyCoins() +
                    " + " + state.getPlayer(2).getRubyPts();
            String p3G_message = "" + state.getPlayer(2).getGoldCoins();
            String p3PP_message ="Prestige Points:" + state.getPlayer(2).getPrestigePts();
            p3Emerald.setText(p3E_message);
            p3Diamond.setText(p3D_message);
            p3Sapphire.setText(p3S_message);
            p3Onyx.setText(p3O_message);
            p3Ruby.setText(p3R_message);
            p3Gold.setText(p3G_message);
            p3PrestigePt.setText(p3PP_message);

            if (state.getPlayerCount() == 4) {
                String p4E_message ="" + state.getPlayer(3).getEmerCoins() +
                        " + " + state.getPlayer(3).getEmerPts();
                String p4D_message ="" + state.getPlayer(3).getDiaCoins() +
                        " + " + state.getPlayer(3).getDiaPts();
                String p4S_message ="" + state.getPlayer(3).getSapphCoins() +
                        " + " + state.getPlayer(3).getSapphPts();
                String p4O_message ="" + state.getPlayer(3).getOnyxCoins() +
                        " + " + state.getPlayer(3).getOnyxPts();
                String p4R_message ="" + state.getPlayer(3).getRubyCoins() +
                        " + " + state.getPlayer(3).getRubyPts();
                String p4G_message = "" + state.getPlayer(3).getGoldCoins();
                String p4PP_message ="Prestige Points:" + state.getPlayer(3).getPrestigePts();
                p4Emerald.setText(p4E_message);
                p4Diamond.setText(p4D_message);
                p4Sapphire.setText(p4S_message);
                p4Onyx.setText(p4O_message);
                p4Ruby.setText(p4R_message);
                p4Gold.setText(p4G_message);
                p4PrestigePt.setText(p4PP_message);
            }
        }
    }

    /**
     * updateInfoBox()
     *
     * This method writes the card information of the currently selected card to the info box
     */
    public void updateInfoBox() {
        if(this.state.getBoughtCard()){
            String info = "Successfully bought Card!";
            if(this.state.getNobleTaken())
            {
                info = "Successfully bought Card " +
                        "\nand received a noble!" +
                        "\nYou have received 3 Prestige Points.";
            }
            infoBox.setText(info);
        }
        else if(this.state.getReserveSuccess()) {
            String info = "Successfully reserved Card!";
            infoBox.setText(info);
        }
        else if(this.state.getTakenCoins()) {
            String info = "Successfully taken coins!";
            infoBox.setText(info);
        }
        else if(this.state.getSelectedCol() == -2) {
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

    /**
     * updateSelectedCards()
     *
     * The method updates what selected card has been picked
     */
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
     * updateSelectedCoins()
     *
     * This method updates coin images to reflect which coins are currently chosen
     */
    public void updateSelectedCoins() {
        for (int coin : this.state.getCoinTracking()) {
            if (coin == 0) {
                rubyCoin.setImageResource(R.drawable.ruby_selected_hr);
            } else if (coin == 1) {
                sapphireCoin.setImageResource(R.drawable.sapphire_selected_hr);
            } else if (coin == 2) {
                emeraldCoin.setImageResource(R.drawable.emerald_selected_hr);
            } else if (coin == 3) {
                diamondCoin.setImageResource(R.drawable.diamond_selected_hrr);
            } else if (coin == 4) {
                onyxCoin.setImageResource(R.drawable.onyx_selected_hr);
            }
        }
    }

    /**
     * updateBuyableCards()
     *
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
     * onClick(View button)
     *
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
    }// onClick

    /**
     * receiveInfo(GameInfo info)
     *
     * callback method when we get a message (e.g., from the game)
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a CounterState message
        if ((info instanceof SplendorGameState))
        {
            // update our state; then update the display
            this.state = (SplendorGameState)info;
        } else {
            errorSound.start();
        }
        updateDisplay();
    }

    /**
     * setAsGui(GameMainActivity activity)
     *
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

