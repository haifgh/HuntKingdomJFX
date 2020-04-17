/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commentaire;
import Models.Guide;
import Models.User;
import Models.rate;
import Services.ServiceCommentaire;
import Services.ServiceGuide;
import Services.ServiceLikes;
import Services.ServiceRate;
import Utilities.UserSession;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import static java.rmi.Naming.list;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Controller.ShowguideuserController.idG;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import sun.awt.SunHints.Value;

/**
 * FXML Controller class
 *
 * @author haifa
 */
public class DetailsguideitemController implements Initializable {

    @FXML
    private AnchorPane a;
    @FXML
    private Text question;
    @FXML
    private JFXButton type_vote_oui;
    @FXML
    private JFXButton type_vote_non;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton addComment;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView img_gui;
    @FXML
    private TextField commentText;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;

    ObservableList<Commentaire> data;
    int i,i2 ;
    public static int idC ;
    @FXML
    private ListView<Commentaire> listcom;
    // int idCli=Util.UserSession.getUser().getId();
     ServiceCommentaire cs =new ServiceCommentaire();
        ServiceGuide sg=new ServiceGuide();
        Guide g=new Guide();
        List<Guide> le = new ArrayList<>();
        ImageView photo=new ImageView();
    User f = UserSession.getInstance().getUser();
    ServiceRate sr=new ServiceRate();
    private Guide evs;
    @FXML
    private Rating rating;
      int Value;    
    @FXML
    private Text question1;
        public void setEvenement(Guide evs) {
        this.evs=evs;
        
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       rating.ratingProperty().addListener(new ChangeListener<Number>() 
          {            
              @Override 
              
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                  Value = newValue.intValue();
                System.out.println(Value);
            }
     

            
       });}
          
     

          
        
        
        
        
         /*System.out.println(""+idG);
       for (Guide g1 : le) {
          
           if  (idG==g1.getId()){
                System.err.println("okiiiiiiiiiiii"+ sg.findGuideById(idG).getPhoto());
                
                question.setText(sg.findGuideById(idG).getTitre());
                
                img_gui.setImage(new Image("http://localhost/pidev/web/images//" + sg.findGuideById(idG).getPhoto()));
                try {
                    data = cs.getAllCommentByGuide(sg.findGuideById(idG));
                } catch (SQLDataException ex) {
                    Logger.getLogger(DetailsguideitemController.class.getName()).log(Level.SEVERE, null, ex);
                }   
         listcom.setItems(data);
      //  listcom.setCellFactory((ListView<Commentaire> param) -> new ListViewCommentEvent());
            } else 
              System.out.println("erreur");*/
              
       
       
    
    public void showEv() throws SQLException {
    
    ServiceGuide es=new ServiceGuide();
        question.setText(evs.getTitre());
         img_gui.setImage(new Image("http://localhost/pidev/web/images//" + evs.getPhoto()));
        question1.setText(evs.getDescription());
        data=cs.getAllCommentByGuide(evs);
        listcom.setItems(data);
    }

    @FXML
    private void Like(ActionEvent event) throws SQLException {
        ServiceLikes sl=new ServiceLikes();
     //Likes l = new Likes();
       // l.setId_user(8);
        //l.setId_guide(idG);
       // sl.ajouterJaime(l);
       sl.likerArticle(evs.getId(), f.getId());
       
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("Your Like has been registred ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("detailsguideitem.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(DetailsguideitemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
    }

    @FXML
    private void Dislike(ActionEvent event) throws SQLException {
         ServiceLikes sl=new ServiceLikes();
     //Likes l1 = new Likes();
        sl.DislikerArticle(evs.getId());
      //  l1.setId_user(8);
        //l1.setId_guide(23);
      // sl.likerArticle(idG, idCli);
        //sl.supprimerJaime();
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("your Dislike has been registred").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("detailsguideitem.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(DetailsguideitemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
        
    }

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void AddComment(ActionEvent event) throws IOException {
       // Guide g=new Guide();
         Commentaire c=new Commentaire(evs.getId(),f.getId(),commentText.getText());
         cs.ajoutercommentaire(c);
         System.out.println("OK");
           
            SuccesNotification();
    }

    @FXML
    private void backtoGuide(ActionEvent event) {
     try {
           Parent root = FXMLLoader.load(getClass().getResource("Showguideuser.fxml"));
            Stage myWindow =  (Stage) addComment.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Guide List");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
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

    @FXML
    private void AddRate(MouseEvent event) throws SQLException {
        
        rate r=new rate();
       //if (!commentaire.getText().equals("")|| Value !=0) {
       r.getId();
       r.setNote(Value);
       r.setId_user(f.getId());
       r.setId_guide(evs.getId());
       //r.getId_guide();
       //    Commentaire c = null;
       //com.ajoutercommentaire(c);
       sr.ajouterRate(r);
           Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("Done")
                .text("thank you ")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
                    System.out.println("you clicked me");
                });
        notificationBuilder.show();
       
       }//else{
          //Alert alert = new Alert(Alert.AlertType.ERROR);
         //alert.setTitle("Dialog");
       
   //alert.setContentText("remplir votre champs!");

     //   alert.showAndWait();
    }
    

