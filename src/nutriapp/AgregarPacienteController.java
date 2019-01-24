/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package nutriapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AgregarPacienteController implements Initializable {
    
    @FXML
    private JFXTextField nombreE;
    @FXML
    private JFXDatePicker fechaE;
    @FXML
    private JFXTextField edadE;
    @FXML
    private JFXComboBox<String> sexoE;
    @FXML
    private JFXDatePicker fechaNacimientoE;
    @FXML
    private JFXComboBox<String> estadoCivilE;
    @FXML
    private JFXTextField escolaridadE;
    @FXML
    private JFXTextField direccionE;
    @FXML
    private JFXTextField telefonoE;
    @FXML
    private JFXTextField correoE;
    @FXML
    private JFXTextField imcE;
    @FXML
    private JFXTextField grasaE;
    @FXML
    private JFXTextField ocupacionE;
    @FXML
    private JFXButton atrasBTN;
    
    /**
     * Initializes the controller class.
     */
    
    conexion cc = new conexion();
    @FXML
    private JFXTextField pesoActualE;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sexoE.getItems().addAll("Masculino","Femenino");
            estadoCivilE.getItems().addAll("Soltero/a","Casado/a","Dirvociado/a","Comprometido/a","Viudo/A");
            nombreE.setText("");
            edadE.setText("");
            sexoE.setValue("");
            fechaNacimientoE.getEditor().setText("");
            estadoCivilE.setValue("");
            escolaridadE.setText("");
            ocupacionE.setText("");
            direccionE.setText("");
            telefonoE.setText("");
            correoE.setText("");
            pesoActualE.setText("");
            imcE.setText("");
            grasaE.setText("");
            fechaE.getEditor().setText("");
            listaPacientes();
                    } catch (SQLException ex) {
            Logger.getLogger(AgregarPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listaPacientes() throws SQLException{
        System.out.println("listar");
        Connection cn = cc.conectar();
        String   sql = "SELECT * from paciente";
        Statement st;
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println("ffffffffffff");
            System.out.println("id: "+rs.getString(1));
            System.out.println("id: "+rs.getString(2));
            System.out.println("id: "+rs.getString(3));
        }
    
    }
    
    @FXML
    private void agregarPacienteEvent(ActionEvent event) throws SQLException {
        Connection cn= cc.conectar();
        PreparedStatement pst = null;
        if(!sexoE.getValue().equals("")&&!estadoCivilE.getValue().equals("")&&!nombreE.getText().equals("")
                &&!edadE.getText().equals("")&&!sexoE.getValue().equals("")&&!fechaNacimientoE.getValue().equals("")
                &&!escolaridadE.getText().equals("")&&!ocupacionE.getText().equals("")&&!direccionE.getText().equals("")
                &&!telefonoE.getText().equals("")&&!correoE.getText().equals("")&&!pesoActualE.getText().equals("")
                &&!imcE.getText().equals("")&&!grasaE.getText().equals("")&&!fechaE.getValue().equals("")){
            try{
                pst = cn.prepareStatement("insert into paciente(nombre,edad,sexo,fecha_nacimiento,estado_civil,"
                        + "escolaridad,ocupacion,direccion,telefono,email,peso,imc,grasa,fecha_consulta) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, nombreE.getText());
                pst.setInt(2,Integer.parseInt(edadE.getText()));
                pst.setString(3, sexoE.getValue());
                pst.setString(4,fechaNacimientoE.getEditor().getText());
                pst.setString(5, estadoCivilE.getValue());
                pst.setString(6,escolaridadE.getText());
                pst.setString(7, ocupacionE.getText());
                pst.setString(8, direccionE.getText());
                pst.setString(9,telefonoE.getText());
                pst.setString(10, correoE.getText());
                pst.setString(11, pesoActualE.getText());
                pst.setString(12,imcE.getText());
                pst.setString(13, grasaE.getText());
                pst.setString(14, fechaE.getEditor().getText());
                pst.executeUpdate();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root = (Parent) fxml.load();
                Stage stage = new Stage();
                stage.setTitle("Menú principal");
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) atrasBTN.getScene().getWindow();
                stage2.close();
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText("Problema al guardar los datos");
                alert.setContentText("Los datos no fueron guardados correctamente");
                alert.showAndWait();
            }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Campos vacios");
            alert.setContentText("Asegurese de llenar todos los campos");
            alert.showAndWait();
        }

        cn.close();
    }
    
    @FXML
    private void atrasEvento(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        stage.setTitle("Menú principal");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) atrasBTN.getScene().getWindow();
        stage2.close();
    }
    
    
}
