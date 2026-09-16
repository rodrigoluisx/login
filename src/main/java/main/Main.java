package main;

import dao.Conexao;
import view.TelaLogin;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Conexao.inicializarBanco();

        SwingUtilities.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        });
    }
}
