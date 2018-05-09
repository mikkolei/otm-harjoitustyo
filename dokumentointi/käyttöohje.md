# Käyttöohje

Lataa tiedosto

## Konfigurointi

Tietokannan luominen

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar opintoapp.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään

![login](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/loginNäkymä.png)

Kirjautuminen onnistuu syöttämällä olemassa oleva käyttäjätunnus ja sen salasana syöttökenttiin ja painamalla "login" -nappia.
Mikäli kirjautuminen epäonnistuu, ilmoittaa ohjelma siitä punaisella merkityllä virheviestillä

## Uuden käyttäjän luominen


![uudenKäyttäjänLuominen](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/uudenKäyttäjänLuonti.png)

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luonti näkymään painamalla "create new user" -nappia. 
Uusi käyttäjä luodaan syöttämällä tiedot syöttökenttiin. Syöttökentät ovat ylhäältä alkaen järjestyksessä: nimi, käyttäjänimi, salasana.
Mikäli uuden käyttäjän luominen onnistuu, ilmoittaa ohjelma siitä vihreällä merkityllä viestillä. Jos uuden käyttäjän luominen epäonnistuu, ilmoittaa ohjelma siitä punaisella merkityllä virheviestillä.

## Kurssien luominen

Onnistuneen kirjautumisen jälkeen siirrytään kirjautuneen käyttäjän omalle kurssisivulle. Kurssisivulla on näkyvissä valmiina käyttäjän keskeneräiset kurssit ja mahdollisuus luoda uusi kurssi.
![TyhjäNäkymä](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/TyhjäNäkymä.png)
Vasemmassa reunassa olevilla syöttökentillä syötetään uudelle kurssille nimi ja sen opintopisteet. Opintopisteet on rajattu välille 0-30 ja oletusarvoisesti Spinnerissä näkyy luku 5.

![UudenKurssinLuominen](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/UudenKurssinLisäysOsaKuva.png)

Painamalla nappia "Add new course" kurssi voidaan luoda näkyväksi listassa. Mikäli kurssin luonti onnistuu, niin ohjelma ilmoittaa siitä vihreällä merkityllä viestillä. Mikäli kurssin luonti epäonnistuu, niin ohjelma ilmoittaa siitä punaisella virheviestillä. Kurssin luonti voi epäonnistua, jos kurssin nimi on liian lyhyt tai liian pitkä. 
Kurssin onnistuneen luonnin jälkeen päivittyy kurssi näkyväksi listaan.
![OnnistunutKurssinLuonti](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/OnnistunutKurssinLuontiNäkyyListassa.png)


## Kurssien merkkaaminen tehdyksi

Kurssit voidaan merkitä tehdyiksi ohjelmassa klikkaamalla haluttua kurssia listasta ja tämän jälkeen klikkaamalla nappia "Mark done". Nappi ei tee mitään mikäli kurssia ei ole aluksi valittu. 
Valitun kurssin ja "Mark done" -napin painalluksen jälkeen aukeaa popup ikkuna, jossa voidaan asettaa kurssille arvosana. Arvosanat on rajattu 0-5 ja oletusarvoisesti ohjelma ehdottaa arvosanaa 5. 
![Popup](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/MerkitseKurssiValmiiksi.png)
Painamalla nappia "Yes" ohjelma palaa takaisin kurssisivulle ja siirtää valitun kurssin tehtyjen kurssien puolelle. Painamalla nappia "Cancel" ohjelma palaa takaisin kurssisivulle tekemättä muutoksia.


## Näkymän vaihtaminen

Käyttäjä voi valita kumpia kursseja listassa esitetään. Painamalla nappia "Switch view" vaihtuu näkyviin valmiit kurssit ja niiden arvosanat tai, jos valmiit kurssit ovat jo näkyvissä niin vaihtuu näkyviin keskeneräiset kurssit ilman arvosanamerkintöjä.
![ListassaNäkyyValmisKurssi](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/ValmisKurssiNäkyyOikeassaListassa.png)


## Sovelluksen sulkeminen

Sovelluksesta voi poistua, kun käyttäjä ei ole enää kirjautuneena sovellukseen. Uloskirjautuminen tapahtuu "logout" -napin avulla.
