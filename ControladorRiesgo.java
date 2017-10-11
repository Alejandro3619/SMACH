/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Riesgo;
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
@WebServlet(name = "ControladorRiesgo", urlPatterns = {"/ControladorRiesgo"})
public class ControladorRiesgo extends HttpServlet {

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
            out.println("<title>Servlet ControladorRiesgo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRiesgo at " + request.getContextPath() + "</h1>");
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
        
        String id             = request.getParameter("fIdRiesgo");
        String Punto          = request.getParameter("fPuntoRiesgo");
        String Fecha          = request.getParameter("fFechaRiesgo");
        String ValorParametro       = request.getParameter("fidValorParametro");
        String accion = request.getParameter("fAccion");
        
    int idRiesgo = 0;
    try{
        idRiesgo = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
    
    LocalDate FechaRiesgo = LocalDate.now();
    try{
        FechaRiesgo = LocalDate.parse(Fecha);
    }catch(DateTimeParseException dtpe){
    
    }
    
    int idValorParametro = 0;
    try{
        idValorParametro = Integer.parseInt(ValorParametro);
    }catch(NumberFormatException nfe){
            
    }
    
        Riesgo unRiesgo = new Riesgo();
        unRiesgo.setIdRiesgo(idRiesgo);
        unRiesgo.setPuntoRiesgo(Punto);
        unRiesgo.setFechaRiesgo(FechaRiesgo);
        unRiesgo.setIdValorParametro(idValorParametro);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unRiesgo.insertar();
            mensaje="Inserto Riesgo";
        break;
        case "modificar":
            unRiesgo.modificar();
            mensaje="Modifico Riesgo";
        break;
        case "eliminar":
            unRiesgo.eliminar();
            mensaje="Elimino Riesgo";
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
