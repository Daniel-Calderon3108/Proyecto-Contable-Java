<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.ProductoDAO"%>
<%@page import="modelVO.ProductoVO"%>
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
        <title>Sarct Asociados - Producto</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Lista de Producto</h1>
            <%if(perfil.equals("Admin"))  {%>
            <div class="btn-create">
                <a href="Producto?opcion=crearProducto">Crear Nuevo Producto</a>
                <a style="margin-left: 15px;" href="Inventario">Ver Inventario</a>
            </div>
            <% } %>
                <form action="Producto" method="get">
                    <table>
                        <thead>
                            <tr>
                                <th># ID</th>
                                <th>NOMBRE</th>
                                <th>PRECIO</th>
                                <th>IMAGEN</th>
                                <th>DESCRIPCION</th>
                                <th>FECHA DE CREACIÓN</th>
                                <th>ESTADO</th>
                                <%if(perfil.equals("Admin"))  {%>
                                <th></th>
                                <% } %>
                            </tr>
                        </thead>
                        <tbody>
                            <% ProductoVO productoVO = new ProductoVO();
                                ProductoDAO productoDAO = new ProductoDAO();

                                ArrayList<ProductoVO> listarProducto = productoDAO.listar();
                                for (int i = 0; i < listarProducto.size(); i++) {

                                    productoVO = listarProducto.get(i);
                                    String estado = Integer.parseInt(productoVO.getEstado()) == 1 ? "Activo" : "Inactivo";
                            %>
                            <tr>
                                <th><%= productoVO.getIdProducto()%></th>
                                <th><%= productoVO.getNombre()%></th>
                                <th><%= productoVO.getPrecio()%></th>
                                <th class="producto-imgs"><img src="img/<%= productoVO.getImagen()%>" alt="alt"/></th>
                                <th><%= productoVO.getDescripcion()%></th>
                                <th><%= productoVO.getFechaCreacion()%></th>
                                <th><%= estado%></th>
                                <%if(perfil.equals("Admin"))  {%>
                                <td><a class="btn-edit" href="Producto?opcion=updateProducto&IdProducto=<%= productoVO.getIdProducto()%>">Editar</a></td>
                                <% } %>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </form>
            </div>
        </section>
        <% if (request.getParameter("exito") != null) { %>
        <script>message("success", '<%= request.getParameter("exito") %>');</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>