package fr.univtln.groupe1.controller;

import fr.univtln.groupe1.ejb.TrainerEJB;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
@Singleton
@Startup
public class ItemController implements Serializable {

    @EJB
    private TrainerEJB trainerEJB;

    @Schedule(minute = "*/10", info = "Nouvel item toutes les 10 minutes")
    public void updateItem(){
        trainerEJB.updateItem();
    }
}
