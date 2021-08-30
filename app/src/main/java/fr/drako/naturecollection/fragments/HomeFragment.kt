package fr.drako.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.drako.naturecollection.R

/*On met un :Fragment() pour déclarer que c'est un fragment (va importer via l'import androidx*/
class HomeFragment : Fragment() {

    /*Cette fonction va se charger d'intégrer un layout via inflater*/
    /*Lors d'un appel via onCreateView on va charger le fragment déclaré dans le return dans notre HomeFragment*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

}