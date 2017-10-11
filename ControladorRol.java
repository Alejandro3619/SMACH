/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Rol;
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
@WebServlet(name = "ControladorRol", urlPatterns = {"/ControladorRol"})
public class ControladorRol extends HttpServlet {

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
            out.println("<title>Servlet ControladorRol</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRol at " + request.getContextPath() + "</h1>");
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
        
        String id            = request.getParameter("fIdRol");
        String Nombre        = request.getParameter("fNombreRol");
        String Analista      = request.getParameter("fAnalistaRol");
        String Elaborador    = request.getParameter("fElaboradorRol");
        String Analisis      = request.getParameter("fAnalisisRol");
        String Solicitante   = request.getParameter("fSolicitanteRol");
        String Municipio     = request.getParameter("fMunicipioRol");
        String PuntoToma     = request.getParameter("fPuntoTomaRol");
        String Departamento  = request.getParameter("fDepartamentoRol");
        String Prestador     = request.getParameter("fPrestadorRol");
        String Resultado     = request.getParameter("fResultadoRol");
        String Vereda        = request.getParameter("fVeredaRol");
        String Parametro     = request.getParameter("fParametroRol");
        String Riesgo        = request.getParameter("fRiesgoRol");
        String Metodo        = request.getParameter("fMetodoRol");
        String Valor         = request.getParameter("fValorRol");
        String Usuario       = request.getParameter("fUsuarioRol");
        String Auditoria     = request.getParameter("fAuditoriaRol");
        String Rol           = request.getParameter("fRolRol");
        String accion = request.getParameter("fAccion");
        
    int idRol = 0;
    try{
        idRol = Integer.parseInt(id);
    }catch(NumberFormatException nfe){
    
    }
   
    
        Rol unRol = new Rol();
        unRol.setIdRol(idRol);
        unRol.setNombreRol(Nombre);
        unRol.setAnalistaRol(Analista);
        unRol.setElaboradorRol(Elaborador);
        unRol.setAnalisisRol(Analisis);
        unRol.setSolicitanteRol(Solicitante);
        unRol.setMunicipioRol(Municipio);
        unRol.setPuntoTomaRol(PuntoToma);
        unRol.setDepartamentoRol(Departamento);
        unRol.setPrestadorRol(Prestador);
        unRol.setResultadoRol(Resultado);
        unRol.setVeredaRol(Vereda);
        unRol.setParametroRol(Parametro);
        unRol.setRiesgoRol(Riesgo);
        unRol.setMetodoRol(Metodo);
        unRol.setValorRol(Valor);
        unRol.setUsuarioRol(Usuario);
        unRol.setAuditoriaRol(Auditoria);
        unRol.setRolRol(Rol);
    
    String mensaje="";
    switch(accion.toLowerCase()){
        case "insertar":
            unRol.insertar();
            mensaje="Inserto Rol";
        break;
        case "modificar":
            unRol.modificar();
            mensaje="Modifico Rol";
        break;
        case "eliminar":
            unRol.eliminar();
            mensaje="Elimino Rol";
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
