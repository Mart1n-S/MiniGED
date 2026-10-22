# MiniGED

Mini-application de **gestion électronique de documents (GED)** développée en Java.

J'ai lancé ce projet pour apprendre Java par la pratique, en travaillant sur les vraies problématiques d'un logiciel de GED : métadonnées, classement, traçabilité et intégrité des documents. Le projet avance par petites étapes et s'enrichit au fur et à mesure de ma progression.

## Fonctionnalités actuelles

- **Menu interactif en ligne de commande**, avec identification de l'utilisateur au démarrage.
- **Ajout de documents** depuis le menu, avec une **catégorie proposée automatiquement** à partir du titre, que l'utilisateur peut accepter ou corriger.
- **Recherche multicritère** :
  - par catégorie ;
  - par auteur, sans tenir compte des majuscules ;
  - par mot-clé contenu dans le titre.
- **Validation des données** : un document ne peut être ni créé ni modifié avec des informations manquantes ou une taille invalide. Les erreurs de saisie sont signalées à l'utilisateur sans interrompre l'application.
- **Métadonnées complètes** pour chaque document : titre, catégorie, auteur, déposant, nom du fichier d'origine, type MIME, taille, emplacement de stockage et date de dépôt.
- **Distinction entre les faits et les descriptions** :
  - les faits (déposant, fichier d'origine, type MIME, taille, emplacement, date de dépôt) ne peuvent plus être modifiés après le dépôt, ce qui garantit la traçabilité ;
  - les descriptions (titre, catégorie, auteur) restent modifiables pour corriger ou reclasser un document.

## Utilisation

Au lancement, l'application demande d'abord de s'identifier, puis affiche le menu :

```
=== MiniGED ===
1. Afficher tous les documents
2. Rechercher par catégorie
3. Rechercher par auteur
4. Rechercher par mot-clé
5. Ajouter un document
0. Quitter
```

Quelques documents de démonstration sont chargés au démarrage pour pouvoir tester les recherches immédiatement.

## Choix de conception

- **Une responsabilité par classe** : le modèle (`Document`), le stockage (`DocumentRepository`), la classification (`DocumentClassifier`) et le dialogue avec l'utilisateur (`ConsoleMenu`) sont séparés.
- **Encapsulation** : les attributs sont privés. La lecture passe par des getters, et seules les descriptions ont des setters.
- **Immutabilité des faits** : les informations liées au dépôt sont déclarées `final`, ce qui permet au compilateur de garantir qu'elles ne changeront jamais.
- **Validation à toutes les portes d'entrée** : le constructeur et chaque setter vérifient les données. Tout document qui existe est donc forcément valide.
- **Collection protégée** : le référentiel ne donne jamais accès à sa liste interne, seulement à des copies en lecture seule.
- **Injection de dépendances** : le menu reçoit le référentiel et le classificateur par son constructeur, au lieu de les créer lui-même.
- **Classement logique et stockage physique séparés** : la catégorie sert au classement visible par l'utilisateur, tandis que l'emplacement de stockage est un chemin technique et unique (année, mois et identifiant UUID), sans lien avec la catégorie. Reclasser un document ne déplace donc jamais le fichier.
- **Sécurité des noms de fichiers** : le nom saisi par l'utilisateur n'est jamais utilisé tel quel pour le stockage, et seules les extensions simples sont conservées, pour éviter les attaques par traversée de répertoire.
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

## Limites connues

- Les documents sont conservés **en mémoire** : ils sont perdus à la fermeture de l'application.
- Aucun fichier n'est réellement stocké : la taille et le type MIME sont **simulés** à partir du nom du fichier.
- La classification automatique repose sur des mots-clés dans le titre. Si un titre contient plusieurs mots-clés, la catégorie proposée n'est pas garantie.
- Les catégories sont affichées avec leur nom technique (`INVOICE`) plutôt qu'avec un libellé en français.

## Structure du projet

```
MiniGED/
├── src/
│   └── fr/miniged/
│       ├── MiniGedApp.java          Point d'entrée de l'application
│       ├── ConsoleMenu.java         Menu interactif et dialogue avec l'utilisateur
│       ├── Document.java            Modèle d'un document et de ses métadonnées
│       ├── DocumentCategory.java    Liste des catégories autorisées
│       ├── DocumentClassifier.java  Proposition automatique de catégorie
│       └── DocumentRepository.java  Conservation et recherche des documents
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

Les fichiers compilés sont générés dans le dossier `out/`, qui n'est pas versionné. Pour repartir d'une compilation propre, supprimer ce dossier avant de recompiler.

## Conventions

- Code écrit en anglais, en suivant les conventions de nommage Java.
- Messages de commit au format [Conventional Commits](https://www.conventionalcommits.org/fr/) (`feat`, `fix`, `refactor`, `docs`, `chore`).

## Feuille de route

- [x] Modèle de document et de ses métadonnées
- [x] Catégories sous forme de liste fermée
- [x] Gestion d'une collection de documents
- [x] Recherche multicritère (par catégorie, auteur, titre)
- [x] Validation des données et gestion des erreurs
- [x] Classification automatique selon des règles simples
- [x] Menu interactif en ligne de commande
- [ ] Libellés en français pour les catégories
- [ ] Stockage réel des fichiers sur disque
- [ ] Versioning des documents et piste d'audit
- [ ] Tests automatisés (JUnit)
- [ ] Gestion du projet avec Maven
- [ ] API REST
- [ ] Interface web en Web Components accessible (RGAA)
