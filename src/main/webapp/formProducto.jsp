<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.ProductoDAO"%>
<%@page import="modelVO.ProductoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    if (!perfil.equals("Admin")) {
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    ProductoVO produVO = null;
    String estado = "Activo";
    String mandarPerfil = "";

    if (request.getAttribute("producto") != null) {
        produVO = (ProductoVO) request.getAttribute("producto");
        estado = Integer.parseInt(produVO.getEstado()) == 1 ? "Activo" : "Inactivo";

    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="js/validaciones.js"></script>
        <title> Sarct Asociados - <%= produVO != null ? "Editar" : "Agregar"%> Producto</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title"><%= produVO != null ? "Editar" : "Agregar Nuevo"%> Producto</h1>
            <form method="POST" action="Producto" id="formProducto" class="form-container" enctype="multipart/form-data">

                <div class="form-body">
                    <label>Nombre</label>
                    <input type="text" id="nombre" name="Nombre" value="<%= produVO != null ? produVO.getNombre() : ""%>" onkeyup="alfanumerico(this.value,'nombre')" onkeypress="alfanumerico(this.value,'nombre')">
                    <input type="hidden" name="IdProducto" value="<%= produVO != null ? produVO.getIdProducto() : ""%>">
                </div>

                <div class="form-body">
                    <label>Precio</label>
                    <input type="text" id="precio" name="precio" value="<%= produVO != null ? produVO.getPrecio() : ""%>" onkeyup="numeros(this.value,'precio')" onkeypress="numeros(this.value,'precio')">
                </div>

                <div class="form-body">
                    <label>Imagen</label>
                    <input type="file" name="imagen" value="<%= produVO != null ? produVO.getImagen() : ""%>">
                </div>

                <div class="form-body">
                    <label>Descripcion</label>
                    <input type="text" id="descripcion" name="descripcion" value="<%= produVO != null ? produVO.getDescripcion() : ""%>" onkeyup="describe(this.value,'descripcion')">
                </div>
                <div class="form-body">
                    <label>Estado</label>
                    <select name="Estado">
                        <% if (estado == "Activo") {
                        %>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                        <% } else { %>
                        <option value="0">Inactivo</option>
                        <option value="1">Activo</option>

                        <% }%>
                    </select>
                </div>
                <div class="form-body">
                    <input type="hidden" name="opcion" value="<%= produVO != null ? "update" : "create"%>">
                    <button type="button" class="submit" onclick="validarProducto()"><%= produVO != null ? "Editar" : "Agregar"%></button>
                </div>
            </form>
        </section>
        <% if (request.getAttribute("error") != null) { %>
        <script>message("error", "${error}");</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>
