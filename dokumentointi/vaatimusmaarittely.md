# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat seurata omien opintojen etenemistä. Sovellus on käytössä rekisteröityneille käyttäjille, joilla jokaisella on oma yksilöllinen opintojen seuraamislistansa.

## Käyttäjät
Alkuvaiheessa sovelluksella on vain yksi käyttäjärooli eli *normaali* käyttäjä. Sovellukseen saatetaan myöhemmin lisätä suuremmilla oikeuksilla varustettu pääkäyttäjä.


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista
* Käyttäjä voi luoda käyttäjätunnuksen järjestelmään 
  * Käyttäjätunnuksien tulee olla uniikkeja ja vähintään 3 merkkiä pitkiä
  * Käyttäjä asettaa salasanan käyttäjätunnukselle luomisen yhteydessä

* Käyttäjä voi kirjautua järjestelmään
  * Kirjautuminen onnistuu syötettäessä olemassa oleva käyttäjätunnus oikean salasanan kanssa

### Kirjautumisen jälkeen
* Käyttäjä näkee omat kurssit
* Käyttäjä voi lisätä uuden suoritettavan kurssin
  * Luotu kurssi näkyy vain sen luoneelle käyttäjällä
* Käyttäjä voi merkitä kurssin suoritetuksi ja arvosanan, jonka on saanut kurssista
* Käyttäjä voi valita näkymän kumpia kursseja taulukossa esitetään
  * Tekemättömät kurssit
  * Tehdyt kurssit
  * Samalla vaihtuu myös sovelluksen alareunassa oleva yhteenlaskettu summa kurssien opintopisteistä
* Käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita
Perusversion toteutuksen jälkeen järjestelmää saatetaan päivittää seuraavilla ominaisuuksilla
* Suoritettujen kurssien arvosanojen painotettu keskiarvo
* Kursseilla voi olla kuvaus tai aikataulutukseen liittyviä tietoja
* Kurssin tietojen editoiminen
* Kurssin suorittamisen ajoittaminen
* Luodut kurssit näkyvät kaikille ja muut voivat valita muiden luomia kursseja
* Käyttäjätunnuksen poistaminen
* Kurssit voidaan poistaa kokonaan näkyvistä
