/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Reclamation;
import Models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.mail.MessagingException;

import Services.UserServices;
import Services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class AdminClaimsController implements Initializable {

    @FXML
    private VBox VBR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       readReclamation();
    }    
    public void readReclamation()
    {
        ServiceReclamation sr = new ServiceReclamation();
            
             List<Reclamation> lr = new ArrayList<>();
             
              UserServices su = new UserServices();
               User u = new User();
         
        //    lj = st.afficherjaime(UserSession.getUser().getId())
        lr = sr.afficherReclamationA();
       
        for (Reclamation evv : lr) {
          
         VBox h1 = new VBox();
         HBox h2 = new HBox();
         HBox h3 = new HBox();
         HBox h4 = new HBox();
         VBR.setSpacing(5);
         h1.setSpacing(3);
         h4.setSpacing(4);
          String stylebt = "-fx-background-color: #FFFFFF;";
         String style = "-fx-background-color: #FFFFFF; -fx-padding: 5 ;";
         String stylU = "-fx-font-weight: bold; -fx-font-size: 20px;";
         h1.setStyle(style);
        
         
         u=su.findById(evv.getReclamer());
         Text objet = new Text(evv.getObject());
         Text contenu = new Text(evv.getContenu());
         Text reclamer = new Text(""+u.getUsername());
        
         Button Block = new Button("block");
         Button unblock = new Button("unblock");
          if(u.getEnabled()){
               h4.getChildren().add(Block);
         }else {
               h4.getChildren().add(unblock);
          }
         h1.getChildren().add(reclamer);
         h2.getChildren().add(new Label("object of claim :"));
         h2.getChildren().add(objet);
         h3.getChildren().add(new Label("body of claim :"));
         h3.getChildren().add(contenu);
         h1.getChildren().add(h2);
         h1.getChildren().add(h3);
         h1.getChildren().add(h4);
         VBR.getChildren().add(h1);
      
         
        final int idtarget = u.getId();
         final String mailtarget = u.getEmail();
        Block.setOnAction(event ->{
             try {
                 blockUser(idtarget,mailtarget);
                 System.out.println("blocked");
                 
             } catch (MessagingException ex) {
                 Logger.getLogger(AdminClaimsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         unblock.setOnAction(event ->{
             try {
                 UnblockUser(idtarget ,mailtarget );
                 
             } catch (MessagingException ex) {
                 Logger.getLogger(AdminClaimsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
   
       
        }
    }
    public void blockUser(int u ,String recipient ) throws MessagingException{
        UserServices su = new UserServices();
        su.BlockUserR(u ,recipient);
        VBR.getChildren().clear();
        readReclamation();
       
        
    }
    
    public void UnblockUser(int u , String recipient ) throws MessagingException{
       UserServices su = new UserServices();
       su.UnBlockUserR(u,recipient);
       VBR.getChildren().clear();
       readReclamation();
    }
 
}
