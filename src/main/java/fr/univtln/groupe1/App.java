package fr.univtln.groupe1;

import fr.univtln.groupe1.ejb.PokemonEJB;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import fr.univtln.groupe1.metier.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class App {


//    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
//            org.apache.log4j.ConsoleAppender.class,
//            PatternLayout.class};

    //Set the logger with the real class name.
//    private static Logger logger= Logger.getLogger(App.class.getName());

//  Log pour tester le deploiement payara
//  Fonctionnement a verifier
    @Inject
PokemonEJB pokemonEJB;

    public static void main(String[] args) {

        Trainer trainer = new Trainer("truc");

    }
}