/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commande;
import Services.CommandeServices;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author rejeb
 */
public class OrdersForUserController implements Initializable {

    private CommandeServices cs;
    @FXML
    private AnchorPane orders;
    @FXML
    private TableView<Commande> orderstable;
    @FXML
    private TableColumn<Commande, Timestamp> date;
    @FXML
    private TableColumn<Commande, Timestamp> deliveredAt;
    @FXML
    private TableColumn<Commande, String> status;
    @FXML
    private TableColumn<Commande, String> address;
    @FXML
    private TableColumn<Commande, Double> price;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cs= new CommandeServices();
        
        
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        deliveredAt.setCellValueFactory(new PropertyValueFactory<>("DateLivraison"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        address.setCellValueFactory(new PropertyValueFactory<>("Addresse"));
        price.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
        orderstable.setItems(cs.findAll());
    }    
    
}
