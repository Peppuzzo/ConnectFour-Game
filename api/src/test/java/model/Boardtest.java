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

package model;

import model.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private final Board board = new Board();

    @Test
    void testBoardInitializationAllCellsNull() {

        for (int row = 0; row < board.getRow(); row++) {
            for (int col = 0; col < board.getColumn(); col++) {
                assertNull(board.getCell(row, col),"Error: The cell at row " + row +
                        " and column " + col + " is not empty (null) at startup!");
            }
        }
    }


    @Test
    void testBoardInitializationRow(){
        assertEquals(6, board.getRow(), "Error: The row number must be 6!");
    }


    @Test
    void testBoardInitializationColumn(){
        assertEquals(7, board.getColumn(), "Error: The column number must be 7!");
    }


    @Test
    void testGetCellValidCoordinates() {
        // The Boundary Value Analysis testing technique is applied.
        assertNull(board.getCell(0, 0), "La cella (0,0) dovrebbe essere null");
        assertNull(board.getCell(5, 6), "La cella (5,6) dovrebbe essere null");
    }


    @Test
    void testGetCellInvalidCoordinates() {

        // 1. Negative row
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getCell(-1, 0);
        }, "Should throw an exception for a negative row");

        // 2. Row out of bounds (e.g., row 6 in a 0-5 grid)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getCell(board.getRow(), 0);
        }, "Should throw an exception for a row >= getRow()");

        // 3. Negative column
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getCell(0, -1);
        }, "Should throw an exception for a negative column");

        // 4. Column out of bounds (e.g., column 7 in a 0-6 grid)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getCell(0, board.getColumn());
        }, "Should throw an exception for a column >= getColumn()");
    }
}
