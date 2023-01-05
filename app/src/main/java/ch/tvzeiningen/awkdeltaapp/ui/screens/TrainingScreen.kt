package ch.tvzeiningen.awkdeltaapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.tvzeiningen.awkdeltaapp.AppModel
import ch.tvzeiningen.awkdeltaapp.model.Person
import ch.tvzeiningen.awkdeltaapp.model.dateString
import ch.tvzeiningen.awkdeltaapp.ui.Screen
import ch.tvzeiningen.awkdeltaapp.ui.common.AvatarInitials
import ch.tvzeiningen.awkdeltaapp.ui.screens.common.BaseScreen

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
            navigationIcon = {
                IconButton(onClick = { AppModel.currentScreen = Screen.HOME }) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
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
                PersonListItem(person, model)
            }

            item {
                Text(
                    text = "Abgemeldet (${unregistered.size})",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(unregistered.toList()) { person ->
                PersonListItem(person, model)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonListItem(person: Person, model: AppModel) {
    with(person) {
        val initials = person.name
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it[0] }
            .joinToString(separator = "") { it.uppercase() }

        var isAttendant by remember {
            mutableStateOf(model.currentTraining?.attendant?.contains(person) ?: false)
        }

        ListItem(
            leadingContent = { AvatarInitials(text = initials )},
            headlineText = { Text(text = name) },
            trailingContent = { if (isAttendant) Icon(Icons.Default.Check, "")},

            tonalElevation = if (isAttendant) 8.dp else 0.dp,
            modifier = Modifier.clickable {
                if (model.currentTraining?.attendant == null) return@clickable

                if (model.currentTraining!!.attendant.contains(person)) {
                    model.currentTraining!!.attendant.remove(person)
                } else {
                    model.currentTraining!!.attendant.add(person)
                }

                isAttendant = !isAttendant
            }
        )
    }
}
