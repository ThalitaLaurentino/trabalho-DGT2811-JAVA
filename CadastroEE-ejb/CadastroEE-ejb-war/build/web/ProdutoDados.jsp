<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cadastroee.model.Produto"%>

<html>
<head>
    <title>Cadastro de Produto</title>
</head>

<body>

<%
    Produto p = (Produto) request.getAttribute("produto");
    String acao = (p == null) ? "incluir" : "alterar";
%>

<h2><%= (acao.equals("incluir") ? "Incluir Produto" : "Alterar Produto") %></h2>

<form action="ServletProdutoFC" method="post">

    <input type="hidden" name="acao" value="<%= acao %>">

    <% if (acao.equals("alterar")) { %>
        <input type="hidden" name="id" value="<%= p.getCodigo() %>">
    <% } %>

    Nome: <input type="text" name="nome"
           value="<%= (p != null ? p.getNome() : "") %>"><br><br>

    Quantidade: <input type="number" name="quantidade"
           value="<%= (p != null ? p.getQuantidade() : "") %>"><br><br>

    Preço: <input type="text" name="preco"
           value="<%= (p != null ? p.getPrecoVenda() : "") %>"><br><br>

    <input type="submit" value="<%= (acao.equals("incluir") ? "Cadastrar" : "Salvar Alterações") %>">
</form>

</body>
</html>
