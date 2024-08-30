package com.noxis.notecomposeapp.features

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.noxis.notecomposeapp.activity.MainActivity
import com.noxis.notecomposeapp.core.util.TestTags
import com.noxis.notecomposeapp.di.AppModule
import com.noxis.notecomposeapp.features.note.presentation.add_edit_note.view.AddEditNoteScreen
import com.noxis.notecomposeapp.features.note.presentation.notes.view.NotesScreen
import com.noxis.notecomposeapp.navigation.Root
import com.noxis.notecomposeapp.ui.theme.NoteComposeAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            NoteComposeAppTheme {
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


    @Test
    fun saveNewNote_editAfterwards() {
        // Нажимаем на FAB, чтобы перейти к экрану добавления заметки
        composeRule.onNodeWithContentDescription("Add note").performClick()
        // Проверяем что появились поля
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).assertIsDisplayed()
        // Вводим текст в текстовые поля заголовка и содержания
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("title test")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput("content test")
        //Сохраняем
        composeRule.onNodeWithContentDescription("Save")
        // Проверяем что заметка с полями появилась на экране
        composeRule.onNodeWithText("title test").assertIsDisplayed()
        composeRule.onNodeWithText("content test").assertIsDisplayed()
        //Нажимаем для редактирования
        composeRule.onNodeWithText("title test").performClick()
        //Проверяем что заметка открылась для редактирования
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .assertTextEquals("title test")
        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .assertTextEquals("content test")

        //Редактируем поле заголовка
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput(" edit")
        //Сохраняем
        composeRule.onNodeWithContentDescription("Save").performClick()
        //Проверяем что заметка изменилась
        composeRule.onNodeWithText("title test edit").assertIsDisplayed()
    }

    @Test
    fun saveNewNotes_orderByTitleDescending() {
        for (i in 1..3) {
            // Нажимаем на FAB, чтобы перейти к экрану добавления заметки
            composeRule.onNodeWithContentDescription("Add note").performClick()
            // Вводим текст в текстовые поля заголовка и содержания
            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            //Сохраняем
            composeRule.onNodeWithContentDescription("Save").performClick()
        }
        //Проверяем что заметки появились в списке
        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()
        //Нажимаем сортировку
        composeRule.onNodeWithContentDescription("Sort").performClick()
        //Выбираем сортировку по заголовку
        composeRule.onNodeWithContentDescription("Title").performClick()
        //Выбираем сортировку по убыванию
        composeRule.onNodeWithContentDescription("Descending").performClick()
        //Проверяем что сортировка сработала
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2]
            .assertTextContains("1")
    }
}
