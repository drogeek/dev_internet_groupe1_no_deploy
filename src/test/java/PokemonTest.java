import fr.univtln.groupe1.metier.Dresseur;
import fr.univtln.groupe1.metier.Pokemon;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// LE nom des fonctions de test doit commencer par test!
// BeforeEach pour initialiser
// AfterEach pour remettre les valeurs initialisées par BéforeEach par null

// Constructeur non testé!

class PokemonTest extends TestCase{

    private Pokemon pokemon;
    private Dresseur dresseur;

    private String valeur_espece = "Pikachu";
    private String valeur_nom = "Pika";
    private int valeur_debutXP;

    private int valeur_level = 5;
    private int valeur_ajoutXP = 100;
    private int valeur_setXP = 500;
    private int valeur_setLevel = 10;
    private String valeur_setEspece = "Bulbizar";
    private String valeur_setName = "Boudou";



    @BeforeEach
    public void init(){
        pokemon = new Pokemon(valeur_espece, valeur_nom, valeur_level);
        dresseur = new Dresseur("Sasha");
//        En cas d'initialisation avec une xp aléatoire
        valeur_debutXP = pokemon.getXp();

//        Création d'un dresseur pour les tests
    }

    @AfterEach
    public void tearDown(){
        pokemon = null;
        dresseur = null;
    }

    @Test
    void testNiveauSupp() throws Exception{
        pokemon.levelSup();
        assertEquals(valeur_level+1, pokemon.getLevel());
    }

    @Test
    void testExpSup() throws Exception{
        pokemon.progessionXp(valeur_ajoutXP);
        assertEquals(valeur_ajoutXP+1, pokemon.getXp());
    }

    @Test
    void testSetExp() throws Exception{
        pokemon.setXp(valeur_setXP);
        assertEquals(valeur_setXP, pokemon.getXp());
    }

    @Test
    void testSetLevel() throws Exception{
        pokemon.setLevel(valeur_setLevel);
        assertEquals(valeur_setLevel, pokemon.getLevel());
    }

    @Test
    void testGetEspece() throws Exception{
        assertEquals(valeur_espece, pokemon.getEspece());
    }

    @Test
    void testSetEspece() throws Exception{
        pokemon.setEspece(valeur_setEspece);
        assertEquals(valeur_setEspece, pokemon.getEspece());
    }

    @Test
    void testGetName() throws Exception{
        assertEquals(valeur_nom, pokemon.getName());
    }

    @Test
    void testSetName() throws Exception{
        pokemon.setName(valeur_setName);
        assertEquals(valeur_setName, pokemon.getName());
    }

    @Test
    void testAddDresseur() throws Exception{
        pokemon.add_Dresseur(dresseur);
        assertEquals(dresseur, pokemon.getDresseur());
    }

    @Test
    void testSupDresseur() throws Exception{
        pokemon.sup_Dresseur();
        assertEquals(null, pokemon.getDresseur());
    }

}
