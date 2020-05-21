package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Planensayo;
import com.mycompany.mavenproject1.model.PlanensayoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class PrimaryController implements Initializable {
    
    
    public void openNewComponente(){
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
            Parent modal = fxmlLoader.load();
            Stage modalStage=new Stage();
            modalStage.setTitle("Menu");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene=new Scene(modal);
            modalStage.setScene(modalScene);
            
            MainController maincontroller=fxmlLoader.getController();
            if(maincontroller!=null){
                maincontroller.setStage(modalStage);
                maincontroller.setParent(this);
                maincontroller.setParams(null);
            }
            
            modalStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

  
    
}
