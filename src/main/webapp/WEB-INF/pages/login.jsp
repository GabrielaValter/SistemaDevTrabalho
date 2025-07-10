<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

    <div class="form-container">
        <h2>Faça login para continuar</h2>

        <c:if test="${not empty msgErro}">
            <p style="color: red;">${msgErro}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" name="login" placeholder="E-mail ou CNPJ/CPF" value="${login}" required />
            <input type="password" name="senha" placeholder="Senha" required />
            <button type="submit">Login</button>
        </form>

        <div class="divider"><span>ou</span></div>
        <div class="register">
            <a href="${pageContext.request.contextPath}/usuario/cadastro">Cadastre-se</a>
        </div>
    </div>

</body>
</html>
