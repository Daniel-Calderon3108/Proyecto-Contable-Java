<%@page import="modelDAO.ProductoDAO"%>
<%@page import="modelVO.ProductoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://kit.fontawesome.com/6955cb7bc8.js" crossorigin="anonymous"></script>
        <title>Sarct Asociados - Generar Reporte</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Generar Reporte</h1>
            <form action="Reporte" class="form-container">
                <div class="form-body">
                    <label>Fecha Desde</label>
                    <input type="date" name="desde" id="desde">
                </div>
                <div class="form-body">
                    <label>Fecha Hasta</label>
                    <input type="date" name="hasta" id="hasta">
                </div>
                <%
                if (perfil.equals("Admin")) {
                %>
                <div class="form-body">
                    <label>Usuario</label>
                    <select id="idUsuario" name="idUsuario">
                        <option value="">Todos</option>
                        <%
                            UsuarioVO usuarioVO = new UsuarioVO();
                            UsuarioDAO usuarioDAO = new UsuarioDAO();

                            ArrayList<UsuarioVO> listarUsuario = usuarioDAO.listar(idEmpresa);
                            for (int i = 0; i < listarUsuario.size(); i++) {
                                usuarioVO = listarUsuario.get(i);
                        %>
                        <option value="<%= usuarioVO.getIdUsuario() %>"><%= usuarioVO.getNombre() %></option>
                        <% }%>
                    </select>
                </div>
                <% } else { %>
                <input type="hidden" name="idUsuario" value="<%= idUsuario %>">
                <% } %>
                <div class="form-body">
                    <label>Producto</label>
                    <select id="idProducto" name="idProducto">
                        <option value="">Todos</option>
                        <%
                            ProductoVO productoVO = new ProductoVO();
                            ProductoDAO productoDAO = new ProductoDAO();

                            ArrayList<ProductoVO> listarProducto = productoDAO.listar();
                            for (int i = 0; i < listarProducto.size(); i++) {
                               productoVO = listarProducto.get(i);
                        %>
                        <option value="<%= productoVO.getIdProducto() %>"><%= productoVO.getNombre() %></option>
                        <% }%>
                    </select>
                </div>
                <div class="form-body">
                    <input type="hidden" name="opcion" value="reporte">
                    <button type="submit" class="submit">Generar</button>
                </div>
            </form>
        </section>
    </body>
</html>
