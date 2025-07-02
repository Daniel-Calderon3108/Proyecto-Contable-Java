<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.ProductoDAO"%>
<%@page import="modelVO.ProductoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    String idNuevaVenta = "";

    if (request.getAttribute("idVentaNueva") != null) {
        idNuevaVenta = String.valueOf(request.getAttribute("idVentaNueva"));
    } else {
        idNuevaVenta = "8";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://kit.fontawesome.com/6955cb7bc8.js" crossorigin="anonymous"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <title>Sarct Asociados - Generar Nueva Venta</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Generar Nueva Venta</h1>
            <form method="POST" action="Venta" class="form-container">
                <div class="form-body">
                    <label style="text-align: justify;">A continuación, encontrara una lista con los productos para ir agregandolos a la venta</label>
                    <select id="idProducto" onchange="traerProducto()">
                        <option value="0">Selecciona un producto</option>
                        <%
                            ProductoVO productoVO = new ProductoVO();
                            ProductoDAO productoDAO = new ProductoDAO();

                            ArrayList<ProductoVO> listarProducto = productoDAO.listar();
                            for (int i = 0; i < listarProducto.size(); i++) {
                                productoVO = listarProducto.get(i);
                        %>
                        <option value="<%= productoVO.getIdProducto()%>"><%= productoVO.getNombre()%></option>
                        <% }%>
                    </select>
                </div>
                <div id="productosAgregados"></div>
                <div class="form-body">
                    <label>Observación (Opcional)</label>
                    <textarea name="observacion" id="observacion"></textarea>
                </div>
                <div class="form-venta">
                    <p>Valor total por producto:</p><br>
                    <div id="products-total"></div><br>
                    <p>Total Venta: $<span id="total">0</span></p>
                </div>
                <div class="form-body">
                    <input type="hidden" id="idVenta" value="<%= idNuevaVenta%>">
                    <button type="button" class="submit" onclick="realizarVenta()">Realizar venta</button>
                </div>
            </form>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
