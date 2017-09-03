package br.com.up.carrosup.dao

import android.arch.persistence.room.Room
import br.com.up.carrosup.CarrosApplication

/**
 * Created by Franklyn on 02/09/2017.
 */
object DatabaseManager {

    private var dbInstance: CarrosDatabase

    init {
        val appContext = CarrosApplication.getInstance().applicationContext

        dbInstance = Room.databaseBuilder(
                appContext,
                CarrosDatabase::class.java,
                "carros.sqlite")
                .build()
    }

    fun getCarroDAO(): CarroDAO {
        return dbInstance.carroDAO()
    }
}