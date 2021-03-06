# Testausdokumentti

Ohjelma on testattu JUnitin yksikkö- ja integraatiotestien avulla.
Järjestelmätestaus on suoritettu manuaalisesti.

## Yksikkö- ja integraatiotestaus

Automatisoidut testit testaavat sovelluslogiikkaa, eli pakkauksen moodtracker.domain
luokkia sekä Data Access Object - luokkien toimintaa, eli pakkauksen moodtracker.dao
luokkia.

### Sovelluslogiikka

Sovelluslogiikkaa testaavat integraatio- ja yksikkötestit [MoodtrackerActionsTest](https://github.com/noorarytila/ot-harjoitustyo/blob/master/Moodtracker/src/test/java/moodtracker/domainTest/MoodtrackerActionsTest.java),
[MoodTest](https://github.com/noorarytila/ot-harjoitustyo/blob/master/Moodtracker/src/test/java/moodtracker/domainTest/MoodTest.java) ja [UserTest](https://github.com/noorarytila/ot-harjoitustyo/blob/master/Moodtracker/src/test/java/moodtracker/domainTest/UserTest.java). Testit testaavat erilaisia sovelluksen toiminnan
kannalta oleellisia ja todellisuudessa vastaan tulevia tapahtumia ja skenaarioita.

### DAO - luokat

DAO-luokkien oikeellisuutta on testattu luomalla tilapäinen tiedosto. Testit
hyödyntävät myös [FakeUserDao](https://github.com/noorarytila/ot-harjoitustyo/blob/master/Moodtracker/src/test/java/moodtracker/daoTest/FakeUserDao.java) ja [FakeMoodDao](https://github.com/noorarytila/ot-harjoitustyo/blob/master/Moodtracker/src/test/java/moodtracker/daoTest/FakeMoodDao.java) luokkia, jotta testit testaisivat
mahdollisimman hyvin sovelluksen todellista käyttöä.

### Testauskattavuus

Sovelluksen testien rivikattavuus on 89% ja haarautumiskattavuus 85%.

![jacoco](https://github.com/noorarytila/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png?raw=true)

Testeistä on jätetty käyttöliittymäkoodi pois. Testaamatta jäivät tilanteet, joissa tiedostoon lukeminen
tai kirjoittaminen epäonnistuu, sekä osa MoodtrackerActions-luokan usersMoods-metodista.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. Testauksen suorittaminen on ollut mahdollista
vain Linux-ympäristössä.

### Asennus ja konfigurointi

Sovelus on testattu Linux-ympäristössä niin, että sovellusta on käytetty
käyttöohjeen kuvaamalla tavalla ja käynnistyshakemistossa on ollut käyttöohjeen mukainen config.properties tiedosto.

### Toiminnallisuudet

Kaikki [vaatimusmäärittelyssa](https://github.com/noorarytila/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
kuvatut toiminnallisuudet on testattu. Tekstikentät on myös yritetty täyttää testatessa tyhjillä arvoilla.

## Sovellukseen jääneet laatuongelmat

* Sovellus ei anna järkevää virheilmoitusta jos käyttäjä yrittää lisätä moodin valitsematta sille arvoa

 
