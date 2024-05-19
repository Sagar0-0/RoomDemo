package org.example.project.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.NotesDao
import database.NotesDatabase

fun getDao(context: Context): NotesDao {
    val dbFile = context.getDatabasePath("notes.db")
    return Room.databaseBuilder<NotesDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
        .getDao()
}