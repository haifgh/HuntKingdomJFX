
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import Models.Commande;
import Models.LigneCommande;

import Services.CommandeServices;
import Services.LigneCommandeServices;

/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class OrderDetailsController implements Initializable {
    LigneCommandeServices                      lcs = new LigneCommandeServices();
    CommandeServices                           cs  = new CommandeServices();
    @FXML
    private TableColumn<LigneCommande, String> lcid;
    @FXML
    private TableColumn<LigneCommande, String> lcprod;
    @FXML
    private TableColumn<LigneCommande, String> lcprix;
    @FXML
    private TableColumn<LigneCommande, String> lcq;
    @FXML
    private TableColumn<LigneCommande, String> lctot;
    @FXML
    private Label                              id;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void load(Integer i) {
        Commande c = cs.FindOne(i);

        id.setText(c.getId().toString());
        price.setText(c.getPrixTotal().toString());
        address.setText(c.getAddresse());
        status.setText(c.getStatus());
        client.setText(c.getUserId().toString());
        tel.setText(c.getTel());
        date.setText(c.getDate().toString());

        if (c.getDateLivraison() != null) {
            deliver.setText(c.getDateLivraison().toString());
        }

        charge.setText(c.getChargeId());
        lcid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        lcprod.setCellValueFactory(new PropertyValueFactory<>("IdProduit"));
        lcprix.setCellValueFactory(new PropertyValueFactory<>("Price"));
        lcq.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        lctot.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.setItems(lcs.findAll(i));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
