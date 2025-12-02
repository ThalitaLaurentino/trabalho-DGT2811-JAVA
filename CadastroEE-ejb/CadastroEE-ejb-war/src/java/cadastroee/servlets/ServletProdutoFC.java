package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String destino = "";

        if (acao == null) acao = "listar";

        switch (acao) {

            case "listar":
                request.setAttribute("lista", facade.findAll());
                destino = "ProdutoLista.jsp";
                break;

            case "formIncluir":
                destino = "ProdutoDados.jsp";
                break;

            case "formAlterar":
                Integer idAlt = Integer.parseInt(request.getParameter("id"));
                Produto prodAlt = facade.find(idAlt);
                request.setAttribute("produto", prodAlt);
                destino = "ProdutoDados.jsp";
                break;

            case "excluir":
                Integer idExc = Integer.parseInt(request.getParameter("id"));
                Produto prodExc = facade.find(idExc);
                facade.remove(prodExc);
                request.setAttribute("lista", facade.findAll());
                destino = "ProdutoLista.jsp";
                break;

            case "alterar":
                Integer idA = Integer.parseInt(request.getParameter("id"));
                Produto pAlt = facade.find(idA);

                pAlt.setNome(request.getParameter("nome"));
                pAlt.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                pAlt.setPrecoVenda(new BigDecimal(request.getParameter("preco")));

                facade.edit(pAlt);

                request.setAttribute("lista", facade.findAll());
                destino = "ProdutoLista.jsp";
                break;

                case "incluir":
                   Produto pInc = new Produto();

                   int novoId = facade.findAll().size() + 1; 
                   pInc.setCodigo(novoId);

                   pInc.setNome(request.getParameter("nome"));
                   pInc.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                   pInc.setPrecoVenda(new BigDecimal(request.getParameter("preco")));

                   facade.create(pInc);

                   request.setAttribute("lista", facade.findAll());
                   destino = "ProdutoLista.jsp";
                   break;

        }

        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
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
