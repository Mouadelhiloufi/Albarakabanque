# Al Baraka Bank

Application console Java 17 pour la gestion des clients, des comptes et des transactions bancaires, avec analyse des opérations et détection d'anomalies.

## Fonctionnalités

- Création d'un client et association d'un compte.
- Gestion des comptes courants et épargne.
- Versement et retrait avec mise à jour du solde.
- Virement entre deux comptes.
- Consultation de l'historique d'un compte, trié par date.
- Recherche de transactions suspectes dépassant 10 000 €.
- Top des clients par solde total.
- Rapport mensuel du nombre et du volume des transactions par type.
- Détection des comptes inactifs.
- Alertes sur les soldes faibles et l'inactivité.

## Technologies

- Java 17
- JDBC
- PostgreSQL
- Maven
- Stream API
- `record`
- `sealed class`
- `switch` expressions
- `Optional`
- `BigDecimal` pour les montants financiers

## Architecture

```text
src/
└── main/
    ├── java/
    │   ├── dao/
    │   │   ├── ClientDAO.java
    │   │   ├── ClientDAOImpl.java
    │   │   ├── CompteDAO.java
    │   │   ├── CompteDAOImpl.java
    │   │   ├── TransactionDAO.java
    │   │   └── TransactionDAOImpl.java
    │   ├── model/
    │   │   ├── Client.java
    │   │   ├── Compte.java
    │   │   ├── CompteCourant.java
    │   │   ├── CompteEpargne.java
    │   │   ├── Transaction.java
    │   │   └── TypeTransaction.java
    │   ├── service/
    │   │   ├── ClientService.java
    │   │   ├── CompteService.java
    │   │   ├── TransactionService.java
    │   │   └── RapportService.java
    │   ├── ui/
    │   │   └── ConsoleUI.java
    │   ├── util/
    │   │   ├── DatabaseConnection.java
    │   │   ├── DateUtil.java
    │   │   └── ValidationUtil.java
    │   └── Main.java
    └── resources/
        ├── database.properties
        └── schema.sql
```

## Modèle métier

### Client

`Client` est un `record` contenant :

- `id`
- `nom`
- `email`

### Compte

`Compte` est une classe `sealed` autorisant uniquement :

- `CompteCourant`, avec un découvert autorisé ;
- `CompteEpargne`, avec un taux d'intérêt.

### Transaction

Une transaction contient :

- une date ;
- un montant ;
- un type : `VERSEMENT`, `RETRAIT` ou `VIREMENT` ;
- un lieu ;
- l'identifiant du compte concerné.

## Configuration PostgreSQL

Créer une base de données PostgreSQL, par exemple :

```sql
CREATE DATABASE "AlbarakaBank";
```

Puis vérifier les paramètres dans :

```text
src/main/java/util/DatabaseConnection.java
```

Les valeurs actuelles sont :

```text
URL      : jdbc:postgresql://localhost:5432/AlbarakaBank
Utilisateur : postgres
Mot de passe : mouad
```

Il est recommandé de remplacer ces valeurs par celles de ton environnement et de ne jamais publier un vrai mot de passe dans Git.

Les tables utilisées par les DAO doivent correspondre exactement aux noms et colonnes des requêtes SQL :

```text
client
compte
tranasaction
```

Le nom `tranasaction` doit être conservé uniquement si c'est le nom réellement créé dans PostgreSQL. Sinon, renommer la table et utiliser le même nom dans `TransactionDAOImpl`.

## Lancer le projet

### Avec IntelliJ IDEA

1. Ouvrir le projet Maven.
2. Vérifier que le SDK du projet est Java 17.
3. Vérifier que `src/main/java` est marqué comme `Sources Root`.
4. Démarrer la classe `Main`.

### Avec Maven

Compiler le projet :

```bash
mvn clean compile
```

La classe principale est :

```text
Main
```

## Menu principal

L'application propose les sections suivantes :

```text
1. Gestion des clients & comptes
2. Gestion des transactions
3. Consultation de l'historique
4. Analyse & rapports
5. Alertes & notifications
0. Quitter
```

## Règles importantes

- Un versement augmente le solde du compte.
- Un retrait diminue le solde et respecte la limite de découvert du compte courant.
- Un compte épargne ne peut pas descendre sous zéro.
- Un virement nécessite un compte source et un compte destinataire différents.
- Une transaction est enregistrée après la mise à jour du solde.
- Les montants sont manipulés avec `BigDecimal`.

## Évolutions prévues

- Ajouter une vraie transaction SQL pour garantir l'atomicité d'un virement.
- Ajouter la détection des lieux inhabituels.
- Ajouter la détection des opérations rapprochées de moins d'une minute.
- Déplacer les paramètres PostgreSQL dans un fichier de configuration sécurisé.
- Ajouter des tests unitaires et d'intégration pour les services et les DAO.
