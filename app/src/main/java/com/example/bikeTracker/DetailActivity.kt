package com.example.bikeTracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coctails.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as CocktailDetailFragment?
        val cocktailId = intent.extras!![EXTRA_COCKTAIL_ID] as Int
        frag!!.setCocktail(cocktailId.toLong())
    }

    companion object {
        const val EXTRA_COCKTAIL_ID = "id"
    }
}