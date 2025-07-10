package br.csi.sistemadev.service;

import br.csi.sistemadev.dao.UsuarioDAO;
import br.csi.sistemadev.model.Usuario;

public class LoginService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String mensagemErro;

    public Usuario autenticar(String login, String senha) {

        Usuario usuario = null;

        // primeiro tenta buscar po email
        usuario = usuarioDAO.buscarPorEmail(login);

        // se não encontrar por email, tenta buscar por cpf/cnpj
        if(usuario == null || usuario.getId() == 0) {
            usuario = usuarioDAO.buscarPorCpfCnpj(login);
        }

        if(usuario == null || usuario.getId() == 0) {
            mensagemErro = "Usuário não encontrado";
            return null;
        }

        // se encontrar o usuário valida a senha
        if(usuario.getSenha() == null || !usuario.getSenha().equals(senha)) {
            mensagemErro = "Senha incorreta";
            return null;
        }

        // login ok
        mensagemErro = null;
        return usuario;
    }

    public String mensagemErro() {
        return mensagemErro;
    }
}
