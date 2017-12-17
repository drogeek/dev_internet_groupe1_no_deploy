package fr.univtln.groupe1.controller;

import fr.univtln.groupe1.ejb.TrainerEJB;
import fr.univtln.groupe1.metier.Pokemon;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TrainerController implements Serializable {

    @EJB
    private TrainerEJB trainerEJB;

    @SessionScoped
    private List<Pokemon> pokemonList = new ArrayList<>();

    @SessionScoped
    private String sTrainerId ="";

    @RequestScoped
    private String sTrainerLendId="";

    @RequestScoped
    private String sPokemonName = "";

    public String createPokemon(){
        trainerEJB.addPokemonTrainer(Integer.valueOf(sTrainerId), sPokemonName);
        getPokemons();
        return "add_accepted";
    }

    public void lendPokemon(int pokemonID){
        trainerEJB.lendPokemon(Integer.valueOf(sTrainerLendId), pokemonID);
        getPokemons();
    }

    public void lendPokemon(int idOwner, int pokemonID){
        trainerEJB.lendPokemon(idOwner, pokemonID);
        getPokemons();
    }


    public void getPokemons (){
        pokemonList = trainerEJB.listPokemon(Integer.valueOf(sTrainerId));
    }

    public String getsTrainerId() {
        return sTrainerId;
    }

    public void setsTrainerId(String sTrainerId) {
        this.sTrainerId = sTrainerId;
    }

    public String getsTrainerLendId() {
        return sTrainerLendId;
    }

    public void setsTrainerLendId(String sTrainerLendId) {
        this.sTrainerLendId = sTrainerLendId;
    }

    public String getsPokemonName() {
        return sPokemonName;
    }

    public void setsPokemonName(String sPokemonName) {
        this.sPokemonName = sPokemonName;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }


//    A mettre dans un autre BEAN!!!
    public void logout() throws IOException {

//        Invalide la session et retour au depart
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/connexion.xhtml");
    }

//   Il faudrait une vérification du mot de passe
    public String connexion(){
//        On charge sa liste de pokemon
        getPokemons();
//        Normalement on check le mot de passe
        return "accepted";
    }

    public String add_pokemon_click(){
        return "createPokemon";
    }
}
