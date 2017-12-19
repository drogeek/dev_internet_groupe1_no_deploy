package fr.univtln.groupe1.controller;

import fr.univtln.groupe1.ejb.PokemonEJB;

import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ApplicationScoped
@Singleton
@Startup
public class PokemonController implements Serializable{
    @EJB
    PokemonEJB pokemonEJB;

    @Schedule(hour = "*", minute = "*/10", second = "*", info = "Diminution des niveaux de tous les pokemons toutes les 10 minutes")
    public void decrease_level() throws InterruptedException {
        pokemonEJB.recomputeStats();
    }
}
