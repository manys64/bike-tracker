package com.example.bikeTracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.R as R

class CocktailListFragment : ListFragment() {
    private var listener: Listener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onListItemClick(listView: ListView, itemView: View, position: Int, id: Long) {
        if (listener != null) {
            listener!!.itemClicked(id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val names = arrayOfNulls<String>(Cocktail.cocktails.size)
        for (i in names.indices) {
            names[i] = Cocktail.cocktails[i].name
        }
        val adapter = ArrayAdapter(
                inflater.context, R.layout.simple_list_item_1, names)
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    internal interface Listener {
        fun itemClicked(id: Long)
    }
}