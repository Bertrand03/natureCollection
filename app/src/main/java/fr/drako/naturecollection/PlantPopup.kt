package fr.drako.naturecollection

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.drako.naturecollection.adapter.PlantAdapter

// Il faut déclarer cette classe en Dialog. On prend en paramètre du dialog la MainActivity car on veut que notre pop up apparaisse par dessus notre activité principale
// En paramètre du dialog se trouve le context. En cas d'erreur sur le context il faut passer la souris dessus et le passer en public

class PlantPopup(private val adapter: PlantAdapter,
                private val currentPlant: PlantModel) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         // Sur la fenetre pop up on ne veut pas de titre
        requestWindowFeature(Window.FEATURE_NO_TITLE)

         //va permettre d'injecter le layout juste au dessus
        setContentView(R.layout.popup_plants_details)

        // Pour initialiser nos composants avec les bonnes valeurs, pour que les images de la pop up correspondent bien à l'image sur laquelle on a cliqué
        setupComponents()

    }

    private fun setupComponents() {
        // Actualiser l'image de la plante. Grâce à l'id unique on récupère notre image qui porte le nom passé en paramètre
        val plantImage = findViewById<ImageView>(R.id.image_item)

        // Pour actualiser l'image on va devoir récupérer notre imageUrl et utiliser Glide pour pouvoir actualiser ce composant
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        // Actualiser le nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name

        // Actualiser la description
        findViewById<TextView>(R.id.popup_plant_desription_subtitle).text = currentPlant.description

    }


}