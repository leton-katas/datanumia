Yatzy Refactoring Kata Java Version
===================================

Dans ce Kata, j'ai procédé au refactoring de la classe Yatzy1.

Reste à faire : 

- Vérifier la valeur d'un dé
- Introduire une classe Roll pour encapsuler les dés et les traitements afférents
- Créer une méthode qui prendra un Roll (liste de valeurs de dés) et une catégorie (chance, yatzy, ...)
- Revoir les tests en factorisant les tests qui se répètent (@ParametrizedTest et @MethodSource 
pour fournir les valeurs des tests)
- Remplacer les valeurs magiques dans le code par des constantes biens définies
- Renommage de la classe Yatzy (YatzyGame ?)