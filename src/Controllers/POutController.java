/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utilities.Connexion;
import Models.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Interfaces.IserviceProduct;

/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class POutController implements Initializable {

    @FXML
    private TableView<Produit> Table_produit;
    @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<?, ?> prix_col;
    @FXML
    private TableColumn<?, ?> promo;
    @FXML
    private TableColumn<?, ?> qte_col;
    @FXML
    private TableColumn<?, ?> description_col;


    private ObservableList<Produit> data;
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Connexion conn;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private ImageView ImageView;
    @FXML
    private JFXTextField tfsearchbyid;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private ImageView logo;
    @FXML
    private TableColumn<?, ?> catid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Connexion.getInstance();
        try {
            InitPOut();
        } catch (SQLException ex) {
            Logger.getLogger(POutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   
    @FXML
    public boolean DeleteProduct(ActionEvent t) throws SQLException {
    conn = Connexion.getInstance();

        int selectedIndex = Table_produit.getSelectionModel().getSelectedIndex();
        Produit m = (Produit) Table_produit.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getNom()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = Connexion.getInstance();
                String query1 = "DELETE FROM produit WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_produit.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }

   
    @FXML
    public boolean UpdateProduct(ActionEvent t) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ProductUPDATE.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            
            Stage stage = new Stage();
            stage.setTitle("UPDATE");
            
            ProductUPDATEController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_produit.getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(POutController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }

    
    public void InitPOut() throws SQLException {
        conn = Connexion.getInstance();

        try {

            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit WHERE qte=0";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = Connexion.getInstance();
                //data.addAll(data);
                data.add(new Produit(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        catid.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));  
        promo.setCellValueFactory(new PropertyValueFactory<>("prix_promo"));
        description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        qte_col.setCellValueFactory(new PropertyValueFactory<>("qte"));

        Table_produit.setItems(null);
        Table_produit.setItems(data);
    }

   
    @FXML
    public void search(ActionEvent t) {
                ObservableList data = Table_produit.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_produit.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Produit> subentries = FXCollections.observableArrayList();

            long count = Table_produit.getColumns().stream().count();
            for (int i = 0; i < Table_produit.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_produit.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_produit.getItems().get(i));
                        break;
                    }
                }
            }
            Table_produit.setItems(subentries);

        });
    }

    

    @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
               conn= Connexion.getInstance();
        int indexSelected = Table_produit.getSelectionModel().getSelectedIndex();
        Produit s = (Produit) Table_produit.getSelectionModel().getSelectedItem();
         conn= Connexion.getInstance();
        int id = s.getId();



        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit where produit.id='"+id+"'";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                       conn= Connexion.getInstance();
                String lien = rs.getString("photo");
                System.out.print(lien);

                String lien2 = lien.replaceAll("//", "/");

                File file = new File(lien2);
                String localUrl = file.toURI().toURL().toString();

                Image image = new Image(localUrl);
                ImageView.setImage(image);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/Views/POut.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
  

}
