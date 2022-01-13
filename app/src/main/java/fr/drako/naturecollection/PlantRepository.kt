package fr.drako.naturecollection

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.drako.naturecollection.PlantRepository.Singleton.databaseRef
import fr.drako.naturecollection.PlantRepository.Singleton.downloadUri
import fr.drako.naturecollection.PlantRepository.Singleton.plantList
import fr.drako.naturecollection.PlantRepository.Singleton.storageReference
import java.net.URI
import java.util.*

class PlantRepository {

    // Le Singleton va permettre de ne pas recréer un objet à chaque opération, il va réutiliser l'instance créée
    object Singleton {
        // Donner le lien pour accéder au bucket (boite à images sur Firebase). Le lien est celui récupéré sur firebase dans notre Bucket
        private val BUCKET_URL: String = "gs://naturecollection-eabeb.appspot.com"

        // Se connecter à notre espace de stockage Storage sur Firebase
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // Se connecter à la référence "plants". On va chercher en bdd avec le nom de la table
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // Créer une liste qui va contenir nos plantes
        val plantList = arrayListOf<PlantModel>()

        // Va contenir le lien de l'image courante
        var downloadUri : Uri? = null
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

    // Créer une fonction pour envoyer des fichiers sur leur storage
    // Uri puisque interne à notre application
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // On utilise le call back pour lui donner une instruction après que l'image ait bien été uploadée
        // Vérifier que ce fichier n'est pas null
        if (file != null) {
            // Pour envoyer un fichier il faut déjà lui donner un nom. On prend un nom au hasard pour éviter les doublons
            // UUID renvoyer un id unique sous la forme d'un texte grâce au toString()
            val fileName = UUID.randomUUID().toString() + ".jpg"
            // Permet de donner le chemin de l'endroit où on va enregistrer la ressource, l'endroit dans la base de données
            val ref = storageReference.child(fileName)
            // On lui associe qu'elle est le contenu à soumettre
            val uploadTask = ref.putFile(file)

            // Démarrer la tache d'envoi
            // Continuation va permettre de passer le type de tache. tuto à 04:15:21
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                // S'il y a eu un problème lors de l'envoi du fichier
                if (!task.isSuccessful) {
                    // Il y a un problème. it c'est l'exception. let permet d'envoyer cette exception
                    task.exception?.let { throw it }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                // Vérifier si tout a bien fonctionné
                if (task.isSuccessful) {
                    // On va récupérer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // Mettre à jour un objet plante en bdd. Auparavant on aura attribué un id à chaque plante en base
    fun updatePlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)
    }

    // Insérer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)


    }

    // Supprimer une plante de la base. Après la suppression il y a un rafraichissement automatique (grace à Firebase ?)
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()

}