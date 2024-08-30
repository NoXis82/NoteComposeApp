package com.noxis.notecomposeapp.di

import android.content.Context
import androidx.room.Room
import com.noxis.notecomposeapp.features.note.data.local.db.NoteDatabase
import com.noxis.notecomposeapp.features.note.data.repository.NoteRepositoryImpl
import com.noxis.notecomposeapp.features.note.domain.repository.NoteRepository
import com.noxis.notecomposeapp.features.note.domain.use_case.AddNote
import com.noxis.notecomposeapp.features.note.domain.use_case.DeleteNote
import com.noxis.notecomposeapp.features.note.domain.use_case.GetNote
import com.noxis.notecomposeapp.features.note.domain.use_case.GetNotes
import com.noxis.notecomposeapp.features.note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java,
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}
