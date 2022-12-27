package ch.tvzeiningen.xawkdeltaapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object AppModel {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

}