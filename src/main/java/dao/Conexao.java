package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {  

    private static final String URL = "jdbc:sqlite:cadastro_produto.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBanco() {

        String sql = """
            CREATE TABLE IF NOT EXISTS produto (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL,
                estoque INTEGER NOT NULL
            )
            """;

        try (Connection conexao = conectar();
             Statement comando = conexao.createStatement()) {

            comando.execute(sql);

            System.out.println("Banco SQLite conectado!");
            System.out.println("Tabela produto verificada!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
