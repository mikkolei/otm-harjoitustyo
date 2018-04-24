# **OpintoApp**


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/mikkolei/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Releases
[Viikko5_release](https://github.com/mikkolei/otm-harjoitustyo/releases/tag/viikko5)

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
### Suoritettavan jarin generointi
komento
```
mvn package
```

### Checkstyle
Checkstylen määrittelemät tarkastukset saadaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```

