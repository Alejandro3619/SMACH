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
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String claveUsuario;
    private String celularUsuario;
    private LocalDate fechaRegistroUsuario;
    private LocalDate fechaUltimaClave;
    private int idRol;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getCelularUsuario() {
        return celularUsuario;
    }

    public void setCelularUsuario(String celularUsuario) {
        this.celularUsuario = celularUsuario;
    }

    public LocalDate getFechaRegistroUsuario() {
        return fechaRegistroUsuario;
    }

    public void setFechaRegistroUsuario(LocalDate fechaRegistroUsuario) {
        this.fechaRegistroUsuario = fechaRegistroUsuario;
    }

    public LocalDate getFechaUltimaClave() {
        return fechaUltimaClave;
    }

    public void setFechaUltimaClave(LocalDate fechaUltimaClave) {
        this.fechaUltimaClave = fechaUltimaClave;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Usuario() {
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
        ArrayList listaUsuario = new ArrayList();
        Usuario unUsuario;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idUsuario";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idUsuario LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unUsuario = new Usuario();
                unUsuario.setIdUsuario(rs.getInt("idUsuario"));
                unUsuario.setNombreUsuario(rs.getString("NombreUsuario"));
                unUsuario.setEmailUsuario(rs.getString("EmailUsuario"));
                unUsuario.setClaveUsuario(rs.getString("ClaveUsuario"));
                unUsuario.setCelularUsuario(rs.getString("CelularUsuario"));
                unUsuario.setFechaRegistroUsuario(rs.getDate("FechaRegistroUsuario").toLocalDate());
                unUsuario.setFechaUltimaClave(rs.getDate("FechaUltimaClave").toLocalDate());
                unUsuario.setIdRol(rs.getInt("idRol"));
                listaUsuario.add(unUsuario);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Usuario: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaUsuario;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO usuario (idUsuario, NombreUsuario, EmailUsuario, ClaveUsuario, CelularUsuario,"
                    + " FechaRegistroUsuario, FechaUltimaClave, idRol) "
                    + " VALUES("+getIdUsuario()+", '"+getNombreUsuario()+"', '"+getEmailUsuario()+"', '"+getClaveUsuario()+"', '"
                    +getCelularUsuario()+"', '"+getFechaRegistroUsuario()+"', '"+getFechaUltimaClave()+"', '"+getIdRol()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Usuario: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            System.out.println("id rol"+getEmailUsuario());
            st.executeUpdate("UPDATE usuario SET NombreUsuario = '"+getNombreUsuario()+"', EmailUsuario = '"+getEmailUsuario()
                    +"', ClaveUsuario = '"+getClaveUsuario()+"', CelularUsuario = '"+getCelularUsuario()+"', FechaRegistroUsuario = '"
                    +getFechaRegistroUsuario()+"', FechaUltimaClave = '"+getFechaUltimaClave()+"', idRol = '"+getIdRol()
                    +"' WHERE idUsuario = "+getIdUsuario());
        }catch (SQLException ex){
            System.err.println("Error al modificar Usuario: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM usuario WHERE idUsuario = "+getIdUsuario());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Usuario: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdUsuario)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Usuario "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}