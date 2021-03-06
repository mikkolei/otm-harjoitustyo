# **OpintoApp**


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/käyttöohje.md)

[Testausdokumentti](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

## Releaset
[Viikko 5 release](https://github.com/mikkolei/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6 release](https://github.com/mikkolei/otm-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/mikkolei/otm-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaus
testit suoritetaan komennolla 
```
mvn test
```
testikattavuus luodaan komennolla
```
mvn jacoco:report
```
Testikattavuutta voidaan tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html
### Suoritettavan .jar -tiedoston generointi
komento
```
mvn package
```
Generoi suoritettavan .jar tiedoston hakemistoon target

### Checkstyle
Checkstylen määrittelemät tarkastukset saadaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Virheilmoitukset voidaan tarkistaa avaamalla selaimella tiedosto  target/site/checkstyle.html

### Javadoc
Javadoc generoidaan komennolla
```
mvn javadoc:javadoc
```
Javadocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
