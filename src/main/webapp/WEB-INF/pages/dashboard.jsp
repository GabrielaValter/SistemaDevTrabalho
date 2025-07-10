<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <div class="header">
        <div class="logo">
            <i class="fa-solid fa-link"></i> CodeLink
        </div>
        <div class="icons">
            <i class="fa-solid fa-bell"></i>
            <i class="fa-solid fa-comment-dots"></i>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link" title="Sair">
                <i class="fa-solid fa-right-from-bracket"></i>
            </a>

        </div>
    </div>


    <div class="container">

<%--        Dados pessoais--%>
    <div class="profile-card">
        <div class="avatar" onclick="window.location.href='${pageContext.request.contextPath}/perfil.jsp'" style="cursor: pointer;"></div>
        <div class="info">
            <strong onclick="window.location.href='${pageContext.request.contextPath}/perfil.jsp'" style="cursor: pointer;">
                ${usuarioLogado.nome}
            </strong><br>
            ${usuarioLogado.email}<br>
            ${usuarioLogado.telefone}<br>
            <br>
            <span>${usuarioLogado.tipo == 'empresa' ? 'Empresa' : 'Programador'}</span>
        </div>
    </div>

<%--        Problemas--%>
        <div class="feed">
            <div class="search-bar">
                <form method="get" action="${pageContext.request.contextPath}/dashboard">
                    <input type="text" name="busca" placeholder="Pesquise por nome ou empresa">
                </form>
            </div>

            <h3>Problemas Esperando soluções</h3>

            <c:forEach var="problema" items="${problemas}">
                <c:if test="${problema.status}">
                    <div class="problem-card">
                        <h4>${problema.titulo}</h4>
                        <p>${problema.descricao}</p>
                        <div class="meta">
                            <span>Valor oferecido: R$ ${problema.valor}</span>
<%--                            <span>${problema.nome}</span>--%>
                        </div>

                        <c:if test="${usuario.tipo == 'programador'}">
                            <form action="${pageContext.request.contextPath}/candidatar/${problema.id}" method="post">
                                <button type="submit" class="candidatar-btn">Quero me candidatar</button>
                            </form>
                        </c:if>
                    </div>
                </c:if>
            </c:forEach>

            <c:if test="${usuarioLogado.tipo == 'empresa'}">
                <a href="${pageContext.request.contextPath}/problema/cadastrar" class="floating-add-bar">
                    <i class="fa-solid fa-plus"></i> Cadastrar novo problema
                </a>
            </c:if>

        </div>
    </div>

</body>
</html>
