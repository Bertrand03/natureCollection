package fr.drako.naturecollection

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.drako.naturecollection.PlantRepository.Singleton.databaseRef
import fr.drako.naturecollection.PlantRepository.Singleton.plantList

class PlantRepository {

    // Le Singleton va permettre de ne pas recréer un objet à chaque opération, il va réutiliser l'instance créée
    object Singleton {
        // Se connecter à la référence "plants". On va chercher en bdd avec le nom de la table
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // Créer une liste qui va contenir nos plantes
        val plantList = arrayListOf<PlantModel>()
    }

    // Le Unit est un paquet d'instructions. Le callback ... Unit sert à laisser le temps de faire la requête en base pour charger ensuite le fragment contenant les données
    fun updateData(callback: () -> Unit) {
        // Absorber les données récupérées dans la database référence pour les donner à notre liste de plantes
        // Le addValueEventListener va servir à écouter et observer les changements des valeurs dans la base de données
        databaseRef.addValueEventListener(object: ValueEventListener {
            // Datasnapshot est un objet qui contient toutes les données qui ont été récupérées. C'est une liste d'objets mais qui n'est pas encore un plantModel
            override fun onDataChange(snapshot: DataSnapshot) {
                // On retire les anciennes plantes de ma liste
                plantList.clear()

                // On va récolter la liste. Children correspond à chaque enfant donc à chaque objet de ma liste d'objets dans ds (DataSnapshot)
                for (ds in snapshot.children) {
                    // Construire un objet plante. On utilise le :: pour récupérer notre objet de type PlantModel
                    val plant = ds.getValue(PlantModel::class.java)

                    // Vérifier si la plant n'est pas null
                    if (plant != null) {
                        // Ajouter la plante à notre liste
                        plantList.add(plant)
                    }
                }
                // Actionner le callback après avoir récolté la liste de données
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    // Mettre à jour un objet plante en bdd. Auparavant on aura attribué un id à chaque plante en base
    fun updatePlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)
    }

}