package ch.tvzeiningen.xawkdeltaapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AvatarInitials(text: String) {
    Box(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape
        ).size(40.dp),
        contentAlignment = Alignment.Center
    ) {
       Text(
           text = text,
           style = MaterialTheme.typography.titleMedium,
           color = MaterialTheme.colorScheme.onPrimaryContainer
       )
    }
}