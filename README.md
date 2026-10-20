# MiniGED

Mini-application de **gestion électronique de documents (GED)** développée en Java.

J'ai lancé ce projet pour apprendre Java par la pratique, en travaillant sur les vraies problématiques d'un logiciel de GED : métadonnées, classement, traçabilité et intégrité des documents. Le projet avance par petites étapes et s'enrichit au fur et à mesure de ma progression.

## Fonctionnalités actuelles

- Modélisation d'un document et de ses métadonnées : titre, catégorie, auteur, déposant, nom du fichier d'origine, type MIME, taille, emplacement de stockage et date de dépôt.
- Date de dépôt fixée automatiquement par le système au moment de la création du document.
- Distinction entre les **faits** et les **descriptions** :
  - les faits (déposant, fichier d'origine, type MIME, taille, emplacement, date de dépôt) ne peuvent plus être modifiés après le dépôt, ce qui garantit la traçabilité ;
  - les descriptions (titre, catégorie, auteur) restent modifiables pour corriger ou reclasser un document.
- Catégories limitées à une liste fermée, pour éviter les documents mal classés à cause d'une faute de frappe.
- Représentation textuelle des documents, utile pour les logs et le débogage.

## Choix de conception

- **Encapsulation** : les attributs sont privés. La lecture passe par des getters, et seules les descriptions ont des setters.
- **Immutabilité des faits** : les informations liées au dépôt sont déclarées `final`, ce qui permet au compilateur de garantir qu'elles ne changeront jamais.
- **Classement logique et stockage physique séparés** : la catégorie sert au classement visible par l'utilisateur, tandis que l'emplacement de stockage est un chemin technique et unique, sans lien avec la catégorie. Reclasser un document ne déplace donc jamais le fichier.
- **Types précis plutôt que texte libre** : la catégorie est une `enum` et non un `String`, pour que le compilateur détecte les erreurs.

## Catégories disponibles

| Valeur | Signification |
|---|---|
| `INVOICE` | Facture |
| `CONTRACT` | Contrat |
| `PAY_SLIP` | Bulletin de paie |
| `CERTIFICATE` | Attestation |
| `MAIL` | Courrier |
| `MEETING_MINUTES` | Compte rendu de réunion |
| `ESTIMATE` | Devis |
| `OTHER` | Autre |

## Structure du projet

```
MiniGED/
├── src/
│   └── fr/miniged/
│       ├── MiniGedApp.java        Point d'entrée de l'application
│       ├── Document.java          Modèle d'un document et de ses métadonnées
│       └── DocumentCategory.java  Liste des catégories autorisées
├── .gitignore
└── README.md
```

## Prérequis

- JDK 17 ou plus récent (le projet est développé avec le JDK 26)

## Compiler et lancer le projet

Depuis la racine du projet :

```bash
javac -d out src/fr/miniged/*.java
java -cp out fr.miniged.MiniGedApp
```

Les fichiers compilés sont générés dans le dossier `out/`, qui n'est pas versionné.

## Conventions

- Code écrit en anglais, en suivant les conventions de nommage Java.
- Messages de commit au format [Conventional Commits](https://www.conventionalcommits.org/fr/) (`feat`, `fix`, `refactor`, `docs`, `chore`).

## Feuille de route

- [x] Modèle de document et de ses métadonnées
- [x] Catégories sous forme de liste fermée
- [ ] Gestion d'une collection de documents
- [ ] Recherche multicritère (par catégorie, auteur, titre)
- [ ] Validation des données et gestion des erreurs
- [ ] Classification automatique selon des règles simples
- [ ] Menu interactif en ligne de commande
- [ ] Stockage réel des fichiers sur disque
- [ ] Versioning des documents et piste d'audit
- [ ] Tests automatisés (JUnit)
- [ ] API REST
- [ ] Interface web en Web Components accessible (RGAA)
