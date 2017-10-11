/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sena
 */
public class Auditoria {
    private int idAuditoria;
    private LocalDate fechaAuditoria;
    private String descripcionAuditoria;
    private int idUsuario;

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public LocalDate getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(LocalDate fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getDescripcionAuditoria() {
        return descripcionAuditoria;
    }

    public void setDescripcionAuditoria(String descripcionAuditoria) {
        this.descripcionAuditoria = descripcionAuditoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Auditoria() {
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
        ArrayList listaAuditoria = new ArrayList();
        Auditoria unAuditoria;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idAuditoria";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idAuditoria LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unAuditoria = new Auditoria();
                unAuditoria.setIdAuditoria(rs.getInt("idAuditoria"));
                unAuditoria.setFechaAuditoria(rs.getDate("FechaAuditoria").toLocalDate());
                unAuditoria.setDescripcionAuditoria(rs.getString("DescripcionAuditoria"));
                unAuditoria.setIdUsuario(rs.getInt("idUsuario"));
                listaAuditoria.add(unAuditoria);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Auditoria: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAuditoria;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO auditoria(idAuditoria, FechaAuditoria, DescripcionAuditoria, idUsuario)"
                    + " VALUES("+getIdAuditoria()+", '"+getFechaAuditoria()+"', '"+getDescripcionAuditoria()+"', '"+getIdUsuario()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Auditoria: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE auditoria SET FechaAuditoria = '"+getFechaAuditoria()+"', DescripcionAuditoria = '"
                    +getDescripcionAuditoria()+"', idUsuario = '"+getIdUsuario()
                    +"' WHERE idAuditoria = "+getIdAuditoria());
        }catch (SQLException ex){
            System.err.println("Error al modificar Auditoria: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM auditoria WHERE idAuditoria = "+getIdAuditoria());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Auditoria: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdAuditoria)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Auditoria "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
