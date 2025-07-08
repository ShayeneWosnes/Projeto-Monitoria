/*
package br.edu.ifpr.bsi.projetopdm.authguard;

import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/pages/admin.xhtml",
        "/pages/monitor.xhtml",
        "/pages/coordenador.xhtml",
        "/pages/aluno.xhtml",
        "/pages/monitoria.xhtml"
})
public class AuthGuard implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthGuard inicializado");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        System.out.println("=== DEBUG AuthGuard ===");
        System.out.println("Request URI: " + requestURI);
        System.out.println("Context Path: " + contextPath);

        UsuarioSistema usuario = UsuarioSistemaSingleton.getInstance();

        if (usuario == null) {
            System.out.println("AuthGuard inicializado");
            System.out.println("Usuário não autenticado. Redirecionando para login...");

            String loginPath = contextPath + "/pages/login.xhtml";
            System.out.println("Redirecionando para: " + loginPath);

            httpResponse.sendRedirect(loginPath);
            return;
        }

        String nivelAcesso = usuario.getNivelAcesso().toUpperCase();

        if (!isAutorizado(requestURI, nivelAcesso)) {
            System.out.println("Acesso negado para " + usuario.getNome() +
                    " (nível: " + nivelAcesso + ") na página: " + requestURI);

            String loginPath = contextPath + "/pages/login.xhtml?erro=acesso_negado";
            httpResponse.sendRedirect(loginPath);
            return;
        }

        System.out.println("Acesso permitido para " + usuario.getNome() +
                " (nível: " + nivelAcesso + ") na página: " + requestURI);
        chain.doFilter(request, response);
    }

    private boolean isAutorizado(String requestURI, String nivelAcesso) {
        System.out.println("Verificando autorização para: " + requestURI + " com nível: " + nivelAcesso);

        if (requestURI.contains("/admin.xhtml")) {
            return "ADMIN".equals(nivelAcesso);
        }

        if (requestURI.contains("/coordenador.xhtml")) {
            return "COORDENADOR".equals(nivelAcesso) || "ADMIN".equals(nivelAcesso);
        }

        if (requestURI.contains("/monitor.xhtml")) {
            return "MONITOR".equals(nivelAcesso) ||
                    "COORDENADOR".equals(nivelAcesso) ||
                    "ADMIN".equals(nivelAcesso);
        }

        return true;
    }

    @Override
    public void destroy() {
        System.out.println("AuthGuard destruído");
    }
}
*/
