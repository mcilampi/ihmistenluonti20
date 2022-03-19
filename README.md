# Satunnaishenkilöiden luontiohjelma v2.0

Työkalu satunnaishenkilöiden tekemiseen, tehty omaan tarpeeseen ohjelmtointiharjoituksena ja datan luomiseen tietokantaharjoituksia varten.

Ominaisuuksia:

- ohjelma arpoo etunimen ja sukunimen omista listoistaan
- luo syntymäajan vuosien 1930-2020 välille
- syntymäajan perusteella luodaan henkilötunnus: 
* alkuosa muodostetaan edellä arvotusta syntymäajasta
* välimerkki muodostetaan oikeaoppisesti syntymävuoden mukaan
* loppuosan kolme ensimmäistä numeroa arvotaan, miehillä viimeinen niistä muutetaan parittomaksi ja naisilla parilliseksi 
* viimeinen merkki eli tarkiste lasketaan oikean kaavan mukaan alku- ja loppuosan yhdistelmästä
- arvotaan lähiosoitteen kadunnimi tiedostosta, muutettavalla todennäköisyydellä lisätään kadunnumero ja jos se löytyy, muutettavalla todennäköisyydellä asunnon numero
- arvotaan postinumero ja toimipaikka-yhdistelmä tiedostosta
- arvotaan suuntanumero ja puhelinnumero
- tiedoston voi tallentaa .txt- tai .csv-muodossa

Kokonaan uudelleenkirjoitettu ensimmäisestä versiosta, nyt paljon nopeampi, koska vähemmän levyltä lukua ja kirjoitusta.

Ohje, miten CSV:stä saa näppärästi tuotua henkilötiedot MySQL- tai MariDB-tietokantaan: 

1. Luodaan taulu, johon tiedot tuodaan (korvaa "asiakkaat" haluamallasi taulun nimellä):
create table asiakkaat (Etunimi VARCHAR(30) NOT NULL, Sukunimi VARCHAR(30) NOT NULL, Syntymaaika VARCHAR(15) NOT NULL, Henkilotunnus VARCHAR(15) NOT NULL, Katuosoite VARCHAR(30) NOT NULL, Postinumero_ja_toimipaikka VARCHAR(30) NOT NULL, Puhelinnumero VARCHAR(30) NOT NULL);

2. Varmista, että tietokannan asetukset sallivat tietojen tuonnin paikallisesta tiedostosta (katso oman tietokantaohjelmistosi ohjeesta).

3. Tuodaan tiedot tietokannasta (korvaa polku omallasi):
LOAD DATA LOCAL infile 'C:/polku/tiedostoon/tiedosto.csv' INTO TABLE asiakkaat CHARACTER SET latin1 FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';
