/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Categorie;
import Services.CategorieServices;
import Services.ProductServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class PieChartController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private ImageView logo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductServices ps = new ProductServices();
        CategorieServices cs = new CategorieServices();
        Categorie c1 = new Categorie();
        Categorie c2 = new Categorie();
        Categorie c3 = new Categorie();
        c1 = cs.findByName("Hunting");
        c2 = cs.findByName("Fishing");
        c3 = cs.findByName("Camping");
        int i1 = (ps.ProdByCat(c1.getId()).size());
        int i2 = (ps.ProdByCat(c2.getId()).size());
        int i3 = (ps.ProdByCat(c3.getId()).size());
        ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList(
        new PieChart.Data("Hunting",i1),new PieChart.Data("Fishing",i2),new PieChart.Data("Camping",i3));
       pieChart.setData(pieChartData);
       pieChart.setLabelLineLength(10);
       pieChart.setLegendSide(Side.LEFT);
       final Label caption = new Label("");
       caption.setTextFill(Color.DARKORANGE);
       caption.setStyle("-fx-font: 24 arial;");

       for (final PieChart.Data data : pieChart.getData()) {
       data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
             }
        });
        
        
    }    
    
}}
