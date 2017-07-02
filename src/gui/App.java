package gui;

import business.action.CategoriaAction;
import business.facade.Facade;
import business.model.Categoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Facade fachada = new Facade();
            fachada.iniciarDB();

            CategoriaAction add = new CategoriaAction();
            Categoria cat1 = new Categoria();

            cat1.setDescricao("abacate");
            add.criar(cat1);

            Categoria a = add.buscar(1);
            System.out.print(a.getDescricao());

            cat1.setDescricao("oi");
            add.atualizar(cat1);

            Categoria a2 = add.buscar(1);
            System.out.print(a.getDescricao());

            add.remover(cat1);
            Categoria a3 = add.buscar(2);
            System.out.print(a.getDescricao());
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
