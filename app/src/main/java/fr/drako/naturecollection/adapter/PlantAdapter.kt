package fr.drako.naturecollection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.drako.naturecollection.R

// C'est cette classe qu'on va donner au RecyclerView pour pouvoir adapter pour chaque plante son équivalent en image
// RecyclerView et notre classe maitresse, elle utilise la classe Adapter en lui préciseant le type de classe utilisée soit PlantAdapter.ViewHolder. ViewHolder est bien à l'intérieur de ma classe PlantAdapter
class PlantAdapter (private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // Va permettre de porter la vue, de faire une sorte de boite à composants.
    // Boite pour ranger tous les composants à controler
    // On passe notre vue au ViewHolder mais on la passe également au composant maître Android "RecyclerView" qui va utiliser notre classe ViewHolder
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        // image de la plante
        val plantImage = view.findViewById<ImageView>(R.id.image_item)

    }

    // Méthode qui injecte (inflate) un composant
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    // Mettre à jour chaque modele avec la plante en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    // Permet de renvoyer combien d'item on veut afficher dynamiquement
    override fun getItemCount(): Int = 5

}