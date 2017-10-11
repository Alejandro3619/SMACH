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
public class Analista {
    private int idAnalista;
    private String nombreAnalista;
    private String cargoAnalista;
    private String matriculaAnalista;

    public int getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(int idAnalista) {
        this.idAnalista = idAnalista;
    }

    public String getNombreAnalista() {
        return nombreAnalista;
    }

    public void setNombreAnalista(String nombreAnalista) {
        this.nombreAnalista = nombreAnalista;
    }

    public String getCargoAnalista() {
        return cargoAnalista;
    }

    public void setCargoAnalista(String cargoAnalista) {
        this.cargoAnalista = cargoAnalista;
    }

    public String getMatriculaAnalista() {
        return matriculaAnalista;
    }

    public void setMatriculaAnalista(String matriculaAnalista) {
        this.matriculaAnalista = matriculaAnalista;
    }

    public Analista() {
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
        ArrayList listaAnalista = new ArrayList();
        Analista unAnalista;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idAnalista";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idAnalista LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unAnalista = new Analista();
                unAnalista.setIdAnalista(rs.getInt("idAnalista"));
                unAnalista.setNombreAnalista(rs.getString("NombreAnalista"));
                unAnalista.setCargoAnalista(rs.getString("CargoAnalista"));
                unAnalista.setMatriculaAnalista(rs.getString("MatriculaAnalista"));
                listaAnalista.add(unAnalista);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Analista: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAnalista;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO analista(idAnalista, NombreAnalista, CargoAnalista, MatriculaAnalista)"
                    + " VALUES("+getIdAnalista()+", '"+getNombreAnalista()+", '"+getCargoAnalista()+"', '"+getMatriculaAnalista()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Analista: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE analista SET NombreAnalista = '"+getNombreAnalista()+"', CargoAnalista = '"+getCargoAnalista()+"', MatriculaAnalista = '"+getMatriculaAnalista()
                    +"' WHERE idAnalista = "+getIdAnalista());
        }catch (SQLException ex){
            System.err.println("Error al modificar Analista: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM analista WHERE idAnalista = "+getIdAnalista());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Analista: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdAnalista)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Analista "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
