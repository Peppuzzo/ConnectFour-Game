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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import model.board.Board;
import model.board.Cell;
import model.board.Color;

import java.util.Locale;

public class GameBoardController {

    @FXML
    private GridPane boardGrid;

    private final GameManager gameManager = GameFactory.getGameManager();

    @FXML
    private void handleColumnClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int col = Integer.parseInt(clickedButton.getUserData().toString());

        boolean moveSuccessful = gameManager.makeMove(col);

        //TODO: handle the case where the grid is full, on the GUI side
        if (moveSuccessful) {
            updateBoardUI();

            if (gameManager.isGameOver()) {
                handleGameOver();
            }
        } else {
            System.out.println("Column full! Choose another one.");
        }
    }


    /**
     * Completely updates the chessboard's visual representation by reading the state from the model.
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

                javafx.scene.paint.Color fxColor = mapCellColorToFX(cell);
                setCellColor(row, col, fxColor);
            }
        }
    }


    /**
     * Converts the model cell state into a JavaFX color using the factory
     */
    private javafx.scene.paint.Color mapCellColorToFX(Cell cell) {
        if (cell == null || cell.color() == null) {
            return javafx.scene.paint.Color.TRANSPARENT;
        }

        Color cellColor = cell.color();

        if (cellColor == GameFactory.getColorPlayer1()) {
            return convertToFXColor(GameFactory.getColorPlayer1());
        } else if (cellColor == GameFactory.getColorPlayer2()) {
            return convertToFXColor(GameFactory.getColorPlayer2());
        }

        return javafx.scene.paint.Color.TRANSPARENT;
    }


    /**
     * Converts the `model.board.Color` enum into the corresponding `javafx.scene.paint.Color`
     */
    //TODO: It doesn't include all the colors!!
    private javafx.scene.paint.Color convertToFXColor(Color engineColor) {
        if (engineColor == null) {
            return javafx.scene.paint.Color.TRANSPARENT;
        }
        return switch (engineColor) {
            case RED -> javafx.scene.paint.Color.RED;
            case YELLOW -> javafx.scene.paint.Color.YELLOW;
            case BLUE -> javafx.scene.paint.Color.BLUE;
            case GREEN -> javafx.scene.paint.Color.GREEN;
        };
    }


    /**
     * Sets the fill color of the circle located at the specified grid coordinates.
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param color the JavaFX color to apply to the circle
     */
    private void setCellColor(int row, int col, javafx.scene.paint.Color color) {
        Circle circle = getCircleAt(row, col);
        if (circle != null) {
            circle.setFill(color);
        }
    }


    /**
     * Retrieves the JavaFX Circle node located at the specified row and column
     * within the board grid.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the {@link Circle} object at the given position, or {@code null} if no circle is found
     */
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


    /**
     * Generate a simple GUI to display the winner's
     * name and terminate the program (provisional)
     */
    //TODO: Redefining the method
    private void handleGameOver() {
        ButtonType loginButtonType = new ButtonType("EXIT", ButtonBar.ButtonData.FINISH);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("End game");
        dialog.setContentText("The winner is: "  + GameFactory.getGameManager().
                getWinner().getName().toUpperCase(Locale.ROOT));
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        dialog.showAndWait();
        dialog.close();
        Platform.exit();
    }
}