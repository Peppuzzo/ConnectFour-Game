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

package model.player;

import model.board.Color;

public abstract class AbstractPlayer {
    private final String name;
    private final Color color;

    /**
     * The constructor of the abstract class that lays
     * a foundation for creating players
     *
     * @param name of the player
     * @param color of the player
     *
     * @throws if the constructor of the abstract class that lays
     * a foundation for creating players
     */
    public AbstractPlayer(String name, Color color){
        if(name.isEmpty() || color == null)
            throw new IllegalArgumentException("Player name and color can't be empty");
        this.name = name;
        this.color = color;
    }


    /**
     * @return the name of player
     */
    public String getName(){
        return this.name;
    }


    /**
     * @return the color of player
     */
    public Color getColor(){
        return this.color;
    }


}
