package br.csi.sistemadev.dao;//package br.csi.dao;
//
//
//// inserir
////alterar
//// buscar por id
//// listar por problema
//// selecionar proposta
//// excluir
//// alterar pelo usuário antes de selecionada
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class PropostaDAO {
//
//    // excluir proposta
//    public boolean excluir(int id) {
//        try {
//            Connection connection = ConectarBancoDados.conectarBancoPostgres();
//            PreparedStatement stmt = connection.prepareStatement(
//                    "DELETE FROM problema WHERE id = ?"
//            );
//
//            stmt.setInt(1, id);
//
//            stmt.execute();
//
//            if (stmt.getUpdateCount() <= 0) {
//                return false;
//            }
//        } catch (SQLException | ClassNotFoundException exception) {
//            System.out.println(exception.getMessage());
//            System.out.println("Erro ao excluir problema");
//        }
//
//        return true;
//    }
//}
