/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SENA
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

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
            out.println("<title>Servlet ControladorUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuario at " + request.getContextPath() + "</h1>");
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
        
        String id                = request.getParameter("fIdUsuario");
        String Nombre            = request.getParameter("fNombreUsuario");
        String Email             = request.getParameter("fEmailUsuario");
        String Clave             = request.getParameter("fClaveUsuario");
        String Celular           = request.getParameter("fCelularUsuario");
        String FechaRegistro     = request.getParameter("fFechaRegistroUsuario");
        String FechaUltimaClaveU  = request.getParameter("fFechaUltimaClave");
        String Rol             = request.getParameter("fidRol");
        String accion = request.getParameter("fAccion");
    int idUsuario = 0;
    try{
        idUsuario = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    LocalDate FechaRegistroUsuario = LocalDate.now();
    try{
        FechaRegistroUsuario = LocalDate.parse(FechaRegistro);
    }catch(DateTimeParseException dtpe){
    
    }
    
    LocalDate FechaUltimaClave = LocalDate.now();
    try{
        FechaUltimaClave = LocalDate.parse(FechaUltimaClaveU);
    }catch(DateTimeParseException dtpe){
    
    }
    
    int idRol = 0;
    try{
        idRol = Integer.parseInt(Rol);
    }catch(NumberFormatException nfe){
            
    }
    
        Usuario unUsuario = new Usuario();
        unUsuario.setIdUsuario(idUsuario);
        unUsuario.setNombreUsuario(Nombre);
        unUsuario.setEmailUsuario(Email);
        unUsuario.setClaveUsuario(Clave);
        unUsuario.setCelularUsuario(Celular);
        unUsuario.setFechaRegistroUsuario(FechaRegistroUsuario);
        unUsuario.setFechaUltimaClave(FechaUltimaClave);
        unUsuario.setIdRol(idRol);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unUsuario.insertar();
            mensaje="Inserto Usuario";
        break;
        case "modificar":
            unUsuario.modificar();
            mensaje="Modifico Usuario";
        break;
        case "eliminar":
            unUsuario.eliminar();
            mensaje="Elimino Usuario";
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
