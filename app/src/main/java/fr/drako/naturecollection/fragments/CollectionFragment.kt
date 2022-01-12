package fr.drako.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.drako.naturecollection.MainActivity
import fr.drako.naturecollection.PlantRepository.Singleton.plantList
import fr.drako.naturecollection.R
import fr.drako.naturecollection.adapter.PlantAdapter
import fr.drako.naturecollection.adapter.PlantItemDecoration

// A la création de la classe on lui donne le contexte en paramètre
// On lui dit qu'on hérite de la classe Fragment, ne pas oublier les paranthèses pour faire l'instanciation
class CollectionFragment(private val context: MainActivity) : Fragment() {
    // Instructions à la création de la vue pour injecter mon layout. Le but est d'adapter notre affichage en fonction de la vue que l'on veut.
    // Ici on veut une collection de plantes dans une autre vue alors quand on fera appel à ce fragment on affichera la vue voulue, donc celle avec le layout qui contient la liste de plantes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflater pour récupérer le layout
        // ? pour dire de continuer s'il y a un soucis
        // On récup grace à R.layout le layout en question
        // Le container (pas plus de précisions)
        // Si on l'attache ou non à l'élément principal
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        // On récupère la RecyclerView
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)

        // On utilise toujours le plantAdapter pour qu'il adapte la vue en question. En paramètre il lui faut le context, la liste de plantes et le type de layout qu'on veut lui appliquer
        // Ici on avait déjà défini un layout pour les plantes listées de manière verticale donc on le réutilise
        // On utilise filter pour n'avoir que les plantes likées. it est par défaut mon "item" donc ma plante
        collectionRecyclerView.adapter = PlantAdapter(context, plantList.filter { it.liked }, R.layout.item_vertical_plant)

        // On définit que ce layoutManager est un linearLayoutManager
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)

        // Permet de rajouter une marge sur l'ensemble de ma vue. AddItemDecoration est une fonction native. PlantItemDecoration est une classe qu'on a déjà défini
        collectionRecyclerView.addItemDecoration(PlantItemDecoration())
        // On assigne la vue
        return view

    }
}