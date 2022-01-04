package fr.drako.naturecollection.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PlantItemDecoration : RecyclerView.ItemDecoration() {

//    Un offset est un décalage, pour mettre un espace entre chaque image de la recyclerView
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 20
    }
}