package em.kh.ua.roomprepo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import em.kh.ua.roomprepo.data.model.Fruit
import em.kh.ua.roomprepo.data.database.FruitDatabase
import em.kh.ua.roomprepo.data.repos.FruitRepository

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