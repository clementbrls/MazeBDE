# Projet java Clément Bourles

### Pré-requis : 
Il n'y a pas de prérequis, ce labyrinthe fonctionne même avec des vielles versions de JAVA, et il peut fonctionner de manière fluide sur des PC récents.

### Conseils d'utilisation : 
Par défaut un labyrinthe aléatoire 15*25 s'ouvre. Vous pouvez le modifier en cliquant directement sur les cases que vous souhaitez modifier.
Vous pouvez sélectionner le type de case que vous souhaitez ajouter en cliquant sur les boutons en bas. Note : un clic droit remplace les murs par des cases vides.
La distance est affichée à droite de l'écran.
Vous pouvez désactiver Dijsktra automatique. A chaque fois que le labyrinthe sera modifié, le trajet disparaitra, et vous pourrez appuyer sur le bouton "solve" pour calculer le nouveau chemin et l'afficher.
La touche A ou la barre d'espace du clavier vous permettent de rapidement générer un nouveau labyrinthe aléatoire.

### Fonctionnalités :
* Le labyrinthe est joli et ergonomique.
* Le labyrinthe est généré aléatoirement. 
* Le labyrinthe change de taille de manière dynamique en fonction de la fenêtre
* On peut choisir d'activer ou non le fait que Dijkstra se fasse tout seul (un bouton apparait si on le désactive pour le faire manuellement)
* La vue utilise un antialiasing pour rendre le labyrinthe plus joli
* Quand Dijkstra est désactivé, le code est intelligent, il n'effacera l'ancien chemin que si celui-ci est susceptible de changer.


### Commentaires sur le code :

* MVC : Ma méthode MVC qui utilise 3 classes différentes est, pour moi, celle qui respecte le mieux les principes de l'O.O
    * Modele : classe Model, elle contient le labyrinthe, le bouton sélectionné, ainsi que la mazebox qui est sous la souris. Les observateurs viennet se renseigner grâce à 'addObserver' et sont prévenus de tout changement grâce à 'setChanged'
    * Controleur : MazePanel, il connait le modèle et le modifie. Il connait aussi la vue et l'appel dans son PaintComponent. J'aurais bien aimé que le controlleur ne connaisse pas la vue, mais en java swing, il n'est pas possible de faire autrement. Séparer l'execution des méthodes dans 2 classes permets, selon moi, de rendre le code plus lisible, et de le faire respecter plus les principes d'O.O. tout en respectant le principe MVC. Il ne fait qu'écrire dans le modèle, il ne le lit pas.
    * Vue : DrawMaze : il s'occupe de tous les calculs géométriques. C'est le MazePanel qui l'appel, mais il récupère les données dans le modèle et les affiches. Le MazePanel lui communique uniquement sont Graphics, ainsi que sa taille et le DrawMaze va s'adapter tout seul à ces contraintes. Il ne connait que le modèle, mais il ne fait que le lire.
* Menu : Le code du menu est hybride entre les items qui sont codés directement dans le menu, mais certain Menuitem plus long sont codés dans des classes héritées. Cela a pour objectif d'améliorer la lisibilité.
* Lecture de fichier : le labyrinthe s'adaptera automatiquement à la taille du fichier donnée. Si le fichier présente un problème, le labyrinthe chargement vide et un message popup apparaitra.
* Couleurs des cases : les mazeBox propose une couleur pour la vue. cette couleur est proposée sous forme d'attribut static. En faisant ça, les mazeBox ne connaisse pas la vue, mais la vue connait les couleurs des cases. Cela permet de respecter le principe de non-intrusion.
* La méthode "changeBox" qui modifie le labyrinthe est "protégé" dans le sens où on lui indique en paramètre ce que l'on souhaite faire, et il va s'adapter tout seul si jamais cela pose des problèmes (départ ou arrivé modifié par exemple). Ainsi on ne modifie pas directement l'attribut, ce qui permet de respecter à 100% le principe de non-intrusion.
* L'utilisation de Path2D au lieu de Polygon permet d'avoir des coordonnées qui ne sont pas forcément entières, et ainsi d'avoir des hexagones plus propres.
* VertexPath permet de connaitre le chemin de Dijkstra, et ainsi de l'afficher.
* Chaque class a un Label statique, il n'est absolument pas utilisé en tant que getLabel, cela est utile uniquement au moment du changeBox, pour être plus clair, mais cela respecte l'O.O
* Le générateur de Graph aléatoire utilise l'algorithme de parcours en profondeur pour créer un chemin. La méthode prend en entrée un graph, ainsi il est utilisable pour tous les graphes, et non seulement pour les labyrinthes.