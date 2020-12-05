package edu.up.cs301.splendor.Setup;

import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import edu.up.cs301.splendor.Game.LocalGame;
import edu.up.cs301.splendor.Game.SplendorLocalGame;
import edu.up.cs301.splendor.Players.GamePlayer;
import edu.up.cs301.splendor.Players.SplendorComputerPlayer;
import edu.up.cs301.splendor.Players.SplendorHumanPlayer;
import edu.up.cs301.splendor.Players.SplendorSmartComputerPlayer;

public class SplendorMainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2234;

/**
 * Create the default configuration for this game:
 * - one human player vs. three computer players
 * - hard 4 players
 * - one kind of computer player available (date tbd)
 *
 * @return
 * 		the new configuration object, representing the default configuration
 * 	**/

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // a human player player type (player type 0)
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new SplendorHumanPlayer(name);
            }});

        // a computer player type (player type 1)
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new SplendorComputerPlayer(name);
            }});

        // a computer player type (player type 2)
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new SplendorSmartComputerPlayer(name);
            }});

        // Create a game configuration class for Counter:
        // - player types as given above
        // - from 1 to 2 players
        // - name of game is "Counter Game"
        // - port number as defined above
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Splendor Game",
                PORT_NUMBER);

        // Add the default players to the configuration
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player


        // Set the default remote-player setup:
        // - player name: "Remote Player"
        // - IP code: (empty string)
        // - default player type: human player
        defaultConfig.setRemoteData("Remote Player", "", 0);

        // return the configuration
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        String[] names = new String[this.getnumPlayers()];
        for (int i = 0; i < this.getnumPlayers(); i++) {
            String name = super.getConfig().getSelName(i);
            names[i] = name;
        }
        return new SplendorLocalGame(this.getnumPlayers(), names);
    }
}
