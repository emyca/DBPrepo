package em.kh.ua.roomprepo.data.repos

import androidx.lifecycle.LiveData
import em.kh.ua.roomprepo.data.model.Fruit
import em.kh.ua.roomprepo.data.database.FruitDao

class FruitRepository(private val fruitDao: FruitDao) {

    private var itemList: LiveData<List<Fruit>>? = null

    fun readAll(): LiveData<List<Fruit>>{
        itemList = fruitDao.readAll()
        return itemList as LiveData<List<Fruit>>
    }

    fun readSearch(search: String?): LiveData<List<Fruit>>{
        itemList = fruitDao.readSearch(search)
        return itemList as LiveData<List<Fruit>>
    }

}