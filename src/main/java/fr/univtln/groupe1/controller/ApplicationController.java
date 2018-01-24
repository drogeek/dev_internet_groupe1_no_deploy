package fr.univtln.groupe1.controller;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import fr.univtln.groupe1.metier.dynamodb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
@Singleton
@Startup
public class ApplicationController implements Serializable {


    private List<Map<String, AttributeValue>> tListe ;

    private String sNameResto ;

    //    A mettre dans un autre BEAN!!!
    public void logout() throws IOException {

//        Invalide la session et retour au depart
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/connexion.xhtml");
    }

    public void KeyValueDB()
    {
        System.out.println("Test dynamoDB");
//        Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
//
//        logger.info("Test dynamoDB _info");
//        logger.debug("Test dynamoDB _debug");
//        logger.warn("Test dynamoDB _warning");
        dynamodb dynam = new dynamodb();
        dynam.Creation();
    }

    public void AjoutData()
    {
        dynamodb dynam = new dynamodb();
        dynam.ajouterElements(200);
    }

    //log
    public void KeyValueGetItems()
    {
        dynamodb dynam = new dynamodb();
//        dynam.getElementsNom("Fast");
        tListe = dynam.getElementsNom(sNameResto);
    }


    public String getsNameResto() {
        return sNameResto;
    }

    public void setsNameResto(String sNameResto) {
        this.sNameResto = sNameResto;
    }

    public List<Map<String, AttributeValue>> gettListe() {
        return tListe;
    }

    public void settListe(List<Map<String, AttributeValue>> tListe) {
        this.tListe = tListe;
    }

}
