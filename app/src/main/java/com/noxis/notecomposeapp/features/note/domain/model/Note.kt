package com.noxis.notecomposeapp.features.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noxis.notecomposeapp.ui.theme.BabyBlue
import com.noxis.notecomposeapp.ui.theme.LightGreen
import com.noxis.notecomposeapp.ui.theme.RedOrange
import com.noxis.notecomposeapp.ui.theme.RedPink
import com.noxis.notecomposeapp.ui.theme.Violet


@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
