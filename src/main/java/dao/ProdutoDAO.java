package dao;

import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ProdutoDAO {
    
    public void cadastrar(Produto produto){
        String sql =  "INSERT INTO produto (nome, preco, estoque) VALUES (?, ?, ?,)";
        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setString(1, produto.getNome());
            comando.setDouble(2, produto.getPreco());
            comando.setInt(3, produto.getEstoque());

            comando.executeUpdate();

            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao cadastrar produto:" + e.getMessage());
        }
    }
}
