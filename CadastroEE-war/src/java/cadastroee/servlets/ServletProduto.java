package cadastroee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cadastroee.controller.ProdutosFacadeLocal;
import cadastroee.model.Produtos;

public class ServletProduto extends HttpServlet {

    // força a injeção usando o nome do bean EJB (classe ProdutosFacade)
    @EJB(beanName = "ProdutosFacade")
    private ProdutosFacadeLocal facade;

    /**
     * Tenta vários nomes JNDI comuns no GlassFish 6
     */
    private ProdutosFacadeLocal lookupManual(PrintWriter out) {
        try {
            Context ctx = new InitialContext();

            String[] candidates = new String[] {
                // nome completo típico com EAR + EJB-JAR + bean + interface
                "java:global/CadastroEE/CadastroEE-ejb/ProdutosFacade!cadastroee.controller.ProdutosFacadeLocal",
                // só módulo EJB + bean + interface
                "java:app/CadastroEE-ejb/ProdutosFacade!cadastroee.controller.ProdutosFacadeLocal",
                // dentro do módulo
                "java:module/ProdutosFacade!cadastroee.controller.ProdutosFacadeLocal",
                // sem interface
                "java:global/CadastroEE/CadastroEE-ejb/ProdutosFacade",
                "java:app/CadastroEE-ejb/ProdutosFacade",
                "java:module/ProdutosFacade"
            };

            for (String jndi : candidates) {
                try {
                    out.println("<p style='color:gray;'>[DEBUG] Tentando lookup JNDI: " + jndi + "</p>");
                    Object obj = ctx.lookup(jndi);
                    if (obj instanceof ProdutosFacadeLocal) {
                        out.println("<p style='color:green;'>[DEBUG] Lookup OK com: " + jndi + "</p>");
                        return (ProdutosFacadeLocal) obj;
                    } else {
                        out.println("<p style='color:orange;'>[DEBUG] Encontrou objeto, mas não é ProdutosFacadeLocal: "
                                + obj.getClass().getName() + "</p>");
                    }
                } catch (NamingException ex) {
                    out.println("<p style='color:#aa0000;'>[DEBUG] Falha no lookup para "
                            + jndi + ": " + ex.getMessage() + "</p>");
                }
            }

        } catch (Exception ex) {
            out.println("<p style='color:red;'>[ERRO] Falha geral no lookup JNDI: "
                    + ex.getClass().getName() + " - " + ex.getMessage() + "</p>");
        }

        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Produtos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Produtos cadastrados</h1>");

            out.println("<p style='color:blue;'>[DEBUG] Entrei no ServletProduto.processRequest()</p>");
            out.println("<p style='color:purple;'>[DEBUG] facade (injeção @EJB) é "
                    + (facade == null ? "NULL" : "NOT NULL") + "</p>");

            // se @EJB falhou, tenta via JNDI
            if (facade == null) {
                out.println("<hr>");
                out.println("<p style='color:blue;'>[DEBUG] Tentando lookup manual via JNDI...</p>");
                facade = lookupManual(out);
            }

            if (facade == null) {
                out.println("<hr>");
                out.println("<p style='color:red; font-weight:bold;'>[ERRO] facade continua NULL. "
                        + "Provavelmente o módulo EJB (CadastroEE-ejb) não foi implantado com o EAR "
                        + "ou houve erro no deploy do EJB.</p>");
                out.println("</body></html>");
                return;
            }

            try {
                List<Produtos> produtos = facade.findAll();

                if (produtos == null) {
                    out.println("<p style='color:orange;'>[DEBUG] Lista de produtos é NULL.</p>");
                } else {
                    out.println("<p style='color:green;'>[DEBUG] Total de produtos retornados: "
                            + produtos.size() + "</p>");
                }

                if (produtos == null || produtos.isEmpty()) {
                    out.println("<p>Não há produtos cadastrados.</p>");
                } else {
                    out.println("<table border='1' cellpadding='5' cellspacing='0'>");
                    out.println("<tr>");
                    out.println("<th>Código</th>");
                    out.println("<th>Nome</th>");
                    out.println("<th>Preço de venda</th>");
                    out.println("</tr>");

                    for (Produtos p : produtos) {
                        out.println("<tr>");
                        out.println("<td>" + p.getCodigo() + "</td>");
                        out.println("<td>" + p.getNome() + "</td>");
                        out.println("<td>" + p.getPrecovenda() + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");
                }
            } catch (Exception e) {
                out.println("<p style='color:red;'>[ERRO] Erro ao consultar produtos: "
                        + e.getClass().getName() + " - " + e.getMessage() + "</p>");
                e.printStackTrace();
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista produtos usando EJB ProdutosFacadeLocal";
    }
}
