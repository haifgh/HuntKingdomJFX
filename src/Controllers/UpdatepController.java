/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import Services.ServicePost;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class UpdatepController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button update;
    @FXML
    private JFXTextField contenu;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    }
    
    public void Update (int id,VBox x, VBox y)  {
  //    int id = Update (id);
   update.setOnAction(event ->  {
       try {
           UpdateA (id,x,y);
       } catch (IOException ex) {
          
       }
   });
      
    }
     
    @FXML 
 public void UpdateA (int id,VBox x, VBox y) throws IOException  {
    ServicePost sp = new ServicePost();
    String m = contenu.getText();
    sp.modifierpost(id, m);
    x.getChildren().clear();
    y.getChildren().clear();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/homepage.fxml"));
     homeController cont = loader.getController();
     cont.ReadPost();
        System.out.println("ok");
    }
 
     public void start(ActionEvent t) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/homepage.fxml"));
         Parent root1 = (Parent)loader.load();
        Stage stage = new Stage ();
        stage.setScene( new Scene(root1));
        stage.show();
        onMouseClickedCancelBtn(t);
    }
    
    
   @FXML 
   public void onMouseClickedCancelBtn(Event e) {
       Window window =   ((Node)(e.getSource())).getScene().getWindow();
       if (window instanceof Stage){
           ((Stage) window).close();
       }
    
         
}
    
    
    
}
