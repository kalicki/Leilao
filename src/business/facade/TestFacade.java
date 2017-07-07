package business.facade;

import static org.junit.jupiter.api.Assertions.*;

import business.model.Categoria;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Lote;
import business.model.Produto;
import business.model.Usuario;
import java.sql.Timestamp;
import java.util.List;

class TestFacade {
  private Facade fachadaLeilao = new Facade();

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }

  @org.junit.jupiter.api.Test
  void iniciarDB() {
  }

  @org.junit.jupiter.api.Test
  void adicionarUsuario() {

    // Usuario já exsite com mesmo CPF/CNPJ
    String resultado = fachadaLeilao.adicionarUsuario("33312109933", "Vendedor", "Abreu", "abreu@email.com", "senha", "end av end", "1");
    if (resultado != null) {
      System.out.println(resultado);
    }

    // Usuario com as mesmas informações mas com CPF/CNPJ diferente junto com o Email
    String resultado1 = fachadaLeilao.adicionarUsuario("33312109922", "Vendedor", "Abreu", "abreu2@email.com", "senha", "end av end", "1");
    System.out.println(resultado1); // Null Cadastrado

    // Usuario participante
    String resultado2 = fachadaLeilao.adicionarUsuario("33312109922", "Participante", "Antonio", "Antonio@email.com", "senha", "end av 2", "1");
    System.out.println(resultado2); // Null Cadastrado
  }

  @org.junit.jupiter.api.Test
  void autorizarUsuario() {
    // Credenciais de vendedor mas nao informado
    String resultado = fachadaLeilao.autorizarUsuario("33312109933", "senha", "PARTICIPANTE");
    System.out.println("Resultado " + resultado + " = ERRO"); // Expectativa

    // Credenciais de vendedor mas nao informado
    String resultado2 = fachadaLeilao.autorizarUsuario("33312109933", "33", "VENDEDOR");
    System.out.println("Resultado " + resultado2 + " = null"); // Expectativa null
  }

  @org.junit.jupiter.api.Test
  void adicionarCategoria() {
    String resultado1 = fachadaLeilao.adicionarCategoria("Informatica");
    String resultado2 = fachadaLeilao.adicionarCategoria("Construcao");
    String resultado3 = fachadaLeilao.adicionarCategoria("Carros");
    String resultado4 = fachadaLeilao.adicionarCategoria(null);

    System.out.println(resultado1 + " = null");
    System.out.println(resultado2 + " = null");
    System.out.println(resultado3 + " = null");
    System.out.println(resultado4 + " = ERRO");
  }

  @org.junit.jupiter.api.Test
  void adicionarLeilao() {
    Timestamp tempoInicio = new Timestamp(System.currentTimeMillis());
    Timestamp tempoTermino = new Timestamp(System.currentTimeMillis());
    tempoTermino.setTime((((14 * 60) + 59)* 1000)); // Aumenta tempo

    Usuario vendedor = fachadaLeilao.buscarUsuario("33312109933"); // Vendedor
    String resultado1 = fachadaLeilao.adicionarLeilao(LeilaoTipo.DEMANDA, LanceTipo.ABERTO,  tempoInicio, tempoTermino, vendedor);

    System.out.println(resultado1 + " = null");
  }

  @org.junit.jupiter.api.Test
  void adicioanarLote() {
    Leilao leilao = fachadaLeilao.buscarLeilao(1); // busca leilao 1
    if (leilao == null) {
      System.out.println("Nada Encontrado!");
      return;
    }

    List<Produto> produto = null;

    String resultado = fachadaLeilao.adicioanarLote(leilao);
    System.out.println(resultado);
  }

  @org.junit.jupiter.api.Test
  void adicinar() {
    Lote lote = fachadaLeilao.buscarLote(1); // busca lote 1
    if (lote == null) {
      System.out.println("Lote nao Encontrado!");
      return;
    }
    Categoria cat = fachadaLeilao.buscarCategoria(1); // busca leilao 1

    String resultado = fachadaLeilao.adicinar("Produto Abacate", "abacate vendedo", lote, cat);
    System.out.println(resultado + " = Cadastrado!");
  }

  @org.junit.jupiter.api.Test
  void adicionarLance() {
  }
}