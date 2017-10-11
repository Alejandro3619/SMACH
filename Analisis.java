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
public class Analisis {
    private int idAnalisis;
    private LocalDate fechaAnalisis;
    private String reporteAnalisis;
    private String tipoAnalisis;
    private String olorAnalisis;
    private String saborAnalisis;
    private int idElaborador;
    private int idAnalista;

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public LocalDate getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(LocalDate fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getReporteAnalisis() {
        return reporteAnalisis;
    }

    public void setReporteAnalisis(String reporteAnalisis) {
        this.reporteAnalisis = reporteAnalisis;
    }

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }

    public String getOlorAnalisis() {
        return olorAnalisis;
    }

    public void setOlorAnalisis(String olorAnalisis) {
        this.olorAnalisis = olorAnalisis;
    }

    public String getSaborAnalisis() {
        return saborAnalisis;
    }

    public void setSaborAnalisis(String saborAnalisis) {
        this.saborAnalisis = saborAnalisis;
    }

    public int getIdElaborador() {
        return idElaborador;
    }

    public void setIdElaborador(int idElaborador) {
        this.idElaborador = idElaborador;
    }

    public int getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(int idAnalista) {
        this.idAnalista = idAnalista;
    }

    public Analisis() {
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
        ArrayList listaAnalisis = new ArrayList();
        Analisis unAnalisis;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idAnalisis";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idAnalisis LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unAnalisis = new Analisis();
                unAnalisis.setIdAnalisis(rs.getInt("idAnalisis"));
                unAnalisis.setFechaAnalisis(rs.getDate("FechaAnalisis").toLocalDate());
                unAnalisis.setReporteAnalisis(rs.getString("ReporteAnalisis"));
                unAnalisis.setTipoAnalisis(rs.getString("TipoAnalisis"));
                unAnalisis.setOlorAnalisis(rs.getString("OlorAnalisis"));
                unAnalisis.setSaborAnalisis(rs.getString("SaborAnalisis"));
                unAnalisis.setIdElaborador(rs.getInt("idElaborador"));
                unAnalisis.setIdAnalista(rs.getInt("idAnalista"));
                listaAnalisis.add(unAnalisis);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Pelicula: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAnalisis;
    }
    
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO analisis(idAnalisis, FechaAnalisis, ReporteAnalisis, TipoAnalisis, OlorAnalisis,"
                    + " SaborAnalisis, idElaborador, idAnalista)"
                    + " VALUES("+getIdAnalisis()+", '"+getFechaAnalisis()+"', '"+getReporteAnalisis()+"', '"+getTipoAnalisis()+"', '"
                    +getOlorAnalisis()+"', '"+getSaborAnalisis()+"', '"+getIdElaborador()+"', '"+getIdAnalista()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Analisis: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE analisis SET FechaAnalisis = '"+getFechaAnalisis()+"', ReporteAnalisis = '"+getReporteAnalisis()
                    +"', TipoAnalisis = '"+getTipoAnalisis()+"', OlorAnalisis = '"+getOlorAnalisis()+"', SaborAnalisis = '"
                    +getSaborAnalisis()+"', idElaborador = '"+getIdElaborador()+"', idAnalista = '"+getIdAnalista()
                    +"' WHERE idAnalisis = "+getIdAnalisis());
        }catch (SQLException ex){
            System.err.println("Error al modificar Analisis: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM analisis WHERE idAnalisis = "+getIdAnalisis());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Analisis: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdAnalisis)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Analisis "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
