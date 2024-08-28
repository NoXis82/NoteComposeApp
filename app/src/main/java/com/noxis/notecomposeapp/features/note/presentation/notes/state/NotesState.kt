package com.noxis.notecomposeapp.features.note.presentation.notes.state

import com.noxis.notecomposeapp.features.note.domain.model.Note
import com.noxis.notecomposeapp.features.note.domain.util.NoteOrder
import com.noxis.notecomposeapp.features.note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)