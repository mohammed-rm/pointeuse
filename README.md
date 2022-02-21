# Projet Java : Pointeuse

## Sujet proposé et encadré par Monsieur Carl Esswein - Polytech Tours

### Étudiants :  
- **Theo BOISSEAU (theo.boisseau@etu.univ-tours.fr)**
- **Sarah DENIS (sarah.denis-2@etu.univ-tours.fr)**  
- **Mohammed RMICH (mohammed.rmich@etu.univ-tours.fr)**  
- **Chadi YASSIN (chadi.yassin@etu.univ-tours.fr)**

#### Application
L'application développée permet de gérer les pointages (arrivées et départs) des employés d'une entreprise donnée.  

Les sources relatives au deux programmes demandés ont été codées dans un seul
projet *Eclipse*, les classes sont ainsi organisées selon des packages de **l'application principale** et de **l'émulateur**.
Nous avons utilisé le modèle **MVC** afin de mieux structurer notre projet et lui donner 
une meilleur lisibilité. Nous avons réparti l'ensemble de nos classes dans des packages **model**, **view**, et **controller**
qui sont propres à chacun des deux programmes (application principale, émulateur).  
En plus, nous avons créé des packages dont les classes permettent de s'occuper de la communication **TCP**
(**controller.mainapp.tcp** et **controller.emulator.tcp**), et d'autres dont les classes (mais pas les instances) sont partagées entre **l'application principale** et **l'émulateur**.  
Nous avons également suivi une conception qui respecte les procédures du *génie logiciel*,
dans le but de rendre l'entretien de notre code plus facile.  

Au niveau de l'emplacement des méthodes main, nous avons créé une classe **Simulation**
qui se trouve dans le package **launching**, celle-ci s'occupe de lancer les deux applications,
afin de faciliter à l'utilisateur la réalisation d'une simulation. Les fichiers de sauvegarde des données sérialisées
de l'application principale (respectivement l'émulateur) se trouve dans le dossier **backupMainapp** (respectivement **backupEmulator**). Afin que la sauvegarde puisse avoir lieu correctement à la fin de la simulation,
il suffit de fermer **d'abord** la fenêtre principale de l'émulateur,**et ensuite** celle de l'application principale.
Les sauvegardes se feront à des intervalles réguliers.   

Afin que l'émulateur puisse communiquer avec l'application principale, celle-ci doit être allumée. Une fois la connexion établie,
l'émulateur reçoit les dernières mises à jour des listes des employées inscrits dans le système, ce qui lui permettra
de valider ou non un identifiant, lors d'un pointage.
