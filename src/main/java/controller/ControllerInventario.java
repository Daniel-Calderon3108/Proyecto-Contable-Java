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
import modelDAO.InventarioDAO;
import modelVO.InventarioVO;

@WebServlet(name = "ControllerInventario", urlPatterns = {"/Inventario"})
public class ControllerInventario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession Sesion = request.getSession();

        String opcion = request.getParameter("opcion") == null ? "index" : request.getParameter("opcion");
        String idInventario = request.getParameter("idInventario");
        String Numerolote = request.getParameter("numerolote");
        String fechaCreacion = request.getParameter("FechaCreacion");
        String Estado = request.getParameter("Estado");
        String cantidad = request.getParameter("cantidad");
        String idProducto = request.getParameter("idProducto");
        String producto = request.getParameter("Nombre");

        InventarioVO InventVO = new InventarioVO(idInventario, Numerolote, fechaCreacion, Estado, cantidad, idProducto, producto);
        InventarioDAO InventDAO = new InventarioDAO(InventVO);

        switch (opcion) {
            case "index":
                if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("inventario.jsp").forward(request, response);
                }
                break;
            case "crearInventario":
                request.getRequestDispatcher("formInventario.jsp").forward(request, response);
                break;
            case "create":
                if (InventDAO.agregarRegistro()) {
                    String mensaje = "El inventario se creo con exito";
                    response.sendRedirect("Inventario?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "ups, fallo algo al crear el inventario");
                    request.getRequestDispatcher("formInventario.jsp").forward(request, response);
                }
                break;
            case "update":
                if (InventDAO.actualizarRegistro()) {
                    String mensaje = "El inventario se editó con exito";
                    response.sendRedirect("Inventario?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "Algo salio mal con la Actualizacion");
                    request.getRequestDispatcher("formInventario.jsp").forward(request, response);
                }
                break;
            case "updateInventario":
                InventarioVO InvenVO;
                InvenVO = InventarioDAO.consultarId(idInventario);
                if (InvenVO != null) {
                    request.setAttribute("inventario", InvenVO);
                    request.getRequestDispatcher("formInventario.jsp").forward(request, response);
                } else {
                    request.setAttribute("error ", "No se encuentra el inventario");
                    request.getRequestDispatcher("inventario.jsp").forward(request, response);
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
