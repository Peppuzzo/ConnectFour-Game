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

import engine.ColorManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import model.board.Color;

public class PlayerSetupController {

  // TODO: size combox1 =! actual?
  @FXML
  private ComboBox<Color> colorsBox;

  @FXML
  private ComboBox<Color> colorsBox2;

  @FXML
  private TextField nameField;

  @FXML
  private TextField nameField2;

  private final ColorManager colorManager = new ColorManager();


  /**
   * Initialize the controller.
   * <p>
   * Populates both color ComboBoxes with the available colors from the
   * ColorManager and sets up their cell factories and button cells so that
   * each item is displayed with a small colored rectangle and the color name.
   */
  @FXML
  public void initialize() {
    colorsBox.getItems().addAll(colorManager.getAvailableColors());
    initializeCell(colorsBox);
    visualButtonCell(colorsBox);
    colorsBox2.getItems().addAll(colorManager.getAvailableColors());
    initializeCell(colorsBox2);
    visualButtonCell(colorsBox2);
  }


  /**
   * Install a custom cell factory on the given ComboBox so items are
   * rendered using {@link #createColorCell()}.
   *
   * @param box the ComboBox to initialize
   */
  @FXML
  protected void initializeCell(ComboBox<Color> box) {
    box.setCellFactory(listView -> createColorCell());
  }


  /**
   * Configure the visible button cell of the given ComboBox to use the
   * same cell appearance produced by {@link #createColorCell()}.
   *
   * @param box the ComboBox to configure the button cell for
   */
  @FXML
  protected void visualButtonCell(ComboBox<Color> box) {
    box.setButtonCell(createColorCell());
  }


  /**
   * Create a ListCell that displays a {@link Color} with a small colored
   * rectangle and the color's name. This cell is suitable for use as a
   * ComboBox cell or button cell.
   *
   * @return a ListCell used to render Color items
   */
  private ListCell<Color> createColorCell() {
    return new ListCell<>() {
      @Override
      protected void updateItem(Color item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
          setText(null);
          setGraphic(null);
        } else {
          Rectangle rect = new Rectangle(20, 20);
          rect.setStyle("-fx-fill: " + item + "; -fx-stroke: black;");
          setText(item.name());
          setGraphic(rect);
        }
      }
    };
  }
}

