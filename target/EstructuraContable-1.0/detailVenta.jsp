<%@page import="modelDAO.VentaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelVO.VentaVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    VentaVO ventaVO = null;
    String observacion = "", estado = "";

    if (request.getAttribute("venta") != null) {
        ventaVO = (VentaVO) request.getAttribute("venta");
        observacion = ventaVO.getObservacion().equals("") ? "Sin observación" : ventaVO.getObservacion();
        estado = Integer.parseInt(ventaVO.getEstado()) == 1 ? "Ok" : "Cancelada";
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
        <title>Sarct Asociados - Detalle Venta</title>
    </head>
    <body>
        <section class="container-section">
            <div class="info-venta">
                <div class="info-ventas">
                    <h1 class="title">Detalle de Venta Número: <%= ventaVO.getNumeroVenta()%></h1>
                </div>
                <div class="info-ventas">
                    <p class="title-venta">Fecha:</p>
                    <p><%= ventaVO.getFechaCreacion()%></p>
                </div>
                <div class="info-ventas">
                    <p class="title-venta">Observación:</p>
                    <p><%= observacion%></p>
                </div>
                <div class="info-ventas">
                    <p class="title-venta">Realizada por:</p>
                    <p><%= ventaVO.getUsuario()%></p>
                </div>
                <div class="info-ventas">
                    <p class="title-venta">Estado:</p>
                    <p><%= estado%></p>
                </div>
                <div class="info-ventas">
                    <p class="title-venta">Total:</p>
                    <p><%= ventaVO.getTotal()%></p>
                </div>
                <div class="info-ventas">
                    <h1 class="title">Productos Vendidos</h1>
                </div>
                <table>
                    <tr>
                        <th>Producto</th>
                        <th>Precio Unidad</th>
                        <th>Cantidad</th>
                        <th>Descuento</th>
                        <th>Total</th>
                    </tr>
                    <%
                        VentaVO venVO = new VentaVO();
                        VentaDAO ventaDAO = new VentaDAO();

                        ArrayList<VentaVO> listarDetalleVenta = ventaDAO.detalleVenta(ventaVO.getIdVenta());

                        for (int i = 0; i < listarDetalleVenta.size(); i++) {
                            venVO = listarDetalleVenta.get(i);
                            int total = venVO.getPrecioUnidad() * venVO.getCantidad();
                    %>
                    <tr>
                        <td><%= venVO.getProducto() %></td>
                        <td><%= venVO.getPrecioUnidad() %></td>
                        <td><%= venVO.getCantidad() %></td>
                        <td><%= venVO.getDescuento() %></td>
                        <td><%= total %></td>
                    </tr>
                    <% }%>
                </table>
            </div>            
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
