# Projet java Clément Bourles

### Pré-requis : 
Il n'y a pas de prérequis, ce labyrinthe fonctionne même avec des vielles versions de JAVA, et il peut fonctionnée de manière fluide sur des PC récents.

### Conseils d'utilisation : 
Par défaut un labyrinthe 10*10 s'ouvre. Vous pouvez le modifier en cliquant directement sur les cases que vous souhatez modifier.
Vous pouvez sélectionner le type de case que vous souhaitez ajouté en cliquant sur les boutons en bas. Note : un clic droit remplace les murs par des cases vides.
La distance est affiché à droite de l'écran.
Vous pouvez désactiver Dijsktra automatique. A chaque fois que le labyrinthe sera modifé, le trajet disparaitra, et vous pourrez appuyez sur le bouton "solve" pour calculer le nouveau chemin et l'afficher.

### Fonctionnalités :
* Le labyrinthe est jolie et ergonomique.
* Le labyrinthe change de taille de manière dynamique
* On peut choisir d'activer ou non le fait que Dijsktra ce fasse tout seul (un bouton apparait si on le désactiver pour le faire manuellement)
* Antiliasing : utilisation de Path2D plutot que des polygones pour avoir des coordonées qui peuvent ne pas être entière, et ainsi avoir des hexagones plus propres
* Quand Dijsktra est désactivé, le code est intéligent, il n'effacera l'ancien chemin que si celui ci est suseptible de changer.


### Commentaires sur le code :

* MVC : Ma méthode MVC qui utilise 3 classes différentes est, pour moi, celle qui respecte le mieux les principes de l'O.O
    * Modele : classe Model, elle contient le labyrinthe, le bouton sélectionné, ainsi que la mazebox qui est sous la souris. Elle ne connait que ses Observeur, mais ces les observeurs qui se font connaitre eux même, cela respecte pour moi l'O.O
    * Controleur : MazePanel, il connait le modèle et le modifie. Il connait aussi la vue et l'appel dans son PaintComponent. J'aurai bien aimé que le controlleur ne connaisse pas la vue, mais en java swing il n'est pas possible de faire autrement. Séparer l'execution des méthodes dans 2 classes permets, selon moi, de rendre le code plus lisible, et de le faire respecté plus les principes d'O.O. tout en respectant le principe MVC.
    * Vue : DrawMaze : il s'occupe de tous les calculs géométrique. C'est le MazePanel qui l'appel mais il récupère les données dans le modèle, et les affiches. Le MazePannel lui communique uniquement sont Graphics, ainsi que sa taille, et le DrawMaze va s'adapter tout seul a ces contraites. 
* Menu : Le code du menu est hybrides entre les items qui sont codées directement dans le menu, mais certain Menuitem plus long sont codés dans des classes hérités. Cela a pour objectif d'améliorer la lisibiltié.
* Lecture de fichier : le labyrinthe s'adaptera automatiquement à la taille du fichier donnée. Si le fichier présente un problème, le labyrinthe chargement vide, et un message popup apparaitra.
* Couleurs des cases : La couleurs des cases est un attribut static de chaque box. Cela permet un code plus lisible. L'alternative étant de faire un switch case, qui ne respecte pas non plus entièrement l'O.O. L'ajout de cet attribut n'empeche toute fois pas l'utilisation d'un switch case. Le principe d'O.O est selon moi toujours respecté.
* La méthode "chageBox" qui modifie le labyrinthe est "protégé" dans le sens où on lui indique en paramètre ce que l'on souhaite faire, et il va s'adapter tout seul si jamais cela pose des problèmes (départ ou arrivé modifié par exemple). Ainsi on ne modifie pas directement l'attribut, ce qui permet de respecté à 100% le principe de non-intrusion.
* L'utilisation de Path2D au lieu de Polygon permet d'avoir des coordonnées qui ne sont pas forcément entière, et ainsi d'avoir des hexagones plus propres.




