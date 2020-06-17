
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Controllers;

import Models.Commande;
import Services.CommandeServices;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.stage.Stage;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import Services.PanierServices;

/**
 *
 * @author rejeb
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private WebView   webView;
    private WebEngine webEngine;
    String            address;
    String            tel;

    private void createCharge(String token) throws StripeException {
        Stripe.apiKey = "sk_test_UAe411cwz5wIeEJMQW8cXSgs00whcLY6Pw";

        PanierServices pas = new PanierServices();
        CommandeServices cs = new CommandeServices();
        Commande c =pas.validerPanier(this.address, this.tel);
        
        ChargeCreateParams params = ChargeCreateParams.builder()
                                                      .setAmount(c.getPrixTotal().longValue()*100)
                                                      .setCurrency("usd")
                                                      .setDescription("from java")
                                                      .setSource(token)
                                                      .build();
        Charge charge = Charge.create(params);
        c.setChargeId(charge.getId());
        cs.update(c);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.setOnAlert(
            event -> {
                try {
                    showAlert(event.getData());
                } catch (StripeException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        webEngine.load(getClass().getResource("/Controllers/index.html").toString());
    }

    private void showAlert(String data) throws StripeException {
        System.out.println(data);
        createCharge(data);

        Stage stage = (Stage) webView.getScene().getWindow();

        stage.close();
    }

    public void setAddress(String a) {
        this.address = a;
    }

    public void setTel(String a) {
        this.tel = a;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
