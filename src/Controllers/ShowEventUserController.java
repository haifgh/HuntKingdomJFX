
package Controllers;


import Models.Evenement;
import Models.ListeParticipants;
import Models.User;
import Services.ServiceEvenement;
import Services.ServiceListeParticipants;
import Services.UserServices;
import Utilities.UserSession;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yosrio
 */
public class ShowEventUserController implements Initializable  {

    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private TableColumn<Evenement, String> nomEvent;
    @FXML
    private TableColumn<Evenement, String> descriptionEvent;
    @FXML
    private TableColumn<Evenement, Integer> colNbrPlace;
    @FXML
    private TableColumn<Evenement, Integer> placeRestante;
    @FXML
    private Button btnParticiper;
    @FXML
    private Label nomLabel;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label imageLabel;
    
   
    private final ServiceListeParticipants serviceInscription = new ServiceListeParticipants();
    private final ServiceEvenement serviceEvenement = new ServiceEvenement ();
   

    private Evenement selectedEvenement;
    private List<ListeParticipants> selectedEvenementInscriptions = new ArrayList<>();
    
    UserServices us = new UserServices();
    User u  = new User("Yuri", "yuri");
    
    private final static String ADD_TEXT = "partcipe";
    private final static String EDIT_TEXT = "annule";
  

    List<Evenement> evenements;

    public ShowEventUserController() throws SQLException {
        this.evenements = serviceEvenement.readAll();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    this.hideEvenementDetails();
      
      this.initTableCols();
        addTableSelectionListener();
        this.btnParticiper.setText(ADD_TEXT);
        this.btnAnnuler.setText(EDIT_TEXT);  
        try {
            this.displayTableEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(ShowEventUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void refreshTable() {
        tableEvent.refresh();
    }

    private void displayTableEvenements() throws SQLException {
        evenements = serviceEvenement.findAllwithInscriptionsCount();
        ObservableList<Evenement> obsList= FXCollections.observableArrayList(evenements);
        tableEvent.setItems(obsList);
        tableEvent.refresh();
        

    }
    
    private void initTableCols() {
        nomEvent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionEvent.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));
        placeRestante.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Evenement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Evenement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNbre_places() - param.getValue().getNbrInscriptions());
            }
        });
       

    }
    
     public void filterTableData(String filterText) {
        ObservableList<Evenement> obList = FXCollections.observableArrayList(evenements);
        FilteredList<Evenement> filteredData = new FilteredList<>(obList);

        filteredData.setPredicate(evenement -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = filterText.toLowerCase();
            if (evenement.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableEvent.comparatorProperty());
        tableEvent.setItems(sortedData);
    }

    @FXML
    private void participer(ActionEvent event) throws SQLException {
        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            ListeParticipants inscription = new ListeParticipants();
            inscription.setUser(this.u);
           
            inscription.setEvenement(selectedEvenement);
            inscription.setDateParticipation( new Date(Calendar.getInstance().getTimeInMillis()));
            this.serviceInscription.saveInscription(inscription);
            
            
            displayTableEvenements();
            this.selectedEvenement = this.evenements.stream().filter(e->e.getId()== id).findAny().get();
            this.setSelectedEvenement(selectedEvenement);

            
        }
    }

    @FXML
    private void annule(ActionEvent event) throws SQLException {
        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            ListeParticipants inscription = selectedEvenementInscriptions
                    .stream()
                    .filter(i -> i.getUser().getId() == UserSession.getInstance().getUser().getId())
                    .findFirst()
                    .get();
            serviceInscription.delete(inscription);
            displayTableEvenements();
            this.selectedEvenement = this.evenements.stream().filter(e->e.getId()== id).findAny().get();
            this.setSelectedEvenement(selectedEvenement);

        }
    }
    
    private void addTableSelectionListener() {
        tableEvent.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Evenement evenement = (Evenement) tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
            setSelectedEvenement(evenement);
        });
    }
    
    public void setSelectedEvenement(Evenement evenement) {
        selectedEvenement = evenement;
        selectedEvenementInscriptions = serviceInscription.findInscriptionsByEvenementId(evenement.getId());
       

        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            nom.setText(selectedEvenement.getNom());
            description.setText(selectedEvenement.getDescription());
            image.setImage(new Image("http://localhost/pidev/web/images/" + selectedEvenement.getPhoto() ));
        }
        this.showEvenementDetails();

        boolean isAlreadySubscribed = selectedEvenementInscriptions.stream().anyMatch(inscription -> inscription.getUser().getId() == UserSession.getInstance().getUser().getId());
        boolean isFull = selectedEvenement.getNbre_places()<= selectedEvenement.getNbrInscriptions();
        this.btnParticiper.setDisable(isAlreadySubscribed || isFull);
        this.btnAnnuler.setDisable(!isAlreadySubscribed);

    }
    
    private void showEvenementDetails() {
        nomLabel.setVisible(true);
        nom.setVisible(true);
        description.setVisible(true);
        descriptionLabel.setVisible(true);
        imageLabel.setVisible(true);
        btnAnnuler.setVisible(true);
        btnParticiper.setVisible(true);
        image.setVisible(true);

    }

    private void hideEvenementDetails() {
        nomLabel.setVisible(false);
        nom.setVisible(false);
        description.setVisible(false);
        descriptionLabel.setVisible(false);
        imageLabel.setVisible(false);
        btnAnnuler.setVisible(false);
        btnParticiper.setVisible(false);
        image.setVisible(false);
        
    }
    
}
