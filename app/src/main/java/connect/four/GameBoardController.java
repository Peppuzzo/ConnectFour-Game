/*
 * MIT License
 *
 * Copyright (c) 2025 Giuseppe Calabrese
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
 */

package connect.four;

import engine.GameFactory;
import engine.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import model.board.Board;
import model.board.Cell;
import model.board.Color;

public class GameBoardController {

    @FXML
    private GridPane boardGrid;

    private final GameManager gameManager = GameFactory.getGameManager();

    @FXML
    private void handleColumnClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int col = Integer.parseInt(clickedButton.getUserData().toString());

        boolean moveSuccessful = gameManager.makeMove(col);

        if (moveSuccessful) {
            updateBoardUI();

            if (gameManager.isGameOver()) {
                handleGameOver();
            }
        } else {
            System.out.println("Colonna piena! Scegline un'altra.");
        }
    }

    /**
     * Aggiorna interamente la grafica della scacchiera leggendo lo stato dal modello.
     */
    private void updateBoardUI() {
        Board boardState = gameManager.getBoard();
        if (boardState == null) {
            return;
        }

        int rows = boardState.getRow();
        int cols = boardState.getColumn();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = boardState.getCell(row, col);

                // Mappa il colore logico nel colore grafico di JavaFX
                javafx.scene.paint.Color fxColor = mapCellColorToFX(cell);

                // Aggiorna il cerchio visivo corrispondente
                setCellColor(row, col, fxColor);
            }
        }
    }

    /**
     * Converte lo stato della cella del modello nel colore JavaFX usando la Factory
     */
    private javafx.scene.paint.Color mapCellColorToFX(Cell cell) {
        // Se la cella è vuota o null
        if (cell == null || cell.color() == null) {
            return javafx.scene.paint.Color.TRANSPARENT;
        }

        Color cellColor = cell.color();

        // Verifichiamo a quale giocatore appartiene la cella confrontando i colori del motore
        if (cellColor == GameFactory.getColorPlayer1()) {
            return convertToFXColor(GameFactory.getColorPlayer1());
        } else if (cellColor == GameFactory.getColorPlayer2()) {
            return convertToFXColor(GameFactory.getColorPlayer2());
        }

        return javafx.scene.paint.Color.TRANSPARENT;
    }

    /**
     * Converte il tuo enum model.board.Color nel javafx.scene.paint.Color corrispondente
     */
    private javafx.scene.paint.Color convertToFXColor(Color engineColor) {
        if (engineColor == null) {
            return javafx.scene.paint.Color.TRANSPARENT;
        }

        // Adatta i casi in base alle costanti presenti nel tuo enum model.board.Color
        switch (engineColor) {
            case RED:
                return javafx.scene.paint.Color.RED;
            case YELLOW:
                return javafx.scene.paint.Color.YELLOW;
            case BLUE:
                return javafx.scene.paint.Color.BLUE;
            // Se hai altri colori nel tuo enum, aggiungili qui sotto:
            // case GREEN: return javafx.scene.paint.Color.GREEN;
            default:
                return javafx.scene.paint.Color.GRAY;
        }
    }

    private void setCellColor(int row, int col, javafx.scene.paint.Color color) {
        Circle circle = getCircleAt(row, col);
        if (circle != null) {
            circle.setFill(color);
        }
    }

    private Circle getCircleAt(int row, int col) {
        for (javafx.scene.Node node : boardGrid.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            int rowIndex = (r == null) ? 0 : r;
            int colIndex = (c == null) ? 0 : c;

            if (rowIndex == row && colIndex == col) {
                if (node instanceof StackPane stackPane) {
                    if (!stackPane.getChildren().isEmpty() && stackPane.getChildren().getFirst() instanceof Circle) {
                        return (Circle) stackPane.getChildren().getFirst();
                    }
                }
            }
        }
        return null;
    }


    private void handleGameOver() {
        System.out.println("Partita terminata! Vincitore: " + gameManager.getWinner().getName());
    }
}