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
