package fr.univtln.groupe1;


import org.apache.log4j.PatternLayout;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@Singleton
@Startup
public class App {

    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
            org.apache.log4j.ConsoleAppender.class,
            PatternLayout.class};

    //Set the logger with the real class name.
    private static Logger logger= Logger.getLogger(App.class.getName());

//  Log pour tester le deploiement payara
//  Fonctionnement a verifier
    @PostConstruct
    public void test2(){
        System.out.println("Sout de test 2");
        logger.info("Le postConstruc fonctionne(logger)");
    }
}
