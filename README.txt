Auteur Romain Huret

Conseil d'utilisation
---------------------

Avant toute chose, il faut créer deux bases de données du nom que vous souhaitez.
Modifier les fichiers properties :
	- src/main/resources/META-INF/databaseHome.properties : base de données principale
	- src/test/resources/configTest/databaseTest.properties : base de données servant aux tests

Pour la base de test, il vous faut lancer les scripts "Structure.sql" puis "DataSet.sql" situé
dans src/test/resources/databaseTest (Créer des entrées dans l'onglet database connections).

Assurez-vous que les fichiers JUnit (src/test/java/database) passent.


Restful api
---------------------

Vous pouvez changer la base de l'url dans le fichier web.xml et l'artefactId du pom.xml si besoin est.

/persons:
	- GET : affiche toutes les personnes
		/json : affiche toutes les personnes sous format json
		/xml : affiche toutes les personnes sous format xml
	- POST : insère une personne
	- DELETE : supprimer toutes les personnes

/persons/{id}:
	- GET : affiche la personne ayant l'id
	- DELETE : supprime la personne ayant l'id
	- PUT : met à jour la personne ayant l'id

/persons/{id}/friends:
	- GET : affiche tous les amis de la personne ayant l'id
	- DELETE : supprime tous les amis de la personne ayant l'id

/persons/{id}/friends/{idFriend}
	- DELETE: supprime l'amis ayant l'idFriend de la personne ayant l'id

Toutes les autres url respectant le modèle REST ne sont pas implémentés car ne sont pas nécessaire.
Par exemple : en PUT - /person/{id}/friends/{idFriend} revient à /persons/{id}
