package br.csi.sistemadev.controller;

import br.csi.sistemadev.model.Usuario;
import br.csi.sistemadev.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "pages/cadastro/cadastro"; // vai para WEB-INF/views/cadastro/cadastro.jsp
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {

        System.out.println("Verificando duplicidade...");
        if (usuarioService.buscarPorEmail(usuario.getEmail()) != null) {
            model.addAttribute("msg", "E-mail já cadastrado");
            return "pages/cadastro/cadastroForm";
        } else if (usuarioService.buscarPorCpfCnpj(usuario.getCpfCnpj()) != null) {
            model.addAttribute("msg", "CPF/CNPJ já cadastrado");
            return "pages/cadastro/cadastroForm";
        }

        try {
            String retorno = usuarioService.inserir(usuario);
            redirectAttributes.addFlashAttribute("msg", retorno);
            return "redirect:/usuario/cadastroFinal";

        } catch (Exception e) {
            model.addAttribute("msg", "Erro ao cadastrar: " + e.getMessage());
            model.addAttribute("usuario", usuario);
            return "pages/cadastro/cadastroForm"; // Se der erro permanece na página de cadastro
        }
    }

    @GetMapping("/cadastroForm")
    public String mostrarFormularioFinal(@RequestParam("tipo") String tipo, Model model) {
        Usuario usuario = new Usuario();
        usuario.setTipo(tipo);

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoSelecionado", tipo);
        return "pages/cadastro/cadastroForm";
    }

    @GetMapping("/cadastroFinal")
    public String mostrarConfirmacao() {
        return "pages/cadastro/cadastroFinal"; // vai para WEB-INF/views/cadastro/cadastroFinal.jsp
    }

}
