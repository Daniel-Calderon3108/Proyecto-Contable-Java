package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelDAO.ProductoDAO;
import modelVO.ProductoVO;

@MultipartConfig
@WebServlet(name = "ControllerProducto", urlPatterns = {"/Producto"})
public class ControllerProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession Sesion = request.getSession();
        
        String opcion = request.getParameter("opcion") == null ? "index" : request.getParameter("opcion");
        String idProducto = request.getParameter("IdProducto");
        String nombre = request.getParameter("Nombre");
        String precio = request.getParameter("precio");
        String descripcion = request.getParameter("descripcion");
        String FechaCreacion = request.getParameter("FechaCreacion");
        String Estado = request.getParameter("Estado");
        String fileName = "";

        if (opcion.equals("create") || opcion.equals("update")) {

            Part filePart = request.getPart("imagen");
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (!fileName.equals("")) {
                InputStream fileContent = filePart.getInputStream();
                File file = new File("C:\\Users\\danie\\Downloads\\EstructuraContable\\src\\main\\webapp\\img\\" + fileName);
                //File file = new File("C:\\Users\\flori\\Downloads\\EstructuraContable\\EstructuraContable\\src\\main\\webapp\\img\\"+fileName);
                if (!file.exists()) {
                Files.copy(fileContent, file.toPath());   
                }
            }
        }

        ProductoVO producVO = new ProductoVO(idProducto, nombre, precio, fileName,
                descripcion, FechaCreacion, Estado);
        ProductoDAO productoDAO = new ProductoDAO(producVO);

        switch (opcion) {
            case "index":
                 if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("producto.jsp").forward(request, response);
                }
                 break;
            case "crearProducto":
                request.getRequestDispatcher("formProducto.jsp").forward(request, response);
                break;
            case "create":
                if (productoDAO.agregarRegistro()) {
                      String mensaje = "El producto se creo con exito";
                      response.sendRedirect("Producto?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "ups, fallo algo al crear el usuario");
                    request.getRequestDispatcher("formUsuario.jsp").forward(request, response);
                }
                break;
            case "update":
                if (productoDAO.actualizarRegistro()) {
                    String mensaje = "El Producto se editó con exito";
                    response.sendRedirect("Producto?exito="+ URLEncoder.encode(mensaje, "UTF-8"));
                } else {
                    request.setAttribute("error", "Algo salio mal con la Actualizacion");
                    request.getRequestDispatcher("formProducto.jsp").forward(request, response);
                }
                break;
            case "updateProducto":
                ProductoVO produVO;
                produVO = ProductoDAO.consultarId(idProducto);
                if (produVO != null) {
                    request.setAttribute("producto", produVO);
                    request.getRequestDispatcher("formProducto.jsp").forward(request, response);
                } else {
                    request.setAttribute("error ", "No se encuentra el producto");
                    request.getRequestDispatcher("producto.jsp").forward(request, response);
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
