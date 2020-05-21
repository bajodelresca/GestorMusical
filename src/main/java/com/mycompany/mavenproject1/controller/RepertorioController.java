/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Marcha;
import com.mycompany.mavenproject1.model.MarchaDAO;
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
 * FXML Controller class
 *
 * @author espin
 */
public class RepertorioController implements Initializable {

    private MainController parent;
    private Object params;
    private Stage reperStage;

    public void setStage(Stage reperStage) {
        this.reperStage = reperStage;
    }

    public void setParent(MainController p) {
        params = p;
    }

    public void setParams(Object p) {
        this.reperStage = reperStage;
    }

    @FXML
    private TableView<Marcha> table;
    @FXML
    private TableColumn<Marcha, String> Nombrecolumna;
    @FXML
    private TableColumn<Marcha, String> Autorcolumna;
    @FXML
    private TableColumn<Marcha, Integer> Anocolumna;
    @FXML
    private TableColumn<Marcha, Integer> IDcolumna;
    public ObservableList<Marcha> datos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.datos = FXCollections.observableArrayList();
        List<Marcha> miscomponentes = MarchaDAO.selectAll();
        datos.addAll(miscomponentes);

        this.Nombrecolumna.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
        });
        this.Autorcolumna.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getAutor());
        });
        this.Anocolumna.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getAñoañadida());
        });
        this.IDcolumna.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getID());

        });
        
         Nombrecolumna.setCellFactory(TextFieldTableCell.forTableColumn());
        Nombrecolumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Marcha, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Marcha, String> t) {

                Marcha selected = (Marcha) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                MarchaDAO dao = new MarchaDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
         Autorcolumna.setCellFactory(TextFieldTableCell.forTableColumn());
        Autorcolumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Marcha, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Marcha, String> t) {

                Marcha selected = (Marcha) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setAutor(t.getNewValue());  //<<- update lista en vista

                MarchaDAO dao = new MarchaDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
    
        table.setEditable(true);
        table.setItems(datos);

    }
     @FXML 
   public void deletemarcha(){
       Marcha selected=table.getSelectionModel().getSelectedItem();
       if(selected!=null){
           if (!showConfirm(selected.getNombre())) {
                return;
            }
           datos.remove(selected);
           MarchaDAO dao=new MarchaDAO(selected);
           dao.remove(selected);
       }else{
           showWarning("Aviso","Ninguna marcha a borrar","Selecciona una marcha");
           
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
        alert.setContentText("Desea borrar la marcha " + Nombre);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }
    public void openNuevom(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevamarcha.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Nueva marcha");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(reperStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            NuevamarController nmarController=fxmlLoader.getController();
            if(nmarController!=null){
                nmarController.setStage(modaleStage);
                nmarController.setParent(this);
                nmarController.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(RepertorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    @FXML
    public void atras(){
        if(this.reperStage != null){
            this.reperStage.close();
        }
    }
}
