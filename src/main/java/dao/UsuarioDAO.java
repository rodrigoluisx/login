package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean verificarLogin(String usuario, String senha) {
        String sql = """
            SELECT id
            FROM usuario
            WHERE usuario = ? AND senha = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario);
            comando.setString(2, senha);

            try (ResultSet resultado = comando.executeQuery()) {
                return resultado.next();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
            return false;
        }
    }
}
