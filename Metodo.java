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
public class Metodo {
    private int idMetodo;
    private String nombreMetodo;
    private String normaMetodo;

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getNormaMetodo() {
        return normaMetodo;
    }

    public void setNormaMetodo(String normaMetodo) {
        this.normaMetodo = normaMetodo;
    }

    public Metodo() {
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
        ArrayList listaMetodo = new ArrayList();
        Metodo unMetodo;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idMetodo";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idMetodo LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unMetodo = new Metodo();
                unMetodo.setIdMetodo(rs.getInt("idMetodo"));
                unMetodo.setNombreMetodo(rs.getString("NombreMetodo"));
                unMetodo.setNormaMetodo(rs.getString("NormaMetodo"));
                listaMetodo.add(unMetodo);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Metodo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaMetodo;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO metodo(idMetodo, NombreMetodo, NormaMetodo)"
                    + " VALUES("+getIdMetodo()+", '"+getNombreMetodo()+"', '"+getNormaMetodo()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Metodo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE metodo SET NombreMetodo = '"+getNombreMetodo()+"', NormaMetodo = '"+getNormaMetodo()
                    +"' WHERE idMetodo= "+getIdMetodo());
        }catch (SQLException ex){
            System.err.println("Error al modificar Metodo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM metodo WHERE idMetodo = "+getIdMetodo());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Metodo: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdMetodo)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Metodo "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
