package gui;

import business.action.CategoriaAction;
import business.facade.Facade;
import business.model.Categoria;
import java.util.Arrays;
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


            CategoriaAction cat = new CategoriaAction();

            Categoria categoriaAbcate = new Categoria();// Cria instancia do objeto
            categoriaAbcate.setDescricao("abacate");    // Cria a descricao do Abacate
            cat.criar(categoriaAbcate);                 // Cria no SQL a categoria

            // Consulta objeto adcionado
            Categoria buscaAbacate = cat.buscar(1);
            if (buscaAbacate != null)
                System.out.println("buscou id 1:" + buscaAbacate.getDescricao());

            // Atualiza descricao de Abacate para Laranja
            categoriaAbcate.setDescricao("laranja");
            cat.atualizar(categoriaAbcate);
            System.out.println("atualizou id 1:" + categoriaAbcate.getDescricao()); // Tem que mostrar laranja

            Categoria a2 = cat.buscar(1);
            if (a2 != null)
                System.out.println("atualizou buscou id 1:" + a2.getDescricao());

            cat.remover(categoriaAbcate);
            Categoria consultaAbacateRemovido = cat.buscar(1);
            if (consultaAbacateRemovido != null)
                System.out.println("atualizou buscou id 1:" + consultaAbacateRemovido.getDescricao());

            // lista todos
            Categoria mamao = new Categoria();
            mamao.setDescricao("mamao");
            cat.criar(mamao);
            System.out.println(cat.listar().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
