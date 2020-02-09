<p>
    <a href="http://137.116.225.223:8085/viewType.html?buildTypeId=Emporio_Build&guest=1">
        <img src="http://137.116.225.223:8085/app/rest/builds/buildType:(id:Emporio_Build)/statusIcon"/></a>
    <a href="https://travis-ci.org/DragonAlex98/E-mporio">
        <img src="https://travis-ci.org/DragonAlex98/E-mporio.svg?branch=master" /></a>
</p>

# E-mporio

- Website: <a href="https://emporio-aru.herokuapp.com/" target="_blank">`emporio-aru-web`</a><small><sup>(1)(2)</sup></small>
- API: <a href="https://emporio-aru.herokuapp.com/api/v1" target="_blank">`emporio-aru-api`</a>

# Vision

La piattaforma permette l'interazione post-acquisto tra acquirente, fattorino e attività commerciale.

Per la prima volta il <b><i>cliente</i></b> potrà acquistare liberamente presso le attività commerciali presenti nella zona, con il vantaggio di ritrovare la merce acquistata in uno luogo sicuro vicino al proprio parcheggio; avrà anche a disposizione uno storico degli ordini effettuati, con un sistema di tracciamento dello stato di avanzamento.

Il <b><i>titolare</i></b> dell'attività potrà utilizzare il sistema per rendere disponibile il proprio catalogo di prodotti, avere accesso a dettagliate statistiche di vendita, un avanzato sistema di gestione personale (<b><i>dipendenti</i></b> e <b><i>gestori marketing</i></b>), creazione di ordini per gli acquirenti che verranno resi disponibili per la consegna da parte dei fattorini.

I <b><i>fattorini</i></b> a loro volta potranno utilizzare il sistema per la completa gestione del processo di consegna, dall'accettazione di una nuovo consegna, fino al ritiro merce da parte dell'acquirente da un locker a lui vicino e riservato.

La piattaforma permette diverse operazioni di amministrazione da parte di <b><i>amministratori</i></b> o <b><i>operatori</i></b> nei singoli comuni, dall'inserimento di nuovi locker presenti nel territorio alla gestione degli utenti.

Vengono inoltre stilate in maniera automatica e pianificata delle classifiche relative all'andamento delle vendite delle varie attività, presenti in homepage.

# Iterazioni

|               | Consegna      | URL<small><sup>(2)</sup></small>            |Repository                                       |
| ------------- |:-------------:| :---------------------------------------------:|:-----------------------------------------------:|
| Iterazione 1  | 30 Nov 2019   | https://emporio-aru-iterazione1.herokuapp.com/ |https://github.com/UNICAM-CS/iteration-w1-team5  |
| Iterazione 2  | 21 Dic 2019   | https://emporio-aru-iterazione2.herokuapp.com/ |https://github.com/UNICAM-CS/iteration-w4-team5  |
| Iterazione 3  | 11 Gen 2020   | https://emporio-aru-iterazione3.herokuapp.com/ |https://github.com/UNICAM-CS/iteration-w7-team5  |
| Iterazione 4  | 1 Feb 2020    | https://emporio-aru-iterazione4.herokuapp.com/ <small><sup>(1)</sup></small> |https://github.com/UNICAM-CS/iteration-w10-team5 |

<p><i><small><sup>(1)</sup></small> può essere necessario aggiornare la pagina, una volta caricata la home, per vedere la classifica delle attività che hanno venduto di più</i></p>
<p><i><small><sup>(2)</sup></small> il primo avvio può richiedere fino ad un minuto</i></p>

# Tecnologie Utilizzate e Struttura Progetto

Il progetto utilizza un'architettura layered formata da tre strati:

- Backend:
    - il framework utilizzato è Spring Boot 2
    - l'autenticazione e l'autorizzazione vengono effettuati tramite spring security e attraverso l'utilizzo di token jwt
    - fornisce chiamate API REST per l'interazione con il frontend
    - è stata seguita una suddivisione delle responsabilità basata su controller, services, repository, entity, per diminuire l'accoppiamento e aumentare la coesione

- Frontend:
    - il framework utilizzato è Angular 8, con la predisposizione alla realizzazione di app mobile tramite nativescript
    - le chiamate al backend vengono intercettate e rese sicure tramite l'aggiunta del token jwt

- Persistenza
    - uso dell'ORM Hibernate per la mappatura con Oggetti Java - Database Relazionali
    - il database utilizzato è MySql 8, hostato su VM Azure
    
# Continuous Integration / Continuous Deployment

Per quanto riguarda le pratiche di <b><i>CI</i></b> è stato impiegato Travis CI per l'esecuzione automatizzata di test (relativi al backend) ad ogni nuovo commit. Le istruzione per l'esecuzione dei test da parte di Travis CI sono state specificate nel file <a href="https://github.com/DragonAlex98/E-mporio/blob/master/.travis.yml">.travis.yml</a>

Per il rilascio in produzione del sistema è stato utilizzato TeamCity 2019 hostato su VM Azure, con comandi volti a eseguire la build del progetto e il relativo deployment sulla piattaforma di hosting Heroku. Ogni nuovo commit su github genera quindi, tramite la pratica del <b><i>CD</i></b>, un rilascio aggiornato verso l'ambiente di produzione
