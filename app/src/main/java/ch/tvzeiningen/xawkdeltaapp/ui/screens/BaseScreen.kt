package ch.tvzeiningen.xawkdeltaapp.ui.screens.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.tvzeiningen.xawkdeltaapp.AppModel
import ch.tvzeiningen.xawkdeltaapp.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    model: AppModel,
    topBar: @Composable (model: AppModel) -> Unit = { TopBar(model) },
    bottomBar: @Composable (model: AppModel) -> Unit = {},
    modifier: Modifier = Modifier,
    previousScreen: Screen? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    with(model) {
        BackHandler(enabled = previousScreen != null) {
            if (previousScreen != null) currentScreen = previousScreen
        }
        Scaffold(
            modifier = modifier,
            topBar = { topBar(model) },
            bottomBar = { bottomBar(model) },
        ) { padding ->
            Box(
                Modifier
                    .padding(
                        top = 64.dp,
                        // start =  16.dp,
                        // end =    16.dp,
                        // bottom = 16.dp
                    )
            ) {
                content(padding)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(model: AppModel) {
    with(model) {
        CenterAlignedTopAppBar(
            title = { Text(text = "AWK TV Zeiningen") }
        )
    }
}