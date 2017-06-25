package business.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManipulaDB {

    public static void selectAllTableUsuario(Connection connection) throws SQLException {
        String sql = "SELECT * " + "FROM usuario";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int tipo = rs.getInt("tipo");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String nomeRua = rs.getString("nomeRua");
            String email = rs.getString("email");
            String numeroResidencia = rs.getString("numeroResidencia");
            System.out.println(tipo + "\t" + nome + "\t" + nomeRua + "\t" + email + "\t" + numeroResidencia);
        }
        statement.close();
        rs.close();
    }

    public static boolean insert(Connection connection, int tipo, String nome, String cpf, String nomeRua, String email, String numeroResidencia) throws SQLException{
        String sql = "INSERT INTO usuario (tipo, nome, cpf, nomeRua, email, numeroResidencia) " +
                "VALUES (seq_filmes.nextval, " + tipo + ", '" + nome + ", " + cpf + ", " + nomeRua + ", " + email + ", " + numeroResidencia + "')";

        Statement statement = connection.createStatement();
        int rowCount = statement.executeUpdate(sql);

        // Row count is the number of affected registers. In this case,
        // when equals to one means that a register was succesfully inserted.
        return rowCount == 1;
    }

}
