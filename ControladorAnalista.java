/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Analista;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SENA
 */
@WebServlet(name = "ControladorAnalista", urlPatterns = {"/ControladorAnalista"})
public class ControladorAnalista extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorAnalista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAnalista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        
        String id             = request.getParameter("fIdAnalista");
        String Nombre         = request.getParameter("fNombreAnalista");
        String Cargo          = request.getParameter("fCargoAnalista");
        String Matricula      = request.getParameter("fMatriculaAnalista");
        String accion = request.getParameter("fAccion");
        
    int idAnalista = 0;
    try{
        idAnalista = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    
        Analista unAnalista = new Analista();
        unAnalista.setIdAnalista(idAnalista);
        unAnalista.setNombreAnalista(Nombre);
        unAnalista.setCargoAnalista(Cargo);
        unAnalista.setMatriculaAnalista(Matricula);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unAnalista.insertar();
            mensaje="Inserto Analista";
        break;
        case "modificar":
            unAnalista.modificar();
            mensaje="Modifico Analista";
        break;
        case "eliminar":
            unAnalista.eliminar();
            mensaje="Elimino Analista";
        break;
    }
    request.getRequestDispatcher("/WEB-INF/FormularioUsuario.jsp?msj="+mensaje).forward(request,response);
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
