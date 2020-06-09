/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commentaire;
import Models.Guide;
import Models.User;
import Services.ServiceCommentaire;
import Services.ServiceGuide;
import Utilities.UserSession;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author haifa
 */
public class AddCommentController implements Initializable {
 List<Guide> listguide;
    ObservableList<Integer> listidguide;
    Guide guide;
    //List<Guide> listCo;
    @FXML
    private JFXComboBox combobox;
     User f = UserSession.getInstance().getUser();
    @FXML
    private JFXTextField comf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void AddComm(ActionEvent event) {
        Guide selectedGuide = listguide.get(combobox.getSelectionModel().getSelectedIndex());
        //selectedGuide.getid()
        Commentaire c=new Commentaire(selectedGuide.getId(),f.getId() ,comf.getText() );
        ServiceCommentaire svc=new ServiceCommentaire();
        if(!comf.getText().equals("")){
           // !combobox.getSelectionModel().isEmpty()
        svc.ajoutercommentaire(c);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Dialog");
       
   alert.setContentText("Sorry empty field !");

        alert.showAndWait();
        }
    }
     public void loadData() {
      
         ServiceGuide coachservice = new ServiceGuide();
       listguide = coachservice.afficherguide();
        listidguide = FXCollections.observableArrayList();
        listidguide.addAll(listguide.stream().map(guide -> guide.getId()).collect(Collectors.toList()));
        combobox.setItems(listidguide);
    }

}
