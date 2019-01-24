/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutriapp;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author ACER
 */
public class paciente {
    
    public SimpleStringProperty nombre;
    public SimpleStringProperty telefono;
    JFXButton boton;
    
    paciente(String nombre, String telefono, JFXButton boton){
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.boton = boton;
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public String getNombre(){
        return nombre.get();
    }
    
    public void setTelefono(String telefono){
        this.telefono.set(telefono);
    }
    
    public String getTelefono(){
        return telefono.get();
    }
    
    
    public Button getBoton() {
	return boton;
    }
}
