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
public class Resultado {
    private int idResultado;
    private String valorResultado;
    private int idParametro;
    private int idAnalisis;

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getValorResultado() {
        return valorResultado;
    }

    public void setValorResultado(String valorResultado) {
        this.valorResultado = valorResultado;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Resultado() {
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
        ArrayList listaResultado = new ArrayList();
        Resultado unResultado;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idResultado";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idResultado LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unResultado = new Resultado();
                unResultado.setIdResultado(rs.getInt("idResultado"));
                unResultado.setValorResultado(rs.getString("ValorResultado"));
                unResultado.setIdParametro(rs.getInt("idParametro"));
                unResultado.setIdAnalisis(rs.getInt("idAnalisis"));
                listaResultado.add(unResultado);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Resultado: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaResultado;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO resultado(idResultado, ValorResultado, idParametro, idAnalisis)"
                    + " VALUES("+getIdResultado()+", '"+getValorResultado()+"', '"+getIdParametro()+"', '"+getIdAnalisis()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Resultado: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE resultado SET ValorResultado = '"+getValorResultado()+"', idParametro = '"
                    +getIdParametro()+"', idAnalisis = '"+getIdAnalisis()
                    +"' WHERE idResultado = "+getIdResultado());
        }catch (SQLException ex){
            System.err.println("Error al modificar Resultado: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM resultado WHERE idResultado = "+getIdResultado());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Resultado: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdResultado)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Resultado "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}