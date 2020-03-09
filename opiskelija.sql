CREATE TABLE Ryhma(
	ryhmaID VARCHAR(20) NOT NULL PRIMARY KEY,
	ryhmannimi VARCHAR(30) NOT NULL,
	toteutusmuoto VARCHAR(20),
	vastuuopettaja VARCHAR(50)
	);

CREATE TABLE Opiskelija(
	opiskelijaID int NOT NULL PRIMARY KEY,
	etunimi VARCHAR(255) NOT NULL,
	sukunimi VARCHAR(255) NOT NULL,
	opintoviikot int,
	sahkoposti VARCHAR(40) NOT NULL,
	ryhmaID VARCHAR(20) NOT NULL,
	FOREIGN KEY (ryhmaID) REFERENCES Ryhma(ryhmaID)
	ON UPDATE CASCADE
	ON DELETE NO ACTION
	);
	
	--Tietojen lisäys alussa
	INSERT INTO Ryhma VALUES 
	("SOKE19A", "Sovelluskehitys, kevät19", "nuoret-päivä", "Heikki Hallinen"),
	("KTUKI19B","Käytön tuki, kevät19", "aikuiset-päivä", "Risto Tamminen"),
	("KTUKI19A","Käytön tuki, kevät19", "nuoret-päivä","Risto Tamminen"),
	("SOKE19B","Sovelluskehitys, kevät19", "aikuiset-päivä", "Heikki Hallinen"),
	("SOKE19C","Sovelluskehitys, kevät 19", "aikuiset-monimuoto", "Maarit Mattila"); 
	
	INSERT INTO Opiskelija VALUES 
	(1234, "Ilkka","Nieminen",0, "ilkka.nieminen@gmail.com", "SOKE19A"),
	(2235, "Kaisa","Tamminen",55, "kaisa@gmail.com", "SOKE19B"),
	(2244, "Erkki","Pohjola",10, "erkki@elisa.fi", "SOKE19C"); 
	
	
	
	
	


