/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commentaire;
import Services.ServiceCommentaire;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author haifa
 */
public class UpdateCommentController implements Initializable {

    @FXML
    private JFXTextField comf;

   Commentaire c =new Commentaire();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateComm(ActionEvent event) throws SQLException {
       
            c.setContenu(comf.getText());
           
                c.setId(c.getId());
       //         c.setDate(c.getDate());
         //       c.setGuide_id(c.getGuide_id());
           //     c.setUser_id(c.getUser_id());
        System.out.println(c.getId());       


     
        System.out.println(this.c.getId());
        ServiceCommentaire svc=new ServiceCommentaire();
         if(!comf.getText().equals("")){
   svc.modifiercommentaire(c);
   }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Dialog");
       
   alert.setContentText("Sorry empty field !");

        alert.showAndWait();
        }
  //svc.updateContrat(c.getId(), comf.getText());
    }

    void setData(int guide_id, int user_id, String contenu, String date, int id) {
          c.setId(id);
          c.setDate(date);
          c.setUser_id(user_id);
          c.getGuide_id();
          comf.getText();
    }
    
  
   
}
