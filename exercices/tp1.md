# TP1

## Convention des commits
- message de commit en anglais
- Utilisations des tags avant le message :
    - build : changements qui affectent le système de build ou des dépendances externes (npm, make…)
    - ci : changements concernant les fichiers et scripts d’intégration ou de configuration (Travis, Ansible, BrowserStack…)
    - feat : ajout d’une nouvelle fonctionnalité
    - fix : correction d’un bug
    - perf : amélioration des performances
    - refactor : modification qui n’apporte ni nouvelle fonctionalité ni d’amélioration de performances
    - style : changement qui n’apporte aucune alteration fonctionnelle ou sémantique (indentation, mise en forme, ajout d’espace, renommante d’une variable…)
    - docs : rédaction ou mise à jour de documentation
    - test : ajout ou modification de tests
- commit lorsqu'on a complété un unité de travail (fonction, refactoring, test, etc...)

## Stratégie de branchage
- Les branches de base sont la branche main qui contient les remises des itérations et la branche develop qui contient les intégrations des branches de feature
- La branche main est LA branche principale
- On créer une nouvelle branche par feature (issue ou regroupement d'issues avec points communs avec le tag 'Requis')
- On fait un pull request lorsque le développement d'un issue est terminé à partir de la branche de la feature vers la branche develop
- On fait également un pull request lorsqu'il est temps de la remise de la branche develop vers la branche main
