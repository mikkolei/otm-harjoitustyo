# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria, koodin pakkausrakenne on seuraavanlainen:

![pakkausrakenne](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/Pakkausrakenne.png)

Pakkaus opintoapp.ui sisältää JavaFX:llä toteutetun käyttöliittymän, opintoapp.domain sisältää sovelluslogiikan ja opintoapp.dao sisältää tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erilaista näkymää:

* Kirjautuminen
* Uuden käyttäjän luominen
* Käyttäjän omat opinnot
   * Voi valita kumpia kursseja näkymässä esitetään
   * Näkymän vaihdon yhteydessä päivittyy myös yhteenlaskettu opintopisteiden määrä.

Käyttöliittymä sisältää myös yhden popup -tyylisen näkymän 

* Kurssin merkkaaminen suoritetuksi

Jokainen näistä näkymistä on toteutettu omana FXML-näkymänä. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen (poikkeuksena popup -näkymä, joka tulee käyttäjän omat opinnot -näkymän päälle merkittäessä kurssia valmiiksi). Käyttöliittymä on rakennettu ohjelmallisesti pakkauksen opintoapp.ui sisällä, jossa jokaisella näkymällä on oma Controller -luokkansa.

Sovelluksen kurssilistaus näkymä voi muuttua muutamassa eri tapauksessa. Näkymää voi vaihtaa alareunassa olevasta "switch view" -painikkeesta, jolloin vaihtelee joko tekemättömät kurssit tai tehdyt kurssit. Käyttäjän lisätessä uusi kurssi näkymä päivittyy niin, että uusi kurssi tulee listaukseen. 

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Course, jotka kuvaavat käyttäjiä ja käyttäjien kursseja.

![Luokkakaavio](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/luokkakaavio.png)

Toiminnallisista kokonaisuuksista vastaa luokan StudyService ainoa olio. Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.

* boolean createUser
* boolean login
* boolean createCourse
* yms.. 

StudyService pääsee käsiksi käyttäjiin ja kursseihin tietojen tallennuksesta vastaavan pakkauksessa opintoapp.dao sijaitsevien rajapintojen UserDao ja CourseDao kautta. Luokkien toteutus injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

StudyServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![luokka/pakkauskaavio](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/luokka_pakkauskaavio.png)
## Tietojen pysyväistalletus

Pakkauksen opintoapp.dao luokat SQLUserDao ja SQLCourseDao huolehtivat tietojen tallentamisesta tietokantaan. Tietokanta on nimeltään opintoApp.db.

Luokat noudattavat Data Access Object -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan tallentamistapaa päätetään vaihtaa. Luokat on eristetty rajapintojen UserDao ja CourseDao taakse ja sovelluslogiikka ei käytä luokkia suoraan. 

### Tietokanta

Sovellus tallettaa tiedot sqlite3:lla tehtyyn tietokantaan.

Tietokannan User-taulu on tehty seuraavalla komennolla:
```
CREATE TABLE User (
   id integer PRIMARY KEY,
   name varchar(50),
   username varchar(50),
   password varchar(50)
);
```
Ensin asetetaan yksilöivä id -tunnusluku tämän jälkeen nimi sitten käyttäjänimi ja lopuksi salasana. Id on sqlite3:n automaattisesti generoima id tunnus, joka toimii samalla myös luokan pääavaimena. Kaikki muut ovat varchar -muotoa ja pituudet on rajattu 50 merkkiin. Sovelluslogiikka määrittelee myös minimipituuden merkkijonoille.

Tietokannan Course-taulu on tehty seuraavalla komennolla:
```
CREATE TABLE Course (
   id integer PRIMARY KEY,
   user_id integer,
   name varchar(100),
   credits integer,
   done boolean,
   grade integer,
   FOREIGN KEY (user_id) REFERENCES User(id)
);
```
Ensin asetetaan yksilöivä id -tunnusluku, sitten käyttäjään kohdistuva id -luku, kurssin nimi, kurssin opintopisteet, totuusarvo onko kurssi suoritettu, kurssin arvosana, ja lopuksi asetetaan viiteavaimen osoittaminen User-luokkaan.

### Päätoiminnallisuudet

Kuvataan seuraavaksi muutamaa sovelluksen päätoiminnallisuutta sekvenssikaavioina

#### Käyttäjän sisäänkirjautuminen
Kun kirjautumisnäkymässä käyttäjä on syöttänyt tekstikenttiin käyttäjätunnuksen ja salasanan ja klikkaa nappia "login", etenee sovelluksen toiminta seuraavasti: 
![LoginSekvenssikaavio](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/login.png)

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu luokan StudyService metodia login, joka hakee käyttäjän tietokannasta käyttäjänimen ja salasanan perusteella. Sovelluslogiikka selvittää löytyykö kyseistä käyttäjää, ja mikäli kirjautuminen onnistuu, asettaa käyttöliittymä näkymäksi UserStudyScenen, eli sovelluksen varsinaisen päänäkymän, jossa on renderöitynä näkymään kirjautuneen käyttäjän kurssit.

#### Uuden käyttäjän luominen
Uuden käytäjän luonti tapauksessa, tarkistetaan ensin käyttäjätunnuksen saatavuus. Mikäli käyttäjätunnus ei ole jo käytössä, etenee sovellus seuraavanlaisesti "create new user" -napin painalluksen jälkeen: 
![CreateNewUserSekvenssikaavio](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavioCreateNewUser.png)

Tapahtumakäsittelijä kutsuu sovelluslogiikan metodia createUser. Sovelluslogiikka selvittää ensin onko käyttäjätunnus jo olemassa. Jos ei, on uuden käyttäjän luominen mahdollista, jolloin sovelluslogiikka luo uuden User-olion ja tallettaa sen kutsumulla userDao:n metodia create. Tästä seurauksena tulee ilmoitus onnistuneesta käyttäjän luomisesta ja käyttäjä voi palata kirjautumisnäkymään "return" -nappia painamalla. 

#### Uuden kurssin luominen käyttäjälle
Uuden kurssin luovan napin, "add new course", painalluksen jälkeen sovellus etenee seuraavanlaisesti:
![CreateNewCourseSekvenssikaavio](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssiCreateNewCourse.png)

Tapahtumakäsittelijä kutsuu sovelluslogiikan metodia createCourse. Sovelluslogiikka luo uuden Course-olion ja tallettaa sen kutsumalla courseDao:n metodia create. Tämän seurauksena käyttöliittymä päivittää näkymän kutsumalla omaa metodia setUndoneCourseList().

## Ohjelman rakenteeseen jääneet heikkoudet

### Salasanat
Sovelluksessa käytettävät salasanat eivät ole kryptattuja. 

### Käyttöliittymä
Erikoismerkit ovat tällä hetkellä sallittuina. Ne eivät kuitenkaan aiheuta toiminnallisesti ongelmia, mutta ne olisi hyvä rajata pois kuitenkin käyttäjien syötteistä.

### DAO-luokat
UserDao:on kohdistuvat SQL-kyselyt sisältävät melko paljon toisteista koodia. Yhden käyttäjän etsintä-operaatiot voitaisiin toteuttaa vähemmällä määrällä metodeja. Kaikilla etsintä-operaatioilla on kuitenkin erilainen ja oma merkityksensä tämän hetkisessä sovelluslogiikassa. 
