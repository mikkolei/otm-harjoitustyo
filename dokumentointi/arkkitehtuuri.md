# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria, koodin pakkausrakenne on seuraavanlainen:

![pakkausrakenne](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/kuvat/Pakkausrakenne.png)

Pakkaus opintoapp.ui sisältää JavaFX:llä toteutetun käyttöliittymän, opintoapp.domain sisältää sovelluslogiikan ja opintoapp.dao sisältää tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erilaista näkymää

* Kirjautuminen
* Uuden käyttäjän luominen
* Käyttäjän lista opinnoista

Jokainen näistä näkymistä on toteutettu omana FXML-näkymänä. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti pakkauksen opintoapp.ui sisällä, jossa jokaisella näkymällä on oma Controller -luokkansa.

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
   name varchar(50),
   username varchar(50,
   password varchar(50)
);
```
eli ensin syötetään nimi, sitten käyttäjänimi ja lopuksi salasana. Kaikki ovat varchar -muotoa ja pituudet on rajattu 50 merkkiin. Sovelluslogiikka määrittelee myös minimipituuden merkkijonoille.

### Päätoiminnallisuudet

#### Käyttäjän sisäänkirjautuminen

#### Uuden käyttäjän luominen

#### Uuden kurssin luominen käyttäjälle


## Ohjelman rakenteeseen jääneet heikkoudet

