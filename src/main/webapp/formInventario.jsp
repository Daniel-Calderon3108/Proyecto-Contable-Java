<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.ProductoDAO"%>
<%@page import="modelVO.ProductoVO"%>
<%@page import="modelVO.InventarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    if (!perfil.equals("Admin")){
    request.getRequestDispatcher("home.jsp").forward(request, response);  
    }
    InventarioVO inventVO = null;
    String estado = "Activo";
    String mandarPerfil = "";
    
    if (request.getAttribute("inventario") != null){
    inventVO = (InventarioVO) request.getAttribute("inventario");
    estado = Integer.parseInt(inventVO.getEstado()) == 1 ? "Activo" : "Inactivo";
    
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
        <title>Sarct Asociados - <%= inventVO != null ? "Editar" : "Agregar" %> Inventario</title>  
    </head>
    <body>
        <section class="container-section">
            <h1 class="title"><%= inventVO != null ? "Editar" : "Agregar Nuevo"%> Inventario</h1>
            <form method="POST" action="Inventario" class="form-container" id="formInventario">
                
                <div class="form-body">
                    <label>NUMERO DE LOTE</label>
                    <input type="text" id="numerolote" name="numerolote" value="<%= inventVO != null ? inventVO.getNumerolote() : ""%>">
                    <input type="hidden" name="idInventario" value="<%= inventVO != null ? inventVO.getIdInventario() : ""%>">
                </div>
                <div class="form-body">
                    <label>CANTIDAD</label>
                    <input type="text" id="cantidad" name="cantidad" value="<%= inventVO != null ? inventVO.getCantidad() : ""%>" onkeyup="numeros(this.value,'cantidad')" onkeypress="numeros(this.value,'cantidad')">
                </div>
                
                <div class="form-body">
                    <label style="text-align: justify;">PRODUCTO</label>
                    <select name="idProducto" id="idProducto">
                        <option value="<%= inventVO != null ? inventVO.getIdProducto() : 0 %>"><%= inventVO != null ? inventVO.getProducto() :  "Selecciona un producto" %></option>
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
                
                <div class="form-body">
                    <label>Estado</label>
                    <select name="Estado">
                        <% if (estado == "Activo"){ 
                        %>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                        <% } else { %>
                        <option value="0">Inactivo</option>
                        <option value="1">activo</option>
                        <%  } %>
                    </select>
                </div>
                <div class="form-body">
                    <input type="hidden" name="opcion" value="<%= inventVO != null ? "update" : "create"%>">
                    <button type="button" class="submit" onclick="validarInventario()"><%= inventVO != null ? "Editar" : "Agregar"%></button>
                </div>
            </form>
        </section>
        <% if (request.getAttribute("error") != null) { %>
        <script>message("error", "${error}");</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>
