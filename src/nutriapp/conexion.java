/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutriapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author ACER
 */
public class conexion {
    Connection con = null;
    public Connection conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:nutriapp.db");
            //con.setAutoCommit(false);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "¡La conexión con la Base de Datos falló!", ButtonType.OK).show();
        }
        return con;
    }
    
}
