## Pour lancer payara:
```
make launch-payara
```

## Pour déployer l'application
```
make deploy
```

## Pour afficher les logs
```
make logs
```

## Pour supprimer payara
```
make rm
```

## Pour redémarrer payara
### (en reconstruisant l'image complétement)
```
make restart
```

## Accéder à la base de donnée
```
make access_db
```

Pour l'installation de SonarQube (en Local):
- Télécharger sonarqube 6.5
- Extraire l'archive dans /etc/
- Lancer sonar avec la commande suivante:
/etc/sonarqube/bin/[OS]/sonar.sh console
- Se connecter à l'URL http://localhost:9000/users avec les identifiants admin et le mot de passe "admin"
- Créer un token et le copier (on ne peut plus le voir après)

Pour ajouter SonarQube à IntelliJ:
- Aller dans Settings/Plug-In
- Ajouter le plugIn SonarLint
- Dans Settings/SonarLint General Settings:
  - Choisir SonarQube
  - Ajouter le serveur avec l'URL précédemment utilisée
  - Saisir ensuite le token généré précédemment

