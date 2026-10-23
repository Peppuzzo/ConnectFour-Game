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
import model.board.Cell;
import model.board.Color;
import model.board.Direction;

/**
 * The responsibility of this class is to calculate a
 * potential victory assessment.
 */
public class EvaluatorManager implements IEvaluatorManager {


    @Override
    public Color checkWin(Board board, int lastRow, int lastCol) {
        Cell lastCell = board.getCell(lastRow, lastCol);
        if (lastCell == null) {
            return null;
        }
        Color targetColor = lastCell.color();

        for (Direction dir : Direction.values()) {
            int consecutiveCount = 1;

            consecutiveCount += countMatchingInDirection(board, lastRow, lastCol, dir.x, dir.y, targetColor);
            consecutiveCount += countMatchingInDirection(board, lastRow, lastCol, -dir.x, -dir.y, targetColor);

            if (consecutiveCount >= 4) {
                return targetColor; // Restituisce il colore vincente!
            }
        }

        return null; // Nessuna vittoria
    }


    /**
     * Counts consecutive pieces of the same color starting from a given position
     * and moving along a specific directional vector.
     *
     * @param board    the current game board
     * @param startRow the starting row index
     * @param startCol the starting column index
     * @param dx       the row direction step (-1, 0, or 1)
     * @param dy       the column direction step (-1, 0, or 1)
     * @param color    the color of the player's pieces to match
     *
     * @return the number of consecutive matching pieces found in that direction
     */
    private int countMatchingInDirection(Board board, int startRow, int startCol, int dx, int dy, Color color) {
        int count = 0;
        int currentRow = startRow + dx;
        int currentCol = startCol + dy;

        while (isWithinBounds(board, currentRow, currentCol)) {
            Cell cell = board.getCell(currentRow, currentCol);
            if (cell != null && cell.color().equals(color)) {
                count++;
                currentRow += dx;
                currentCol += dy;
            } else {
                break;
            }
        }
        return count;
    }


    /**
     * Verifies whether the specified coordinates lie within the valid boundaries of the board.
     *
     * @param board the current game board
     * @param row   the row index to check
     * @param col   the column index to check
     *
     * @return true if the coordinates are within bounds, false otherwise
     */
    private boolean isWithinBounds(Board board, int row, int col) {
        return row >= 0 && row < board.getRow() && col >= 0 && col < board.getColumn();
    }
}
