package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Produto> produtos = facade.findAll();

            out.println("<html><head><title>Produtos</title></head><body>");
            out.println("<h1>Lista de Produtos</h1>");
            if (produtos.isEmpty()) {
                out.println("<p>Nenhum produto cadastrado.</p>");
            } else {
                out.println("<table border='1'><tr><th>Código</th><th>Nome</th><th>Qtd</th><th>Preço</th></tr>");
                for (Produto p : produtos) {
                    out.println("<tr><td>" + p.getCodigo() + "</td><td>" + p.getNome() + "</td><td>" + p.getQuantidade() + "</td><td>" + p.getPrecoVenda() + "</td></tr>");
                }
                out.println("</table>");
            }
            out.println("</body></html>");
        }
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
}
