package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Trainer;
import fr.univtln.groupe1.metier.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;

@Table(
        name = "user",
        catalog = "db1",
        schema = "public"
)

@Stateless
@Path("/connect")
@Produces({"application/json", "application/xml"})
@Consumes({"application/json", "application/xml"}) // ?
public class connectEJB {

    @PersistenceContext(
            unitName = "db1"
    )
    private EntityManager em;

    public connectEJB()
    {

    }

    @Path("/test")
    @GET
    @Produces({"text/plain"})
    public String test() {
        return "Ca fonctionne";
    }

    //vérifie si user exist, s oui connect, si non retourne demande confirmation
    @Path("/try/{name}")
    @GET
    public void tryConnect(@PathParam("name") String nom) {
        Trainer trainer = Trainer.builder().name(nom).build();
        TypedQuery<User> query = this.em.createNamedQuery("FIND USER", User.class);
        query.setParameter("nameUser", nom);
        query.setParameter("passUser", "");
        User utilisateur = query.getSingleResult();
//        if (utilisateur != null)
            //connect


    }

    @Path("/add/{name}")
    @POST
    public User addUser(@PathParam("name") String nom) {
        User utilisateur = User.builder().create(nom).build();
        this.em.persist(utilisateur);
        this.em.flush();
        this.em.refresh(utilisateur);
        return utilisateur;
    }

    @Path("/add/{name}/{pwd}")
    @POST
    public User addUser(@PathParam("name") String nom,@PathParam("pwd") String pwd) {
        User utilisateur = User.builder().create(nom,pwd).build();
        this.em.persist(utilisateur);
        this.em.flush();
        this.em.refresh(utilisateur);
        return utilisateur;
    }
}

