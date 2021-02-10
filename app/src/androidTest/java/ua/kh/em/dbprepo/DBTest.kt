package ua.kh.em.dbprepo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import ua.kh.em.dbprepo.data.database.FruitDatabase
import java.io.IOException


@RunWith(AndroidJUnit4::class)
abstract class DBTest {

    protected lateinit var db: FruitDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), FruitDatabase::class.java)
            // Allowing main thread just for testing.
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}