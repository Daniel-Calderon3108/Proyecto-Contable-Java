<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.VentaDAO"%>
<%@page import="modelVO.VentaVO"%>
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
        <title>Sarct Asociados - Venta</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Listado de Ventas</h1>
            <div class="btn-create">
                <a href="Venta?opcion=nuevaVenta&usuario=<%= idUsuario%>">Generar Nueva Venta</a>
            </div>
            <form action="Venta" method="get">
                <table>
                    <thead>
                        <tr>
                            <th># ID</th>
                            <th>NÚMERO</th>
                            <th>FECHA</th>
                            <th>OBSERVACIÓN</th>
                            <th>REALIZADA POR</th>
                            <th>ESTADO</th>
                            <th>TOTAL</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            VentaVO ventaVO = new VentaVO();
                            VentaDAO ventaDAO = new VentaDAO();

                            ArrayList<VentaVO> listarVenta = ventaDAO.listar(idEmpresa);
                            for (int i = 0; i < listarVenta.size(); i++) {

                                ventaVO = listarVenta.get(i);

                                String estado = "";

                                if (Integer.parseInt(ventaVO.getEstado()) == 1) {
                                    estado = "OK";
                                } else if (Integer.parseInt(ventaVO.getEstado()) == 2) {
                                    estado = "EN PROCESO";
                                } else {
                                    estado = "CANCELADA";
                                }

                                String observacion = ventaVO.getObservacion() == "" ? "Sin Observación" : ventaVO.getObservacion();
                        %>

                        <tr>
                            <td><%= ventaVO.getIdVenta()%></td>
                            <td><%= ventaVO.getNumeroVenta()%></td>
                            <td><%= ventaVO.getFechaCreacion()%></td>
                            <td><%= observacion%></td>
                            <td><%= ventaVO.getUsuario()%></td>
                            <td><%= estado%></td>
                            <td><%= ventaVO.getTotal()%></td>
                            <td><a class="btn-edit" href="Venta?opcion=detail&idVenta=<%= ventaVO.getIdVenta()%>">Ver Detalle</a></td>
                        </tr>
                        <% } %>
                    </tbody>    
                </table>
            </form>
        </section>
        <% if (request.getAttribute("exito") != null) { %>
        <script>message("success", "${exito}");</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>
