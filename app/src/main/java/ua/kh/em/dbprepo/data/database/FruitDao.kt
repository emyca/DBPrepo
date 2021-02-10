package ua.kh.em.dbprepo.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.kh.em.dbprepo.data.model.Fruit


@Dao
interface FruitDao {

    @Query("SELECT * FROM fruits")
    fun readAll(): LiveData<List<Fruit>>

    @Query("SELECT * FROM fruits WHERE fruit_name LIKE :search")
    fun readSearch(search: String?): LiveData<List<Fruit>>

    // Just for test
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFruit(fruit: Fruit)
}