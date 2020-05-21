/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Componente;
import com.mycompany.mavenproject1.model.ComponenteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author espin
 */
public class ComponentesController implements Initializable{
    private MainController parent;
    private Object params;
    private Stage compStage;

    public void setStage(Stage compStage) {
        this.compStage = compStage;
    }

    public void setParent(MainController p) {
        params = p;
    }

    public void setParams(Object p) {
        this.compStage = compStage;
    }
     @FXML
     private TableView<Componente> table;
     @FXML
     private TableColumn<Componente,String> Nombrecolumn;
     @FXML
     private TableColumn<Componente,String> Instrumentocolumn;
     @FXML
     private TableColumn<Componente,Integer> Anocolumn;
     @FXML
     private TableColumn<Componente,Integer> IDcolumn;
     public ObservableList<Componente> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data=FXCollections.observableArrayList();
        List<Componente> miscomponentes=ComponenteDAO.selectAll();
        data.addAll(miscomponentes);
        
        this.Nombrecolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
        this.Instrumentocolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getInstrumento());
            });
        this.Anocolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getFechainscripcion());
            });
        this.IDcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getID());
            
        });
        
        Nombrecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Nombrecolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Componente, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Componente, String> t) {

                Componente selected = (Componente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                ComponenteDAO dao = new ComponenteDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        Instrumentocolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Instrumentocolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Componente, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Componente, String> t) {

                Componente selected = (Componente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setInstrumento(t.getNewValue());  //<<- update lista en vista

                ComponenteDAO dao = new ComponenteDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        table.setEditable(true);
        table.setItems(data);
       
    }
    
   @FXML 
   public void deletecomponente(){
       Componente selected=table.getSelectionModel().getSelectedItem();
       if(selected!=null){
           if (!showConfirm(selected.getNombre())) {
                return;
            }
           data.remove(selected);
           ComponenteDAO dao=new ComponenteDAO(selected);
           dao.remove(selected);
       }else{
           showWarning("Aviso","Ningún componente a borrar","Selecciona un componente");
           
       }
   } 
    public void showWarning(String title,String header,String description){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
                
    }
    public boolean showConfirm(String Nombre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Eliminar");
        alert.setContentText("Desea borrar al componente " + Nombre);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }
    @FXML
    public void atras(){
        if(this.compStage != null){
            this.compStage.close();
        }
    }
         public void openNuevoc(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevocomp.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Nuevo Componente");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(compStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            NuevocompController ncompController=fxmlLoader.getController();
            if(ncompController!=null){
                ncompController.setStage(modaleStage);
                ncompController.setParent(this);
                ncompController.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ComponentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
      
}
