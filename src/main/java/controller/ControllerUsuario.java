package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelDAO.UsuarioDAO;
import modelVO.UsuarioVO;


@WebServlet(name = "ControllerUsuario", urlPatterns = {"/Usuario"})
public class ControllerUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession Sesion = request.getSession();
        
        String opcion = request.getParameter("opcion") == null ? "index" : request.getParameter("opcion");
        String idEmpresa = request.getParameter("idEmpresa");
        String Nombre = request.getParameter("Nombre");
        String Correo = request.getParameter("Correo");
        String Clave = request.getParameter("Clave");
        String Telefono = request.getParameter("Telefono");
        String Direccion = request.getParameter("Direccion");
        String FechaCreacion = request.getParameter("FechaCreacion");
        String Estado = request.getParameter("Estado");
        String idPerfil = request.getParameter("idPerfil");
        String Empresa = request.getParameter("Empresa");
        String Perfil = request.getParameter("Perfil");
        String IdUsuario = request.getParameter("IdUsuario");

        UsuarioVO usuVO = new UsuarioVO(idEmpresa, IdUsuario, Nombre,
                Correo, Clave, Telefono, Direccion, FechaCreacion, Estado,
                idPerfil, Empresa, Perfil);
        UsuarioDAO usuDAO = new UsuarioDAO(usuVO);

        switch (opcion) {
            case "login":
                if (!usuDAO.IniciarSesion(Nombre, Clave)) {
                    request.setAttribute("Error", "El usuario y/o la contraseña son"
                            + " incorrectos");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    UsuarioVO usuaVO;
                    usuaVO = UsuarioDAO.consultarUsuario(Nombre);
                    if (usuaVO != null) {
                        Sesion = request.getSession(true);
                        Sesion.setAttribute("dataUser", usuaVO);
                        request.setAttribute("perfil", usuaVO.getPerfil());
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }
                }
                break;
            case "createUser":
                request.getRequestDispatcher("formUsuario.jsp").forward(request, response);
                break;
            case "create":
                if (usuDAO.agregarRegistro()) {
                    String mensaje = "El Usuario se creó con exito";
                    response.sendRedirect("Usuario?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "ups, fallo algo al crear el usuario");
                    request.getRequestDispatcher("formUsuario.jsp").forward(request, response);
                }
                break;
            case "update":
                if (usuDAO.actualizarRegistro()) {
                    String mensaje = "El Usuario se editó con exito";
                    response.sendRedirect("Usuario?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "Algo salio mal con la Actualizacion");
                    request.getRequestDispatcher("actualizarUsuario.jsp").forward(request, response);
                }

                break;
            case "updateUser":
                UsuarioVO userVO;
                userVO = UsuarioDAO.consultarId(IdUsuario);
                if (userVO != null) {
                    request.setAttribute("user", userVO);
                    request.getRequestDispatcher("formUsuario.jsp").forward(request, response);
                } else {
                    request.setAttribute("error ", "No se encuentra el usuario");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
                break;
            case "index":
                if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
                break;
            default:
                if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
