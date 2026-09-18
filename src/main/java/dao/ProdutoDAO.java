package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO {

    // CADASTRAR
    public void cadastrar(Produto produto) {

        String sql = """
            INSERT INTO produto (nome, preco, estoque)
            VALUES (?, ?, ?)
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, produto.getNome());
            comando.setDouble(2, produto.getPreco());
            comando.setInt(3, produto.getEstoque());

            comando.executeUpdate();

            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao cadastrar produto: " + e.getMessage()
            );
        }
    }

    // LISTAR
    public List<Produto> listar() {

        List<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT * FROM produto
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Produto produto = new Produto(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getDouble("preco"),
                    resultado.getInt("estoque")
                );

                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println(
                "Erro ao listar produtos: " + e.getMessage()
            );
        }

        return produtos;
    }

    // EXCLUIR
    public void excluir(int id) {

        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

            System.out.println("Produto excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao excluir produto: " + e.getMessage()
            );
        }
    }

    // ATUALIZAR
    public void atualizar(Produto produto) {

        String sql = """
            UPDATE produto
            SET nome = ?, preco = ?, estoque = ?
            WHERE id = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, produto.getNome());
            comando.setDouble(2, produto.getPreco());
            comando.setInt(3, produto.getEstoque());
            comando.setInt(4, produto.getId());

            comando.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println(
                "Erro ao atualizar produto: " + e.getMessage()
            );
        }
    }
}
