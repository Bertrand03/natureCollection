package fr.drako.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.drako.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // injecter le fragment dans notre boite (fragment_container)
        // supportFragmentManager permet de gérer les fragments
        val transaction = supportFragmentManager.beginTransaction()
        // "R" doit représenter ma "root", mon projet global.
        // Je pars donc de R pour trouver mon id, qui ici, s'appelle fragment_container (dans "activity_main.xml")
        // Et je le remplace par le fragment qui se trouve dans HomeFragment avec l'appel de méthode "onCreateView())
        transaction.replace(R.id.fragment_container, HomeFragment(this)) // Lorsqu'on va créer le homefragment on va lui donner l'activité principale en paramètre (va voir dans HomeFragment pour la suite de l'explication)
        // Permet de ne pas avoir de retour sur ce composant.
        transaction.addToBackStack(null)
        transaction.commit()
    }
}