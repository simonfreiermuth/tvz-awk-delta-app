package ch.tvzeiningen.xawkdeltaapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ch.tvzeiningen.xawkdeltaapp.data.XoyondoConnector
import ch.tvzeiningen.xawkdeltaapp.model.Training
import ch.tvzeiningen.xawkdeltaapp.ui.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object AppModel {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val trainingsConnector = XoyondoConnector()

    // data
    var trainings = mutableStateListOf<Training>()

    // view states
    var currentTraining by mutableStateOf<Training?>(null)
    var currentScreen by mutableStateOf(Screen.HOME)

    var loadingTrainings by mutableStateOf(false)

    init {
        loadTrainings()
    }

    private fun loadTrainings() {
        loadingTrainings = true
        modelScope.launch {
            trainings.clear()
            trainings.addAll(trainingsConnector.getTrainings())
            loadingTrainings = false
        }
    }
}