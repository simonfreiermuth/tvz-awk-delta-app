package ch.tvzeiningen.xawkdeltaapp.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ch.tvzeiningen.xawkdeltaapp.AppModel
import ch.tvzeiningen.xawkdeltaapp.model.Person
import ch.tvzeiningen.xawkdeltaapp.model.dateString
import ch.tvzeiningen.xawkdeltaapp.ui.Screen
import ch.tvzeiningen.xawkdeltaapp.ui.common.AvatarInitials
import ch.tvzeiningen.xawkdeltaapp.ui.screens.common.BaseScreen

@Composable
fun TrainingScreen(model: AppModel) {
    BaseScreen(
        model = model,
        previousScreen = Screen.HOME,
        topBar = { TopAppBar(model) },
        content = { Body(model)}
    )
}

@Composable
private fun Body(model: AppModel) {
    PeopleList(model)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(model: AppModel) {
    with(model.currentTraining !!) {
        CenterAlignedTopAppBar(
            title = { Text(text = dateString() )}
        )
    }
}

@Composable
private fun PeopleList(model: AppModel) {
    with(model.currentTraining !!) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    text = "Angemeldet (${registered.size})",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(registered.toList()) { person ->
                PersonListItem(person)
            }

            item {
                Text(
                    text = "Abgemeldet",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(unregistered.toList()) { person ->
                PersonListItem(person)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonListItem(person: Person) {
    with(person) {
        val initials = person.name
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it[0] }
            .joinToString(separator = "") { it.uppercase() }

        ListItem(
            leadingContent = { AvatarInitials(text = initials )},
            headlineText = { Text(text = name) }
        )
    }
}
