
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

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import com.jfoenix.controls.JFXTabPane;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class SlidePaneAdminController implements Initializable {
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private AnchorPane user;

    @FXML
    private void goToOrders(ActionEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/OrdersForAdmin.fxml"));
        HBox   content = (HBox) tabPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent p;

        try {
            p = FXMLLoader.load(getClass().getResource("/Views/SlidePaneUser.fxml"));
            user.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(SlidePaneAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load_promote(MouseEvent event) throws IOException {
        Parent p       = FXMLLoader.load(getClass().getResource("/Views/Promote1.fxml"));
        HBox   content = (HBox) tabPane.getParent().getParent().getParent().getParent().getScene().lookup("#content");

        content.getChildren().setAll(p);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
