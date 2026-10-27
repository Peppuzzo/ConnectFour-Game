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

import model.board.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the set of available piece colors used by the game.
 *
 * <p>The manager keeps an internal list of colors and provides methods to
 * obtain a defensive copy of the currently available colors and to remove a
 * color (for example when a player selects it).
 */
public class ColorManager {

    private final List<Color> colors = new ArrayList<>();

    /**
     * Create a new ColorManager initialized with all values of {@link Color}.
     */
    public ColorManager() {
        colors.addAll(Arrays.asList(Color.values()));
    }


    /**
     * Returns a defensive copy of the currently available colors.
     *
     * @return a new {@link List} containing the available {@link Color} values
     *         (modifications to the returned list do not affect this manager)
     */
    public List<Color> getAvailableColors() {
        return new ArrayList<>(colors);
    }


    /**
     * Remove the specified color from the available set.
     *
     * <p>If the color is not present this method has no effect.
     *
     * @param color the color to remove
     */
    public void removeColor(Color color){
        colors.remove(color);
    }


}
