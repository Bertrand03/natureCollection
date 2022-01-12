package fr.drako.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.drako.naturecollection.fragments.CollectionFragment
import fr.drako.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charger notre plantRepository (On l'instancie)
        val repo = PlantRepository()

        // Mettre à jour la liste de plantes
        // Déclaration du callback d'instructions à faire après que les données aient été récupérées
        // En rajoutant le callback dans mon repository je suis passé de repo.updateData () à repo.updateData {} avec l'appel à mon fragment
        repo.updateData {
            // injecter le fragment dans notre boite (fragment_container)
            // supportFragmentManager permet de gérer les fragments
            val transaction = supportFragmentManager.beginTransaction()
            // "R" doit représenter ma "root", mon projet global.
            // Je pars donc de R pour trouver mon id, qui ici, s'appelle fragment_container (dans "activity_main.xml")
            // Et je le remplace par le fragment qui se trouve dans HomeFragment avec l'appel de méthode "onCreateView())
            transaction.replace(R.id.fragment_container, CollectionFragment(this)) // Lorsqu'on va créer le homefragment on va lui donner l'activité principale en paramètre (va voir dans HomeFragment pour la suite de l'explication)
            // Permet de ne pas avoir de retour sur ce composant.
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}