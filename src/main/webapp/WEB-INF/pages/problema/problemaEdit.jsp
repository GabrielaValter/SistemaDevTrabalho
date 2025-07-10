<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Problema</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/problemaForm.css">
</head>
<body>
<div class="form-container">
    <h2>Editar Problema</h2>

    <c:if test="${not empty msg}">
        <p class="msg">${msg}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/problema/editar">
        <input type="hidden" name="id" value="${problema.id}" />

        <label for="titulo">Título</label>
        <input type="text" name="titulo" id="titulo" value="${problema.titulo}" required />

        <label for="descricao">Descrição</label>
        <textarea name="descricao" id="descricao" required>${problema.descricao}</textarea>

        <label for="valor">Valor Proposto</label>
        <input type="number" step="0.01" name="valor" id="valor" value="${problema.valor}" required />

        <label for="status">Status</label>
        <select name="status" id="status" required>
            <option value="true" selected>Ativo</option>
            <option value="false">Inativo</option>
        </select>

        <button type="submit">Salvar Alterações</button>
    </form>
</div>
</body>
</html>