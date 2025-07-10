<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <div class="left">
        <div class="left-container">
            <h2>Bem-vindo!</h2>
            <p>Conecte-se com empresas e projetos reais.</p>
            <p>Já tem uma conta?</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Fazer login</button>
        </div>
    </div>

    <div class="right">
        <div class="form-container">
            <h2>Criar conta</h2>
            <p>Para prosseguir, primeiro selecione a modalidade do cadastro.</p>
            <form action="${pageContext.request.contextPath}/usuario/cadastroForm" method="get">
                <select name="tipo" required>
                    <option value="" disabled selected>Selecione</option>
                    <option value="programador">Programador</option>
                    <option value="empresa">Empresa</option>
                </select>
                <button type="submit">
                    Próximo <i class="fa-solid fa-arrow-right"></i>
                </button>
            </form>
        </div>
    </div>

</body>
</html>
