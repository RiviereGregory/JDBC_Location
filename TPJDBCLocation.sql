DROP DATABASE IF EXISTS location;
CREATE DATABASE location;
USE location;

CREATE TABLE Client (codecl INT NOT NULL AUTO_INCREMENT,nomcl VARCHAR(255), adresse VARCHAR(255), ville VARCHAR(255), PRIMARY KEY (codecl));
CREATE TABLE Contrat (nocontrat INT NOT NULL AUTO_INCREMENT,date_contrat DATE, date_enlevement DATE,
date_retour DATE,codecl INT,noimmatriculation INT, PRIMARY KEY (nocontrat));
CREATE TABLE Prevoir (nocontrat INT, codesuplement INT, PRIMARY KEY (nocontrat,codesuplement));
CREATE TABLE Suplements (codesuplement INT NOT NULL AUTO_INCREMENT , libelle_supp VARCHAR(255) , tarif_jour INT, PRIMARY KEY (codesuplement));
CREATE TABLE Voiture (noimmatriculation INT NOT NULL AUTO_INCREMENT, marque VARCHAR(255), modele VARCHAR(255), couleur VARCHAR(255),
cumul_reparation INT, disponible BOOLEAN, code_type INT, PRIMARY KEY (noimmatriculation));
CREATE TABLE Reparations (num_rep INT NOT NULL AUTO_INCREMENT, date_rep DATE, description_panne VARCHAR(255), montant_repa INT,
noimmatriculation INT, PRIMARY KEY (num_rep));
CREATE TABLE Type_voiture (code_type INT NOT NULL AUTO_INCREMENT,description_type VARCHAR(255), places INT, prix_jour INT, PRIMARY KEY (code_type));

ALTER TABLE Prevoir ADD CONSTRAINT fk_Prevoir_Contrat_nocontrat FOREIGN KEY (nocontrat) REFERENCES Contrat (nocontrat);
ALTER TABLE Prevoir ADD CONSTRAINT fk_Prevoir_Suplements_codesuplement FOREIGN KEY (codesuplement) REFERENCES Suplements (codesuplement);
ALTER TABLE Contrat ADD CONSTRAINT fk_Contrat_Client_codecl FOREIGN KEY (codecl) REFERENCES Client (codecl);
ALTER TABLE Contrat ADD CONSTRAINT fk_Contrat_Voiture_noimmatriculation FOREIGN KEY (noimmatriculation) REFERENCES Voiture (noimmatriculation);
ALTER TABLE Voiture ADD CONSTRAINT fk_Voiture_Type_voiture_code_type FOREIGN KEY (code_type) REFERENCES Type_voiture (code_type);
ALTER TABLE Reparations ADD CONSTRAINT fk_Reparations_Voiture_noimmatriculation FOREIGN KEY (noimmatriculation) REFERENCES Voiture (noimmatriculation);


INSERT INTO Client (nomcl,adresse,ville) VALUES ('Dupont','rue tintin','moutarde'),('Durant','rue tin','moutarde'),
('Dupond','rue Hadock','Dijon'),('Paul','rue Jean','Aix'),('River','rue ocean','Marseille'),('Aden','rue Paradis','Marseille'),
('Pont','rue Levis','Chateau'),('Toto','rue tata','Cousin');

INSERT INTO Type_voiture (description_type, places, prix_jour) VALUES ('Citadine Essence', 5, 65),('Citadine Essence', 3, 55),('Citadine Diesel', 5, 70),
('Citadine Diesel', 3, 65),('Monospace Essence', 5, 95),('Essence', 5, 50),('Monospace Diesel', 5, 105),('Essence', 3, 65);

INSERT INTO Voiture (marque , modele , couleur ,cumul_reparation, disponible , code_type) VALUES ('Peugeot' , '207' , 'blanche' ,1000, true , 1),
('Citroen' , 'C2' , 'blanche' ,0, true , 2),('Renault' , 'CLIO' , 'noire' ,2000, true , 3),('Peugeot' , '207' , 'noire' ,0, false , 4),
('Peugeot' , '807' , 'bleu' ,3000, false , 5),('Renault' , 'scenic' , 'orange' ,0, true , 6),('Citroen' , 'C3 Picasso' , 'gris' ,0, false , 7),
('Peugeot' , '206' , 'blanche' ,4000, true , 8);

INSERT INTO Reparations (date_rep, description_panne, montant_repa,noimmatriculation) VALUES ('2011-03-12', 'Pompe essence', 1000,1),
('2010-03-15', 'Moteur', 3000,5),('2012-05-12', 'Pompe gasoil', 1000,3),('2013-07-10', 'couroie', 1000,3),
('2013-03-12', 'Pompe essence et filtre', 2000,8),('2012-08-12', 'Couroie et batterie', 2000,8);

INSERT INTO Contrat (date_contrat , date_enlevement ,date_retour ,codecl ,noimmatriculation) VALUES ('2011-03-12', '2011-03-12','2011-03-12',1,1),
('2013-07-12', '2013-07-12','2013-07-19',2,4),('2013-07-12', '2013-07-12','2013-07-20',3,5),('2013-07-12', '2013-07-12','2013-07-18',4,7),
('2012-03-12', '2012-03-12','2012-03-13',1,2),('2011-03-12', '2011-03-12','2011-03-12',5,8),('2011-03-12', '2011-03-12','2011-03-12',8,8),
('2011-03-12', '2011-03-12','2011-03-12',7,7),('2011-03-12', '2011-03-12','2011-03-12',6,7),('2013-03-12', '2013-03-12','2013-03-12',8,8);

INSERT INTO Suplements (libelle_supp, tarif_jour) VALUES ('GPS' , 20),('Plein Essence' , 50),('Nettoyage' , 10);

INSERT INTO Prevoir (nocontrat, codesuplement) VALUES (1,1),(1,2),(1,3),(2,1),(3,1),(4,1),(5,2),(8,2);
