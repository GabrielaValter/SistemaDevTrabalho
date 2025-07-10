package br.csi.sistemadev.controller;

import br.csi.sistemadev.model.Problema;
import br.csi.sistemadev.model.Usuario;
import br.csi.sistemadev.service.ProblemaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/problema")
public class ProblemaController {

    private final ProblemaService problemaService = new ProblemaService();

    @GetMapping("/cadastrar")
    public String formCadastrarProblema(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !"empresa".equalsIgnoreCase(usuario.getTipo())) {
            model.addAttribute("msg", "Apenas empresas podem acessar esta página.");
            return "pages/erro";
        }

        model.addAttribute("problema", new Problema());
        return "pages/problema/problemaForm";
    }

    @PostMapping("/cadastrar")
    public String cadastrarProblema(@ModelAttribute Problema problema,
                                    HttpSession session,
                                    Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (problema.getStatus() == null) {
            problema.setStatus(true);
        }

        String msg = problemaService.inserir(problema, usuario);

        if (msg.contains("sucesso")) {
            return "redirect:/perfil";
        } else {
            model.addAttribute("msg", msg);
            model.addAttribute("problema", problema);
            return "pages/problema/problemaForm";
        }
    }

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        model.addAttribute("problemas", problemaService.listarTodos());
        return "pages/problema/listar"; // /WEB-INF/pages/problema/listar.jsp
    }

    @GetMapping("/listarPorEmpresa")
    public String listarDaEmpresa(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario != null && "empresa".equalsIgnoreCase(usuario.getTipo())) {
            model.addAttribute("problemas", problemaService.listarPorEmpresa(usuario.getId()));
            return "pages/problema/listarPorEmpresa";
        }

        return "redirect:/login";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Problema problema = problemaService.buscarPorId(id);

        if (problema != null && problema.getIdEmpresa() == usuario.getId()) {
            model.addAttribute("problema", problema);
            return "pages/problema/problemaForm";
        }

        return "redirect:/problema/listarEmpresa";
    }

    @PostMapping("/editar")
    public String salvarEdicao(@ModelAttribute Problema problema,
                               HttpSession session,
                               Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        String msg = problemaService.alterar(problema, usuario);

        if (msg.contains("sucesso")) {
            return "redirect:/problema/listarPorEmpresa";
        } else {
            model.addAttribute("msg", msg);
            model.addAttribute("problema", problema);
            return "pages/problema/problemaForm";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Problema problema = problemaService.buscarPorId(id);

        String msg = problemaService.excluir(problema, usuario);

        if (msg.contains("sucesso")) {
            return "redirect:/problema/listarPorEmpresa";
        } else {
            model.addAttribute("msg", msg);
            return "pages/erro";
        }
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable("id") int id, Model model) {
        Problema problema = problemaService.buscarPorId(id);
        model.addAttribute("problema", problema);
        return "pages/problema/detalhes"; // /WEB-INF/pages/problema/detalhes.jsp
    }
}
