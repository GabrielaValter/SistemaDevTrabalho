package br.csi.sistemadev.service;

import br.csi.sistemadev.dao.ProblemaDAO;
import br.csi.sistemadev.model.Problema;
import br.csi.sistemadev.model.Usuario;

import java.util.ArrayList;

public class ProblemaService {

    private static ProblemaDAO problemaDAO = new ProblemaDAO();

    public String inserir(Problema problema, Usuario usuario) {

        // verifica o tipo de usuário
        if (!"empresa".equalsIgnoreCase(usuario.getTipo())) {
            return "Apenas empresas podem cadastrar problemas";
        }

        problema.setIdEmpresa(usuario.getId());

        // verifica se a empresa já cadastrou um problema com esse título
        if (problemaDAO.existeProblemaComTitulo(usuario.getId(), problema.getTitulo())) {
            return "Você já cadastrou um problema com esse título.";
        }

        return problemaDAO.inserir(problema);
    }

    public String alterar(Problema problema, Usuario usuario) {

        // verifica se o usuário é empresa e se foi ela que cadastrou o problema
        if ("empresa".equalsIgnoreCase(usuario.getTipo()) && problema.getIdEmpresa() == usuario.getId()) {
            return problemaDAO.alterar(problema);
        }

        return "Apenas a empresa que cadastrou o problema pode alterar";
    }

    public String excluir(Problema problema, Usuario usuario) {
        Problema problemaBanco = problemaDAO.buscarPorId(problema.getId());

        // busca o problema pelo id, verifica se o usuario é empresa e se é o dono do problema
        if (problema != null && "empresa".equalsIgnoreCase(usuario.getTipo()) && problema.getIdEmpresa() == usuario.getId()) {
            boolean sucesso = problemaDAO.excluir(problema.getId());

            if (sucesso) {
                return "Problema excluído com sucesso";
            } else {
                return "Erro ao excluir o problema";
            }
        }

        return "Apenas a empresa que criou o problema pode excluí-lo";
    }

    public Problema buscarPorId(int id) { return problemaDAO.buscarPorId(id); }

    public ArrayList<Problema> buscarPorTitulo(String titulo) { return problemaDAO.buscarPorTitulo(titulo); }

    public ArrayList<Problema> listarTodos() { return problemaDAO.listarTodos(); }

    public ArrayList<Problema> listarPorEmpresa(int idEmpresa) { return problemaDAO.listarPorEmpresa(idEmpresa); }
}
