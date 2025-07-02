<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.InventarioDAO"%>
<%@page import="modelVO.InventarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <title>Sarct Asociados - Inventario</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Lista de Inventario</h1>
            <%if(perfil.equals("Admin")) {%>
            <div class="btn-create">
                <a href="Inventario?opcion=crearInventario">Crear Nuevo Inventario</a>
            </div>
            <% } %>
            <form action="Inventario" method="get">
                <table>
                    <thead>
                        <tr>
                            <th># ID</th>
                            <th>NUMERO DE LOTE</th><!-- NUMERO DE LOTE -->
                            <th>FECHA DE CREACION</th>
                            <th>CANTIDAD</th>
                            <th>PRODUCTO</th>
                            <th>ESTADO</th>
                            <%if (perfil.equals("Admin")) {%>
                            <th></th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%InventarioVO inventVO = new InventarioVO();
                        InventarioDAO inventDAO = new InventarioDAO();
                        
                        ArrayList<InventarioVO> listarInventario = inventDAO.listar();
                        for (int i = 0; i <listarInventario.size(); i++){
                            inventVO = listarInventario.get(i);
                            String estado = Integer.parseInt(inventVO.getEstado()) == 1 ? "Activo" : "Inactivo";
                        %>
                        <tr>
                            <th><%= inventVO.getIdInventario()%></th>
                            <th><%= inventVO.getNumerolote() %></th>
                            <th><%= inventVO.getFechaCreacion() %></th>
                            <th><%= inventVO.getCantidad() %></th>
                            <th><%= inventVO.getProducto() %></th>
                            <th><%= estado %></th>
                            <% if(perfil.equals("Admin")) {%>
                            <td><a class="btn-edit" href="Inventario?opcion=updateInventario&idInventario=<%= inventVO.getIdInventario()%>">Editar</a></td>
                            <% } %>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </form>
        </section>
        <% if (request.getParameter("exito") != null) { %>
        <script>message("success", '<%= request.getParameter("exito") %>');</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>
