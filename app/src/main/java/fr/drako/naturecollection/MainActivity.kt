package fr.drako.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.drako.naturecollection.fragments.AddPlantFragment
import fr.drako.naturecollection.fragments.CollectionFragment
import fr.drako.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Force l'affichage sur ce fragment au démarrage
        loadFragment(HomeFragment(this), R.string.home_page_title)

        // Import de la bottom navigation view tuto à 04:53:06
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            // When est l'équivalent d'un switch en java
            when(it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this), R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.add_plant_page -> {
                    loadFragment(AddPlantFragment(this), R.string.add_plant_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                // Il faut un cas par défaut au when
                else -> false
            }
        }

        // Méthode pour permettre d'afficher le bon fragment en fonction du choix de la nav bar
        loadFragment(HomeFragment(this), R.string.home_page_title)
    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // Charger notre plantRepository (On l'instancie)
        val repo = PlantRepository()

        // Actualiser le titre de la page en fonction de celle sur laquelle on se trouve
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

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
            transaction.replace(R.id.fragment_container, fragment) // Lorsqu'on va créer le homefragment on va lui donner l'activité principale en paramètre (va voir dans HomeFragment pour la suite de l'explication)
            // Permet de ne pas avoir de retour sur ce composant.
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}