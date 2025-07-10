package br.csi.sistemadev.dao;

import br.csi.sistemadev.model.Problema;

import java.sql.*;
import java.util.ArrayList;

// inserir um novo problema vinculado à empresa
// alterar
// excluir
// buscar por id
// buscar por titulo
// listar todos os problemas
// listar todos os problemas criados por determinada empresa

public class ProblemaDAO {

    // empresa deve inserir um problema
    public String inserir(Problema problema) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO problema (titulo, descricao, valor, status, id_empresa) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, problema.getTitulo());
            stmt.setString(2, problema.getDescricao());
            stmt.setBigDecimal(3, problema.getValor());
            stmt.setBoolean(4, problema.getStatus());
            stmt.setInt(5, problema.getIdEmpresa());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao inserir usuario");
        }

        return "Problema inserido com sucesso";
    }

    // a mesma empresa não pode criar dois problemas com o mesmo nome
    public boolean existeProblemaComTitulo(int idEmpresa, String titulo) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT 1 FROM problema WHERE id_empresa = ? AND titulo ILIKE ?"
            );
            stmt.setInt(1, idEmpresa);
            stmt.setString(2, titulo);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // retorna true se encontrou algum
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // alterar
    public String alterar(Problema problema){
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE problema SET titulo = ?, descricao = ?, valor = ?, status = ? WHERE id = ?"
            );

            // o id da empresa não pode ser alterado
            stmt.setString(1, problema.getTitulo());
            stmt.setString(2, problema.getDescricao());
            stmt.setBigDecimal(3, problema.getValor());
            stmt.setBoolean(4, problema.getStatus());
            stmt.setInt(5, problema.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao alterar problema");
        }

        return "Problema alterado com sucesso";
    }

    // excluir proplema
    public boolean excluir(int id) {
        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM problema WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if(stmt.getUpdateCount() <= 0){
                return false;
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao excluir problema");
        }

        return true;
    }

    // buscar problema pelo id
    public Problema buscarPorId(int id) {
        Problema problema = new Problema();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM problema WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                problema = objetoProblema(rs);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao buscar problema");
        }

        return problema;
    }

    // buscar problema pelo título
    public ArrayList<Problema> buscarPorTitulo(String titulo) {

        ArrayList<Problema> problemas = new ArrayList<>();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM problema WHERE titulo = ?"
            );

            stmt.setString(1, titulo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Problema problema = objetoProblema(rs);
                problemas.add(problema);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao buscar problema");
        }

        return problemas;
    }

    // listar todos os problemas criados
    public ArrayList<Problema> listarTodos() {
        ArrayList<Problema> problemas = new ArrayList<>();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = connection.createStatement();

            String sql = "SELECT p.id, p.titulo, p.descricao, p.valor, p.status, p.id_empresa, u.nome " +
                    "FROM problema p JOIN usuario u ON p.id_empresa = u.id WHERE p.status = true";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                problemas.add(objetoProblema(rs));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao listar problemas");
        }

        return problemas;
    }


    // listar todos os problemas criados por determinada empresa
    public ArrayList<Problema> listarPorEmpresa(int id_empresa) {

        ArrayList<Problema> lista = new ArrayList<>();

        try {
            Connection connection = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM problema WHERE id_empresa = ?");

            stmt.setInt(1, id_empresa);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(objetoProblema(rs));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao listar problemas por empresa");
        }

        return lista;
    }

    // metodo para montar o objeto Problema
    private Problema objetoProblema(ResultSet rs) throws SQLException {

        Problema problema = new Problema();

        problema.setId(rs.getInt("id"));
        problema.setTitulo(rs.getString("titulo"));
        problema.setDescricao(rs.getString("descricao"));
        problema.setValor(rs.getBigDecimal("valor"));
        problema.setStatus(rs.getBoolean("status"));
        problema.setIdEmpresa(rs.getInt("id_empresa"));

        problema.setNome(rs.getString("nome"));

        return problema;
    }
}
