/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Util.JavaMailUtil;
import Util.UserSession;
import entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.ServiceReclamation;
import services.UserServices;

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
         HBox h1 = new HBox();
         VBR.setSpacing(20);
         h1.setSpacing(30);
         u=su.findById(evv.getReclamer());
         Text objet = new Text(evv.getObject());
         Text contenu = new Text(evv.getContenu());
         Text reclamer = new Text(""+u.getUsername());
         Button Block = new Button("block");
         Button unblock = new Button("unblock");
         Button show = new Button("show");
         h1.getChildren().add(new Label("object of claim :"));
         h1.getChildren().add(objet);
         h1.getChildren().add(new Label("body of claim :"));
         h1.getChildren().add(contenu);
         h1.getChildren().add(new Label("target"));
         h1.getChildren().add(reclamer);
         h1.getChildren().add(Block);
         h1.getChildren().add(unblock);
          h1.getChildren().add(show);
         VBR.getChildren().add(h1);
         final int idtarget = u.getId();
          final String mailtarget = u.getEmail();
         Block.setOnAction(event ->{
             try {
                 blockUser(idtarget,mailtarget);
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
        show.setOnAction(event ->showR(evv.getId()));
       
        }
    }
    public void blockUser(int u ,String recipient ) throws MessagingException{
        UserServices su = new UserServices();
        su.BlockUserR(u ,recipient);
       
        
    }
    
    public void UnblockUser(int u , String recipient ) throws MessagingException{
       UserServices su = new UserServices();
       su.UnBlockUserR(u,recipient);
    }
     public void showR(int u){
       ServiceReclamation su = new ServiceReclamation();
       su.showReclamation(u);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/show.fxml"));
           Parent root = loader.load();
            VBR.getScene().setRoot(root); 
        } catch (IOException ex) {
            Logger.getLogger(AdminClaimsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
