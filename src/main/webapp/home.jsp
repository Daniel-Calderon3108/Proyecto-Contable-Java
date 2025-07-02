<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.UsuarioDAO"%>
<%@page import="modelVO.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Sarct Asociados - Inicio</title>
    </head>
    <body>
        <section class="container-section">
            <div class="container-welcome">
             <h1>Bienvenido ${dataUser.getNombre()}</h1>
            <p>Perfil asignado: ${dataUser.getPerfil()}</p>
            <p>Software dedicado a llevar un proceso de contabilidad, almacenando las ventas 
               e inventario con los productos de tu empresa. Ademas de tener la
               posibilidad de generar reportes de tus ventas por media de una fecha.</p>
            <% if(perfil.equals("Admin")) { %>
            <p>Con el perfil administrador podras, administrar los productos e inventario,
                podras crear y gestionar tus usuarios, generar reportes, 
                crear y gestionar ventas.</p>
            <% } else { %>
            <p>Con el perfil de vendedor podras revisar que productos se encuentran disponibles
                en el inventario, realizar ventas y sacar reportes de las ventas que hayas
                realizado.</p>
            <% } %>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
