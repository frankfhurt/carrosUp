package br.com.up.carrosup.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.up.carrosup.domain.Carro

/**
 * Created by Franklyn on 02/09/2017.
 */
@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase : RoomDatabase() {
    abstract fun carroDAO(): CarroDAO
}