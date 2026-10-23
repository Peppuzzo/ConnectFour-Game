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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    private GameManager gameManager;
    private AbstractPlayer player1;
    private AbstractPlayer player2;

    @BeforeEach
    void setUp() {
        Board board = new Board();

        player1 = new MyPlayer("Player 1", Color.RED);
        player2 = new MyPlayer("Player 2", Color.YELLOW);

        List<AbstractPlayer> players = List.of(player1, player2);
        EvaluatorManager evaluatorManager = new EvaluatorManager();

        gameManager = new GameManager(players, evaluatorManager, board);
    }

    @Test
    void testInitialState() {
        assertEquals(player1, gameManager.getPlayer(), "The first turn must belong to Player 1");
        assertNull(gameManager.getWinner(), "There must be no winner at the start");
        assertFalse(gameManager.isGameOver(), "The game must not be over at the start");
    }

    @Test
    void testTurnAlternationOnValidMove() {
        assertEquals(player1, gameManager.getPlayer(), "The first turn must belong to Player 1");

        boolean success = gameManager.makeMove(0);
        assertTrue(success, "The move in column 0 must succeed");

        assertEquals(player2, gameManager.getPlayer(), "The turn must pass to Player 2 after a valid move.");
    }

    @Test
    void testInvalidMoveDoesNotSwitchTurn() {
        assertEquals(player1, gameManager.getPlayer(), "The first turn must belong to Player 1");

        for (int i = 0; i < 6; i++) {
            gameManager.makeMove(0);
        }

        AbstractPlayer currentPlayerBefore = gameManager.getPlayer();
        boolean success = gameManager.makeMove(0);

        assertFalse(success, "A move into a full column must fail");
        assertEquals(currentPlayerBefore, gameManager.getPlayer(), "If the move fails, the turn must NOT advance.");
    }

    @Test
    void testWinningCondition() {
        gameManager.makeMove(0); // P1 (Col 0)
        gameManager.makeMove(0); // P2 (Col 0 - Discard elsewhere, ideally—but here, we fill.)

        // Let's create a clean, alternating sequence to get 4 in a
        // horizontal row along the bottom line.

        // P1 -> col 0, P2 -> col 0
        // P1 -> col 1, P2 -> col 1
        // P1 -> col 2, P2 -> col 2
        // P1 -> col 3 (Victory!)

        gameManager.resetGame();

        // Move 1: P1 -> col 0
        gameManager.makeMove(0);
        // Move 2: P2 -> col 0
        gameManager.makeMove(0);

        // Move 3: P1 -> col 1
        gameManager.makeMove(1);
        // Move 4: P2 -> col 1
        gameManager.makeMove(1);

        // Move 5: P1 -> col 2
        gameManager.makeMove(2);
        // Move 6: P2 -> col 2
        gameManager.makeMove(2);

        // Move 7: P1 -> col 3
        gameManager.makeMove(3);

        assertTrue(gameManager.isGameOver(), "The game must end after 4 in a row.");
        assertEquals(player1, gameManager.getWinner(), "The winner must be Player 1");

        assertFalse(gameManager.makeMove(4), "Moves cannot be made once the game has ended");
    }
}
