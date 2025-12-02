<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cadastroee.model.Produto"%>

<html>
<head>
    <title>Lista de Produtos</title>
</head>

<body>

<h2>Produtos Cadastrados</h2>

<a href="ServletProdutoFC?acao=formIncluir">Incluir Novo Produto</a>
<br><br>

<%
    List<Produto> lista = (List<Produto>) request.getAttribute("lista");
%>

<table border="1" width="60%">
    <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Quantidade</th>
        <th>Preço</th>
        <th>Ações</th>
    </tr>

<%
    if (lista != null) {
        for (Produto p : lista) {
%>
            <tr>
                <td><%= p.getCodigo() %></td>
                <td><%= p.getNome() %></td>
                <td><%= p.getQuantidade() %></td>
                <td><%= p.getPrecoVenda() %></td>

                <td>
                    <a href="ServletProdutoFC?acao=formAlterar&id=<%= p.getCodigo() %>">Alterar</a>
                    |
                    <a href="ServletProdutoFC?acao=excluir&id=<%= p.getCodigo() %>">Excluir</a>
                </td>
            </tr>
<%
        }
    }
%>

</table>

</body>
</html>
