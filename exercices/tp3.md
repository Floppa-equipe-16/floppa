# TP3
## User Stories

### Évaluation de vendeur

#### Description

En tant qu'acheteur, je peux évaluer les vendeurs afin de donner mon opinion sur leur service après avoir acheté un produit.

#### Critères de succès

- L'acheteur du produit est spécifié.
- L'acheteur est bel et bien le détenteur de l'offre sélectionné.
- Le message de l'évaluation est d'au moins 100 caractères.
- L'évaluation possède une date de création non modifiable.
- Le vendeur et le produit doivent être spécifiés.
- L'acheteur ne peut pas avoir déjà évalué le vendeur pour le même produit.

### Notification du vendeur par courriel

#### Description

En tant que vendeur, je suis averti par courriel lorsqu'une nouvelle offre est créée pour un de mes produits afin de pouvoir la consulter.

#### Critères de succès

- Le courriel doit être envoyé au vendeur du produit.
- Le courriel doit contenir le nom du produit.
- Le courriel doit contenir le nom de l'acheteur.
- Le courriel doit contenir le prix de l'offre.

### Historique d'achats

#### Description

En tant qu'acheteur, je peux consulter l'historique de mes achats afin de pouvoir voir les produits que j'ai achetés.

#### Critères de succès

- Je vois l'information de base du produit.
- Je vois le prix du produit à laquelle il a été acheté.
- Je vois quelques statistiques sur les achats (montant total, moyenne, prix minimum, prix maximum).
- Le prix minimum des statistiques est null si aucun produit n'a été acheté.
- Le prix maximum des statistiques est null si aucun produit n'a été acheté.
- La moyenne des statistiques est null si aucun produit n'a été acheté.

### Classement des vendeurs

#### Description
En tant qu'utilisateur, je peux consulter les meilleurs vendeurs avec leurs scores respectifs

#### Critères de succès

- Je vois les informations de base du vendeur.
- Je vois le score du vendeur.
- Je vois les vendeurs classés par score décroissant.
- Je vois le nombre de vendeurs demandé.


## Rétrospective sur le processus
### Pipeline CI
#### 1. Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?
    1 heure
#### 2. Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?
    15 minutes
#### 3. Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.
    - Le CI permet de vérifier que le code est fonctionnel avant de le merger, donc qu'il puisse build.
    - Le CI permet de vérifier que le formattage du code est fait
    - Le CI permet de vérifier que les tests fonctionnent   
#### 4. Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangereux pour le processus, le produit et/ou l'équipe? Justifiez.
    - Le CI peut être dangereux si les tests ne sont pas bien écrits, car il peut donner une fausse impression de sécurité.

### Tests
#### 1. Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?
    La proportion était de 1/2 tests et 1/2 code fonctionnel. Cette proportion évolue au fil du temps car plus le projet
    prend de l'envergure, plus une petite implémentation non fonctionnelle peut avoir un impact sur la fonctionnalité 
    du projet.


#### 2. L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)
    L'implémentation de tests ont augmenté le nombre de issue car on séparait le code fonctionnel avec l'implémentation
    des tests. 
    Cela a aussi augmenté le temps d'implémentation car il fallait implémenter les tests en même temps que le code fonctionnel.
    La planification a aussi été affecté car il fallait planifier le temps nécessaire pour implémenter les tests.
#### 3. Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.
    Les tests nous donne une confiance supplémentaire car ils nous permet de vérifier que certaine parties du code fonctionne correctement.
    Mais cela ne nous donne pas une confiance totale car les tests ne couvrent pas le fonctionnement globale de l'application.

#### 4. Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.
    - Améliorer et corriger les tests unitaires qui couvraient trop de cas en même temps.
    - Implémenter des tests d'intégration pour les fonctions qui ne sont pas testées.
    - Implémenter des tests end to end pour chaque routes.

## Planification du travail sur Github
### Github Project
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1093216282641440878/image.png)
### Milestone
![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1093218674766249994/image.png)
![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1093218614817062964/image.png)
### Issues
#### Issue # 1
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1093219000181334146/image.png)
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1093219249608208504/image.png)
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1093219329685856377/image.png)

#### Issue # 2
![i2](https://cdn.discordapp.com/attachments/1069318680736964628/1093219576973643787/image.png)

#### Issue # 3
![i3](https://cdn.discordapp.com/attachments/1069318680736964628/1093219907958751353/image.png)

### Pull Requests
#### Pull request # 1
![pr1](https://cdn.discordapp.com/attachments/1069318680736964628/1093220118915448872/image.png)
![pr1](https://cdn.discordapp.com/attachments/1069318680736964628/1093221986785837157/image.png)
#### Pull request # 2
![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1093220225182351440/image.png)
![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1093222209037811803/image.png)
#### Pull request # 3
![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1093222823423651850/image.png)
![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1093222876850696292/image.png)
### Arbre de commits
![a1](https://cdn.discordapp.com/attachments/1069318680736964628/1093217842586992731/image.png)