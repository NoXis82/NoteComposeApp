package com.noxis.notecomposeapp.navigation

import kotlinx.serialization.Serializable

object Root {

    @Serializable
    data object NotesScreen

    @Serializable
    data class AddEditNoteScreen(
        val noteId: Int = -1,
        val noteColor: Int = -1
    )

}