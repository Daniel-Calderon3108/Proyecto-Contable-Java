package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelDAO.ArbolBinario;

@WebServlet(name = "Arboles", urlPatterns = {"/Arboles"})
public class Arboles extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String opcion = request.getParameter("opcion") == null ? "" : request.getParameter("opcion");
        
        if (opcion.equals("")) {
            request.getRequestDispatcher("Arboles.jsp").forward(request, response);
        }
        
        int tamano = Integer.parseInt(request.getParameter("tamano"));
        String recorrer = request.getParameter("recorrer");
        String[] numeros = request.getParameterValues("numeros[]");
        int[] arrayNumero = new int[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            arrayNumero[i] = Integer.parseInt(numeros[i]);
        }

        ArbolBinario arbol = new ArbolBinario();

        List<Integer> resultado;

        for (int i = 0; i < numeros.length; i++) {
            arbol.insertar(arrayNumero[i]);
        }
        
        int nivel = arbol.nivelArbol() + 1;

        switch (recorrer) {
            case "In":  
                resultado = arbol.imprimirIn();
                request.setAttribute("InOrden", resultado);
                request.setAttribute("nivel", nivel);
                request.setAttribute("tamano", tamano);
                request.getRequestDispatcher("Arboles.jsp").forward(request, response);
                break;
            case "Pre":  
                resultado = arbol.imprimirPre();
                request.setAttribute("PreOrden", resultado);
                request.setAttribute("nivel", nivel);
                request.setAttribute("tamano", tamano);
                request.getRequestDispatcher("Arboles.jsp").forward(request, response);
                break;
            case "Post":  
                resultado = arbol.imprimirPos();
                request.setAttribute("PostOrden", resultado);
                request.setAttribute("nivel", nivel);
                request.setAttribute("tamano", tamano);
                request.getRequestDispatcher("Arboles.jsp").forward(request, response);
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