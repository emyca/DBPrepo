package em.kh.ua.roomprepo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import em.kh.ua.roomprepo.database.Fruit
import em.kh.ua.roomprepo.database.FruitDatabase
import em.kh.ua.roomprepo.repos.FruitRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FruitRepository
    private lateinit var allItems: LiveData<List<Fruit>>

    init {
        val fruitsDao = FruitDatabase.getDatabase(application).fruitDao()
        repository = FruitRepository(fruitsDao)
    }

    fun readAll(): LiveData<List<Fruit>> {
        allItems = repository.readAll()
        return allItems
    }

    fun readSearch(search: String?): LiveData<List<Fruit>> {
        allItems = repository.readSearch(search)
        return allItems
    }
}