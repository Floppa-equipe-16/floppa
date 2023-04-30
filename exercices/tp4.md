# TP4
## Open Sourcing 
### 1. Nommez 3 avantages à contribuer à des projets open source en tant qu'entreprise et justifiez en quoi cela peut être bénéfique pour tous.
- Visibilité : En contribuant a des projets open source, une entreprise peut se faire connaître par la communauté open source et par les utilisateurs de ces projets.
- Accès a des technologies innovantes : Les projets open source sont souvent a la pointe de la technologie. En contribuant a ces projets, une entreprise peut accéder a des technologies innovantes.
- Économie de temps et d'argent : En contribuant a des projets open source, une entreprise peut profiter des efforts de la communauté pour développer des logiciels et des technologies.
### 2. Décrivez 3 défis qu'impose la mise en place d'un projet open source et justifiez.
- Assurer la qualité du code : En open source, le code est modifié et utilisé par des milliers de personne. Il est donc d'avoir une gestion stricte des contributions, des revues de code rigoureuse et une attention constante à la qualité du code produit.
- Maintenir l'engagement des contributeurs : En open source, les contributeurs sont bénévoles. Il est donc important de maintenir leur engagement en les remerciant et en les impliquant dans le projet. Il est aussi important de les inciter à continuer de contribuer au projet.
- Gérer la gouvernance du projet : Les membres de la communauté peuvent avoir des opinions divergentes sur la direction du projet, les fonctionnalités à ajouter, les normes de qualité à suivre, etc. 
### 3. Quelle information vous a-t-elle le plus surprise à propos de l'open source?
L'ampleur de l'open source. Nous avons été surpris d'apprendre que l'open source était adapté à travers le monde entier et que autant de projets open source existaient. Des projets open source sont utilisés par des millions de personnes et sont maintenus par des milliers de contributeurs.
    
## Outils d'analyse de code
### Qualité du code - PMD
Extrait des logs pendant l'exécution de verify
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099014439337996298/pmd-exec-trace.png)
Rapport HTML résultant d'un verify
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099014439568674816/pmd-report.png)

### Failles de sécurité - Snyk
Information sur le pom.xml
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099705348765536366/snyk-pom-issues.png)
Intégration dans le CI
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099705349038145646/snyk-ci.png)
Détail d'un check dans le CI
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099705349281435658/snyk-error-free-pr.png)

### Test coverage - Jacoco
Extrait des logs pendant l'exécution de verify
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099014439044386836/jacoco-test-fail.png)
Rapport HTML résultat d'un verify
![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1099014439765803088/jacoco-report.png)
