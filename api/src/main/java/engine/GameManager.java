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

import java.util.List;

/**
 * The responsibility of this class will be to coordinate the game logic.
 */
public class GameManager implements IGameManager {
    private Board board;
    private List<AbstractPlayer> players;
    private int currentPlayerIndex;

    GameManager(List<AbstractPlayer> players) {
        if(players == null || this.players.size() < 2)
            throw new IllegalArgumentException("The game requires at least 2 players.");
        this.players = players;
        currentPlayerIndex = 0; //The first player on the list begins
    }


    @Override
    public boolean makeMove(int col){

    }


    @Override
    public AbstractPlayer getPlayer(){
        return this.players.get(currentPlayerIndex);
    }


    @Override
    public Board getBoard(){
        return this.board.copyBoard();
    }


    @Override
    public AbstractPlayer getWinner(){
        
    }


    @Override
    public boolean isGameOver(){
        return getWinner() != null || this.board.isFull();
    }


    /**
     *
     */
    private void switchToNextTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % this.players.size();
    }



}
