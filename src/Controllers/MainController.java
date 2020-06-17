
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.VBox;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import Models.User;

import Services.UserServices;
import Utilities.UserSession;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class MainController implements Initializable {
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXDrawer    drawer;
    @FXML
    private AnchorPane main;
    @FXML
    private HBox content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("/Views/SlidePane.fxml"));

            drawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p;    
        try {
            if(UserSession.getInstance().getUser().isAdmin()){
                p = FXMLLoader.load(getClass().getResource("/Views/PieChart.fxml"));
            content.getChildren().setAll(p);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(burger);

        burger.setVisible(false);
        transition.play();
        drawer.open();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
