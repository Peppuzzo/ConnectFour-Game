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
import model.player.BotPlayer;
import model.player.MyPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    /**
     * Main method that coordinates the creation of the game.
     *
     * @param gameConfig the DTO containing the user's preferences
     *
     * @return the ready-to-use GameManager
     */
    public GameManager initializeGame(GameConfig gameConfig) {
        if (gameConfig == null) {
            throw new IllegalArgumentException("GameConfig cannot be null");
        }
        Board board = new Board();
        IEvaluatorManager valuator = new EvaluatorManager();
        List<AbstractPlayer> players = createPlayers(gameConfig);

        return new GameManager(players, valuator, board);
    }


    /**
     * Helper method dedicated exclusively to the creation and configuration of players.
     *
     * @param gameConfig the DTO containing the setup data
     *
     * @return the list of players, correctly configured
     */
    private List<AbstractPlayer> createPlayers(GameConfig gameConfig) {
        List<AbstractPlayer> players = new ArrayList<>();

        players.add(new MyPlayer(
                gameConfig.getPlayerConfig1().getName(),
                gameConfig.getPlayerConfig1().getColor()
        ));


        if (gameConfig.getMode() == Mode.MultiPlayer) {
            players.add(new MyPlayer(
                    gameConfig.getPlayerConfig2().getName(),
                    gameConfig.getPlayerConfig2().getColor()
            ));
        } else if (gameConfig.getMode() == Mode.SinglePlayer) {
            // If, in SinglePlayer, you want to use the color set by the user for the bot (or a default one)
            Color botColor = (gameConfig.getPlayerConfig2() != null)
                    ? gameConfig.getPlayerConfig2().getColor()
                    : Color.BLUE;

            players.add(new BotPlayer("Bot", botColor));
        }

        return players;
    }

}
