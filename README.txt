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


JPA stratégie d'héritage
-----------------------

Il y a 3 types d'hériatges :
	- En une seule table (SINGLE_TABLE) : toutes les entités filles sont mises dans la même table. 
		Ce qui a pour conséquence de partager des informations entre elles pas forcément utiles ou pertinentes.
		Les colonnes en base de données ne peuvent être nulle, toutes colonnes doit être renseignées pour chaques filles.
		Un camoin peut hérité d'un véhicule mais n'a pas les mêmes attributs qu'une voiture.
		Ce qui implique également que les relations ne peuvent être effectuées que sur le parent et non sur les filles.
		Dans notre cas, cette stratégie est la plus adaptée car les entité partage une colone en commun : le sexe 
		et nous n'avons pas besoin d'avantage.
		
	- Chaque entité concrète possède sa table (TABLE_PER_CLASS) : toute entité non abstraite possède sa table
		dans la base de données. Les colonnes des parents sont reprises dans le schéma en plus de celle de l'objet.
		Cela rend impossible l'unicité des champs partagés car ils sont, justement, partagés.
	
	- Chaque entité possède sa table (JOINED) : toute entité même abstraite possède sa table dans la base de données.
		Les tables sont liées entre elles par leur id. Les colonnes des parents ne sont pas dupliquées chez les filles 
		et il n'y pas de problème d'unicité. Le soucis se situe au niveau des requête CRUD qui sont plus lente que les
		autres stratégies.
		
Implémentation :

	Conseil
	-------
	
	Entre chaque stratégie effectuer un clean sur le serveur pour que les changements prennent effet.
	
	- SINGLE_TABLE : mise par défaut dans PersonDo
	
	- JOINED : commentez la ligne @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
		et décommentez @Inheritance(strategy = InheritanceType.JOINED)
		
	- TABLE_PER_CLASS : il faut commenter dans PersonDo :
		37 - @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
		38 - @Inheritance(strategy = InheritanceType.JOINED)
		40 - @DiscriminatorColumn(name = "sexe", discriminatorType = DiscriminatorType.STRING)
		41 - @Table(name = "PERSON")
		
		et sur l'id
		53 - @GeneratedValue(strategy = GenerationType.AUTO)
		
		Il faut ensuite décommenter dans PersonDo :
		39 - @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
		et sur l'id
		54 - @GeneratedValue(strategy = GenerationType.TABLE)
		
		et dans les classes filles il faut commenter :
		14 - @DiscriminatorValue(Sexe.Values.HOMME)
		
Vous savez maintenant tout ce qu'il y a savoir sur le POC

Bonne chance.
		

