package model.player;

import model.board.Color;

//TODO: The bot must calculate its move using the Minimax algorithm,
// based on the state of the game grid.
public class BotPlayer extends AbstractPlayer implements IStrategy{

    public BotPlayer(String name, Color color){
        super(name, color);
    }
}
