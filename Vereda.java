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
public class Vereda {
    private int idVereda;

    public int getIdVereda() {
        return idVereda;
    }

    public void setIdVereda(int idVereda) {
        this.idVereda = idVereda;
    }

    public String getNombreVereda() {
        return nombreVereda;
    }

    public void setNombreVereda(String nombreVereda) {
        this.nombreVereda = nombreVereda;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    private String nombreVereda;
    private int idMunicipio;

    public Vereda() {
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
        ArrayList listaVereda = new ArrayList();
        Vereda unVereda;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idVereda";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idAVereda LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unVereda = new Vereda();
                unVereda.setIdVereda(rs.getInt("idVereda"));
                unVereda.setNombreVereda(rs.getString("NombreVereda"));
                unVereda.setIdMunicipio(rs.getInt("idMunicipio"));
                listaVereda.add(unVereda);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Vereda: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaVereda;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO vereda(idVereda, NombreVereda, idMunicipio)"
                    + " VALUES("+getIdVereda()+", '"+getNombreVereda()+"', '"+getIdMunicipio()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Vereda: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE vereda SET NombreVereda = '"+getNombreVereda()+"', idMunicipio = '"+getIdMunicipio()
                    +"' WHERE idVereda = "+getIdVereda());
        }catch (SQLException ex){
            System.err.println("Error al modificar Vereda: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM vereda WHERE idVereda = "+getIdVereda());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Vereda: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdVereda)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Vereda "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}