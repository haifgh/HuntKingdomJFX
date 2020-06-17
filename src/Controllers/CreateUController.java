/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import Services.UserServices;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class CreateUController implements Initializable {
@FXML
    private AnchorPane anac;

    @FXML
    private JFXPasswordField CtextPass;

    @FXML
    private JFXTextField Ctextuser;

    @FXML
    private JFXTextField Ctextmail;

    @FXML
    private Button Csignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
     private void Create(ActionEvent event) throws IOException {
        
        UserServices srv = new UserServices();
        User e = new User();
        e.setUsername(Ctextuser.getText());
        e.setUsernameCanonical(Ctextuser.getText());
        e.setPassword(CtextPass.getText());
        e.setEmail(Ctextmail.getText());
        e.setEmailCanonical(Ctextmail.getText());
        srv.create(e);
        srv.Authentification(e);
        JOptionPane.showMessageDialog(null, " ok !");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/login.fxml"));
         Parent root = loader.load();
         Ctextuser.getScene().setRoot(root);
       
        
    }
    
}
