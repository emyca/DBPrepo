package em.kh.ua.roomprepo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


// In the database file itself, the fields must be NOT NULL,
// otherwise the collapse of the database schema will occure.
// DB Browser for SQlite, for example, for viewing and editing.

@Parcelize
@Entity(tableName = "fruits")
data class Fruit (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "fruit_name")
    val fruitName: String,

    @ColumnInfo(name = "fruit_desc")
    val fruitDesc: String

):Parcelable