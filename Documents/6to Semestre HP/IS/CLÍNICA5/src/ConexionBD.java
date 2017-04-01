/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Hevl1
 */
public class ConexionBD {
    static Connection conn=null;
    
    public static Connection getConexion(){
        String url="jdbc:sqlserver://JORGEHP:1433;databaseName=Clinica5";
        try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,ex,"Error en la conexion con la Base de Datos "+ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
        try{    
            conn=DriverManager.getConnection(url,"sa","123alegria");
        }catch(SQLException e){
            showMessageDialog(null,"Error: "+e.getMessage(),"Error de Conexion ",JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
    
    public static ResultSet Consulta(String consulta){
        Connection conn=getConexion();
        Statement declara;
        try{
            declara=conn.createStatement();
            ResultSet respuesta=declara.executeQuery(consulta);
            return respuesta;
        }catch(SQLException e){
            showMessageDialog(null,"Error: "+e.getMessage(),"Error de Conexion ",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static void close(ResultSet rs){
        try{
            if(rs!=null)
                rs.close();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }
}
