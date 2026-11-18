/*
 * MIT License
 *
 * Copyright (c) 2026 Giuseppe Calabrese
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package engine;

import model.board.Board;
import model.board.Color;
import model.player.AbstractPlayer;
import model.player.MyPlayer;

import java.util.List;

public class GameFactory {

    private static GameManager manager;
    private static GameConfig config;

    /**
     * Main method that coordinates the creation of the game.
     *
     * @param gameConfig the DTO containing the user's preferences
     */
    public static void initializeGame(GameConfig gameConfig) {
        if (gameConfig == null) {
            throw new IllegalArgumentException("GameConfig cannot be null");
        }
        config = gameConfig;
        Board board = new Board();
        IEvaluatorManager valuator = new EvaluatorManager();
        List<AbstractPlayer> players = createPlayers(gameConfig);

        manager = new GameManager(players, valuator, board);
    }


    /**
     * Returns the initialized manager
     *
     * @return the initialized manager
     */
    public static GameManager getGameManager() {
        return manager;
    }


    /**
     * Returns the color configuration of the first player
     *
     * @return the color configuration of the first player
     */
    public static Color getColorPlayer1() {
        return config.getPlayerConfig1().getColor();
    }


    /**
     * Returns the color configuration of the first player
     *
     * @return the color configuration of the second player
     */
    public static Color getColorPlayer2() {
        return config.getPlayerConfig2().getColor();
    }


    /**
     * Helper method dedicated exclusively to the creation and configuration of players.
     *
     * @param gameConfig the DTO containing the setup data
     *
     * @return the list of players, correctly configured
     */
    private static List<AbstractPlayer> createPlayers(GameConfig gameConfig) {
        return List.of(new MyPlayer(gameConfig.getPlayerConfig1().getName(),
                gameConfig.getPlayerConfig1().getColor()),
                new MyPlayer(gameConfig.getPlayerConfig2().getName(),
                        gameConfig.getPlayerConfig2().getColor()));
    }
}
