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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


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
    private TableColumn<Commande, String> price;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cs= new CommandeServices();
        
        
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        deliveredAt.setCellValueFactory(new PropertyValueFactory<>("DateLivraison"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        address.setCellValueFactory(new PropertyValueFactory<>("Addresse"));
       
         Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory1;
        cellFactory1 = (TableColumn<Commande, String> param) -> {
                           final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                               @Override
                               public void updateItem(String Item, boolean empty) {
                                   super.updateItem(Item, empty);

                                   if (empty) {
                                       setGraphic(null);
                                       setText(null);
                                   } else {
                                       Commande p = getTableView().getItems().get(getIndex());

                                       setText(p.getPrixTotal().toString()+" TND");
                                   }
                               }
                           };

                           return cell;
                       };
         price.setCellFactory(cellFactory1);
        orderstable.setItems(cs.findAll());
    }    
    
}
