/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Vereda;
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
@WebServlet(name = "ControladorVereda", urlPatterns = {"/ControladorVereda"})
public class ControladorVereda extends HttpServlet {

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
            out.println("<title>Servlet ControladorVereda</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorVereda at " + request.getContextPath() + "</h1>");
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
        
        String id             = request.getParameter("fIdVereda");
        String Nombre         = request.getParameter("fNombreVereda");
        String Municipio       = request.getParameter("fidMunicipio");
        String accion = request.getParameter("fAccion");
        
    int idVereda = 0;
    try{
        idVereda = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    int idMunicipio = 0;
    try{
        idMunicipio = Integer.parseInt(Municipio);
    }catch(NumberFormatException nfe){
            
    }
    
        Vereda unVereda = new Vereda();
        unVereda.setIdVereda(idVereda);
        unVereda.setNombreVereda(Nombre);
        unVereda.setIdMunicipio(idMunicipio);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unVereda.insertar();
            mensaje="Inserto Vereda";
        break;
        case "modificar":
            unVereda.modificar();
            mensaje="Modifico Vereda";
        break;
        case "eliminar":
            unVereda.eliminar();
            mensaje="Elimino Vereda";
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
