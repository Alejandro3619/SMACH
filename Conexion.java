/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author SENA
 */
public class Conexion {
    
    Connection conexion = null;
    
    public Statement conectar(){
        Statement st = null;
        try{
            Context ctx  = new InitialContext();
            DataSource ds =(DataSource)ctx.lookup("jdbc/BDProyecto30");
            conexion = ds.getConnection("Alejandro","1234"); //usuaro y clave
            st = conexion.createStatement();
        }catch(NamingException ex){
            System.err.println("Error al Iniciar contexto: "+ex.getMessage());
        }catch(SQLException ex){
            System.err.println("Error al Conectarse  a la BD: "+ex.getLocalizedMessage());
        }
        return st;
    }
    
    public void desconectar(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.err.println("Error al Cerrar la BD: "+ex.getLocalizedMessage());
        }
    }
    
}
