package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

fun getDao(): NotesDao {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "notes.db")
    return Room.databaseBuilder<NotesDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
        .getDao()
}