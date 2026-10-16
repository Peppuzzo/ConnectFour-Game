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
