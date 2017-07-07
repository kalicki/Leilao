package gui;

import business.facade.Facade;
import com.jfoenix.controls.JFXToggleButton;
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
  private static final String fxHome = "xHome.fxml";
  private static final String fxProduto = "fxCadastroProduto.fxml";
  private static final String fxLeilao = "fxCadastroLeilao.fxml";

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
    String cpfCnpj =  usuarioLogin.getText();
    String senha =  senhaLogin.getText();
    String tipo = vendedorLogin.getText();

    System.out.println(tipo);

    String resultado = fachadaLeilao.autorizarUsuario(cpfCnpj, senha, tipo);

    if (resultado != null) {
      alertaLogin.setText(resultado);
      return;
    }

    // Redirecionar ao login
    redirecionarHomeOnClick(event);
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
    redirecionarLoginOnClick(event);
  }

  /* HOME */
  @FXML
  public void cadastrarProdutoOnClick(ActionEvent event) {
    redirecionarProduto(event);
  }
  @FXML
  public void cadastrarLeilaoOnClick(ActionEvent event) {
    redirecionarLeilao(event);
  }
  @FXML
  public void cadastrarLoteOnClick(ActionEvent event) {

  }
  @FXML
  public void realizarLanceOnClick(ActionEvent event) {

  }

  /* GENERAL */
  @FXML
  public void redirecionarLoginOnClick(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();

    janela.setScene(new Scene(this.templateLogin()));
    janela.show();
  }
  @FXML
  public void redirecionarHomeOnClick(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();

    janela.setScene(new Scene(this.templateHome()));
    janela.show();
  }
  @FXML
  public void redirecionarProduto(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();

    janela.setScene(new Scene(this.templateProduto()));
    janela.show();
  }
  @FXML
  public void redirecionarLeilao(ActionEvent event) {
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();

    janela.setScene(new Scene(this.templateLeilao()));
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
  private Parent templateHome() {
    try {
      return template = FXMLLoader.load(getClass().getResource(fxHome));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  private Parent templateProduto() {
    try {
      return template = FXMLLoader.load(getClass().getResource(fxProduto));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  private Parent templateLeilao() {
    try {
      return template = FXMLLoader.load(getClass().getResource(fxLeilao));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
