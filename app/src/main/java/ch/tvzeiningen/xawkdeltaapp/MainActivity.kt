package ch.tvzeiningen.xawkdeltaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ch.tvzeiningen.xawkdeltaapp.ui.theme.XawkDeltaAppTheme

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
