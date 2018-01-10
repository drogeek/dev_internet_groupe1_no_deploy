package fr.univtln.groupe1.controller;

import fr.univtln.groupe1.ejb.PokemonEJB;

import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ApplicationScoped
@Singleton
@Startup
public class PokemonController implements Serializable{
    @EJB
    PokemonEJB pokemonEJB;

    @RequestScoped
    private String sItemId="";

    @RequestScoped
    private String sTrainerId="";

    @Schedule(minute = "*/10", info = "Diminution des niveaux de tous les pokemons toutes les 10 minutes")
    public void decrease_level() throws InterruptedException {
        pokemonEJB.recomputeStats();
    }

    public String getsItemId() {
        return sItemId;
    }

    public void setsItemId(String sItemId) {
        this.sItemId = sItemId;
    }

    public String getsTrainerId() {
        return sTrainerId;
    }

    public void setsTrainerId(String sTrainerId) {
        this.sTrainerId = sTrainerId;
    }

    public void useItem(String sItemId, String sPokemonId){
        pokemonEJB.applyItem(Integer.valueOf(sPokemonId), Integer.valueOf(sItemId));
    }
}
