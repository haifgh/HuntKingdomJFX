/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Guide;
import Services.ServiceGuide;
import Utilities.Connexion;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author haifa
 */
public class ShowguideController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Guide> table_l;
    private int as;
    
    @FXML
    private TableColumn<Guide, String> col_titre;
    @FXML
    private TableColumn<Guide, String> col_datecreation;
    @FXML
    private TableColumn<Guide, String> col_categorie;
    @FXML
    private TableColumn<Guide, String> col_description;
    @FXML
    private TableColumn<Guide, Double> col_note;
    @FXML
    private TableColumn<Guide, String> col_photo;
    @FXML
    private TableColumn<Guide, String> col_lien;
    @FXML
    private JFXButton AddGuide;
    @FXML
    private JFXButton DeleteGuide;
    @FXML
    private JFXButton UpdateGuide;
      ObservableList<Guide> obsguidelist=FXCollections.observableArrayList();
      Connexion con ;
    @FXML
    private JFXButton tnguide;
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con=Connexion.getInstance();
        ServiceGuide srvg = new ServiceGuide();
        srvg.afficherguide().stream().forEach((p) -> {obsguidelist.add(p);});
        
      // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       col_datecreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
       col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
       col_note.setCellValueFactory(new PropertyValueFactory<>("note"));
        col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
         col_lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
       table_l.setItems(obsguidelist);
     
    }    
    
    @FXML
    private void AddGuide(ActionEvent event) {
     try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Views/Addguide.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("error");
}
    
    }

    @FXML
    private void DeleteGuide(ActionEvent event) {
         con=Connexion.getInstance();
         ServiceGuide vs = new ServiceGuide();
        Guide v = (Guide) table_l.getSelectionModel().getSelectedItem();
        vs.supprimerguide(v);
   
                    table_l.getItems().clear();
vs.afficherguide();    
setCellTableNormale();
vs.afficherguide();
         
    }

    @FXML
    private void UpdateGuide(ActionEvent event) {
          ServiceGuide sg = new ServiceGuide();
        Guide g = (Guide) table_l.getSelectionModel().getSelectedItem();
           
        try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("/Views/Updateguide.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                UpdateguideController test = pane.getController();
          as=table_l.getSelectionModel().getSelectedItem().getId();

        String  titre=table_l.getSelectionModel().getSelectedItem().getTitre();
         String  description=table_l.getSelectionModel().getSelectedItem().getDescription();
      String categorie=table_l.getSelectionModel().getSelectedItem().getCategorie();
         String photo=table_l.getSelectionModel().getSelectedItem().getPhoto();
      
       
             test.setData(titre, categorie, description, titre, photo, as);
                                         
                 

              table_l.getSelectionModel().getSelectedItem().getTitre();
              table_l.getSelectionModel().getSelectedItem().getCategorie();
               table_l.getSelectionModel().getSelectedItem().getDescription();
                table_l.getSelectionModel().getSelectedItem().getPhoto();
                  table_l.getSelectionModel().getSelectedItem().getId();
        
    } catch(Exception e)
    {
     System.out.println("eer");
}
            
    }


    private void setCellTableNormale() {
            
    ObservableList<Guide> obsguidelist=FXCollections.observableArrayList();
       
    ServiceGuide srvg = new ServiceGuide();
         srvg.afficherguide().stream().forEach((p) -> {obsguidelist.add(p);});
     //  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       col_datecreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
       col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
       col_note.setCellValueFactory(new PropertyValueFactory<>("note"));
        col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
         col_lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
       table_l.setItems(obsguidelist);
    }

    @FXML
    private void AddguideAction(ContextMenuEvent event) {
    }

    @FXML
    private void Gotoguides(ActionEvent event) {
     setCellTableNormale();
    
    }
    
}