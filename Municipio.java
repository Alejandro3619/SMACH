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
public class Municipio {
    private int idMunicipio;
    private String nombreMunicipio;
    private int idDepartamento;

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Municipio() {
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
        ArrayList listaMunicipio = new ArrayList();
        Municipio unMunicipio;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idMunicipio";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idMunicipio LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unMunicipio = new Municipio();
                unMunicipio.setIdMunicipio(rs.getInt("idMunicipio"));
                unMunicipio.setNombreMunicipio(rs.getString("NombreMunicipio"));
                unMunicipio.setIdDepartamento(rs.getInt("idDepartamento"));
                listaMunicipio.add(unMunicipio);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Municipio: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaMunicipio;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO municipio(idMunicipio, NombreMunicipio, idDepartamento)"
                    + " VALUES("+getIdMunicipio()+", '"+getNombreMunicipio()+"', '"+getIdDepartamento()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Municipio: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE municipio SET NombreMunicipio = '"+getNombreMunicipio()+"', idDepartamento = '"+getIdDepartamento()
                    +"' WHERE idMunicipio = "+getIdMunicipio());
        }catch (SQLException ex){
            System.err.println("Error al modificar Municipio: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM municipio WHERE idMunicipio = "+getIdMunicipio());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Municipio: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdMunicipio)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Municipio "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}