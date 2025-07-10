package br.csi.sistemadev.controller;

import br.csi.sistemadev.model.Problema;
import br.csi.sistemadev.model.Usuario;
import br.csi.sistemadev.service.ProblemaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DashboardController {

    private final ProblemaService problemaService = new ProblemaService();

    @GetMapping("/dashboard")
    public String mostrarDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario != null) {
            ArrayList<Problema> problemas = problemaService.listarTodos();
            model.addAttribute("problemas", problemas);
            model.addAttribute("usuario", usuario);
            return "pages/dashboard";
        }

        return "redirect:/login";
    }
}
