package business.db;

import business.model.UsuarioTipo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManipulaDB {



    public static void selectAll(Connection connection) throws SQLException {
        String sql = "SELECT * " + "FROM usuario";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String cpfCnpj = rs.getString("cpfCnpj");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            String enderecoRua = rs.getString("enderecoRua");
            int enderecoNumero = rs.getInt("enderecoNumero");
            System.out.println(cpfCnpj + "\t" + nome + "\t" + email + "\t" + email + "\t" + senha + "\t" + enderecoRua + "\t" + enderecoNumero);
        }
        statement.close();
        rs.close();


    }

    public static boolean insert(Connection connection, String cpfCnpj, UsuarioTipo usuarioTipo, String nome, String email, String senha, String enderecoRua, Integer enderecoNumero) throws SQLException{
        String sql = "INSERT INTO usuario (cpfCnpj, nome, email, senha, enderecoRua, enderecoNumero) " +
                "VALUES (seq_val.nextval, " + cpfCnpj + ", '" + nome + ", " + email + ", " + senha + ", " + enderecoRua + ", " + enderecoNumero + "')";

        Statement statement = connection.createStatement();
        int rowCount = statement.executeUpdate(sql);

        // Row count is the number of affected registers. In this case,
        // when equals to one means that a register was succesfully inserted.
        return rowCount == 1;
    }

    public static void select(Connection connection, String cpfCnpj) throws SQLException {
        String sql = "SELECT * " + "FROM usuario " + "WHERE cpfCnpj = " + cpfCnpj;

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            //String cpfCnpj = rs.getString("cpfCnpj");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            String enderecoRua = rs.getString("enderecoRua");
            int enderecoNumero = rs.getInt("enderecoNumero");
            System.out.println(cpfCnpj + "\t" + nome + "\t" + email + "\t" + email + "\t" + senha + "\t" + enderecoRua + "\t" + enderecoNumero);
        }


        statement.close();
        rs.close();
    }

    public static boolean delete(Connection connection, String cpfCnpj) throws SQLException{
        String sql = "DELETE FROM usuario " +
                "WHERE cpfCnpj = " + cpfCnpj;

        Statement statement = connection.createStatement();
        int rowCount = statement.executeUpdate(sql);

        // Row count is the number of affected registers. In this case,
        // when equals to one means that a register was succesfully deleted.
        return rowCount == 1;
    }

    public static boolean update(Connection connection, String cpfCnpj) throws SQLException{
        String sql = "UPDATE usuario " +
                "WHERE cpfCnpj = " + cpfCnpj;

        Statement statement = connection.createStatement();
        int rowCount = statement.executeUpdate(sql);

        // Row count is the number of affected registers. In this case,
        // when equals to one means that a register was succesfully deleted.
        return rowCount == 1;
    }

}
