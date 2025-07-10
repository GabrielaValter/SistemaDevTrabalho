package br.csi.sistemadev.dao;

import br.csi.sistemadev.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

// inserir um novo usuário (empresa ou programador)
// alterar o usuário
// excluir
// buscar por id
// buscar por email
// buscar por cpf/cnpj
// buscar por nome
// listar

public class UsuarioDAO {

    // inserir usuário (empresa ou programador)
    public String inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection connection = ConectarBancoDados.conectarBancoPostgres();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO usuario (nome, email, senha, tipo, cpf_cnpj, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTipo());
        stmt.setString(5, usuario.getCpfCnpj());
        stmt.setString(6, usuario.getTelefone());
        stmt.setString(7, usuario.getEndereco());

        System.out.println("Inserindo usuário: " + usuario.getEmail());

        stmt.execute(); // Se der erro
        return "Usuário cadastrado com sucesso!";
    }

    // alterar usuário (empresa ou programador)
    public String alterar(Usuario usuario) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo = ?, telefone = ?, endereco = ? WHERE id = ?"
            );

            // cpf ou cnpj não pode ser alterado
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getEndereco());
            stmt.setInt(7, usuario.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuário alterado com sucesso";
    }

    // excluir usuário
    public boolean excluir(int id) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao excluir usuario");
        }

        return true;
    }

    // buscar usuário pelo id
    public Usuario buscarPorId(int id) {
        Usuario usuario = new Usuario();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = objetoUsuario(rs);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao buscar usuário");
        }

        return usuario;
    }


    // buscar usuário pelo email
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = new Usuario();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE email = ?"
            );

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return objetoUsuario(rs);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao buscar usuário pelo e-mail");
        }

        return null;
    }

    // buscar usuário pelo cpf ou cnpj
    public Usuario buscarPorCpfCnpj(String cpfCnpj) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE cpf_cnpj  = ?"
            );

            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return objetoUsuario(rs); // Retorna o objeto encontrado
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null; // Se não encontra retorna null
    }

    // buscar usuário pelo nome
    public ArrayList<Usuario> buscarPorNome(String nome) {
        ArrayList<Usuario> usuario = new ArrayList<>();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE nome = ?"
            );

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuario.add(objetoUsuario(rs));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao buscar usuário pelo nome");
        }

        return usuario;
    }

    // listar todos os usuários
    public ArrayList<Usuario> listarTodos() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("select * from usuario");

            while (rs.next()) {
                Usuario u = objetoUsuario(rs);
                usuarios.add(u);
            }
        } catch (SQLException | ClassNotFoundException exception) {
        System.out.println(exception.getMessage());
        System.out.println("Erro ao listar usuários");
    }

        return usuarios;
    }

    // metodo para montar o objeto Usuario e não deixar repetitivo
    private Usuario objetoUsuario(ResultSet rs) throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipo(rs.getString("tipo"));
        usuario.setCpfCnpj(rs.getString("cpf_cnpj"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setEndereco(rs.getString("endereco"));

        return usuario;
    }

}
