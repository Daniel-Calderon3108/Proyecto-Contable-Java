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
        <title>Sarct Asociados Arboles</title>
    </head>
    <body>
        <section class="container-section">
            <h1 class="title">Arboles </h1>
            <form action="Arboles" class="form-container" method="post" id="formArbol">
                <div class="form-body"> 
                    <label>Tamaño del Arbol</label>
                    <input type="text" name="tamano" id="tamano" onkeyup="cargarInputs()">
                </div>            
                <div id="numeros"></div>
                <div class="form-body">
                    <label>Recorrido</label>
                    <select name="recorrer" id="recorrer">
                        <option value="">Seleccione la forma en la que quiere recorrer el árbol</option>
                        <option value="In">InOrden</option>
                        <option value="Pre">PreOrden</option>
                        <option value="Post">PostOrden</option>
                    </select>

                </div>
                <div class="form-body">
                    <input type="hidden" name="opcion" value="arbol">
                    <button type="button" class="submit" onclick="validarArbol()">Enviar</button>
                </div>
            </form>
            <% if (request.getAttribute("tamano") != null) {
            %>
            <table>
                <thead>
                    <tr>
                        <% if (request.getAttribute("PostOrden") != null) { %>
                        <th>Recorrido en PostOrden </th>
                            <% } else if (request.getAttribute("InOrden") != null) { %>
                        <th>Recorrido en InOrden</th>
                            <% } else { %>
                        <th>Recorrido en PreOrden</th>
                            <%}%>
                        <th>El peso del arbol es</th>
                        <th>El nivel del arbol es </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <% if (request.getAttribute("PostOrden") != null) {%>
                        <th><%= request.getAttribute("PostOrden")%></th>
                            <% } else if (request.getAttribute("InOrden") != null) {%>
                        <th><%= request.getAttribute("InOrden")%></th>
                            <% } else {%>
                        <th><%= request.getAttribute("PreOrden")%></th>
                            <%}%>
                        <th><%= request.getAttribute("tamano")%></th>
                        <th><%= request.getAttribute("nivel")%></th>
                    </tr>
                </tbody>
            </table>
            <% }%>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>