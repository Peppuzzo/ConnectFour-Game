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


/**
 * The responsibility of this class is to pass the game configuration,
 * along with the data, to the GameFactory class.
 */
public class GameConfig {
    private PlayerConfig playerConfig1;
    private PlayerConfig playerConfig2;
    private Mode mode;

    GameConfig(PlayerConfig playerConfig1, PlayerConfig playerConfig2, Mode mode) {
        if(playerConfig1 == null && playerConfig2 == null && mode == null)
            throw new  NullPointerException("Either playerConfig1 or playerConfig2 is null");
        this.playerConfig1 = playerConfig1;
        this.playerConfig2 = playerConfig2;
        this.mode = mode;
    }


    /**
     * Returns the configuration for player 1
     *
     * @return the configuration for player 1
     */
    public PlayerConfig getPlayerConfig1() {
        return playerConfig1;
    }


    /**
     * Returns the configuration for player 2
     *
     * @return the configuration for player 1
     */
    public PlayerConfig getPlayerConfig2() {
        return playerConfig2;
    }


    /**
     * Returns the mode chosen by the player
     *
     * @return the mode chosen by the player
     */
    public Mode getMode() {
        return mode;
    }


}
