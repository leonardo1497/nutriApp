/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutriapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AgregarEquivalenteController implements Initializable {

    @FXML
    private JFXButton atrasBTN;
    @FXML
    private JFXComboBox<String> tipoEquivalente;
    @FXML
    private JFXTextField nombreE;
    @FXML
    private JFXComboBox<String> tipoUnidadE;
    @FXML
    private JFXTextField energiaE;
    @FXML
    private JFXTextField proteinaE;
    @FXML
    private JFXTextField lipidosE;
    @FXML
    private JFXTextField carbohidratosE;
    @FXML
    private JFXButton guardarBtn;

    /**
     * Initializes the controller class.
     */
    
    conexion c = new conexion();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoEquivalente.getItems().addAll("Verduras","Frutas","Cereale s-grasa","Leguminosas","AOA Muy Bajo Aporte Grasa","AOA Bajo Aporte Grasa","Leche","Aceites y grasas");
        tipoUnidadE.getItems().addAll("Taza","pieza","gramo","rebanada","cucharada","mitad");
        tipoUnidadE.setValue("");
        energiaE.setText("");
        proteinaE.setText("");
        carbohidratosE.setText("");
        lipidosE.setText("");
        nombreE.setText("");
        
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

    @FXML
    private void mostarEntradas(ActionEvent event) {
        tipoUnidadE.setVisible(true);
        energiaE.setVisible(true);
        proteinaE.setVisible(true);
        carbohidratosE.setVisible(true);
        lipidosE.setVisible(true);
        nombreE.setVisible(true);
    }
    
    @FXML
    private void guardarEquivalente(ActionEvent event) throws IOException, SQLException {
        Connection cn= c.conectar();
        PreparedStatement pst = null;
        if(!tipoUnidadE.getValue().equals("")&&!energiaE.getText().equals("")&&!proteinaE.getText().equals("")
                &&!carbohidratosE.getText().equals("")&&!lipidosE.getText().equals("")&&!nombreE.getText().equals("")){
            
            try {
                pst = cn.prepareStatement("insert into equivalentes(tipo_unidad,nombre,energia,proteina,lipidos,carbohidratos,tipo_alimento)"
                        + "values(?,?,?,?,?,?,?)");
            
                pst.setString(1, tipoUnidadE.getValue());
                pst.setString(2,nombreE.getText());
                pst.setString(3, energiaE.getText());
                pst.setString(4, proteinaE.getText());
                pst.setString(5, lipidosE.getText());
                pst.setString(6,carbohidratosE.getText());
                pst.setString(7, tipoEquivalente.getValue());
                pst.executeUpdate();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText("Registro exitoso");
                alert.setContentText("El equivalente fue agregado");
                alert.showAndWait();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root = (Parent) fxml.load();
                Stage stage = new Stage();
                stage.setTitle("Menú principal");
                stage.setScene(new Scene(root));
                stage.show();
                Stage stage2 = (Stage) atrasBTN.getScene().getWindow();
                stage2.close();
            } catch (SQLException ex) {
                Logger.getLogger(AgregarEquivalenteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Campos vacíos");
            alert.setContentText("Verique que lleno todo los campos");
            alert.showAndWait();
        }
        cn.close();
    }
    
}
