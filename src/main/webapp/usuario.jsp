<%@page import="java.util.ArrayList"%>
<%@page import="modelDAO.UsuarioDAO"%>
<%@page import="modelVO.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<%
    if (!perfil.equals("Admin")) {
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        <title>Sarct Asociados - Usuario</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Listado de Usuarios</h1>
            <div class="btn-create">
                <a href="Usuario?opcion=createUser">Crear Nuevo Usuario</a>
            </div>
            <form action="Usuario" method="get">
                <table>
                    <thead>
                        <tr>
                            <th># ID</th>
                            <th>NOMBRE</th>
                            <th>CORREO</th>
                            <th>TELÉFONO</th>
                            <th>DIRECCIÓN</th>
                            <th>FECHA DE CREACIÓN</th>
                            <th>ESTADO</th>
                            <!--<th>EMPRESA</th>-->
                            <th>PERFIL</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% UsuarioVO usuarioVO = new UsuarioVO();
                           UsuarioDAO usuarioDAO = new UsuarioDAO();
                           
                           ArrayList<UsuarioVO> listarUsuario = usuarioDAO.listar(idEmpresa);
                           for(int i = 0; i < listarUsuario.size(); i ++){
                           
                           usuarioVO = listarUsuario.get(i);
                           
                           String estado = Integer.parseInt(usuarioVO.getEstado()) == 1 ? "Activo" : "Inactivo";
                        %>
                        
                        <tr>
                            <td><%= usuarioVO.getIdUsuario() %></td>
                            <td><%= usuarioVO.getNombre() %></td>
                            <td><%= usuarioVO.getCorreo()%></td>
                            <td><%= usuarioVO.getTelefono()%></td>
                            <td><%= usuarioVO.getDireccion() %></td>
                            <td><%= usuarioVO.getFechaCreacion() %></td>
                            <td><%= estado %></td>
                            <!--<td><%= usuarioVO.getEmpresa() %></td>-->
                            <td><%= usuarioVO.getPerfil() %></td>
                            <td><a class="btn-edit" href="Usuario?opcion=updateUser&IdUsuario=<%= usuarioVO.getIdUsuario() %>">Editar</a></td>
                        </tr>
                        <% } %>
                    </tbody>    
                </table>
            </form>
        </section>
        <% if(request.getParameter("exito") != null) { %>
         <script>message("success",'<%= request.getParameter("exito") %>');</script>
         <% } %>
        <%@include file="footer.jsp" %>
    </body>
</html>
