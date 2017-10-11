/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Analisis;
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
@WebServlet(name = "ControladorAnalisis", urlPatterns = {"/ControladorAnalisis"})
public class ControladorAnalisis extends HttpServlet {

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
            out.println("<title>Servlet ControladorAnalisis</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAnalisis at " + request.getContextPath() + "</h1>");
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
        
        String id             = request.getParameter("fIdAnalisis");
        String Fecha          = request.getParameter("fFechaAnalisis");
        String Reporte        = request.getParameter("fReporteAnalisis");
        String Tipo           = request.getParameter("fTipoAnalisis");
        String Olor           = request.getParameter("fOlorAnalisis");
        String Sabor          = request.getParameter("fSaborAnalisis");
        String Elaborador     = request.getParameter("fidElaborador");
        String Analista       = request.getParameter("fidAnalista");
        String accion = request.getParameter("fAccion");
        
    int idAnalisis = 0;
    try{
        idAnalisis = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    LocalDate FechaAnalisis = LocalDate.now();
    try{
        FechaAnalisis = LocalDate.parse(Fecha);
    }catch(DateTimeParseException dtpe){
    
    }
    
    int idElaborador = 0;
    try{
        idElaborador = Integer.parseInt(Elaborador);
    }catch(NumberFormatException nfe){
            
    }
    
    int idAnalista = 0;
    try{
        idAnalista = Integer.parseInt(Analista);
    }catch(NumberFormatException nfe){
            
    }
    
        Analisis unAnalisis = new Analisis();
        unAnalisis.setIdAnalisis(idAnalisis);
        unAnalisis.setFechaAnalisis(FechaAnalisis);
        unAnalisis.setReporteAnalisis(Reporte);
        unAnalisis.setTipoAnalisis(Tipo);
        unAnalisis.setOlorAnalisis(Olor);
        unAnalisis.setSaborAnalisis(Sabor);
        unAnalisis.setIdElaborador(idElaborador);
        unAnalisis.setIdAnalista(idAnalista);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unAnalisis.insertar();
            mensaje="Inserto Analisis";
        break;
        case "modificar":
            unAnalisis.modificar();
            mensaje="Modifico Analisis";
        break;
        case "eliminar":
            unAnalisis.eliminar();
            mensaje="Elimino Analisis";
        break;
    }
    request.getRequestDispatcher("/WEB-INF/FormularioAnalisis.jsp?msj="+mensaje).forward(request,response);
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
