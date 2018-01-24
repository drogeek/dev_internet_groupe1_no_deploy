package fr.univtln.groupe1.metier;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.*;


import java.util.*;
import java.util.logging.Logger;

public class dynamodb {

    DynamoDB database;
    Logger LOG;

    public void Creation() {
        //test plutot que d'utiliser persistence.xml
        //    AmazonDynamoDB Client = AmazonDynamoDBClientBuilder.standard().build();
        BasicAWSCredentials crede = new BasicAWSCredentials("admin","admin");
        AmazonDynamoDB Client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(crede))
                .build();

        database = new DynamoDB(Client);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG =Logger.getLogger(dynamodb.class.getName());

        String sNomTable = "Restaurants";

        LOG.info("Création de la table "+sNomTable+" en cours.");
//        System.out.println("Création de la table "+sNomTable+" en cours.");
        try {
            Table table = database.createTable(sNomTable,
                    Arrays.asList(new KeySchemaElement("Adresse", KeyType.HASH), // adresse, élément majeur de la clé
                            new KeySchemaElement("Nom", KeyType.RANGE)), //tri plus simple pour afficher les magasins d'une chaine
                    Arrays.asList(new AttributeDefinition("Adresse", ScalarAttributeType.S),
                            new AttributeDefinition("Nom", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L) //débit alloué lecture écritue, inutilise sur la version locale, ignoré
            );
//            System.out.println("Création terminée.");
            LOG.info("Création terminée.");
            ajouterElements(800);
        }
        catch (Exception e)
        {
            System.err.println("Création de la table impossible "+e.getMessage());
            LOG.warning("Création de la table impossible "+e.getMessage());
        }


    }

    public void ajouterElements(int nbElts)
    {

        BasicAWSCredentials crede = new BasicAWSCredentials("admin","admin");
        AmazonDynamoDB Client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(crede))
                .build();

        database = new DynamoDB(Client);
        LOG =Logger.getLogger(dynamodb.class.getName());

        //*************

        Table table2 = database.getTable("Restaurants");
        LOG.info("Ajout d'éléments.");
        ArrayList<String> tRues = new ArrayList<String>(Arrays.asList("Rue", "Avenue", "Chemin", "Place", "Allée"));
        ArrayList<String> tEmplacements = new ArrayList<String>(Arrays.asList("de la Célébration", "de la Paix", "des fleurs", "du marais salant", "de la mouette rieuse", "de Kaamelott", "du caillou incandescent"));
        ArrayList<String> tNoms = new ArrayList<String>(Arrays.asList("McDonny", "LGD", "Nouilles & Co", "Fast", "BurgerQueen", "Bretagnol"));
        Random generateur = new Random() ;
        int nNumRue, nTypeRue, nTypePlace, nNom ;
        String sNom, sAdresse;

        Map<String, Object> infos = new HashMap<String, Object>();
//        infos.put("Tel", "0XXXXXX"+generateur.nextInt(999));
//        infos.put("Mail", "confidentiel");
//        infos.put("Notation", generateur.nextInt(9)+1);

        for (int i=0;i<nbElts;i++)
        {
            nNom = generateur.nextInt(tNoms.size());
            nNumRue = generateur.nextInt(5000);
            nTypeRue = generateur.nextInt(tRues.size());
            nTypePlace = generateur.nextInt(tEmplacements.size());

            sAdresse = nNumRue+", ";
            sAdresse+= tRues.get(nTypeRue) + " ";
            sAdresse+= tEmplacements.get(nTypePlace);
            sNom = tNoms.get(nNom);

            infos = new HashMap<String, Object>();
            infos.put("Tel", "0XXXXX"+generateur.nextInt(9999));
            infos.put("Mail", "confidentiel");
            infos.put("Notation", generateur.nextInt(9)+1);


            try{
                PutItemOutcome hRetour = table2.putItem(
                        new Item().withPrimaryKey( "Adresse", sAdresse, "Nom", sNom).withMap("infos", infos));
            }
            catch (Exception e)
            {
                System.out.println("Impossible d'ajouter un objet: "+e.getMessage());
            }
        }
        LOG.info("Ajout d'éléments terminé.");
    }

    public List<Map<String, AttributeValue>> getElementsNom(String sNom)
    {
        LOG =Logger.getLogger(dynamodb.class.getName());
        List<Map<String, AttributeValue>> tRetour ;
        tRetour = new ArrayList<>();


        LOG.info("Obtention d'éléments.");
        BasicAWSCredentials crede = new BasicAWSCredentials("admin","admin");
        AmazonDynamoDB Client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(crede))
                .build();

        database = new DynamoDB(Client);
//        Table table = database.getTable("Restaurants");

//        sNom = "Fast" ;
//        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Nom", sNom);

        Map<String, AttributeValue> expressionAttr =
                new HashMap<String, AttributeValue>();
        expressionAttr.put(":name", new AttributeValue().withS(sNom));
        ScanRequest requete = new ScanRequest()
                .withTableName("Restaurants")
                .withFilterExpression("Nom = :name")
                .withExpressionAttributeValues(expressionAttr);

        LOG.info("Obtention de résultats pour "+sNom+" en cours.");
        try
        {
//            Item hSortie = table.getItem(spec);
            ScanResult hSortie = Client.scan(requete);
            LOG.info("Resultats pour "+sNom+"\n"+hSortie.getItems().toString());
            tRetour = hSortie.getItems() ;
        }
        catch (Exception e)
        {
            LOG.warning("Impossible d'obtenir l'objet.  "+e.getMessage() );
        }

        return tRetour ;

    }

}
