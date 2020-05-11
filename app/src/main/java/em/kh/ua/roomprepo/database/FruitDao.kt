package em.kh.ua.roomprepo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface FruitDao {

    @Query("SELECT * FROM fruits")
    fun readAll(): LiveData<List<Fruit>>

    @Query("SELECT * FROM fruits WHERE fruit_name LIKE :search")
    fun readSearch(search: String?): LiveData<List<Fruit>>
}