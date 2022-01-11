package fr.drako.naturecollection

//Après PlantModel on supprime les accolades et on met des parenthèses pour lister et donner toutes les caractéristiques d'une plante
//J'ai créé cette classe au moment où, dans le cours, on cherche à remplacer l'image par défaut répétée à chaque fois. On veut afficher des plantes différentes dans notre liste
// Après avoir créé ma bdd et déclarer mes plantes, quand j'ajoute une plante sur Firebase je n'ai pas besoin de relancer mon application pour que ça rafraichisse les valeurs. Ca le fait tout seul.

class PlantModel (
    // val pour valeur que l'on attribue. valeur fixe ?
    // var pour pouvoir changer la valeur, ici c'est pour montrer si la plante a été liké ou pas (logo étoile)
    val id: String = "plant0",
    val name: String = "Tulipe",
    val description: String = "Petite description",
    val imageUrl: String = "http://graven.yt/plante.jpg",
    val grow: String = "Faible",
    val water: String = "Moyenne",
    var liked: Boolean = false
)