/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sena
 */
public class PuntoToma {
    private int idPuntoToma;
    private String nombrePunto;
    private String descripcionPunto;
    private String direccionPunto;
    private String estradomiciliarioPunto;
    private int idVereda;

    public int getIdPuntoToma() {
        return idPuntoToma;
    }

    public void setIdPuntoToma(int idPuntoToma) {
        this.idPuntoToma = idPuntoToma;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getDescripcionPunto() {
        return descripcionPunto;
    }

    public void setDescripcionPunto(String descripcionPunto) {
        this.descripcionPunto = descripcionPunto;
    }

    public String getDireccionPunto() {
        return direccionPunto;
    }

    public void setDireccionPunto(String direccionPunto) {
        this.direccionPunto = direccionPunto;
    }

    public String getEstradomiciliarioPunto() {
        return estradomiciliarioPunto;
    }

    public void setEstradomiciliarioPunto(String estradomiciliarioPunto) {
        this.estradomiciliarioPunto = estradomiciliarioPunto;
    }

    public int getIdVereda() {
        return idVereda;
    }

    public void setIdVereda(int idVereda) {
        this.idVereda = idVereda;
    }

    public PuntoToma() {
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
        ArrayList listaPuntoToma = new ArrayList();
        PuntoToma unPuntoToma;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idPuntoToma";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idPuntoToma LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unPuntoToma = new PuntoToma();
                unPuntoToma.setIdPuntoToma(rs.getInt("idPuntoToma"));
                unPuntoToma.setNombrePunto(rs.getString("NombrePunto"));
                unPuntoToma.setDescripcionPunto(rs.getString("DescripcionPunto"));
                unPuntoToma.setDireccionPunto(rs.getString("DireccionPunto"));
                unPuntoToma.setEstradomiciliarioPunto(rs.getString("EstradomiciliarioPunto"));
                unPuntoToma.setIdVereda(rs.getInt("idVereda"));
                listaPuntoToma.add(unPuntoToma);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar PuntoToma: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPuntoToma;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO puntotoma(idPuntoToma, NombrePunto, DescripcionPunto, DireccionPunto, EstradomiciliarioPunto, idVereda)"
                    + " VALUES("+getIdPuntoToma()+", '"+getNombrePunto()+"', '"+getDescripcionPunto()+"', '"+getDireccionPunto()+"', '"
                    +getEstradomiciliarioPunto()+"', '"+getIdVereda()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar PuntoToma: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE puntotoma SET NombrePunto = '"+getNombrePunto()+"', DescripcionPunto = '"+getDescripcionPunto()
                    +"', DireccionPunto = '"+getDireccionPunto()+"', EstradomiciliarioPunto = '"+getEstradomiciliarioPunto()+"', idVereda = '"+getIdVereda()
                    +"' WHERE idPuntoToma = "+getIdPuntoToma());
        }catch (SQLException ex){
            System.err.println("Error al modificar PuntoToma: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM puntotoma WHERE idPuntoToma = "+getIdPuntoToma());
        }catch (SQLException ex){
            System.err.println("Error al eliminar PuntoToma: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdPuntoToma)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas PuntoToma "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}