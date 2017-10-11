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
public class ValorParametro {
    private int idValorParametro;
    private String valorParametroMax;
    private String valorParametroMin;
    private int idParametro;

    public int getIdValorParametro() {
        return idValorParametro;
    }

    public void setIdValorParametro(int idValorParametro) {
        this.idValorParametro = idValorParametro;
    }

    public String getValorParametroMax() {
        return valorParametroMax;
    }

    public void setValorParametroMax(String valorParametroMax) {
        this.valorParametroMax = valorParametroMax;
    }

    public String getValorParametroMin() {
        return valorParametroMin;
    }

    public void setValorParametroMin(String valorParametroMin) {
        this.valorParametroMin = valorParametroMin;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public ValorParametro() {
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
        ArrayList listaValorParametro = new ArrayList();
        ValorParametro unValorParametro;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idValorParametro";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idValorParametro LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unValorParametro = new ValorParametro();
                unValorParametro.setIdValorParametro(rs.getInt("idValorParametro"));
                unValorParametro.setValorParametroMax(rs.getString("ValorParametroMax"));
                unValorParametro.setValorParametroMin(rs.getString("ValorParametroMin"));
                unValorParametro.setIdParametro(rs.getInt("idParametro"));
                listaValorParametro.add(unValorParametro);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar ValorParametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaValorParametro;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO valorParametro(idValorParametro, ValorParametroMax, ValorParametroMin, idParametro)"
                    + " VALUES("+getIdValorParametro()+", '"+getValorParametroMax()+"', '"+getValorParametroMin()+"', '"+getIdParametro()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar ValorParametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE valorParametro SET ValorParametroMax = '"+getIdValorParametro()+"', ValorParametroMin = '"+getValorParametroMin()
                    +"', idParametro = '"+getIdParametro()
                    +"' WHERE idValorParametro = "+getIdValorParametro());
        }catch (SQLException ex){
            System.err.println("Error al modificar Analisis: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM valorParametro WHERE idValorParametro = "+getIdValorParametro());
        }catch (SQLException ex){
            System.err.println("Error al eliminar ValorParametro: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdValorParametro)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas ValorParametro "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}