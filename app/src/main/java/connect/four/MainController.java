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
 */

package connect.four;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class MainController {

  @FXML
  private Button modeMultiplayer;

  @FXML
  private Button modeSinglePayer;

  @FXML
  private AnchorPane Pane;

  @FXML
  Stage stage;


  @FXML
  public void handleGameModeSelection(javafx.event.ActionEvent event) {
    Button buttonInput = (Button) event.getSource();
    loadPlayerCreationScreen(buttonInput);
  }


  /**
   * Loads the player setup screen (create-player.fxml), instantiates its controller,
   * and passes the selected game mode to it.
   *
   * @param buttonInput the button clicked by the player, used to retrieve the current window stage.
   */
  private void loadPlayerCreationScreen(Button buttonInput) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/create-player.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage) buttonInput.getScene().getWindow();
      stage.setScene(new Scene(root, 447, 242));
      String css = Objects.requireNonNull(this.getClass().
              getResource("/css/player_setup_controller.css")).
              toExternalForm();
      root.getStylesheets().add(css);
      stage.setResizable(false);
      stage.show();

    } catch (IOException e) {
      System.err.println("Failed to load FXML create-palyer.fxml");
    }
  }


  @FXML
  public void logoutApp() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Logout");
    alert.setHeaderText("You are about to log out of the application!");
    alert.setContentText("Progress will not be saved, confirm choice? ");

    if(alert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent()) {
      stage = (Stage) Pane.getScene().getWindow();
      stage.close();
    }
  }

}


