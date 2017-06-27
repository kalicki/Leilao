package gui;

import business.db.ManipulaDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import business.db.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Database db = new Database();
        Connection dbCom = db.DatabaseConnection();

        /*
        try{
            ManipulaDB.selectAll(dbCom);
            ManipulaDB.select(dbCom,"11111111111");
        }
        catch (SQLException ex) {

            ex.printStackTrace();
            System.exit(-1);
        };
        */

        launch(args);
    }
}
