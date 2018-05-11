# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tehdyillä järjestelmätason testeillä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka
Tärkeimmät testit on kohdistettu sovelluslogiikasta vastaavaan pakkaukseen [opintoapp.domainiin](https://github.com/mikkolei/otm-harjoitustyo/tree/master/OpintoApp/src/main/java/opintoapp/domain). Luokkia testaavat integraatiotestit [StudyServiceUserTest](https://github.com/mikkolei/otm-harjoitustyo/blob/master/OpintoApp/src/test/java/opintoapp/domain/StudyServiceUserTest.java) ja [StudyServiceCourseTest](https://github.com/mikkolei/otm-harjoitustyo/blob/master/OpintoApp/src/test/java/opintoapp/domain/StudyServiceCourseTest.java), joiden määrittelemät testitapaukset simuloivat käyttöliittymän [StudyService](https://github.com/mikkolei/otm-harjoitustyo/blob/master/OpintoApp/src/main/java/opintoapp/domain/StudyService.java) -luokan avulla suoritettuja toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistalletukseen DAO-rajapintojen keskusmuistitoteutusta [FakeUserDao:n](https://github.com/mikkolei/otm-harjoitustyo/blob/master/OpintoApp/src/test/java/opintoapp/domain/FakeUserDao.java) ja [FakeCourseDao:n](https://github.com/mikkolei/otm-harjoitustyo/blob/master/OpintoApp/src/test/java/opintoapp/domain/FakeCourseDao.java) muodossa.


### DAO-luokat
Molempien DAO-luokkien toiminnallisuuksia on testattu luomalla testeissä testitietokanta *OpintoAppFake.db*. Tämä tietokanta luodaan projektin juureen ja tyhjennetään testien päätteeksi.

### Testauskattavuus
Käyttöliittymää poislukien, sovelluksen testauksen rivikattavuus on % ja haarautumakattavuus on %.

Testauksen ulkopuolelle jäi tilanne, jossa SQLCourseDao:ssa olevan kurssin valmiiksi merkkaaminen onnistuu. 

## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti. Käyttökelpoisen jar- tiedoston suoritusta on testattu OSX-ympäristössä

### Toiminnallisuudet
Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä syötekentät on yritetty täyttää myös virheellisillä arvoilla, kuten tyhjillä. 
Valmiiksi merkityt kurssit on myös testattu, että näitä ei voi uudelleen merkitä valmiiksi.

## Sovellukseen jääneet laatuongelmat
Sovelluksen kaikkia virheilmoituksia ei käsitellä. Manuaalisien testauksien yhteydessä ei käytännössä ole kuitenkaan tullut vastaan tilannetta, jossa virheilmoituksia tulostuisi komentoriville. Kuitenkin tämä osa voitaisiin toteuttaa paremmin, mikäli ongelmia tulisi vastaan. 

