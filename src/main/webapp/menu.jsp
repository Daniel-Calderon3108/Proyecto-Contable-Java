<%@page import="modelVO.UsuarioVO"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
    response.setDateHeader("Expires", 0);

    HttpSession Sesion = (HttpSession) request.getSession();
    String perfil = "";
    String idEmpresa = "";
    String idUsuario = "";
    if (Sesion.getAttribute("dataUser") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        UsuarioVO usuarioVO = (UsuarioVO) Sesion.getAttribute("dataUser");
        perfil = usuarioVO.getPerfil();
        idEmpresa = usuarioVO.getIdEmpresa();
        idUsuario = usuarioVO.getIdUsuario();
    }
%>
<form action="Sesion" method="POST">
    <header>

        <h1>Sarct Asociados</h1>
        <nav>
            <ul>
                <li><a href="home.jsp">Inicio</a></li>
                <% if(perfil.equals("Admin")) { %>
                <li><a href="Usuario">Usuarios</a></li>
                <% } %>
                <li><a href="Producto">Productos</a></li>
                <li><a href="Venta">Ventas</a></li>
                <li><a href="Reporte">Reportes</a></li>
                <li><a href="Arboles">Árbol</a></li>
                <li><button type="submit">Cerrar Sesión</button></li>
            </ul>
        </nav>   
    </header>
</form>