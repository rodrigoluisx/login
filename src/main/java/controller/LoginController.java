package controller;

import dao.UsuarioDAO;

public class LoginController {

    private final UsuarioDAO usuarioDAO;

    public LoginController() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean realizarLogin(String usuario, String senha) {
        return usuarioDAO.verificarLogin(usuario, senha);
    }
}
