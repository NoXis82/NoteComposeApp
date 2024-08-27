package com.noxis.notecomposeapp.features.note.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
