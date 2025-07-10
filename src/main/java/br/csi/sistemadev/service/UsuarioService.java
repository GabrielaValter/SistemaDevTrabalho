package br.csi.sistemadev.service;

import br.csi.sistemadev.dao.UsuarioDAO;
import br.csi.sistemadev.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioService {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public String inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
        return usuarioDAO.inserir(usuario);
    }
    public  String alterar(Usuario usuario) {
        return usuarioDAO.alterar(usuario);
    }

    public String excluir(Usuario usuario, Usuario usuarioLogado) {

        if (usuarioLogado != null && usuarioLogado.getId() == usuario.getId()) {
            boolean sucesso = usuarioDAO.excluir(usuario.getId());

            if (sucesso) {
                return "Usuário excluído com sucesso";
            } else {
                return "Erro ao excluir usuário";
            }
        }
        return "Você só pode excluir sua própria conta";
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    public Usuario buscarPorCpfCnpj(String cpfCnpj) {
        return usuarioDAO.buscarPorCpfCnpj(cpfCnpj);
    }

    public ArrayList<Usuario> buscarPorNome(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public ArrayList<Usuario> listarTodos() { return usuarioDAO.listarTodos(); }


}
