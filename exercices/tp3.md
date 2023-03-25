# TP3
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
    prend de l'envergure, plus une petite implémentation non fonctionnelle peut avoir un impact sur la fonctionnalité du projet.


#### 2. L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)
    L'implémentation de tests ont augmenté le nombre de issue car on séparait le code fonctionnel avec l'implémentation des tests. 
    Cela a aussi augmenté le temps d'implémentation car il fallait implémenter les tests en même temps que le code fonctionnel.
    La planification a aussi été affecté car il fallait planifier le temps nécessaire pour implémenter les tests.
#### 3. Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.
    Les tests nous donne une confiance supplémentaire car ils nous permet de vérifier que certaine parties du code fonctionne correctement.
    Mais cela ne nous donne pas une confiance totale car les tests ne couvrent pas le fonctionnement globale de l'application.

#### 4. Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.
    - Améliorer et corriger les tests unitaires qui couvraient trop de cas en même temps.
    - Implémenter des tests d'intégration pour les fonctions qui ne sont pas testées.
    - Implémenter des tests end to end pour chaque routes.


