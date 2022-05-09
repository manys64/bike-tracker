package com.example.bikeTracker

class Cocktail private constructor(val name: String, val recipe: String) {

    override fun toString(): String {
        return name
    }

    companion object {
        @JvmField
        val cocktails = arrayOf(
                Cocktail("Bloody Mary",
                        """Składniki:
 40 ml wódki
 10 ml soku z cytryny
 120 ml soku pomidorowego
 sos worchestershire
 sól
 pieprz
 tabasco
 gałązka selera naciowego

 Sposób przygotowania:
 Wszystkie składniki wymieszać w szklance z lodem i ozdobić selerem naciowym."""),
                Cocktail("Gorzka mietowa",
                        """Składniki:
 10 ml wódki
 50 ml soku z cytryny
 10 ml soku pomidorowego
 tabasco
 gałązka selera naciowego

 Sposób przygotowania:
 Wszystkie składniki wymieszać w szklance z lodem i ozdobić selerem naciowym.""")
        )
    }

}