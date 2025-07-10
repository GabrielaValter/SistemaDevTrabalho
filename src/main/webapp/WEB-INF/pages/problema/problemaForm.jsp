<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Problema</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/problemaForm.css">
</head>
<body>
<div class="form-container">
    <h2>Cadastrar Novo Problema</h2>

    <c:if test="${not empty msg}">
        <p class="msg">${msg}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/problema/cadastrar">
        <label for="titulo">Título</label>
        <input type="text" name="titulo" id="titulo" required />

        <label for="descricao">Descrição</label>
        <textarea name="descricao" id="descricao" required></textarea>

        <label for="valor">Valor Proposto</label>
        <input type="number" step="0.01" name="valor" id="valor" required />

        <label for="status">Status</label>
        <select name="status" id="status" required>
            <option value="true" selected>Ativo</option>
            <option value="false">Inativo</option>
        </select>

        <button type="submit">Cadastrar Problema</button>
    </form>
</div>
</body>
</html>