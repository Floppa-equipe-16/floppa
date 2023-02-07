# TP1

## Convention des commits
- message de commit en anglais
- Utilisations des tags avant le message :
    - build : changements qui affectent le système de build ou des dépendances externes (npm, make…)
    - ci : changements concernant les fichiers et scripts d’intégration ou de configuration (Travis, Ansible, BrowserStack…)
    - feat : ajout d’une nouvelle fonctionnalité
    - fix : correction d’un bug
    - perf : amélioration des performances
    - refactor : modification qui n’apporte ni nouvelle fonctionnalité ni d’amélioration de performances
    - style : changement qui n’apporte aucune alteration fonctionnelle ou sémantique (indentation, mise en forme, ajout d’espace, renommer une variable…)
    - docs : rédaction ou mise à jour de documentation
    - test : ajout ou modification de tests
- commit lorsqu'on a complété une unité de travail (fonction, refactoring, test, etc...)

## Stratégie de branchage
- Les branches de base sont la branche main qui contient les remises des itérations et la branche develop qui contient les intégrations des branches de feature
- La branche main est LA branche principale
- On crée une nouvelle branche par feature (issue ou regroupement d'issues avec points communs avec le tag 'Requis')
- On fait un pull request lorsque le développement d'une issue est terminé à partir de la branche de la feature vers la branche develop
- On fait également un pull request lorsqu'il est temps de la remise de la branche develop vers la branche main]()

## Analyse de formattage
Nous utilisons Spotless. Le goal maven est  ```mvn spotless:apply``` 

[Lien vers le plugin](https://mvnrepository.com/artifact/com.diffplug.spotless/spotless-maven-plugin)
## Planification du travail sur Github
  ### Issues
  Issue # 1
  ![i1](https://cdn.discordapp.com/attachments/1069318680736964628/1069319635431858176/image.png)
Issue # 2
  ![i2](https://cdn.discordapp.com/attachments/1069318680736964628/1069319739438010469/image.png)
Issue # 3
  ![i3](https://cdn.discordapp.com/attachments/1069318680736964628/1072633038602776646/image.png)

  ### Pull requests
  Pull request # 1
  ![pr1](https://cdn.discordapp.com/attachments/1069318680736964628/1069319129296797706/image.png)
    Pull request # 2
  ![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1072631459254378657/image.png)
  ![pr2](https://cdn.discordapp.com/attachments/1069318680736964628/1072631564921487532/image.png)
    Pull request # 3
  ![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1072632189285564556/image.png)
  ![pr3](https://cdn.discordapp.com/attachments/1069318680736964628/1072632281568657508/image.png)
  ### Arbre de commits
![a1](https://cdn.discordapp.com/attachments/1069318680736964628/1072633487720468560/image.png)

  ### Github Project
  ![g1](https://cdn.discordapp.com/attachments/1069318680736964628/1072636279881203712/image.png)
  ### Milestone
  ![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1072636832057143356/image.png)
  ![m1](https://cdn.discordapp.com/attachments/1069318680736964628/1072639673106714745/image.png)
