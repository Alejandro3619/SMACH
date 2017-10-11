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
public class Elaborador {
    private int idElaborador;
    private String nombreElaborador;
    private String profesionAnalista;

    public int getIdElaborador() {
        return idElaborador;
    }

    public void setIdElaborador(int idElaborador) {
        this.idElaborador = idElaborador;
    }

    public String getNombreElaborador() {
        return nombreElaborador;
    }

    public void setNombreElaborador(String nombreElaborador) {
        this.nombreElaborador = nombreElaborador;
    }

    public String getProfesionAnalista() {
        return profesionAnalista;
    }

    public void setProfesionAnalista(String profesionAnalista) {
        this.profesionAnalista = profesionAnalista;
    }

    public Elaborador() {
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
        ArrayList listaElaborador = new ArrayList();
        Elaborador unElaborador;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idElaborador";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idElaborador LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unElaborador = new Elaborador();
                unElaborador.setIdElaborador(rs.getInt("idElaborador"));
                unElaborador.setNombreElaborador(rs.getString("NombreElaborador"));
                unElaborador.setProfesionAnalista(rs.getString("ProfesionAnalista"));
                listaElaborador.add(unElaborador);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Elaborador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaElaborador;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO elaborador(idElaborador, NombreElaborador, ProfesionAnalista)"
                    + " VALUES("+getIdElaborador()+", '"+getNombreElaborador()+"', '"+getProfesionAnalista()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Elaborador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE elaborador SET NombreElaborador = '"+getNombreElaborador()+"', ProfesionAnalista = '"
                    +getProfesionAnalista()
                    +"' WHERE idElaborador = "+getIdElaborador());
        }catch (SQLException ex){
            System.err.println("Error al modificar Elaborador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM elaborador WHERE idElaborador = "+getIdElaborador());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Elaborador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdElaborador)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Elaborador "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
