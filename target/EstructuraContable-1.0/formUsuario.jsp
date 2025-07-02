<%@page import="modelDAO.PerfilDAO"%>
<%@page import="modelVO.PerfilVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.EmpresaDAO"%>
<%@page import="modelVO.EmpresaVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    if (!perfil.equals("Admin")) {
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    UsuarioVO userVO = null;
    String estado = "Activo";
    String mandarPerfil = "";

    
    if (request.getAttribute("user") != null) {
        userVO = (UsuarioVO) request.getAttribute("user");
        estado = Integer.parseInt(userVO.getEstado()) == 1 ? "Activo" : "Inactivo";
        mandarPerfil = userVO.getIdPerfil();
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
        <title> Sarct Asociados - <%= userVO != null ? "Editar" : "Agregar"%> Usuario</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title"><%= userVO != null ? "Editar" : "Agregar Nuevo"%> Usuario</h1>
            <form method="POST" id="formUsuario" action="Usuario" class="form-container"> 
                <div class="form-body">
                    <label>Nombre</label>
                    <input type="text" id="nombre" name="Nombre" value="<%= userVO != null ? userVO.getNombre() : ""%>" onkeyup="alfanumerico(this.value,'nombre')" onkeypress="alfanumerico(this.value,'nombre')">
                    <input type="hidden" name="IdUsuario" value="<%= userVO != null ? userVO.getIdUsuario() : ""%>">
                    <input type="hidden" name="idEmpresa" value="<%= idEmpresa %>">
                </div>
                <div class="form-body">
                    <label>Correo</label>
                    <input type="text" id="correo" name="Correo" value="<%= userVO != null ? userVO.getCorreo() : ""%>">
                </div>
                <div class="form-body">
                    <label>Clave</label>
                    <input type="password" id="clave" name="Clave" value="<%= userVO != null ? userVO.getClave() : ""%>">
                </div>
                <div class="form-body">
                    <label>Telefono</label>
                    <input type="text" id="tel" name="Telefono" value="<%= userVO != null ? userVO.getTelefono() : ""%>" onkeyup="numeros(this.value,'tel')" onkeypress="numeros(this.value,'tel')">
                </div>
                <div class="form-body">
                    <label>Direccion</label>
                    <input type="text" id="direccion" name="Direccion" value="<%= userVO != null ? userVO.getDireccion() : ""%>" onkeyup="describe(this.value,'direccion')" onkeypress="describe(this.value,'direccion')">
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
                        
                        <% } %>
                    </select>
                </div>
                <div class="form-body">
                    <select name="idPerfil" id="idPerfil">
                        <option value ="<%= userVO != null ? userVO.getIdPerfil() : 0 %>"><%= userVO != null ? userVO.getPerfil() : "Seleccionar Perfil"%></option>
                        <%                        
                            PerfilVO perfilVO = new PerfilVO();
                            PerfilDAO perfilDAO = new PerfilDAO();

                            ArrayList<PerfilVO> listarperfil = perfilDAO.listar(mandarPerfil);
                            for (int i = 0; i < listarperfil.size(); i++) {
                                perfilVO = listarperfil.get(i);
                        %>
                        <option value="<%= perfilVO.getIdPerfil()%>"><%= perfilVO.getNombre()%>
                            <% } %>
                    </select>
                    <input type="hidden" name="opcion" value="<%= userVO != null ? "update" : "create" %>">
                </div>
                <div class="form-body">
                    <button type="button" class="submit" onclick="validarUsuario()"><%= userVO != null ? "Editar" : "Agregar"%></button>
                </div>
            </form>
        </section>
        <% if (request.getAttribute("error") != null) { %>
        <script>message("error", "${error}");</script>
        <% }%>
        <%@include file="footer.jsp" %>
    </body>
</html>
