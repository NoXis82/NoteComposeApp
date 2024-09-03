package com.noxis.notecomposeapp.features.note.presentation.notes.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.noxis.notecomposeapp.navigation.Root
import com.noxis.notecomposeapp.ui.theme.NoteComposeAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesScreenComposeTest {

    @get: Rule
    val composeRule = createComposeRule()

    @Test
    fun set() {
        composeRule.setContent {
            Button(
                onClick = { /* Button click action */ }
            ) {
                Text("Click Me")
            }
        }

        // Check if the button is displayed
        composeRule.onNodeWithText("Click Me").assertIsDisplayed()
        // Perform a click action on the button
        composeRule.onNodeWithText("Click Me").performClick()

        // Check if the button is displayed
//        onView(withText("Click Me")).check(matches(isDisplayed()))

        // Perform a click action on the button
//        onView(withText("Click Me")).perform(click())

    }




}