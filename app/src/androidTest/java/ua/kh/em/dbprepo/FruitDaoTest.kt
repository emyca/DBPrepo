package ua.kh.em.dbprepo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.kh.em.dbprepo.data.model.Fruit


@RunWith(AndroidJUnit4::class)
open class FruitDaoTest: DBTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun readFruitTest() {
        val fruit1 = Fruit(id = 1, fruitName = "Apricot", fruitDesc = "Tasty fruit")
        db.fruitDao().insertFruit(fruit1)
        val fruit2 = Fruit(id = 2, fruitName = "Plum", fruitDesc = "Good fruit")
        db.fruitDao().insertFruit(fruit2)
        val name = db.fruitDao().readAll().getValueBlocking()?.size
        assertEquals(name, 2)
    }

    @Test
    fun searchFruitTest() {
        val fruit = Fruit(id = 1, fruitName = "Apricot", fruitDesc = "Tasty fruit")
        db.fruitDao().insertFruit(fruit)
        val name = db.fruitDao().readSearch("Apricot").getValueBlocking()?.size
        assertEquals(name, 1)
    }
}