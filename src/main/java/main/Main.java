package main;

import dao.Conexao;
import view.TelaCadastro;
import view.TelaLogin;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Conexao.inicializarBanco();

        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true);
        });
    }
}
