package em.kh.ua.roomprepo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import em.kh.ua.roomprepo.data.model.Fruit

@Database(entities = [Fruit::class],version = 1,exportSchema = false)
abstract class FruitDatabase: RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object{

        @Volatile
        private var INSTANCE: FruitDatabase? = null

        fun getDatabase(context: Context): FruitDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FruitDatabase::class.java,
                    "roomrepo.db"
                )
                .createFromAsset("database/fruitsdb.db")
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}