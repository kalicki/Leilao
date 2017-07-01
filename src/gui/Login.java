package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class Login {
    @FXML private Text alertaLogin;
    @FXML private JFXTextField usuarioLogin;
    @FXML private JFXPasswordField senhaLogin;

    @FXML
    public void entrarOnClick(ActionEvent event) {
      if (usuarioLogin.getText().equalsIgnoreCase("abc") && senhaLogin.getText().equalsIgnoreCase("abc")) {
        try {
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
          stage.setTitle("Leilão");
          stage.setScene(new Scene(root));
          stage.show();
        } catch (Exception e) {
          e.printStackTrace();
        }

      } else {
        alertaLogin.setText("Algo erradoooo!!!");
      }
    }

    @FXML
    public void registerOnClick(ActionEvent event) {
      //logic code:
      alertaLogin.setText("Register not yet implemented!");
    }
}
