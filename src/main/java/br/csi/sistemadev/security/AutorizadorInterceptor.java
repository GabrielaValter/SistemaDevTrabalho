package br.csi.sistemadev.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // Verifica se a URL acessada é a de login
        String uri = request.getRequestURI();

        if (uri.equals("/") ||
                uri.startsWith("/login") ||
                uri.startsWith("/usuario/cadastro") ||
                uri.startsWith("/usuario/cadastroForm") ||
                uri.startsWith("/usuario/cadastroFinal") ||
                uri.startsWith("/css/")
                || uri.startsWith("/logout")) {
            return true;
        }

        // Verifica se o usuário está logado
        if (request.getSession().getAttribute("usuarioLogado") == null) {
            response.sendRedirect("/login"); // Redireciona para a página de login
            return false;
        }
        return true;
    }
}
