package com.noxis.notecomposeapp.features.note.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noxis.notecomposeapp.features.note.data.local.dao.NoteDao
import com.noxis.notecomposeapp.features.note.domain.model.Note


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}
