/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Util.UserSession;
import entities.Reclamation;
import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
         h1.getChildren().add(new Label("object of claim :"));
         h1.getChildren().add(objet);
         h1.getChildren().add(new Label("body of claim :"));
         h1.getChildren().add(contenu);
         h1.getChildren().add(new Label("target"));
         h1.getChildren().add(reclamer);
         h1.getChildren().add(Block);
         h1.getChildren().add(unblock);
         VBR.getChildren().add(h1);
        /* btnsupp.setOnAction(event -> DeleteR(evv.getId()));
         btnmod.setOnAction(event -> UpdateR(evv.getId()));*/
        }
    }
}
