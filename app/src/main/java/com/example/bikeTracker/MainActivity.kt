package com.example.bikeTracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coctails.R

class MainActivity : AppCompatActivity(), CocktailListFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //    public void onShowDetail(View view) {
    //        Intent intent = new Intent(this, DetailActivity.class);
    //        startActivity(intent);
    //    }
    override fun itemClicked(id: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_COCKTAIL_ID, id.toInt())
        startActivity(intent)
    }
}