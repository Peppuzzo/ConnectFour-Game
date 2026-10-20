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

package model.board;

/**
 * The responsibility of this class will be to represent the game grid.
 */
public class Board {
    private final int row = 6;
    private final int col = 7;
    private final Cell[][] cells;

    public Board(){
        // initialization of the empty grid
        this.cells = new Cell[row][col];
    }


    /**
     * This method returns the number of rows
     *
     * @return the number of rows in the grid
     */
    public int getRow() {
        return this.row;
    }


    /**
     * This method returns the number of columns
     *
     * @return the number of columns in the grid
     */
    public int getColumn() {
        return this.col;
    }


    /**
     * Returns the current cell based on its position in the grid
     *
     * @param row the position in that row
     * @param col the position in that col
     *
     * @return the cell in the position
     */
    public Cell getCell(int row, int col) {
        if (row < 0 || row >= getRow() || col < 0 || col >= getColumn())
            throw new IndexOutOfBoundsException("Coordinates outside the grid limits");

        return cells[row][col];
    }


    /**
     * Determine whether the entire grid has reached a
     * configuration in which it is no longer possible to place any pieces.
     *
     * @return {@code true} If the grid is full
     *         false otherwise;
     *         {@code false} otherwise.
     */
    //TODO: Under what circumstances can we use it?
    private boolean isFull() {
        return java.util.stream.IntStream.range(0, getRow())
                .allMatch(row -> java.util.stream.IntStream.range(0, getColumn())
                        .allMatch(col -> getCell(row, col) != null));
    }


    /**
     * Inserts a piece into the specified column of the game grid.
     * The piece falls to the lowest available row according to gravity.
     *
     * @param col   the column index where the piece is to be dropped
     * @param color the color of the piece being inserted
     *
     * @return true if the piece was successfully dropped,
     *         false if the column is full
     *
     * @throws IllegalArgumentException if the column does not fall
     *         within the dimensions of the game grid or the color is null
     */
    public boolean dropPiece(int col, Color color){
        if(col < 0 || col >= getColumn() || color == null)
            throw new IllegalArgumentException("Invalid column or color");
        for(int row = getRow() - 1; row >= 0; row--){
            if(getCell(row, col) == null){
                cells[row][col] = new Cell(new Position(row, col), color);
                return true;
            }
        }
        return false;
    }



}
