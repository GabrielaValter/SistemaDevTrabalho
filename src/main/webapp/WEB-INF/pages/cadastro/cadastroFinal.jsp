<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro Concluído</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastroFinal.css">
</head>
<body>

    <div class="container">
        <h2>Cadastro realizado com sucesso!</h2>
        <p>${msg}</p>
        <a href="${pageContext.request.contextPath}/login" class="button">Fazer o login</a>
    </div>

</body>
</html>
