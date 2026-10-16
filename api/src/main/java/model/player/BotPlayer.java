package model.player;

import model.board.Color;

public class BotPlayer extends AbstractPlayer implements IStrategy{
    public BotPlayer(String name, Color color){
        super(name, color);
    }
}
