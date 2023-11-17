# Task

## Énoncé

On aimerait pouvoir créer une API pour gérer l’équipe de football de Nice en ligue 1.
Le directeur sportif du club voudrait répertorier en base de données la liste de
   ses joueurs et le budget de l’équipe afin de gérer au mieux le marché de transfert
   à venir.

## Consigne
   Faire une API Rest qui aura 2 méthodes (l’ajout d’autres méthodes n’est pas de
   refus et sera considéré comme un bonus) :
- Une qui retournera une liste d’équipes contenant chacune une liste de joueurs. 
  La liste sera paginée et pourra être triée côté serveur (tri sur nom 
  d’équipe, acronyme et budget).
- Une autre qui permettra d’ajouter une équipe avec ou sans joueurs
  associés (tous les autres champs sont obligatoires).

## Environnement technique 
- Spring
- Hibernate
- Base de données au choix (embarqué, postgres, oracle, …)

A la livraison du projet, merci d’envoyer un guide d’installation pour pouvoir
 tester la solution, ainsi que le code source. 
Chaque choix technique devra être expliqué.

Exemple de modèle de données:
- Équipe : [id, name, acronym, joueurs, budget]
- Joueur : [id, name, position]

Seront évalués :
- Architecture
- Choix techniques
- Commentaires /Javadoc / Logs
- Tests unitaires et tests d’intégration.
