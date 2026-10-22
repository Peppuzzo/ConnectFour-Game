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
import model.player.AbstractPlayer;

public interface IGameManager {


    /**
     * Executes a move in the chosen column for the current player.
     * Handles the insertion of the game piece and, if the insertion is successful,
     * automatically passes the turn to the next player.
     *
     * @param col the column chosen by the player
     *
     * @return {@code true} if the move was executed successfully,
     *         {@code false} if the column is full
     *
     * @throws IllegalArgumentException if the column does not fall
     *         within the dimensions of the game grid
     *
     * @apiNote The turn must remain with the same player.
     */
    boolean makeMove(int col);


    /**
     * Returns the current player who has just completed their turn
     *
     * @return the current player
     */
    AbstractPlayer getPlayer();


    /**
     * Determine whether the game has ended (cases: a winner or a draw)
     *
     * @return {@code true} if there is a winner
     *         {@code false} otherwise or a draw
     */
    boolean isGameOver();


    /**
     * Returns the winner of the match, if one exists.
     *
     * @return the winner of the match
     */
    AbstractPlayer getWinner();


    /**
     * Returns the current state of the game grid
     *
     * @return the Board object
     */
    Board getBoard();


    /**
     * Resets the game state to begin a new game
     */
    void resetGame();
}
