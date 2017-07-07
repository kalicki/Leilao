package gui;

import business.facade.Facade;
import business.model.Categoria;
import business.model.Usuario;
import business.model.UsuarioTipo;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
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


public class Controle {
  private static Stage janela = null;
  private static Parent template = null;
  private static Facade fachadaLeilao = new Facade();
  private static final String fxLogin = "xLogin.fxml";
  private static final String fxCadastroUsuario = "xCadastroUsuario.fxml";

  /* Login */
  @FXML private Text alertaLogin;
  @FXML private JFXTextField usuarioLogin;
  @FXML private JFXPasswordField senhaLogin;
  @FXML private JFXToggleButton vendedorLogin;

  /* Cadastro */
  @FXML private Text alertaCadastro;
  @FXML private JFXTextField nomeCadastro;
  @FXML private JFXTextField emailCadastro;
  @FXML private JFXTextField enderecoCadastro;
  @FXML private JFXTextField numeroCadastro;
  @FXML private JFXTextField cpfCnpjCadastro;
  @FXML private JFXToggleButton vendedorCadastro;
  @FXML private JFXPasswordField senhaCadastro;


  /* LOGIN */
  @FXML
  public void entrarOnClick(ActionEvent event) {
    if (usuarioLogin.getText().equalsIgnoreCase("abc") && senhaLogin.getText().equalsIgnoreCase("abc")) {
      janela.setScene(new Scene(this.templateLogin()));
      janela.show();
    } else {
      alertaLogin.setText("");
    }
  }
  @FXML
  public void registrarOnClick(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    janela.setScene(new Scene(this.templateCadastro()));
    janela.show();
  }

  /* CADASTRO */
  @FXML
  public void criarUsuarioOnClick(ActionEvent event) {
    String cpfCnpj =  cpfCnpjCadastro.getText();
    String tipo = vendedorCadastro.getText();
    String nome = nomeCadastro.getText();
    String email = emailCadastro.getText();
    String endereco = enderecoCadastro.getText();
    String endNumero = numeroCadastro.getText();
    String senha =  senhaCadastro.getText();

    String resultado = fachadaLeilao.adicionarUsuario(cpfCnpj, tipo, nome, email, senha, endereco, endNumero);

    if (resultado != null) {
      alertaCadastro.setText(resultado);
      return;
    }

    // Retorna ao login
    alertaCadastro.setText("Cadastro Efetuado!!! Redirecionando para o Login...");
    try {
      Thread.sleep(20000);

      janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
      janela.setScene(new Scene(this.templateLogin()));
      janela.show();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void voltarCadastroOnClick(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();

    janela.setScene(new Scene(this.templateLogin()));
    janela.show();
  }


  /* TEMPLATES */
  private Parent templateLogin() {
    try {
      return template = FXMLLoader.load(getClass().getResource(fxLogin));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  private Parent templateCadastro() {
    try {
      return template = FXMLLoader.load(getClass().getResource(fxCadastroUsuario));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
