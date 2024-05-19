package database

import androidx.room.Room

fun getDao(): NotesDao {
    val dbFile = NSHomeDirectory() + "/notes.db"
    return Room.databaseBuilder<NotesDatabase>(
        name = dbFile,
        factory = { NotesDatabase::class.instantiateImpl() } // This too will show error
    )
        .setDriver(BundledSQLiteDriver())
        .build()
        .getDao()
}