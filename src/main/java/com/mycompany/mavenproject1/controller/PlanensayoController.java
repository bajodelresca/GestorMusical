/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Componente;
import com.mycompany.mavenproject1.model.ComponenteDAO;
import com.mycompany.mavenproject1.model.Planensayo;
import com.mycompany.mavenproject1.model.PlanensayoDAO;
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
import javafx.collections.ObservableListBase;
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
public class PlanensayoController implements Initializable {

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
    private TableView<Planensayo> table;
    @FXML
    private TableColumn<Planensayo, String> Nombreccolumn;
    @FXML
    private TableColumn<Planensayo, String> Nombremcolumn;
    @FXML
    private TableColumn<Planensayo, Integer> IDcolumn;
    @FXML
    private TableColumn<Planensayo, Integer> IDCcolumn;
    @FXML
    private TableColumn<Planensayo, Integer> IDMcolumn;
    public ObservableList<Planensayo> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        List<Planensayo> miscomponentes = PlanensayoDAO.selectAll();
        data.addAll(miscomponentes);

        this.Nombreccolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombrecomponen());
        });
        this.Nombremcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombremarcha());
        });
        this.IDcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getID());
        });
        this.IDCcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getIDcomponente());

        });
        this.IDMcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getIDmarcha());

        });
        
         Nombreccolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Nombreccolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Planensayo, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Planensayo, String> t) {

                Planensayo selected = (Planensayo) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombrecomponen(t.getNewValue());  //<<- update lista en vista

                PlanensayoDAO dao = new PlanensayoDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
         Nombremcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Nombremcolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Planensayo, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Planensayo, String> t) {

                Planensayo selected = (Planensayo) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombremarcha(t.getNewValue());  //<<- update lista en vista

                PlanensayoDAO dao = new PlanensayoDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        
    
        table.setEditable(true);
        table.setItems(data);

    }
@FXML 
   public void deleteplan(){
       Planensayo selected=table.getSelectionModel().getSelectedItem();
       if(selected!=null){
           if (!showConfirm(selected.getID())) {
                return;
            }
           data.remove(selected);
           PlanensayoDAO dao=new PlanensayoDAO(selected);
           dao.remove(selected);
       }else{
           showWarning("Aviso","Ningún plan a borrar","Selecciona un plan");
           
       }
   } 
   
    public void showWarning(String title,String header,String description){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
                
    }
    public boolean showConfirm(int ID) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Eliminar");
        alert.setContentText("Desea borrar el plan " + ID);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }
    public void openNuevop(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("nuevoplan.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Nuevo Plan");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(compStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            NuevoplaController ncompController=fxmlLoader.getController();
            if(ncompController!=null){
                ncompController.setStage(modaleStage);
                ncompController.setParent(this);
                ncompController.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PlanensayoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    @FXML
    public void atras(){
        if(this.compStage != null){
            this.compStage.close();
        }
    }
}
