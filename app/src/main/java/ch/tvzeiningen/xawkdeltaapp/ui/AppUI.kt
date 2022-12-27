package ch.tvzeiningen.xawkdeltaapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ch.tvzeiningen.xawkdeltaapp.AppModel
import ch.tvzeiningen.xawkdeltaapp.ui.screens.HomeScreen
import ch.tvzeiningen.xawkdeltaapp.ui.theme.AppTheme

@Composable
fun AppUI(model: AppModel) {
    with(model) {
        AppTheme {
            Crossfade(targetState = currentScreen) { screen ->
                when(screen) {
                    Screen.HOME -> HomeScreen(model)
                    Screen.TRAINING -> TODO()
                }
            }
        }
    }
}