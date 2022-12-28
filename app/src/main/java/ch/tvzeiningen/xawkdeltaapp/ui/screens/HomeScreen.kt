package ch.tvzeiningen.xawkdeltaapp.ui.screens

import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.tvzeiningen.xawkdeltaapp.AppModel
import ch.tvzeiningen.xawkdeltaapp.ui.Screen
import ch.tvzeiningen.xawkdeltaapp.ui.common.AvatarInitials
import ch.tvzeiningen.xawkdeltaapp.ui.screens.common.BaseScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.*

@Composable
fun HomeScreen(model: AppModel) {
    BaseScreen(
        model = model,
        content = { Body(model) }
    )
}

@Composable
private fun Body(model: AppModel) {
    with(model) {
        TrainingsList(model)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrainingsList(model: AppModel) {
    val locale = Resources.getSystem().getConfiguration().getLocales().get(0)
    with(model) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            items(trainings.sortedBy { it.date }) { training ->
                ListItem(
                    modifier = Modifier.clickable {
                        currentTraining = training
                        currentScreen = Screen.TRAINING
                    },
                    tonalElevation = if (training.date == LocalDate.now())
                        4.dp
                    else 0.dp,
                    leadingContent = {
                        val day = training.date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT, locale)
                            .substring(0,2)

                        AvatarInitials(day)
                    },
                    headlineText = {
                        Text(
                            text = training
                                .date
                                .format(
                                    DateTimeFormatter
                                        .ofLocalizedDate(FormatStyle.MEDIUM)
                                ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    trailingContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Filled.Groups, "")
                            Text(text = "${training.people.size}")
                        }
                    }
                )
            }
        }
    }
}










