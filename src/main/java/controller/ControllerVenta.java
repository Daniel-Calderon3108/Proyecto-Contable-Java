package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelDAO.VentaDAO;
import modelVO.VentaVO;

@WebServlet(name = "ControllerVenta", urlPatterns = {"/Venta"})
public class ControllerVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession Sesion = request.getSession();

        String opcion = request.getParameter("opcion") == null ? "index" : request.getParameter("opcion");
        String idVenta = request.getParameter("idVenta");
        String numeroVenta = request.getParameter("numeroVenta");
        String fechaCreacion = request.getParameter("fechaCreacion");
        String estado = request.getParameter("estado");
        String total = request.getParameter("total");
        String observacion = request.getParameter("observacion");
        String idUsuario = request.getParameter("idUsuario");
        String idProducto = request.getParameter("idProducto");
        String idDetalleVenta = request.getParameter("idDetalleVenta");
        int precioUnidad = request.getParameter("precioUnidad") == null ? 0 : Integer.parseInt(request.getParameter("precioUnidad"));
        int cantidad = request.getParameter("cantidad") == null ? 0 : Integer.parseInt(request.getParameter("cantidad"));
        int descuento = request.getParameter("descuento") == null ? 0 : Integer.parseInt(request.getParameter("descuento"));
        String producto = request.getParameter("producto");
        String usuario = request.getParameter("usuario");

        boolean error = true;
        String mensaje;

        VentaVO ventaVO = new VentaVO(idVenta, numeroVenta, fechaCreacion, estado,
                total, observacion, idUsuario, idProducto, idDetalleVenta,
                precioUnidad, cantidad, descuento, producto, usuario);
        VentaDAO ventaDAO = new VentaDAO(ventaVO);

        switch (opcion) {
            case "index":
                if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
                }
                break;
            case "detail":
                VentaVO venVO;
                venVO = VentaDAO.infodetalleVenta(idVenta);
                if (venVO != null) {
                    request.setAttribute("venta", venVO);
                    request.getRequestDispatcher("detailVenta.jsp").forward(request, response);
                } else {
                    request.setAttribute("error ", "No se encuentra la venta");
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
                }
                break;
            case "nuevaVenta":
                String idNuevaVenta = ventaDAO.crearVenta(usuario);
                if (!idNuevaVenta.equals("")) {
                    request.setAttribute("idVentaNueva", idNuevaVenta);
                    request.getRequestDispatcher("formVenta.jsp").forward(request, response);
                } else {
                    request.setAttribute("error ", "Ocurrio un error al intentar generar una nueva venta");
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
                }
            case "traerProducto":
                if (ventaDAO.registrarProductoVenta()) {
                    error = false;
                    mensaje = "Se agrego el producto al detalle de venta";
                } else {
                    mensaje = "Ups, algo fallo al guardar el producto en el detalle de venta";
                }
                response.setContentType("application/json");
                out.print("{\"error\": \"" + error + "\",\"mensaje\": \"" + mensaje + "\"}");
                out.flush();
                break;
            case "actualizarDetalle":
                VentaVO ventVO;
                ArrayList<VentaVO> listarDetalleVenta = ventaDAO.detalleVenta(idVenta);
                error = false;
                mensaje = "Ok";
                String[] datosDetalleVenta = new String[listarDetalleVenta.size()];
                for (int i = 0; i < listarDetalleVenta.size(); i++) {
                    ventVO = listarDetalleVenta.get(i);

                    ObjectMapper mapper = new ObjectMapper();
                    String detalleVenta = mapper.writeValueAsString(ventVO);

                    datosDetalleVenta[i] = detalleVenta;
                }
                response.setContentType("application/json");
                out.print("{\"error\": \"" + error + "\",\"mensaje\": \"" + mensaje + "\",\"data\": " + Arrays.toString(datosDetalleVenta) + "}");
                out.flush();
                break;
            case "eliminarProducto":
                if (ventaDAO.eliminarProductoVenta()) {
                    error = false;
                    mensaje = "Se elimino el producto del detalle de venta con exito";
                } else {
                    mensaje = "Ups, algo fallo al eliminar el producto del detalle de venta";
                }
                response.setContentType("application/json");
                out.print("{\"error\": \"" + error + "\",\"mensaje\": \"" + mensaje + "\"}");
                out.flush();
                break;
            case "actualizarVenta":
                if (ventaDAO.actualizarVenta()) {
                    error = false;
                    mensaje = "Se actualizo la cantidad del producto de la venta seleccionada";
                } else {
                    mensaje = "Ups algo no fallo al actualizar la venta";
                }
                response.setContentType("application/json");
                out.print("{\"error\": \"" + error + "\",\"mensaje\": \"" + mensaje + "\"}");
                out.flush();
                break;
            case "guardarVenta":
                String url = "";
                if (ventaDAO.guardarVenta()) {
                    error = false;
                    mensaje = "OK";
                    url = "Venta?opcion=exito";
                } else {
                    mensaje = "Ups algo fallo al guardar los datos de la venta";
                }
                response.setContentType("application/json");
                out.print("{\"error\": \"" + error + "\",\"mensaje\": \"" + mensaje + "\",\"url\":\"" + url +"\"}");
                out.flush();
                break;
            case "exito":
                request.setAttribute("exito", "La venta se creo con exito");
                request.getRequestDispatcher("venta.jsp").forward(request, response);
                break;
            default:
                if (Sesion.getAttribute("dataUser") == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
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
