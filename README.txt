Auteur Romain Huret

Conseil d'utilisation
---------------------

Avant toute chose, il faut cr�er deux bases de donn�es du nom que vous souhaitez.
Modifier les fichiers properties :
	- src/main/resources/META-INF/databaseHome.properties : base de donn�es principale
	- src/test/resources/configTest/databaseTest.properties : base de donn�es servant aux tests

Pour la base de test, il vous faut lancer les scripts "Structure.sql" puis "DataSet.sql" situ�
dans src/test/resources/databaseTest (Cr�er des entr�es dans l'onglet database connections).

Assurez-vous que les fichiers JUnit (src/test/java/database) passent.


Restful api
---------------------

Vous pouvez changer la base de l'url dans le fichier web.xml et l'artefactId du pom.xml si besoin est.

/persons:
	- GET : affiche toutes les personnes
		/json : affiche toutes les personnes sous format json
		/xml : affiche toutes les personnes sous format xml
	- POST : ins�re une personne
	- DELETE : supprimer toutes les personnes

/persons/{id}:
	- GET : affiche la personne ayant l'id
	- DELETE : supprime la personne ayant l'id
	- PUT : met � jour la personne ayant l'id

/persons/{id}/friends:
	- GET : affiche tous les amis de la personne ayant l'id
	- DELETE : supprime tous les amis de la personne ayant l'id

/persons/{id}/friends/{idFriend}
	- DELETE: supprime l'amis ayant l'idFriend de la personne ayant l'id

Toutes les autres url respectant le mod�le REST ne sont pas impl�ment�s car ne sont pas n�cessaire.
Par exemple : en PUT - /person/{id}/friends/{idFriend} revient � /persons/{id}
