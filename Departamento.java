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
public class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Departamento() {
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
        ArrayList listaDepartamento = new ArrayList();
        Departamento unDepartamento;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idDepartamento";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idDepartamento LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unDepartamento = new Departamento();
                unDepartamento.setIdDepartamento(rs.getInt("idDepartamento"));
                unDepartamento.setNombreDepartamento(rs.getString("NombreDepartamento"));
                listaDepartamento.add(unDepartamento);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Departamento: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaDepartamento;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO departamento(idDepartamento, NombreDepartamento)"
                    + " VALUES("+getIdDepartamento()+", '"+getNombreDepartamento()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Departamento: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE departamento SET NombreDepartamento = '"+getNombreDepartamento()
                    +"' WHERE idDepartamento = "+getIdDepartamento());
        }catch (SQLException ex){
            System.err.println("Error al modificar Departamento: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM departamento WHERE idDepartamento = "+getIdDepartamento());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Departamento: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdDepartamento)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Departamento "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
