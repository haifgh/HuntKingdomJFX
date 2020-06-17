
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserServices us = new UserServices();
        User         u  = new User("iheb", "iheb");

        us.Authentification(u);

        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("/Views/SlidePane.fxml"));

            drawer.setSidePane(vbox);
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
