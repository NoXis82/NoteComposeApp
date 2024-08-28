package com.noxis.notecomposeapp.features.note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.noxis.notecomposeapp.features.note.data.repository.FakeNoteRepository
import com.noxis.notecomposeapp.features.note.domain.model.InvalidNoteException
import com.noxis.notecomposeapp.features.note.domain.model.Note
import com.noxis.notecomposeapp.features.note.domain.util.NoteOrder
import com.noxis.notecomposeapp.features.note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var getNotes: GetNotes
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        addNote = AddNote(fakeRepository)
        getNotes = GetNotes(fakeRepository)
    }

    @Test
    fun `Add notes is title blank`() = runBlocking {
        val note = Note(
            title = "",
            content = "test",
            timestamp = 0L,
            color = 0
        )
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assertThat(e).hasMessageThat().contains("The title of the note can't be empty.")
        }
    }

    @Test
    fun `Add notes is content blank`() = runBlocking {
        val note = Note(
            title = "test",
            content = "",
            timestamp = 0L,
            color = 0
        )
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assertThat(e).hasMessageThat().contains("The content of the note can't be empty.")
        }
    }

    @Test
    fun `Add notes is correct`() = runBlocking {
        val note = Note(
            title = "test",
            content = "test",
            timestamp = 0L,
            color = 0
        )
        addNote.invoke(note)
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        assertThat(notes).contains(note)
    }
}