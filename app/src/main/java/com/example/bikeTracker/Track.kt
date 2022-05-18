package com.example.bikeTracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room

@Entity
data class Track(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "length") val length: Float,
    @ColumnInfo(name = "topTime") var topTime: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "type") val type: TrackType,
    @ColumnInfo(name = "lastTime") var lastTime: String,
    @ColumnInfo(name = "map") val map: String?
) {
    companion object {
        private val db = Room.databaseBuilder(
            MainActivity.applicationContext(),
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()


        fun getTrack(): List<Track> {
            return db.trackDao().getAll()
        }

        fun init() {
            db.trackDao().insertAll(Track(0, "Wartostrada", 5.45.toFloat(), "2:34",
                "Trasa wzdłóż warty", TrackType.SHORT, "2:55", null))
            db.trackDao().insertAll(Track(1, "Grodzisk-Opalenica", 7.2.toFloat(), "3:32",
                "Droga rowerowa z grodziska Wlkp. do Opalenicy, ufundowana przez fundusz rekreacji Unii Europejskiej",
                TrackType.SHORT, "3:55", null))
            db.trackDao().insertAll(Track(2, "Próba ognia", 16.8.toFloat(), "19:49",
                "Prosta szybka trasa testująca twoje możliwości szybkiej jazdy na średnim dystansie",
                TrackType.LONG, "20:51", null))
            db.trackDao().insertAll(Track(3, "Jezioro Wonieskie", 19.5.toFloat(), "62:06",
                "Trasa wokół jeziora Woneskiego", TrackType.LONG, "64:28", null))
        }
    }
}
