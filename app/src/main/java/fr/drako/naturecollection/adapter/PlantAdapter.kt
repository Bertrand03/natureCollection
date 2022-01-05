package fr.drako.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.drako.naturecollection.MainActivity
import fr.drako.naturecollection.PlantModel
import fr.drako.naturecollection.R

// C'est cette classe qu'on va donner au RecyclerView pour pouvoir adapter pour chaque plante son équivalent en image
// RecyclerView et notre classe maitresse, elle utilise la classe Adapter en lui préciseant le type de classe utilisée soit PlantAdapter.ViewHolder. ViewHolder est bien à l'intérieur de ma classe PlantAdapter
class PlantAdapter (private val context: MainActivity,
                    private val plantList: List<PlantModel>,
                    private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // Va permettre de porter la vue, de faire une sorte de boite à composants.
    // Boite pour ranger tous les composants à controler
    // On passe notre vue au ViewHolder mais on la passe également au composant maître Android "RecyclerView" qui va utiliser notre classe ViewHolder
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        // image de la plante
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById(R.id.name_item) // "?" mis pour dire qu'il faut qu'il tente de récupérer le texte. Si pas possible, ne change pas le texte. "Si le composant est null ne change pas son texte"
        val plantDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    // Méthode qui injecte (inflate) un composant
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    // Mettre à jour chaque modele avec la plante en question. On récupère toutes les informations de la plante en fonction de son index
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // récupérer les informations de la plante qui a la position définie
        val currentPlant = plantList[position]

        // utiliser Glide pour récupérer l'image à partir de son lien -> composant
        // le context c'est une sorte de base de données interne qui va contenir toutes les informations contextuelles de l'application
        // comme le num de version, les informations spécifiques de l'activité etc...
//        Pour récupérer ce context on va devoir faire un passage de la MainActivity (qui contient ce context) pour passer ces informations d'une classe à une autre
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage) // Glide va jouer son rôle de générateur d'images à partir du context. On charge une image à partir d'un lien

        // Mettre à jour le nom de la plante (utiliser le nom de la plante au lieu du nom générique donné pour l'exemple)
        holder.plantName?.text = currentPlant.name

        // Mettre à jour la description de la plante (utiliser le nom de la plante au lieu du nom générique donné pour l'exemple)
        holder.plantDescription?.text = currentPlant.description

        // Vérifier si la plante a été likée ou non
        if (currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

    }

    // Permet de renvoyer combien d'item on veut afficher dynamiquement
    override fun getItemCount(): Int = plantList.size

}