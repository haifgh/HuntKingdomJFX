/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Commentaire;
import Services.ServiceCommentaire;
import Utilities.Connexion;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author haifa
 */
public class CommentAdminController implements Initializable {
     private int as;
    @FXML
    private TableView<Commentaire> table_l;
    @FXML
    private TableColumn<?, ?> col_id_guide;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_id_user;
    @FXML
    private TableColumn<?, ?> col_contenu;
    @FXML
    private JFXButton UpdateComment;
    @FXML
    private JFXButton AddComment;
    @FXML
    private JFXButton DeleteComment;
    @FXML
    private JFXButton tnguide;
ObservableList<Commentaire> obscomlist=FXCollections.observableArrayList();
Commentaire c=new Commentaire();
      Connexion con ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        con=Connexion.getInstance();
        ServiceCommentaire srvc = new ServiceCommentaire();
        srvc.affichercommentaire().stream().forEach((p) -> {obscomlist.add(p);});
        
       col_id_guide.setCellValueFactory(new PropertyValueFactory<>("guide_id"));
       col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
       table_l.setItems(obscomlist);
     
        
    }    

    //@FXML
   // private void AddComment(ContextMenuEvent event) {
     //}

    @FXML
    private void UpdateComment(ActionEvent event) {
      con=Connexion.getInstance();
         ServiceCommentaire vs = new ServiceCommentaire();
        Commentaire c= (Commentaire) table_l.getSelectionModel().getSelectedItem();
        try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("UpdateComment.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                UpdateguideController test = pane.getController();
          as=table_l.getSelectionModel().getSelectedItem().getId();

        String  contenu=table_l.getSelectionModel().getSelectedItem().getContenu();
         //lezem nkamel hedha 
             //test.setData(contenu,);
                                         
                 
              
              table_l.getSelectionModel().getSelectedItem().getGuide_id();
              table_l.getSelectionModel().getSelectedItem().getUser_id();
               table_l.getSelectionModel().getSelectedItem().getContenu();
                table_l.getSelectionModel().getSelectedItem().getDate();
                  
        
    } catch(Exception e)
    {
     System.out.println("eer");
}
    
    }

    @FXML
    private void AddComment(ActionEvent event) {
      
        try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("AddComment.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
    

    @FXML
    private void DeleteComment(ActionEvent event) {
        con=Connexion.getInstance();
        /* con=Connexion.getInstance();
         ServiceCommentaire vsc = new ServiceCommentaire();
        Commentaire c= (Commentaire) table_l.getSelectionModel().getSelectedItem();
        vsc.supprimercommentaire(c);
   
                    table_l.getItems().clear();
   
setCellTableNormale();
vsc.affichercommentaire();*/
         if(table_l.getSelectionModel().getSelectedItem() == null){
            ErrorNotification();
        }else{   
            SuccesNotification();
     ServiceCommentaire srvc = new ServiceCommentaire();
        Commentaire c = table_l.getSelectionModel().getSelectedItem();
        srvc.supprimercommentaire(c);
        table_l.getItems().clear();
        srvc.affichercommentaire();    
         setCellTableNormale();
           srvc.affichercommentaire();
       
         }}

    @FXML
    private void GotComment(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("CommentAdmin.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
    
    
    private void setCellTableNormale() {
            
    ObservableList<Commentaire> observableList=FXCollections.observableArrayList();
       
    ServiceCommentaire srvc = new ServiceCommentaire();
         srvc.affichercommentaire().stream().forEach((p) -> {obscomlist.add(p);});
       col_id_guide.setCellValueFactory(new PropertyValueFactory<>("guide_id"));
       col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
       table_l.setItems(obscomlist);
    }
    private void ErrorNotification() {
        Notifications notificationBuilder = Notifications.create()
        .title("Fail :(")
                .text("Operation Failed")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                   @Override
                            public void handle(ActionEvent event){
                    System.out.println("TEST");
                }
                        });
        notificationBuilder.showError();       
    }
    private void SuccesNotification() {
        Notifications notificationBuilder = Notifications.create()
        .title("Success :)")
                .text("Operation Succeeded")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                   @Override
                            public void handle(ActionEvent event){
                    System.out.println("TEST");
                }
                        });
                      
        notificationBuilder.showConfirm();  
    }
}
