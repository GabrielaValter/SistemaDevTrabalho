package br.csi.sistemadev.controller;

import br.csi.sistemadev.model.Usuario;
import br.csi.sistemadev.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService = new LoginService();

    @GetMapping
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("login", "");
        return "pages/login"; // /WEB-INF/pages/login.jsp
    }

    @PostMapping
    public String processarLogin(@RequestParam("login") String login,
                                 @RequestParam("senha") String senha,
                                 HttpServletRequest request,
                                 Model model) {

        Usuario usuario = loginService.autenticar(login, senha);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/dashboard"; // ou outra página protegida
        } else {
            model.addAttribute("msgErro", loginService.mensagemErro());
            model.addAttribute("login", login);
            return "pages/login"; // volta para a tela de login com erro
        }
    }
}
