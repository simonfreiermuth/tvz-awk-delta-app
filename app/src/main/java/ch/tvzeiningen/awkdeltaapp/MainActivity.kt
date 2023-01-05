package ch.tvzeiningen.awkdeltaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var app: AwkApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = AwkApp

        app.initialize(activity = this)

        setContent {
            app.CreateUI()
        }
    }

    override fun onStop() {
        super.onStop()
        app.onStop(activity = this)
    }
}
