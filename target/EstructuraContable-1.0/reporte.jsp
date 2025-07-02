<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.ReporteDAO"%>
<%@page import="modelVO.VentaVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    if (request.getParameter("desde") == null) {
        request.getRequestDispatcher("Reporte");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Sarct Asociados - Reporte</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Reporte del <%= request.getParameter("desde")%> al <%= request.getParameter("hasta")%></h1>
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
                            VentaVO reporte = new VentaVO();
                            ReporteDAO reporteDAO = new ReporteDAO();

                            String idusuario = request.getParameter("idUsuario");
                            String desde = request.getParameter("desde");
                            String hasta = request.getParameter("hasta");
                            String idProducto = request.getParameter("idProducto");

                            ArrayList<VentaVO> listarReporte = reporteDAO.listar(idusuario, idEmpresa, desde, hasta, idProducto);
                            
                            if (listarReporte.size() == 0) {
                        %>
                        <tr>
                            <td colspan="8">No se encontro ningún resultado con los parámetros enviados</td>
                        </tr>
                        <%
                            }
                            
                            for (int i = 0; i < listarReporte.size(); i++) {
                                reporte = listarReporte.get(i);

                                String estado = "";

                                if (Integer.parseInt(reporte.getEstado()) == 1) {
                                    estado = "OK";
                                } else if (Integer.parseInt(reporte.getEstado()) == 2) {
                                    estado = "EN PROCESO";
                                } else {
                                    estado = "CANCELADA";
                                }

                                String observacion = reporte.getObservacion() == "" ? "Sin Observación" : reporte.getObservacion();
                        %>
                        <tr>
                            <td><%= reporte.getIdVenta()%></td>
                            <td><%= reporte.getNumeroVenta()%></td>
                            <td><%= reporte.getFechaCreacion()%></td>
                            <td><%= observacion%></td>
                            <td><%= reporte.getUsuario()%></td>
                            <td><%= estado%></td>
                            <td><%= reporte.getTotal()%></td>
                            <td><a class="btn-edit" href="Venta?opcion=detail&idVenta=<%= reporte.getIdVenta()%>">Ver Detalle</a></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </form>
        </section>
    </body>
</html>
