package fr.drako.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.drako.naturecollection.MainActivity
import fr.drako.naturecollection.PlantModel
import fr.drako.naturecollection.R
import fr.drako.naturecollection.adapter.PlantAdapter
import fr.drako.naturecollection.adapter.PlantItemDecoration

/*On met un :Fragment() pour déclarer que c'est un fragment (va importer via l'import androidx*/
class HomeFragment (private val context: MainActivity): Fragment() { // récupération du context (utilisé avec Glide pour récupérer les images à partir d'une url). Utilisé ensuite par PlantAdapter

    /*Cette fonction va se charger d'intégrer un layout via inflater*/
    /*Lors d'un appel via onCreateView on va charger le fragment déclaré dans le return dans notre HomeFragment*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        // créer une liste qui va stocker ces plantes
        val plantList = arrayListOf<PlantModel>()

        // enregistrer une première plante dans notre liste (pissenlit)
        // On instancie une classe de plante que l'on veut créer
        plantList.add(
            PlantModel(
            "Pissenlit",
            "jaune soleil",
            "https://cdn.pixabay.com/photo/2014/05/01/17/51/flower-335662_960_720.jpg",
            false
        ))

        // enregistrer une deuxième plante dans notre liste (rose)
        plantList.add(
            PlantModel(
                "Rose",
                "ça pique un peu",
                "https://cdn.pixabay.com/photo/2018/05/16/22/27/rose-3407234_960_720.jpg",
                false
            ))

        // enregistrer une troisième plante dans notre liste (cactus)
        plantList.add(
            PlantModel(
                "Cactus",
                "ça pique beaucoup",
                "https://cdn.pixabay.com/photo/2016/07/06/20/47/prickly-pear-1501307_960_720.jpg",
                true
            ))

        // enregistrer une quatrieme plante dans notre liste (tulipe)
        plantList.add(
            PlantModel(
                "Tulipe",
                "c'est beau",
                "https://cdn.pixabay.com/photo/2017/03/13/21/19/tulip-2141216_960_720.jpg",
                false
            ))

        // Récupérer le recyclerView - affichage horizontal
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant) // On récupère le context pour charger les images via Glide

        // récupérer le second RecyclerView - affichage vertical
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        // Le itemDecoration sert à espacer les images les unes des autres dans le RecyclerView. Pour cela on a créé une classe Kotlin PlantItemDecoration (qui hérite de ItemDecoration) pour définir
        // quel espace sera appliqué
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}