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
public class Rol {

    private int idRol;
    private String nombreRol;
    private String analistaRol;
    private String elaboradorRol;
    private String analisisRol;
    private String solicitanteRol;
    private String municipioRol;
    private String puntoTomaRol;
    private String departamentoRol;
    private String prestadorRol;
    private String resultadoRol;
    private String veredaRol;
    private String parametroRol;
    private String riesgoRol;
    private String metodoRol;
    private String valorRol;
    private String usuarioRol;
    private String auditoriaRol;
    private String rolRol;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getAnalistaRol() {
        return analistaRol;
    }

    public void setAnalistaRol(String analistaRol) {
        this.analistaRol = analistaRol;
    }

    public String getElaboradorRol() {
        return elaboradorRol;
    }

    public void setElaboradorRol(String elaboradorRol) {
        this.elaboradorRol = elaboradorRol;
    }

    public String getAnalisisRol() {
        return analisisRol;
    }

    public void setAnalisisRol(String analisisRol) {
        this.analisisRol = analisisRol;
    }

    public String getSolicitanteRol() {
        return solicitanteRol;
    }

    public void setSolicitanteRol(String solicitanteRol) {
        this.solicitanteRol = solicitanteRol;
    }

    public String getMunicipioRol() {
        return municipioRol;
    }

    public void setMunicipioRol(String municipioRol) {
        this.municipioRol = municipioRol;
    }

    public String getPuntoTomaRol() {
        return puntoTomaRol;
    }

    public void setPuntoTomaRol(String puntoTomaRol) {
        this.puntoTomaRol = puntoTomaRol;
    }

    public String getDepartamentoRol() {
        return departamentoRol;
    }

    public void setDepartamentoRol(String departamentoRol) {
        this.departamentoRol = departamentoRol;
    }

    public String getPrestadorRol() {
        return prestadorRol;
    }

    public void setPrestadorRol(String prestadorRol) {
        this.prestadorRol = prestadorRol;
    }

    public String getResultadoRol() {
        return resultadoRol;
    }

    public void setResultadoRol(String resultadoRol) {
        this.resultadoRol = resultadoRol;
    }

    public String getVeredaRol() {
        return veredaRol;
    }

    public void setVeredaRol(String veredaRol) {
        this.veredaRol = veredaRol;
    }

    public String getParametroRol() {
        return parametroRol;
    }

    public void setParametroRol(String parametroRol) {
        this.parametroRol = parametroRol;
    }

    public String getRiesgoRol() {
        return riesgoRol;
    }

    public void setRiesgoRol(String riesgoRol) {
        this.riesgoRol = riesgoRol;
    }

    public String getMetodoRol() {
        return metodoRol;
    }

    public void setMetodoRol(String metodoRol) {
        this.metodoRol = metodoRol;
    }

    public String getValorRol() {
        return valorRol;
    }

    public void setValorRol(String valorRol) {
        this.valorRol = valorRol;
    }

    public String getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public String getAuditoriaRol() {
        return auditoriaRol;
    }

    public void setAuditoriaRol(String auditoriaRol) {
        this.auditoriaRol = auditoriaRol;
    }

    public String getRolRol() {
        return rolRol;
    }

    public void setRolRol(String rolRol) {
        this.rolRol = rolRol;
    }

    public Rol() {
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
        ArrayList listaRol = new ArrayList();
        Rol unRol;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY idRol";
        
        if(pagina>0){
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado ="SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idRol LIMIT "+paginacionMin+","+paginacionMax;
        }
        try{
            ResultSet rs = st.executeQuery(listado);
            while(rs.next()){
                unRol = new Rol();
                unRol.setIdRol(rs.getInt("idRol"));
                unRol.setNombreRol(rs.getString("NombreRol"));
                unRol.setAnalistaRol(rs.getString("AnalistaRol"));
                unRol.setElaboradorRol(rs.getString("ElaboradorRol"));
                unRol.setAnalisisRol(rs.getString("AnalisisRol"));
                unRol.setSolicitanteRol(rs.getString("SolicitanteRol"));
                unRol.setMunicipioRol(rs.getString("MunicipioRol"));
                unRol.setPuntoTomaRol(rs.getString("PuntoTomaRol"));
                unRol.setDepartamentoRol(rs.getString("DepartamentoRol"));
                unRol.setPrestadorRol(rs.getString("PrestadorRol"));
                unRol.setResultadoRol(rs.getString("ResultadoRol"));
                unRol.setVeredaRol(rs.getString("VeredaRol"));
                unRol.setParametroRol(rs.getString("ParametroRol"));
                unRol.setRiesgoRol(rs.getString("RiesgoRol"));
                unRol.setMetodoRol(rs.getString("MetodoRol"));
                unRol.setValorRol(rs.getString("ValorRol"));
                unRol.setUsuarioRol(rs.getString("UsuarioRol"));
                unRol.setAuditoriaRol(rs.getString("AuditoriaRol"));
                unRol.setRolRol(rs.getString("RolRol"));
                listaRol.add(unRol);
            }
        }catch (SQLException ex){
            System.err.println("Error al listar Rol: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaRol;
    }
    
    public void insertar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("INSERT INTO rol(idRol, NombreRol, AnalistaRol, ElaboradorRol, AnalisisRol,"
                    + " SolicitanteRol, MunicipioRol, PuntoTomaRol, DepartamentoRol, PrestadorRol, ResultadoRol,"
                    + " VeredaRol, ParametroRol, RiesgoRol, MetodoRol, ValorRol, UsuarioRol, AuditoriaRol, RolRol)"
                    + " VALUES("+getIdRol()+", '"+getNombreRol()+"', '"+getAnalistaRol()+"', '"+getElaboradorRol()+"', '"
                    +getAnalisisRol()+"', '"+getSolicitanteRol()+"' '"+getMunicipioRol()+"', '"+getPuntoTomaRol()+"', '"
                    +getDepartamentoRol()+", '"+getPrestadorRol()+"', '"+getResultadoRol()+"', '"+getVeredaRol()+"', '"
                    +getParametroRol()+", '"+getRiesgoRol()+"', '"+getMetodoRol()+"', '"+getValorRol()+"', '"
                    +getUsuarioRol()+"', '"+getAuditoriaRol()+"', '"+getRolRol()+"')");
        }catch (SQLException ex){
            System.err.println("Error al isertar Rol: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void modificar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("UPDATE rol SET NombreRol = '"+getNombreRol()+"', AnalistaRol = '"+getAnalistaRol()
                    +"', ElaboradorRol = '"+getElaboradorRol()+"', AnalisisRol = '"+getAnalisisRol()+"', SolicitanteRol = '"
                    +getSolicitanteRol()+"', MunicipioRol = '"+getMunicipioRol()+"', PuntoTomaRol = '"+getPuntoTomaRol()
                    +"', DepartamentoRol = '"+getDepartamentoRol()+"', PrestadorRol = '"+getPrestadorRol()+"', ResultadoRol = '"
                    +getResultadoRol()+"', VeredaRol = '"+getVeredaRol()+"', ParametroRol = '"+getParametroRol()
                    +"', RiesgoRol = '"+getRiesgoRol()+"', MetodoRol = '"+getMetodoRol()+"', ValorRol = '"
                    +getValorRol()+"', UsuarioRol = '"+getUsuarioRol()+"', AuditoriaRol = '"+getAuditoriaRol()+"', RolRol = '"+getRolRol()
                    +"' WHERE idRol = "+getIdRol());
        }catch (SQLException ex){
            System.err.println("Error al modificar Rol: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public void eliminar(){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try{
            st.executeUpdate("DELETE FROM rol WHERE idRol = "+getIdRol());
        }catch (SQLException ex){
            System.err.println("Error al eliminar Rol: "+ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }
    
    public int cantidadPaginas(){
        Conexion Conexion = new Conexion();
        Statement st = Conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(IdRol)/"+this.paginacion+") AS cantidad FROM "
                    +this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la cantidad de paginas Rol "+ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }
}
