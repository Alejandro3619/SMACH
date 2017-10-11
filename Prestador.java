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
public class Prestador {
    private int idPrestador;
    private String nombrePrestador;

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getNombrePrestador() {
        return nombrePrestador;
    }

    public void setNombrePrestador(String nombrePrestador) {
        this.nombrePrestador = nombrePrestador;
    }

    public Prestador() {
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
        ArrayList listaPrestador = new ArrayList();
        Prestador unPrestador;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idPrestador";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idPrestador LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unPrestador = new Prestador();
                unPrestador.setIdPrestador(rs.getInt("idPrestador"));
                unPrestador.setNombrePrestador(rs.getString("NombrePrestador"));
                listaPrestador.add(unPrestador);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Prestador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPrestador;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO prestador(idPrestador, NombrePrestador)"
                    + " VALUES("+getIdPrestador()+", '"+getNombrePrestador()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Prestador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE prestador SET NombrePrestador = '"+getNombrePrestador()
                    +"' WHERE idPrestador = "+getIdPrestador());
        }catch (SQLException ex){
            System.err.println("Error al modificar Prestador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM prestador WHERE idPrestador = "+getIdPrestador());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Prestador: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdPrestador)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Prestador "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}