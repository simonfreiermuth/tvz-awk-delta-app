package ch.tvzeiningen.xawkdeltaapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import ch.tvzeiningen.xawkdeltaapp.ui.AppUI

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