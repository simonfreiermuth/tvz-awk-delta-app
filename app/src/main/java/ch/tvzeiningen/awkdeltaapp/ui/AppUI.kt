package ch.tvzeiningen.awkdeltaapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import ch.tvzeiningen.awkdeltaapp.AppModel
import ch.tvzeiningen.awkdeltaapp.ui.screens.HomeScreen
import ch.tvzeiningen.awkdeltaapp.ui.screens.TrainingScreen
import ch.tvzeiningen.awkdeltaapp.ui.theme.AppTheme

@Composable
fun AppUI(model: AppModel) {
    with(model) {
        AppTheme {
            Crossfade(targetState = currentScreen) { screen ->
                when(screen) {
                    Screen.HOME -> HomeScreen(model)
                    Screen.TRAINING -> TrainingScreen(model)
                }
            }
        }
    }
}