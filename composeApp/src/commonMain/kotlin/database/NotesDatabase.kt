package database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getDao(): NotesDao
}

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(item: NotesEntity)

    @Delete
    suspend fun delete(item: NotesEntity)

    @Query("SELECT * FROM NotesEntity")
    fun getAll(): Flow<List<NotesEntity>>
}

@Entity
data class NotesEntity(
    val note: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)