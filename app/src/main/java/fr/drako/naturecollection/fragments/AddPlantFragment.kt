package fr.drako.naturecollection.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import fr.drako.naturecollection.MainActivity
import fr.drako.naturecollection.R

class AddPlantFragment(private val context: MainActivity): Fragment() {
    // val ça change pas de valeurs, var ça change de valeurs
    private var uploadedImage: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant, container, false)

        // Récupérer uploadedImage pour lui associer son composant.
        // Je crée mon composant et à la création de la vue ça va venir associer ma miniature (pour pouvoir la mettre à jour après le choix du user après mon onActivityResult)
        uploadedImage = view.findViewById(R.id.preview_image)

        // On récupère le bouton pour charger l'image miniature
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        // On lui associe une interaction, lorsqu'on clic dessus ça ouvre les images du téléphone
        pickupImageButton.setOnClickListener { pickupImage() }


        return view
    }

    private fun pickupImage() {
        // Pour ouvrir les images du téléphone on va créer une intent, pour ouvrir les contacts, images, changer d'activité etc...
        val intent = Intent()

        // Bien écrire image au singulier pour avoir accès au répertoire dans le téléphone
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT

        // Va envoyer une instruction et attend le résultat. Ici on récupérera l'image choisie
        // Request_code, numéro pour permettre de dire l'action qui va être faite (ici au hasard c'est le 47)
        // startActivityForResult -> On envoie l'action
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    // Quand l'activité a fini de chercher elle va lancer cette méthode, renvoie le result_code et les données
    // onActivityResult -> On récupère l'action
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // On controle que c'est bien le bon resultcode demandé et que le user a bien choisi une image
        if (requestCode == 47 && resultCode == Activity.RESULT_OK) {
            // On vérifie si les données réceptionnées sont null
            if (data == null || data.data == null) return

            // Si c'est valide on récupère l'image sélectionnée
            val selectedImage = data.data

            // On met à jour l'aperçu de l'image miniature
            // uploadedImage étant initialisée à null
            // Toujours bien mettre le ? pour gérer le cas d'une valeur à null. Ici on va forcer pour attribuer la valeur de l'image sélectionnée tuto à 04:06:24.

            uploadedImage?.setImageURI(selectedImage)
        }
    }

}