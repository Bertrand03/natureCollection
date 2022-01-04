package fr.drako.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.drako.naturecollection.R
import fr.drako.naturecollection.adapter.PlantAdapter
import fr.drako.naturecollection.adapter.PlantItemDecoration

/*On met un :Fragment() pour déclarer que c'est un fragment (va importer via l'import androidx*/
class HomeFragment : Fragment() {

    /*Cette fonction va se charger d'intégrer un layout via inflater*/
    /*Lors d'un appel via onCreateView on va charger le fragment déclaré dans le return dans notre HomeFragment*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        // Récupérer le recyclerView - affichage horizontal
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(R.layout.item_horizontal_plant)

        // récupérer le second RecyclerView - affichage vertical
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(R.layout.item_vertical_plant)
        // Le itemDecoration sert à espacer les images les unes des autres dans le RecyclerView. Pour cela on a créé une classe Kotlin PlantItemDecoration (qui hérite de ItemDecoration) pour définir
        // quel espace sera appliqué
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}