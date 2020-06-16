
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import java.net.URL;

import java.sql.Timestamp;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import Models.Commande;
import Models.LigneCommande;

import Services.CommandeServices;
import Services.LigneCommandeServices;
import Services.ProductServices;
import Services.ProductServices1;
import Services.UserServices;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class OrderDetailsController implements Initializable {
    LigneCommandeServices                      lcs = new LigneCommandeServices();
    CommandeServices                           cs  = new CommandeServices();
    @FXML
    private TableColumn<LigneCommande, String> lcprod;
    @FXML
    private TableColumn<LigneCommande, String> lcprix;
    @FXML
    private TableColumn<LigneCommande, String> lcq;
    @FXML
    private TableColumn<LigneCommande, String> lctot;
    @FXML
    private Label                              price;
    @FXML
    private Label                              address;
    @FXML
    private Label                              status;
    @FXML
    private Label                              client;
    @FXML
    private Label                              tel;
    @FXML
    private Label                              date;
    @FXML
    private Label                              deliver;
    @FXML
    private Label                              charge;
    @FXML
    private TableView<LigneCommande>           table;
    @FXML
    private JFXButton                          deliv;
    @FXML
    private JFXButton                          cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void load(Integer i) {
        Commande        c  = cs.FindOne(i);
        UserServices    us = new UserServices();
        ProductServices ps = new ProductServices();

        price.setText(c.getPrixTotal().toString()+" TND");
        address.setText(c.getAddresse());
        status.setText(c.getStatus());
        client.setText(us.findById(c.getUserId()).getUsername());
        tel.setText(c.getTel());
        date.setText(c.getDate().toString());

        if (c.getDateLivraison() != null) {
            deliver.setText(c.getDateLivraison().toString());
        } else {
            deliver.setText("");
        }

        if ("Delivered".equals(c.getStatus())) {
            deliv.setDisable(true);
        } else {
            deliv.setDisable(false);
        }

        if ("Cancelled".equals(c.getStatus())) {
            cancel.setDisable(true);
        } else {
            cancel.setDisable(false);
        }

        charge.setText(c.getChargeId());
        lcq.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
   

        // start of test
        Callback<TableColumn<LigneCommande, String>, TableCell<LigneCommande, String>> cellFactory;
        Callback<TableColumn<LigneCommande, String>, TableCell<LigneCommande, String>> cellFactory1;
Callback<TableColumn<LigneCommande, String>, TableCell<LigneCommande, String>> cellFactory2;
        cellFactory = (TableColumn<LigneCommande, String> param) -> {
                          final TableCell<LigneCommande, String> cell = new TableCell<LigneCommande, String>() {
                              @Override
                              public void updateItem(String Item, boolean empty) {
                                  super.updateItem(Item, empty);

                                  if (empty) {
                                      setGraphic(null);
                                      setText(null);
                                  } else {
                                      LigneCommande p = getTableView().getItems().get(getIndex());

                                      setText(ps.getP(p.getIdProduit()).getNom());
                                  }
                              }
                          };

                          return cell;
                      };
        cellFactory1 = (TableColumn<LigneCommande, String> param) -> {
                           final TableCell<LigneCommande, String> cell = new TableCell<LigneCommande, String>() {
                               @Override
                               public void updateItem(String Item, boolean empty) {
                                   super.updateItem(Item, empty);

                                   if (empty) {
                                       setGraphic(null);
                                       setText(null);
                                   } else {
                                       LigneCommande p = getTableView().getItems().get(getIndex());
                                        setText(ps.getP(p.getIdProduit()).getPrix()+" TND");
                                      
                                   }
                               }
                           };

                           return cell;
                       };
        cellFactory2 = (TableColumn<LigneCommande, String> param) -> {
                           final TableCell<LigneCommande, String> cell = new TableCell<LigneCommande, String>() {
                               @Override
                               public void updateItem(String Item, boolean empty) {
                                   super.updateItem(Item, empty);

                                   if (empty) {
                                       setGraphic(null);
                                       setText(null);
                                   } else {
                                       LigneCommande p = getTableView().getItems().get(getIndex());

                                       setText(p.getPrice().toString()+" TND");
                                   }
                               }
                           };

                           return cell;
                       };
        lcprod.setCellFactory(cellFactory);
        lcprix.setCellFactory(cellFactory1);
        lctot.setCellFactory(cellFactory2);
        // end of test
        table.setItems(lcs.findAll(i));
        deliv.setOnAction(
            event -> {
                c.setDateLivraison(new Timestamp(System.currentTimeMillis()));
                c.setStatus("Delivered");
                cs.update(c);
                this.load(i);
            });
        cancel.setOnAction(
            event -> {
                c.setDateLivraison(null);
                c.setStatus("Cancelled");
                System.out.println(c.getDateLivraison());
                cs.update(c);
                this.load(i);
            });
    }

    @FXML
    private void setCancel(ActionEvent event) {}

    @FXML
    private void setDeliv(ActionEvent event) {}
}


//~ Formatted by Jindent --- http://www.jindent.com
