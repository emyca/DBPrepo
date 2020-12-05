package ua.kh.em.dbprepo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ua.kh.em.dbprepo.data.model.Fruit
import ua.kh.em.dbprepo.data.database.FruitDatabase
import ua.kh.em.dbprepo.data.repos.FruitRepository

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