package gui;

import business.facade.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Facade fachada = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("xLogin.fxml"));
        primaryStage.setTitle("Leilão");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            if (fachada == null) {
                fachada = new Facade();
            }
            fachada.iniciarDB();

        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
