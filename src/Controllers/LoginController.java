/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.UserServices;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField textuser;
    @FXML
    private JFXPasswordField textpass;
   
    @FXML
    private AnchorPane ana;
    @FXML
    private Button btnSignup;
    @FXML
    private Label btnForgot;
    @FXML
    private Button signup;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void login() throws IOException {
        
        UserServices srv = new UserServices();
        User e = new User();
        e.setUsername(textuser.getText());
        e.setPassword(textpass.getText());
        srv.Authentification(e);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homepage.fxml"));
         Parent root = loader.load();
         textuser.getScene().setRoot(root);
        
    }
   
    @FXML
     private void loadCreate(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CreateU.fxml"));
         Parent root = loader.load();
         textuser.getScene().setRoot(root);
     }
     
}
