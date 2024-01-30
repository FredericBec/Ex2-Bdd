-- Générer la base de données Shop
DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

-- Création de chaque table
CREATE TABLE T_Articles(
	IdArticle		int(4)		PRIMARY KEY AUTO_INCREMENT,
	Description		VARCHAR(30) NOT NULL,
	Brand			VARCHAR(30) NOT NULL,
	UnitaryPrice	float(8)	NOT NULL
) ENGINE = InnoDB;

-- insertion de chaque élément dans la table articles de la base de données
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris', 'Logitoch', 65);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clavier', 'Microhard', 49.5);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Systeme d''exploitation', 'Fenetres Vistouilles', 150);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Tapis souris', 'Chapeau Bleu', 5);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('cle USB 8 To', 'Syno', 8);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Laptop', 'PH', 1199);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('CD x 500', 'CETME', 250);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD-R x 100', 'CETME', 99);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD+R x 100', 'CETME', 105);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Batterie Laptop', 'PH', 80);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Audio', 'Syno', 105);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('WebCam', 'Logitoch', 755);

-- affichage des tables
SHOW TABLES;

-- décrire la table articles et categories
DESCRIBE T_Articles;
SELECT * FROM T_Articles;

-- ajout à la table articles des occurences de mon choix
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('MacBook Pro', 'Apple', 2500);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Bluetooth KITTY', 'Razer', 100);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Antivirus', 'Avast', 30);
SELECT * FROM T_Articles;

-- modification d'un article puis vérifier si les modifications sont prises en compte
UPDATE T_Articles SET Brand = 'Bitedefender', UnitaryPrice = 65 WHERE IdArticle = 15;
SELECT * FROM T_Articles;

-- supprimer un article à la table articles
DELETE FROM T_Articles WHERE IdArticle = 6;
SELECT * FROM T_Articles;

-- afficher tous les articles dont UnitaryPrice > 100
SELECT * FROM T_Articles WHERE UnitaryPrice > 100;

-- afficher tous les articles dont le prix est compris entre 50 et 250
SELECT * FROM T_Articles WHERE UnitaryPrice BETWEEN 50 and 250;

-- afficher les articles dans l'ordre croissant prix
SELECT * FROM T_Articles ORDER BY UnitaryPrice ASC;

-- afficher uniquement la description des articles
SELECT Description FROM T_Articles;

-- choisir une requête particulièrement intéressante à présenter
-- requête permettant d'afficher les résultats grouper par la Description
SELECT Description, UnitaryPrice FROM T_Articles GROUP BY Description HAVING UnitaryPrice > 200;

-- ajout de la table catégories et insertion des éléments
CREATE TABLE T_Categories(
	IdCategory		int(4)		PRIMARY KEY AUTO_INCREMENT,
	CatName			VARCHAR(30) NOT NULL,
	Description 	VARCHAR(50) NOT NULL
)ENGINE = InnoDB;

INSERT INTO T_Categories(CatName, Description) VALUES ('PC', 'Laptop et micro ordinateur');
INSERT INTO T_Categories(CatName, Description) VALUES ('Logiciel', 'SE, Antivirus, IDE...');
INSERT INTO T_Categories(CatName, Description) VALUES ('Materiel info', 'tout matériel indispensable à un PC');

-- modification de la table articles pour inclure la clé étrangère IdCategory et mise à jour de la table articles
ALTER TABLE T_Articles ADD COLUMN IdCategory int(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 1;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 2;
UPDATE T_Articles SET IdCategory = 2 WHERE IdArticle = 3;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 4;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 5;
UPDATE T_Articles SET IdCategory = 1 WHERE IdArticle = 6;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 7;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 8;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 9;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 10;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 11;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 12;
UPDATE T_Articles SET IdCategory = 1 WHERE IdArticle = 13;
UPDATE T_Articles SET IdCategory = 3 WHERE IdArticle = 14;
UPDATE T_Articles SET IdCategory = 2 WHERE IdArticle = 15;

SELECT * FROM T_Articles;

-- trouver la requête qui permet d'obtenir le résultat selon le pdf ExosBdd
-- SELECT IdArticle, T_Articles.Description, Brand, UnitaryPrice, CatName FROM T_Articles INNER JOIN T_Categories ON IdArticle = T_Categories.IdCategory;
SELECT IdArticle, a.Description, Brand, UnitaryPrice, CatName FROM T_Articles AS a INNER JOIN T_Categories as c ON IdArticle = c.IdCategory;
