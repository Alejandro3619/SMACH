/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sena
 */
public class Riesgo {
    private int idRiesgo;
    private String puntoRiesgo;
    private LocalDate fechaRiesgo;
    private int idValorParametro;

    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getPuntoRiesgo() {
        return puntoRiesgo;
    }

    public void setPuntoRiesgo(String puntoRiesgo) {
        this.puntoRiesgo = puntoRiesgo;
    }

    public LocalDate getFechaRiesgo() {
        return fechaRiesgo;
    }

    public void setFechaRiesgo(LocalDate fechaRiesgo) {
        this.fechaRiesgo = fechaRiesgo;
    }

    public int getIdValorParametro() {
        return idValorParametro;
    }

    public void setIdValorParametro(int idValorParametro) {
        this.idValorParametro = idValorParametro;
    }

    public Riesgo() {
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
        ArrayList listaRiesgo = new ArrayList();
        Riesgo unRiesgo;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idRiesgo";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idRiesgo LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unRiesgo = new Riesgo();
                unRiesgo.setIdRiesgo(rs.getInt("idRiesgo"));
                unRiesgo.setPuntoRiesgo(rs.getString("PuntoRiesgo"));
                unRiesgo.setFechaRiesgo(rs.getDate("FechaRiesgo").toLocalDate());
                unRiesgo.setIdValorParametro(rs.getInt("idValorParametro"));
                listaRiesgo.add(unRiesgo);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Riesgo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaRiesgo;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO riesgo(idRiesgo, PuntoRiesgo, FechaRiesgo, idValorParametro)"
                    + " VALUES("+getIdRiesgo()+", '"+getPuntoRiesgo()+"', '"+getFechaRiesgo()+"', '"+getIdValorParametro()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Riesgo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE riesgo SET PuntoRiesgo = '"+getPuntoRiesgo()+"', FechaRiesgo = '"
                    +getFechaRiesgo()+"', idValorParametro = '"+getIdValorParametro()
                    +"' WHERE idRiesgo = "+getIdRiesgo());
        }catch (SQLException ex){
            System.err.println("Error al modificar Riesgo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM riesgo WHERE idRiesgo = "+getIdRiesgo());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Riesgo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdRiesgo)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Riesgo "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}