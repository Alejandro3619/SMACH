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
public class Parametro {
    private int idParametro;
    private String nombreParametro;
    private String tipoParametro;
    private String unidadParametro;
    private int idMetodo;

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getUnidadParametro() {
        return unidadParametro;
    }

    public void setUnidadParametro(String unidadParametro) {
        this.unidadParametro = unidadParametro;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public Parametro() {
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
        ArrayList listaParametro = new ArrayList();
        Parametro unParametro;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idParametro";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idParametro LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unParametro = new Parametro();
                unParametro.setIdParametro(rs.getInt("idParametro"));
                unParametro.setNombreParametro(rs.getString("NombreParametro"));
                unParametro.setTipoParametro(rs.getString("TipoParametro"));
                unParametro.setUnidadParametro(rs.getString("UnidadParametro"));
                unParametro.setIdMetodo(rs.getInt("idMetodo"));
                listaParametro.add(unParametro);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Parametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaParametro;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO parametro(idParametro, NombreParametro, TipoParametro, UnidadParametro, idMetodo)"
                    + " VALUES("+getIdParametro()+", '"+getNombreParametro()+"', '"+getTipoParametro()+"', '"
                    +getUnidadParametro()+"', '"+getIdMetodo()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Parametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE parametro SET NombreParametro = '"+getNombreParametro()+"', TipoParametro = '"
                    +getTipoParametro()+"', UnidadParametro = '"+getUnidadParametro()+"', idMetodo = '"+getIdMetodo()
                    +"' WHERE idParametro = "+getIdParametro());
        }catch (SQLException ex){
            System.err.println("Error al modificar Parametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM parametro WHERE idParametro = "+getIdParametro());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Parametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdParametro)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Parametro "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
