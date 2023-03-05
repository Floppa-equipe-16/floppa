# TP2
## Rétrospective sur le processus
#### Combien de temps (moyenne, minimum, maximum) votre équipe prenait-elle pour implémenter une issue?
- moyenne: 1h00
- minimum: 30min
- maximum: 2h30

#### Combien de temps (moyenne, minimum, maximum) votre équipe prenait-elle pour intégrer une pull-request? (review + correctifs)
- moyenne: 12h00
- minimum: 1h
- maximum: 24h

#### Combien de personnes (moyenne, minimum, maximum) travaillaient sur chaque issue? (individuel, paire, équipe, etc.)
- moyenne: paire (2 personnes)
- minimum: individuel (1 personne)
- maximum: équipe (5 personnes)

#### Combien de personnes (moyenne, minimum, maximum) reviewaient chaque pull-request?
- moyenne: 4 personnes
- minimum: 3 personnes
- maximum: 4 personnes

#### Combien d'issue (moyenne, minimum, maximum) étaient en cours d'implémentation en même temps?
- moyenne: 3 issues
- minimum: 1 issue
- maximum: 5 issues
#### Combien de pull-requests (moyenne, minimum, maximum) étaient en cours de review en même temps?
- moyenne: 1 pull-request
- minimum: 1 pull-request
- maximum: 2 pull-requests
### Réflexions 
Après avoir mesuré ces métriques, voici les réflexions que vous devez poser en équipe et répondre dans le document d'exercice :

#### Selon vous, est-ce que les issues/pull-requests prenaient trop de temps à être terminées? Ou pas assez? Quel serait le temps idéal (approximatif) pour chacun?
- Le temps pour les issues était correct, mais pour les pull-requests, il était trop long. Le temps idéal serait de 1h pour les issues et 6h pour les pull-requests.
#### Quel est le lien entre la taille de ces issues/pull-requests et le temps que ça prenait à les terminer?
- Plus la taille des issues/pull-requests est grande et plus le temps pour les terminer est long.
#### Donnez au moins 3 trucs pour améliorer votre processus (tailles des issues/pr, communication, code reviews, uniformisation, etc.)
1. Réduire la taille des issues/pr
2. Activer les notifications sur discord et sur github pour les pull-requests
3. Faire des revues de code plus rapides et approfondies
## Planification du travail sur Github
### Issues
Issue # 1
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1082043528420528188/image.png)
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1082043583860854804/image.png)
![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1082043679876857906/image.png)
Issue # 2
![i2](https://cdn.discordapp.com/attachments/1069318680736964628/1082043441237725214/image.png)
Issue # 3
![i3](https://cdn.discordapp.com/attachments/1069318680736964628/1082044287002345632/image.png)

### Pull requests
Pull request # 1
![pr1](https://cdn.discordapp.com/attachments/1069318680736964628/1082045301512212490/image.png)
Pull request # 2
![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1082044528904642640/image.png)
![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1082044597376647279/image.png)
Pull request # 3
![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1082044991876104202/image.png)
![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1082045081453871104/image.png)
### Arbre de commits
![a1](https://cdn.discordapp.com/attachments/1069318680736964628/1082042128936489020/image.png)

### Github Project
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1082042405886361600/image.png)
### Milestone
![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1082042647239209161/image.png)
![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1082042771692584990/image.png)

### Diagrammes
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1082045245463736440/diag.drawio_3.png)
#### Description du projet
SellingService est le seul lien de l'api vers le domaine. Il permet de traiter les requêtes de l'api en manipulant les différentes classes du domaine.
Chaque entité du domaine (Seller, Offer, Product) est supporté par les classes suivantes : 
- Factory : Gère la logique de création des instances de l'entité
- Validator: Effectue la validation des attributs des entités
- Repository: Gère la persistance des entités. Pour les inMemoryRepository, on sauvegarde les entités dans la mémoire. 
- Mapper: Gère la conversion des entités du domaine en entités de l'api (Request, Response) et vice versa.

Ensuite, chaque entité du domaine est représentée par une classe Resource dans l'api qui capte la requête et la redirige vers le SellingService.

#### Justification des choix
D'abord, le choix des Repository a été fait en prévision de l'implémentation de la base de données. Les Factory ont été ajouter pour séparer et ainsi faciliter le testage de la validation et
de la logique de création. L'ajout de la couche Service a été fait pour séparer l'api et le domaine.
Chaque Mapper a été ajouté pour réduire le nombre d'étapes de conversion entre les classes du domaine et les classes de l'api.
Le reste des classes sont inspirés par les laboratoires.
#### Potentiels problèmes et solutions
Les Mapper ont beaucoup de dépendances ce qui les rend fragiles aux changements. La couche service dépend énormément de la couche api ce qui peut causer des problèmes si jamais il y a des changements à la couche api.
Au lieu du Mapper, on pourrait utiliser des DTO (Data Transfer Object) pour réduire le nombre de dépendances. Chaque Response utiliserait un DTO dans son constructeur. Ça réduirait la dépendance de la couche service à la couche api, mais ça rendrait l'api fragile.
Hormis les cas discutés, le reste des classes sont assez indépendantes et ne devraient pas poser de problèmes.

![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1082045244838785085/diag.drawio_6.png)
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1082045245052702800/diag.drawio_5.png)
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1082045245262397450/diag.drawio_4.png)