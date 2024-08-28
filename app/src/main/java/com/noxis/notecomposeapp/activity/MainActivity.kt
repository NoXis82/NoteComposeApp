package com.noxis.notecomposeapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.noxis.notecomposeapp.features.note.presentation.add_edit_note.view.AddEditNoteScreen
import com.noxis.notecomposeapp.features.note.presentation.notes.view.NotesScreen
import com.noxis.notecomposeapp.navigation.Root
import com.noxis.notecomposeapp.navigation.Screen
import com.noxis.notecomposeapp.ui.theme.NoteComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteComposeAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Root.NotesScreen
                    ) {
                        composable<Root.NotesScreen> {
                            NotesScreen(navController = navController)
                        }

                        composable<Root.AddEditNoteScreen> {
                            val args = it.toRoute<Root.AddEditNoteScreen>()
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = args.noteColor,
                                noteId = args.noteId
                            )
                        }
                    }
                }
            }
        }
    }
}
