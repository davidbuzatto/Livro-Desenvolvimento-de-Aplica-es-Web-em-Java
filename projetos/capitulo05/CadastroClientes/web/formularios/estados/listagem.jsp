<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Estados Cadastrados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css"/>
    </head>

    <body>
        
        <h1>Estados Cadastrados</h1>

        <p><a href="${pageContext.request.contextPath}/formularios/estados/novo.jsp">Novo Estado</a></p>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Sigla</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>

                <jsp:useBean id="servicos" scope="page" class="cadastroclientes.servicos.EstadoServices"/>

                <c:forEach items="${servicos.todos}" var="estado">
                    <tr>
                        <td>${estado.id}</td>
                        <td>${estado.nome}</td>
                        <td>${estado.sigla}</td>
                        <td><a href="${pageContext.request.contextPath}/processaEstados?acao=prepararAlteracao&id=${estado.id}">Alterar</a></td>
                        <td><a href="${pageContext.request.contextPath}/processaEstados?acao=prepararExclusao&id=${estado.id}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="${pageContext.request.contextPath}/formularios/estados/novo.jsp">Novo Estado</a></p>
        
        <p><a href="${pageContext.request.contextPath}/index.jsp">Tela Principal</a></p>

    </body>

</html>
