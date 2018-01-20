package fr.univtln.groupe1.controller;

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

@Named
@ApplicationScoped
@Singleton
@Startup
public class ApplicationController implements Serializable {

    //    A mettre dans un autre BEAN!!!
    public void logout() throws IOException {

//        Invalide la session et retour au depart
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/connexion.xhtml");
    }
}
