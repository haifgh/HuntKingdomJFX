
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import Models.User;

import Utilities.UserSession;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class SlidePaneController implements Initializable {
    @FXML
    private VBox spane;
    @FXML
    private Pane content;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent p;
        User   u = UserSession.getInstance().getUser();

        if (u.isAdmin()) {
            try {
                p = FXMLLoader.load(getClass().getResource("/Views/SlidePaneAdmin.fxml"));
                content.getChildren().setAll(p);
            } catch (IOException ex) {}
        } else {
            try {
                p = FXMLLoader.load(getClass().getResource("/Views/SlidePaneUser.fxml"));
                content.getChildren().setAll(p);
            } catch (IOException ex) {}
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
