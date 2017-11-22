package fr.univtln.groupe1.ejb;


import fr.univtln.groupe1.metier.Pokemon;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@javax.persistence.Table(name = "Pokemon", catalog = "db1", schema = "public")
@Stateless
public class PokemonEJB {

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

    public Pokemon addPokemon(Pokemon pokemon){
        EntityManager em = emf.createEntityManager();
        em.persist(pokemon);
        em.flush();
        em.refresh(pokemon);
        return pokemon;
    }
}
