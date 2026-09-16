package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel lblMensagem;
    private JButton btnEntrar;

    private final LoginController controller;

    public TelaLogin() {
        controller = new LoginController();
        configurarTela();
    }

    private void configurarTela() {
        setTitle("Tela de Login");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("LOGIN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Usuário:"), gbc);

        txtUsuario = new JTextField(18);
        gbc.gridx = 1;
        painel.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Senha:"), gbc);

        txtSenha = new JPasswordField(18);
        gbc.gridx = 1;
        painel.add(txtSenha, gbc);

        btnEntrar = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        painel.add(btnEntrar, gbc);

        lblMensagem = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridy = 4;
        painel.add(lblMensagem, gbc);

        btnEntrar.addActionListener(e -> realizarLogin());
        txtSenha.addActionListener(e -> realizarLogin());

        add(painel);
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            lblMensagem.setText("Preencha usuário e senha.");
            lblMensagem.setForeground(Color.RED);
            return;
        }

        if (controller.realizarLogin(usuario, senha)) {
            lblMensagem.setText("Login realizado com sucesso!");
            lblMensagem.setForeground(new Color(0, 130, 0));
        } else {
            lblMensagem.setText("Falha no login! Usuário ou senha incorretos.");
            lblMensagem.setForeground(Color.RED);
        }
    }
}
