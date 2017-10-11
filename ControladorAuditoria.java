/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Auditoria;
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
@WebServlet(name = "ControladorAuditoria", urlPatterns = {"/ControladorAuditoria"})
public class ControladorAuditoria extends HttpServlet {

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
            out.println("<title>Servlet ControladorAuditoria</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAuditoria at " + request.getContextPath() + "</h1>");
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
        
        String id             = request.getParameter("fIdAuditoria");
        String Fecha          = request.getParameter("fFechaAuditoria");
        String Descripcion    = request.getParameter("fDescripcionAuditoria");
        String Usuario        = request.getParameter("fidUsuario");
        String accion = request.getParameter("fAccion");
        
    int idAuditoria = 0;
    try{
        idAuditoria = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    LocalDate FechaAuditoria = LocalDate.now();
    try{
        FechaAuditoria = LocalDate.parse(Fecha);
    }catch(DateTimeParseException dtpe){
    
    }
    
    int idUsuario = 0;
    try{
        idUsuario = Integer.parseInt(Usuario);
    }catch(NumberFormatException nfe){
     
    }
    
        Auditoria unAuditoria = new Auditoria();
        unAuditoria.setIdAuditoria(idAuditoria);
        unAuditoria.setFechaAuditoria(FechaAuditoria);
        unAuditoria.setDescripcionAuditoria(Descripcion);
        unAuditoria.setIdUsuario(idUsuario);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unAuditoria.insertar();
            mensaje="Inserto Auditoria";
        break;
        case "modificar":
            unAuditoria.modificar();
            mensaje="Modifico Auditoria";
        break;
        case "eliminar":
            unAuditoria.eliminar();
            mensaje="Elimino Auditoria";
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
