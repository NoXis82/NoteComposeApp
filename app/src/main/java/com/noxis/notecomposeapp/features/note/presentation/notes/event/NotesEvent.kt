package com.noxis.notecomposeapp.features.note.presentation.notes.event

import com.noxis.notecomposeapp.features.note.domain.model.Note
import com.noxis.notecomposeapp.features.note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data object RestoreNote: NotesEvent()
    data object ToggleOrderSection: NotesEvent()
}