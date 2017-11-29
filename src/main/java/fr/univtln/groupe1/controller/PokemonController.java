package fr.univtln.groupe1.controller;

import fr.univtln.groupe1.ejb.PokemonEJB;
import fr.univtln.groupe1.ejb.TrainerEJB;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PokemonController {

    @Inject
    private TrainerEJB trainerEJB;

    private String sTrainerId ="";
    private String sPokemonName = "";

    public void createPokemon(){
        trainerEJB.addPokemonTrainer(Integer.valueOf(sTrainerId), sPokemonName);
    }

    public String getsTrainerId() {
        return sTrainerId;
    }

    public void setsTrainerId(String sTrainerId) {
        this.sTrainerId = sTrainerId;
    }

    public String getsPokemonName() {
        return sPokemonName;
    }

    public void setsPokemonName(String sPokemonName) {
        this.sPokemonName = sPokemonName;
    }
}
