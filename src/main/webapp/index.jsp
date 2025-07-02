<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
         <script src="js/main.js" type="text/javascript"></script>
        <title>Sarct Asociados - Iniciar Sesión</title>
    </head>
    <body>
        <form action="Usuario" method="post">
            <h1>Sarct Asociados</h1>
            <div class="form">
                <label>Usuario</label>
                <input type="text" name="Nombre">
            </div>
            <div class="form">
                <label>Clave</label>
                <input type="password" name="Clave">
                <input type="hidden" name="opcion" value="login">
            </div>
            <div class="form">
                <button class="btn">Iniciar Sesión</button>
            </div>
        </form>
         <% if(request.getAttribute("Error") != null) { %>
         <script>message("error","${Error}");</script>
         <% } %>
    </body>
</html>
