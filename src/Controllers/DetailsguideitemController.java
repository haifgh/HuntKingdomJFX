/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commentaire;
import Models.Guide;
import Models.User;
import Models.rate;
import Services.ServiceCommentaire;
import Services.ServiceGuide;
import Services.ServiceLikes;
import Services.ServiceRate;
import Utilities.Connexion;
import Utilities.UserSession;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import sun.awt.SunHints.Value;

/**
 * FXML Controller class
 *
 * @author haifa
 */
public class DetailsguideitemController implements Initializable {

    @FXML
    private AnchorPane a;
    @FXML
    private Text question;
    @FXML
    private JFXButton type_vote_oui;
    @FXML
    private JFXButton type_vote_non;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton addComment;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView img_gui;
    @FXML
    private TextField commentText;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;
 Connection con;   

   ObservableList<Commentaire> data;
 private ObservableList<String> items = FXCollections.observableArrayList();
    int i,i2 ;
    public  int idC ;
    public  int idU ;
    public   int cd;
   String contenu ;
    @FXML
    //private ListView<Commentaire> listcom;
        private ListView<String> listcom;    
    // int idCli=Util.UserSession.getUser().getId();
     ServiceCommentaire cs =new ServiceCommentaire();
        ServiceGuide sg=new ServiceGuide();
        Guide g=new Guide();
        List<Guide> le = new ArrayList<>();
        ImageView photo=new ImageView();
    User f = UserSession.getInstance().getUser();
    ServiceRate sr=new ServiceRate();
    private Guide evs;
    @FXML
    private Rating rating;
      int Value;    
    @FXML
    private Text question1;
    @FXML
    private JFXButton updateComment;
    @FXML
    private JFXButton deleteComment;
    Commentaire c=new Commentaire();
        public void setEvenement(Guide evs) {
        this.evs=evs;
        
    }

    public DetailsguideitemController() {
        con=Connexion.getInstance().getCnx();
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       rating.ratingProperty().addListener(new ChangeListener<Number>() 
          {            
              @Override 
              
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                  Value = newValue.intValue();
                System.out.println(Value);
            }
     

            
       });}
          
     

          
        
        
        
        
         /*System.out.println(""+idG);
       for (Guide g1 : le) {
          
           if  (idG==g1.getId()){
                System.err.println("okiiiiiiiiiiii"+ sg.findGuideById(idG).getPhoto());
                
                question.setText(sg.findGuideById(idG).getTitre());
                
                img_gui.setImage(new Image("http://localhost/pidev/web/images//" + sg.findGuideById(idG).getPhoto()));
                try {
                    data = cs.getAllCommentByGuide(sg.findGuideById(idG));
                } catch (SQLDataException ex) {
                    Logger.getLogger(DetailsguideitemController.class.getName()).log(Level.SEVERE, null, ex);
                }   
         listcom.setItems(data);
      //  listcom.setCellFactory((ListView<Commentaire> param) -> new ListViewCommentEvent());
            } else 
              System.out.println("erreur");*/
              
       
       
    
    public void showEv() throws SQLException {
    
    ServiceGuide es=new ServiceGuide();
        question.setText(evs.getTitre());
         img_gui.setImage(new Image("http://localhost/pidev/web/images//" + evs.getPhoto()));
        question1.setText(evs.getDescription());
        //data=cs.getAllCommentByGuide(evs);
        listcom.setItems(items);
         listerCategorie(evs);
      //  listcom.setItems(data);
    }
    public void listerCategorie( Guide g)
    {
        
         try{
            this.data = FXCollections.observableArrayList();
            
        
           List<Commentaire> list=new ArrayList<Commentaire>();
             int c=0;
             String req="select * from commentaire where guide_id="+g.getId();
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery(req);
            while(rs.next())
            {
               //items.add((rs.getString(1)));
               //items.add((rs.get(1)));
               // String b = rs.getString(2);
              // System.out.println(rs.getString(2));
              //Commentaire cm=new Commentaire();
                 
                 
                 items.add(rs.getString(4));
              //  items.add(rs.getString(5));
               
            }
            
            st.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void Like(ActionEvent event) throws SQLException {
        ServiceLikes sl=new ServiceLikes();
     //Likes l = new Likes();
       // l.setId_user(8);
        //l.setId_guide(idG);
       // sl.ajouterJaime(l);
       sl.likerArticle(evs.getId(), f.getId());
       
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("Your Like has been registred ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           
        }
    

    @FXML
    private void Dislike(ActionEvent event) throws SQLException {
         ServiceLikes sl=new ServiceLikes();
     //Likes l1 = new Likes();
        sl.DislikerArticle(evs.getId());
      //  l1.setId_user(8);
        //l1.setId_guide(23);
      // sl.likerArticle(idG, idCli);
        //sl.supprimerJaime();
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("your Dislike has been registred").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
         
        
    }

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void AddComment(ActionEvent event) throws IOException {
       // Guide g=new Guide();
       if (!commentText.getText().equals("")||commentText.getText().length()>10){
         Commentaire c=new Commentaire(evs.getId(),f.getId(),commentText.getText());
         cs.ajoutercommentaire(c);
         System.out.println("OK");
        
           cd=f.getId();
           System.out.println("l'id de user"+cd);
            SuccesNotification();}
            else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Dialog");
       
   alert.setContentText("Sorry empty field !");

        alert.showAndWait();
    }}

    @FXML
    private void backtoGuide(ActionEvent event) {
     try {
            AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Views/Showguideuser.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
     private void SuccesNotification() {
        Notifications notificationBuilder = Notifications.create()
        .title("Success :)")
                .text("Operation Succeeded")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                   @Override
                            public void handle(ActionEvent event){
                    System.out.println("TEST");
                }
                        });
                      
        notificationBuilder.showConfirm();  
    }
    private void ErrorNotification() {
        Notifications notificationBuilder = Notifications.create()
        .title("Fail :(")
                .text("Operation Failed")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                   @Override
                            public void handle(ActionEvent event){
                    System.out.println("TEST");
                }
                        });
        notificationBuilder.showError();       
    }

    @FXML
    private void AddRate(MouseEvent event) throws SQLException {
        
        rate r=new rate();
       //if (!commentaire.getText().equals("")|| Value !=0) {
       r.getId();
       r.setNote(Value);
       r.setId_user(f.getId());
       r.setId_guide(evs.getId());
       //r.getId_guide();
       //    Commentaire c = null;
       //com.ajoutercommentaire(c);
       sr.ajouterRate(r);
           Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("Done")
                .text("thank you ")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
                    System.out.println("you clicked me");
                });
        notificationBuilder.show();
       
       }//else{
          //Alert alert = new Alert(Alert.AlertType.ERROR);
         //alert.setTitle("Dialog");
       
   //alert.setContentText("remplir votre champs!");

     //   alert.showAndWait();

    @FXML
    private void updateComment(ActionEvent event) throws SQLException {
    if(commentText.getText().isEmpty())
     {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("Le champ catégorie doit contenir une valeur");
 
        alert.showAndWait();
     }
        
        
        else if(a.equals(commentText.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mise à jour catégorie");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez effectué aucun changement");
                alert.showAndWait();
                // clearData(event);
                 refreshList();
        }
     else
        {
           
              if(idU==f.getId()){  
               cs.modifiercommentaireUser(commentText.getText(), idC);
               
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mise à jour catégorie");
                alert.setHeaderText(null);
                alert.setContentText("Catégorie mis à jour avec succès");
                alert.showAndWait();
                // clearData(event);
                 refreshList();
        }else{
                         Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("erreur");
                alert3.setHeaderText(null);
                alert3.setContentText("erreur");
                    }
    }}
   
    @FXML
    private void deleteComment(ActionEvent event) {
        
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression d'une catégorie");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer ?");
               // alert.showAndWait();
                //clearData(event);

                 Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK){
                       Commentaire com=new Commentaire(evs.getId(), f.getId(),commentText.getText() );
                    if(idU==f.getId()){
                       cs.removeCommentuser(idC);
                       refreshList();
                     //   System.out.println(cd);
                    } else {
                         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("erreur");
                alert1.setHeaderText(null);
                alert1.setContentText("erreur");
                    }}else{
                    
                 }
                 
    
   
    }
    public void setFILES(String Body, String Body1, String Body2, String Body3) {
       
        try {

            OutputStream file = new FileOutputStream(new File(evs.getTitre()+".pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();
            document.addTitle("Ticket");

            //com.itextpdf.text.Image img;
            //img = com.itextpdf.text.Image.getInstance("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/louay.jpg");
            //com.itextpdf.text.Image.getInstance(img);
            //document.add(img);
            document.add(new Paragraph("                    "));
            document.add(new Paragraph("                    "));

            document.add(new Paragraph(Body));
            document.add(new Paragraph(Body1));
            document.add(new Paragraph(Body2));
            document.add(new Paragraph(Body3));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();

        }}
         

    
    public void btnPDF(String Body, String Body1, String Body2, String Body3) throws IOException, SQLException {
        //User userTest = new User(102, "Louay", "Yahyaoui", "louay@esprit.tn", "louay", "oussema", "male", "28-08-1992", 234223878, "SimpleUtilisateur", "Comedie", "hahaha", 5000);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Button button2 = new Button();
        button2.setStyle("-fx-background-color: #00ff00");
        alert.setTitle("PDF ");
        alert.setContentText("Participation avec succés !  vous voulez exporter votre ticket en PDF ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            setFILES(Body, Body1, Body2, Body3);

        } else {

        }
    }
    

    @FXML
    private void generatepdf(ActionEvent event) throws SQLException {
        String Body = "Bonjour Mr/Mme "+evs.getLien()+" je vous souhaite la bienvenue ";
                                    String Body1 = "Au sein de notre Evenement intutilé ---";
                                    String Body2 = "Qui aura lieu le  ";
                                    String Body3 = "";
                                    try {
                                        btnPDF(Body, Body1, Body2, Body3);
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
    }

    @FXML
    private void Selec(MouseEvent event) {
        try {
                    String query = "select * from commentaire where contenu=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, (String)listcom.getSelectionModel().getSelectedItem());
                    ResultSet rs = pst.executeQuery();
                    
                    while(rs.next())
                    {
                     commentText.setText(rs.getString("contenu"));
                     contenu=rs.getString("contenu");
                    //   c = rs.getString("contenu");
                        idC = rs.getInt(1);
                        System.out.println(idC);
                        idU=rs.getInt(3);
                        System.out.println(idU);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    public void refreshList()
 {
   items.clear();
   listerCategorie(evs);
 }
    }
    

