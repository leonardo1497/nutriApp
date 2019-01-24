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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DietasPacienteController implements Initializable {
    
    @FXML
    private JFXButton atrasBTN;
    @FXML
    private TableView<paciente>  tabla;
    
    private ObservableList<paciente> pacientes;
    
    conexion c= new conexion();
    @FXML
    private TableColumn nombreC, telefonoC, dietasC;
    @FXML
    private AnchorPane panePacientes;
    @FXML
    private AnchorPane paneCrearDieta;
    @FXML
    private JFXButton atrasCrearDieta;
    @FXML
    private JFXComboBox<String> tipoAliE;
    @FXML
    private JFXTextField cantidadE;
    @FXML
    private JFXComboBox<String> alimentoE;
    @FXML
    private Button agregarBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoAliE.getItems().addAll("Verduras","Frutas","Cereale s-grasa","Leguminosas","AOA Muy Bajo Aporte Grasa","AOA Bajo Aporte Grasa","Leche","Aceites y grasas");
        try {
            // TODO
            inicializarTablaEspacios();
            cargarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(DietasPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void cargarTabla() throws SQLException{
        Connection cn = c.conectar();
        pacientes.removeAll(pacientes);
        nombreC.setCellValueFactory(new PropertyValueFactory<paciente, String>("nombre"));
        telefonoC.setCellValueFactory(new PropertyValueFactory<paciente, Integer>("telefono"));
        dietasC.setCellValueFactory(new PropertyValueFactory<paciente, String>("boton"));
        tabla.setItems(pacientes);
        
        String   sql = "SELECT nombre,telefono,dieta_activa from paciente";
        JFXButton boton;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                boton = new JFXButton();
                if(rs.getBoolean(3))
                    boton.setText("Modificar");
                else
                    boton.setText("Crear");
                boton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        panePacientes.setVisible(false);
                        paneCrearDieta.setVisible(true);
                    }
                });
                paciente p;
                    p = new paciente(rs.getString(1),rs.getString(2),boton);
                    pacientes.add(p);
                }
        } catch (SQLException ex) {
            Logger.getLogger(DietasPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cn.close();
    }
    
   private void inicializarTablaEspacios() {
        nombreC.setCellValueFactory(new PropertyValueFactory<paciente, String>("nombre"));
        telefonoC.setCellValueFactory(new PropertyValueFactory<paciente, Integer>("telefono"));
        dietasC.setCellValueFactory(new PropertyValueFactory<paciente, String>("boton"));
        pacientes = FXCollections.observableArrayList();
        tabla.setItems(pacientes);
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
    private void atrasDietaEvento(ActionEvent event) {
        panePacientes.setVisible(true);
        paneCrearDieta.setVisible(false);
    }
    
    @FXML
    private void eventoTipoAlimento(ActionEvent event) throws SQLException {
        alimentoE.getItems().clear();
        Connection cn = c.conectar();
        String   sql = "SELECT nombre from equivalentes where tipo_alimento = '"+tipoAliE.getValue()+"'";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            alimentoE.getItems().add(rs.getString(1));
        }
        if(alimentoE.getItems().isEmpty()){
            alimentoE.getItems().add("Vacío");
        }

        cn.close();
        
    }

    @FXML
    private void eventoAlimento(ActionEvent event) {
    }

    @FXML
    private void agregarAlimentoEvent(ActionEvent event) {
    }
    
}
