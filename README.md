# Hello ☀️

Hello est la réalisation de du test technique pour la liste FizzBuzz.

## Index
- [Étapes](#etapes)
- [UI](#ui)
- [Formule](#formule)
- [Optimisation](#optimisation)
- [Conclusion](#conclusion)


## Étapes

J'ai découpé la réalisation en quelques 3 étapes assez simple : 

 - La réalisation de l'UI, sans aucune logique afin d'avoir un visuel de l'app sur lequel travailler.

 - Mettre en place la formule pour générer la liste, de manière la plus simple possible. L'objectif dans cette étape est de répondre au problème le plus rapidement possible

 - Une fois l'application fonctionelle, l'objectif est de l'optimiser au possible afin de la rendre prête pour la production.

## UI

Afin de réaliser l'UI de l'application, j'ai utilisé Jetpack Compose. Cela permet d'avoir quelque chose de simple et propre assez rapidement. 

Il a quand même fallut ajouter un peu de logique a cette étape pour gérer les données du formulaire.

Le tout se fait assez simplement avec un viewModel, les composants s'occupe uniquement de l'affichage. 

## Formule

Ici l'idée était d'obtenir la liste le plus facilement possible.
Pour cela, la première étape est d'utiliser une boucle for jusqu'à la limite pour générer la liste et l'envoyer à la vue.
Une fois fonctionnelle, le but est de tester l'app pour voir si des problèmes surviennent (notamment lorsque la limite est grande) et d'itérer afin de les corriger et optimiser le fonctionnement. 

## Optimisation

L'optimisation a été la partie la plus longue afin d'arriver à un résultat concluant. 
Comme prévu, l'utilisation d'une simple boucle ses limites.

Le premier problème a été le freeze de l'app de quelques secondes lors du chargement de la nouvelle page. 
La solution à été de réaliser les calcul dans un autre thread.

Une fois ce thread en place, l'utilisation de Flow permet d'incrémenter la liste à chaque calcul d'un élément.
Le problème a été que la vue devais se mettre à jour trop souvent et causais donc des ralentissement. Un buffer à été mis en place et calculer à partir de la limit donnée afin de mettre à jour la vue beaucoup moins souvent (par lot de 500 items par exemple).

Le dernier problème a été le remplissage de la mémoire au bout de quelque instant, avec une limite très haute. Malgré le buffer, les calculs continuent en arrière-plan et remplissent la mémoire. 

Pour palier à cela un système de "pagination" à été utilisé, afin d'effectuer le calcul uniquement pour un certain nombre d'élements nécessaires, en fonction du scroll dans la liste.

A ce point nous arrivons à une expérience fluide et sans soucis de mémoire, même avec une limite élevée. 

## Conclusion

La réalisation du projet aurait pu être plus simple si j'étais directement partie avec la solution optimale dès le début, mais je voulais voir au fur et à mesure les problèmes que je pouvais rencontrer et les solution que j'allais trouver à ces problèmes.

De plus en utilisant de nouveaux frameworks, j'ai rencontré des petits imprévus (gestion du cycle de vie avec Compose et les view model, scope de couroutines ou passage de LiveData à Flow puis States...) mais qui m'ont permis de mieux les apréhender. 

Avec plus de temps, il pourrait être utile de voir si, avec une grande limite et un scroll assez long, la liste ne prends pas trop de place en mémoire.

Également pousser un peu plus l'injection de dépendance avec Hilt (notamment pour mon manager) peut être une piste également

Une amélioration de l'UI est aussi très envisageable!

