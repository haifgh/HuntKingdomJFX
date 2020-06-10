/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Commentaire;
import Models.User;
import Services.ServiceCommentaire;
import Utilities.Connexion;
import Utilities.UserSession;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
     User f = UserSession.getInstance().getUser();
ObservableList<Commentaire> obscomlist=FXCollections.observableArrayList();
 ServiceCommentaire srvc = new ServiceCommentaire();
Commentaire c;
      Connexion con ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        con=Connexion.getInstance();
       
       // srvc.affichercommentaire().stream().forEach((p) -> {obscomlist.add(p);});
        ObservableList<Commentaire>data=FXCollections.observableArrayList();
       data.addAll(srvc.affichercommentaire());
            System.out.println(data.size());
       col_id_guide.setCellValueFactory(new PropertyValueFactory<>("guide_id"));
       col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
       //table_l.setItems(obscomlist);
       table_l.setItems(data);
       table_l.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commentaire>() {
            
            @Override
            public void changed(ObservableValue<? extends Commentaire> observable, Commentaire oldValue, Commentaire newValue) {
               c =  table_l.getSelectionModel().getSelectedItem();
            }
        }
        );
        
    }    

    //@FXML
   // private void AddComment(ContextMenuEvent event) {
     //}

    @FXML
    private void UpdateComment(ActionEvent event) {
      con=Connexion.getInstance();
         ServiceCommentaire vs = new ServiceCommentaire();
       Commentaire c= (Commentaire) table_l.getSelectionModel().getSelectedItem();
       
       // vs.modifiercommentaire(c);
        try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("/views/UpdateComment.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                UpdateCommentController test = pane.getController();
          //as=table_l.getSelectionModel().getSelectedItem().getId();
                   as=table_l.getSelectionModel().getSelectedItem().getId();
        String  contenu=table_l.getSelectionModel().getSelectedItem().getContenu();
        int  guide_id=table_l.getSelectionModel().getSelectedItem().getGuide_id();
        int  user_id=table_l.getSelectionModel().getSelectedItem().getUser_id();
        String  date=table_l.getSelectionModel().getSelectedItem().getDate();
            test.setData(
                                         
                 
              
              table_l.getSelectionModel().getSelectedItem().getGuide_id(),
              table_l.getSelectionModel().getSelectedItem().getUser_id(),
               table_l.getSelectionModel().getSelectedItem().getContenu(),
                table_l.getSelectionModel().getSelectedItem().getDate(),
                     table_l.getSelectionModel().getSelectedItem().getId());
            
                  
    //   vs.modifiercommentaire(c);
    } catch(Exception e)
    {
     System.out.println("eer");
}
    
    }

    @FXML
    private void AddComment(ActionEvent event) {
      
        try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/views/AddComment.fxml"));
  
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
       /* con=Connexion.getInstance();
        
         if(table_l.getSelectionModel().getSelectedItem() == null){
            ErrorNotification();
        }else{   
            SuccesNotification();
     ServiceCommentaire srvc = new ServiceCommentaire();
        Commentaire c = (Commentaire) table_l.getSelectionModel().getSelectedItem();
        srvc.supprimercommentaire(c);
      //  srvc.removeComment(c);
        table_l.getItems().clear();
        //srvc.affichercommentaire();    
         setCellTableNormale();
           srvc.affichercommentaire();
        
    
         
        
         }*/
       con=Connexion.getInstance();
       /*if (c != null) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure to delete ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
               ServiceCommentaire serviceformation = new ServiceCommentaire();
                serviceformation.removeComment(c.getId());
               // loadScreenButton();
            }
            // Status =serviceconseil.delete(conseilmodel.getIdcons());
            //if (Status > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supprimer Formation");
            alert1.setHeaderText("Information");
            alert1.setContentText(" Formation bien supprimé");
            alert1.showAndWait();
        }*/
       Commentaire c=(Commentaire)table_l.getSelectionModel().getSelectedItem();
        ServiceCommentaire svc=new ServiceCommentaire();
       
        svc.removeComment(c);
        table_l.getItems().clear();
       
         setCellTableNormale();
      
    }
    

    @FXML
    private void GotComment(ActionEvent event) throws IOException {
        
       setCellTableNormale();
    }
    
    
    private void setCellTableNormale() {
            
    //ObservableList<Commentaire> observableList=FXCollections.observableArrayList();
    ObservableList<Commentaire>data=FXCollections.observableArrayList();
       data.addAll(srvc.affichercommentaire());
            System.out.println(data.size());
    //ServiceCommentaire srvc = new ServiceCommentaire();
     //    srvc.affichercommentaire().stream().forEach((p) -> {obscomlist.add(p);});
       col_id_guide.setCellValueFactory(new PropertyValueFactory<>("guide_id"));
       col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
       table_l.setItems(data);
    }
    private void ErrorNotification() {
        Notifications notificationBuilder = Notifications.create()
        .title("Fail :(")
                .text("Operation Failed Selecet Comment")
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
                .text("Operation Succeeded Comment deleted")
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

    @FXML
    private void AddComment(ContextMenuEvent event) {
    }
    
}
