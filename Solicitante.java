/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author sena
 */
public class Solicitante {
    private int idSolicitante;
    private String nombreSolicitante;
    private String documentoSolicitante;
    private String emailSolicitante;
    private String telefonoSolicitante;
    private int idPrestador;
    private int idPuntoToma;

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getTelefonoSolicitante() {
        return telefonoSolicitante;
    }

    public void setTelefonoSolicitante(String telefonoSolicitante) {
        this.telefonoSolicitante = telefonoSolicitante;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public int getIdPuntoToma() {
        return idPuntoToma;
    }

    public void setIdPuntoToma(int idPuntoToma) {
        this.idPuntoToma = idPuntoToma;
    }

    public Solicitante() {
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }
    
    private int paginacion=0;
    
    public ArrayList listar(int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaSolicitante = new ArrayList();
        Solicitante unSolicitante;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idSolicitante";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idSolicitante LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unSolicitante = new Solicitante();
                unSolicitante.setIdSolicitante(rs.getInt("idSolicitante"));
                unSolicitante.setNombreSolicitante(rs.getString("NombreSolicitante"));
                unSolicitante.setDocumentoSolicitante(rs.getString("DocumentoSolicitante"));
                unSolicitante.setEmailSolicitante(rs.getString("EmailSolicitante"));
                unSolicitante.setIdPrestador(rs.getInt("idPrestador"));
                unSolicitante.setIdPuntoToma(rs.getInt("idPuntoToma"));
                listaSolicitante.add(unSolicitante);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Solicitante: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaSolicitante;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO solicitante(idSolicitante, NombreSolicitante, DocumentoSolicitante, "
                    + " EmailSolicitante, idPrestador, idPuntoToma)"
                    + " VALUES("+getIdSolicitante()+", '"+getNombreSolicitante()+"', '"+getDocumentoSolicitante()+"', '"
                    +getEmailSolicitante()+"', '"+getIdPrestador()+"', '"+getIdPuntoToma()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Solicitante: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE solicitante SET NombreSolicitante = '"+getNombreSolicitante()+"', DocumentoSolicitante = '"+getDocumentoSolicitante()
                    +"', EmailSolicitante = '"+getEmailSolicitante()+"', idPrestador = '"+getIdPrestador()+"', idPuntoToma = '"+getIdPuntoToma()
                    +"' WHERE idSolicitante = "+getIdSolicitante());
        }catch (SQLException ex){
            System.err.println("Error al modificar Solicitante: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM solicitante WHERE idSolicitante = "+getIdSolicitante());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Solicitante: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdSolicitante)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Solicitante "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
