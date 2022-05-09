package com.example.bikeTracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coctails.R

class CocktailDetailFragment : Fragment() {
    private var cocktailId: Long = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    fun setCocktail(id: Long) {
        cocktailId = id
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            val cocktail = Cocktail.cocktails[cocktailId.toInt()]
            title.text = cocktail.name
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.text = cocktail.recipe
        }
    }
}