/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Guide;
import Models.User;
import Services.ServiceGuide;
import Services.UserServices;
import Utilities.Connexion;
import Utilities.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author haifa
 */
public class ShowguideuserController implements Initializable {

    private static Guide guide_à_ouvrir = new Guide();

    public static Guide getGuide_à_ouvrir() {
        return guide_à_ouvrir;
    }

    public static void setGuide_à_ouvrir(Guide guide_à_ouvrir) {
        
        ShowguideuserController.guide_à_ouvrir = guide_à_ouvrir;}
    
     
              private   Connection cnx;
              public static int idG ;
    
    ObservableList<Guide> obsguidelist=FXCollections.observableArrayList();
    @FXML
    private AnchorPane a;
    @FXML
    private ScrollPane s1;
    @FXML
    private VBox vbox1;
    @FXML
    private MenuButton choice;
    @FXML
    private MenuItem aff1;
  //  String URLimg;
  ImageView photo=new ImageView();
  ServiceGuide sg=new ServiceGuide();
    @FXML
    private Label list; 
    private JFXTextField searchField;
List<Guide> le = new ArrayList<>();
 User f = UserSession.getInstance().getUser();    
 User f1=new User();
 Guide g=new Guide();
@FXML
    private JFXTextField rech;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
   cnx=Connexion.getInstance().getCnx();
         UserServices us = new UserServices();
         
         User e = new User(20, "java", "java", "java@java", "java2@java", true, "java");
               us.Authentification(e);
      
        le = sg.afficherguide();   
        for (Guide g1 : le) {
            idG=g1.getId();
    ImageView photo = new ImageView(new Image("http://localhost/pidev/web/images//"+ g1.getPhoto()));
     Button b1=new Button();          
            Label titre = new Label();
            Label description = new Label();
            Label date_creation = new Label();
              Text add = new Text("tittre: ");
            Text ty = new Text("description: ");
            Text dd = new Text("date creation: ");
               titre.setText(g1.getTitre());
              description.setText(g1.getDescription());
            date_creation.setText((g1.getDate_creation()));
                        
             add.setFill(Color.DARKORANGE);
            ty.setFill(Color.DARKORANGE);
            dd.setFill(Color.DARKORANGE);
          // add.setFill(Color.DARKORANGE);
          
            HBox h1 = new HBox();
            HBox btn = new HBox();
 
             h1.setSpacing(10);
            photo.setFitHeight(200);
           photo.setFitWidth(400);
   h1.getChildren().add(photo);
      // h1.getChildren().add(ty);
       h1.spacingProperty();
         h1.getChildren().add(add);
           h1.getChildren().add(titre);
             h1.getChildren().add(dd);
            h1.getChildren().add(date_creation);
            h1.getChildren().add(ty);
                 h1.getChildren().add(description);
              
            
                  vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
    vbox1.setSpacing(20);
    vbox1.setAlignment(Pos.CENTER);
        
           Button bt2 = new Button("more Détails"); 
                        
                  btn.getChildren().add(bt2);

              vbox1.getChildren().add(h1);
                  vbox1.getChildren().add(btn);
                  
     bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Parent root ;
                             try {   
       //AnchorPane pane   = FXMLLoader.load(getClass().getResource("detailsguideitem.fxml"));
  
//Stage stage = new Stage();
//stage.setScene(new Scene(pane));
//stage.show();

      FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("detailsguideitem.fxml"));
                     //    loader.setLocation(getClass().getResource("Readdetailsguide.fxml"));
                        AnchorPane pp = loader.load();
                      DetailsguideitemController shev = loader.getController();
                      // ReaddetailsguideController shev=loader.getController();
                        a.getChildren().setAll(pp);
                         shev.setEvenement(g1);
                        shev.showEv();
    
  /*  root = FXMLLoader.load(getClass().getResource("detailsguideitem.fxml"));
    root = FXMLLoader.load(getClass().getResource("Readdetailsguide.fxml"));
            Stage myWindow =  (Stage) bt2.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Les details");
            myWindow.show();  */

    } catch(Exception e)
    {
     System.out.println("eer");
}
                }});   
        
       }}

    
    
    
  /*  private List<Guide> fetchDateCreation() throws SQLException {
        try {
            return ServiceGuide.TrierParDateCreation();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
 
*/
   /* private void loadDateCreation() throws SQLException {
        List<Guide> guide = fetchDateCreation();
        Node[] nodes = new Node[guide.size()];

        AtomicInteger i = new AtomicInteger(0);
        guide.forEach(sujet -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                displaySujetDetails(node, sujet);
            } catch (SQLException ex) {
                Logger.getLogger(AffichageForumController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, sujet, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }
*/
    
    
    
    
    
    
    
    
    
    @FXML
    private void trier_par_date_creation(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
       // Guide g1=new Guide();
        le=sg.searchByName(rech.getText());
          for (Guide g1 : le) {
         vbox1.getChildren().clear();
        
    ImageView photo = new ImageView(new Image("http://localhost/pidev/web/images//"+ g1.getPhoto()));
     Button b1=new Button();          
            Label titre = new Label();
            Label description = new Label();
            Label date_creation = new Label();
              Text add = new Text("tittre: ");
            Text ty = new Text("description: ");
            Text dd = new Text("date creation: ");
               titre.setText(g1.getTitre());
              description.setText(g1.getDescription());
            date_creation.setText((g1.getDate_creation()));
                        
             add.setFill(Color.DARKORANGE);
            ty.setFill(Color.DARKORANGE);
            dd.setFill(Color.DARKORANGE);
          // add.setFill(Color.DARKORANGE);
          
            HBox h1 = new HBox();
            HBox btn = new HBox();
 
             h1.setSpacing(10);
            photo.setFitHeight(200);
           photo.setFitWidth(400);
   h1.getChildren().add(photo);
      // h1.getChildren().add(ty);
       h1.spacingProperty();
         h1.getChildren().add(add);
           h1.getChildren().add(titre);
             h1.getChildren().add(dd);
            h1.getChildren().add(date_creation);
            h1.getChildren().add(ty);
                 h1.getChildren().add(description);
              
            
                  vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
    vbox1.setSpacing(20);
    vbox1.setAlignment(Pos.CENTER);
      Button bt2 = new Button("more Détails"); 
                        
                  btn.getChildren().add(bt2);

            vbox1.getChildren().add(h1);
                  vbox1.getChildren().add(btn);
                  sg.searchByName(rech.getText());
     bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                             try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("Detailsguideitem.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
                }});
        
    }
}} 
   
     
     
     
     
     
     
     
     
     
     /* private void affiche_guide(MouseEvent event) {
        
        ServiceGuide srvg = new ServiceGuide();
       srvg.afficherguide();
        srvg.afficherguide().stream().forEach((p) -> {obsguidelist.add(p);});
    
     
    }*/
    

        
