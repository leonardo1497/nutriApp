/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutriapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane principal;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton agregarEquivalenteBT;
    @FXML
    private JFXButton agregarPacienteBT;
    @FXML
    private JFXButton consultarBT;
    @FXML
    private AnchorPane menuAP;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void agregarPacienteEvent(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("agregarPaciente.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        stage.setTitle("Agregar Paciente");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) agregarPacienteBT.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void agregarEquivalenteEvent(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("agregarEquivalente.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        stage.setTitle("Agregar Equivalente");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) agregarEquivalenteBT.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void pacienteDietas(ActionEvent event) throws IOException{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("dietasPaciente.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        stage.setTitle("Dietas paciente");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) consultarBT.getScene().getWindow();
        stage2.close();
    }
    
}
