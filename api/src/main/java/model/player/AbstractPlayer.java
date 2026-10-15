package model.player;

import java.awt.Color;

public abstract class AbstractPlayer {
    private final String name;
    private final Color color;

    public AbstractPlayer(String name, Color color){
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
