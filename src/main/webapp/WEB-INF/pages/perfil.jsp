<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="header">
    <div class="logo" onclick="window.location.href='${pageContext.request.contextPath}/dashboard'">
        <i class="fa-solid fa-link"></i> CodeLink
    </div>
    <div class="icons">
        <i class="fa-solid fa-bell"></i>
        <i class="fa-solid fa-comment-dots"></i>
    </div>
</div>

<div class="container">

    <div class="profile-card">
        <div class="avatar"></div>
        <div class="info">
            <strong>${usuario.nome}</strong><br>
            ${usuario.email}<br>
            ${usuario.telefone}<br>
            ${usuario.tipo == 'empresa' ? 'Empresa' : 'Programador'}<br>
        </div>
    </div>

    <div class="feed">
        <h3>Meus Problemas</h3>

        <c:if test="${empty problemas}">
            <p>Você ainda não cadastrou nenhum problema.</p>
        </c:if>

        <c:forEach var="problema" items="${problemas}">
            <div class="problem-card">
                <h4>${problema.titulo}</h4>
                <p>${problema.descricao}</p>
                <div class="meta">
                    <span>Valor: R$ ${problema.valor}</span>
                    <span>Status: ${problema.status ? 'Ativo' : 'Inativo'}</span>
                </div>
                <div class="actions">
                    <a href="${pageContext.request.contextPath}/problema/editar/${problema.id}" class="edit-link">
                        <i class="fa-solid fa-pen-to-square"></i> Editar
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
