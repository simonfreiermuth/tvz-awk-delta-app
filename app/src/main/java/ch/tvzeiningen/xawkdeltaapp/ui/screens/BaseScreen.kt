package ch.tvzeiningen.xawkdeltaapp.ui.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.tvzeiningen.xawkdeltaapp.AppModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    model: AppModel,
    topBar: @Composable (model: AppModel) ->Unit = { TopBar(model) },
    bottomBar: @Composable (model: AppModel) -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    with(model) {
        Scaffold(
            modifier = modifier,
            topBar = { topBar(model) },
            bottomBar = { bottomBar(model) },
        ) { padding ->
            Box(
                Modifier
                    .padding(16.dp)
            ) {
                content(padding)
            }
        }
    }
}

@Composable
private fun TopBar(model: AppModel) {
    with(model) {
        MediumTopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = currentScreen.toString())
                }
            }
        )
    }
}