package ch.tvzeiningen.awkdeltaapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import ch.tvzeiningen.awkdeltaapp.ui.AppUI

object AwkApp {
    private lateinit var model: AppModel

    fun initialize(activity: ComponentActivity) {
        model = AppModel
    }

    @Composable
    fun CreateUI() {
        AppUI(model)
    }

    fun onStop(activity: ComponentActivity) {
    }
}