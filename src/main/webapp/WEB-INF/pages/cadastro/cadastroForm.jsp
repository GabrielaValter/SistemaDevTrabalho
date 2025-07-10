<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro - Etapa 2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastroForm.css">
</head>
<body>

    <div class="form-container">
        <h2>Preencha seus dados</h2>

        <c:if test="${not empty msg}">
            <p style="color:red">${msg}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/usuario/cadastro" method="post">
            <input type="hidden" name="tipo" value="${tipoSelecionado}" />

            <input type="text" name="nome" placeholder="Nome completo" required>
            <input type="email" name="email" placeholder="E-mail" required>
            <input type="password" name="senha" placeholder="Senha" required>

            <c:choose>
                <c:when test="${tipoSelecionado == 'programador'}">
                    <input type="text" name="cpfCnpj" placeholder="CPF" required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="cpfCnpj" placeholder="CNPJ" required>
                </c:otherwise>
            </c:choose>

            <input type="text" name="telefone" placeholder="Telefone">
            <input type="text" name="endereco" placeholder="Endereço">

            <button type="submit">Cadastrar</button>
        </form>
    </div>

</body>
</html>
