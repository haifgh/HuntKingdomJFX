
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import Models.Produit;

import Services.PanierServices;
import Services.ProductServices;

import Utilities.UserSession;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class ShopController implements Initializable {
    PanierServices                        pas = new PanierServices();
    @FXML
    private AnchorPane                    shop;
    ProductServices                       ps;
    @FXML
    private TableView<Produit>            shoptable;
    @FXML
    private TableColumn<Produit, String>  name;
    @FXML
    private TableColumn<Produit, Integer> price;
    @FXML
    private TableColumn                   add;
    @FXML
    private Label                         total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ps = new ProductServices();
        name.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        price.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        total.setText(pas.getPrixPanier().toString());

        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFactory;

        cellFactory = (TableColumn<Produit, String> param) -> {
                          final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                              @Override
                              public void updateItem(String Item, boolean empty) {
                                  super.updateItem(Item, empty);

                                  if (empty) {
                                      setGraphic(null);
                                      setText(null);
                                  } else {

                                      // action button here
                                      final JFXButton addButton = new JFXButton("add");

                                      addButton.getStyleClass().add("custom-button");
                                      addButton.getStyleClass().add(".jfx-button");
                                      addButton.setOnAction(
                                          event -> {
                                              Produit p = getTableView().getItems().get(getIndex());

                                              UserSession.getInstance().addToPanier(p.getId(), 1);
                                              total.setText(pas.getPrixPanier().toString());
                                          });
                                      setGraphic(addButton);
                                  }
                              }
                          };

                          return cell;
                      };
        add.setCellFactory(cellFactory);
        shoptable.setItems(ps.findAll());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
