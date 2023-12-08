package com.dhozhenkohealthdatademo.presentation.healthdata.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconWithProgressIndicator(
    @DrawableRes icon: Int,
    title: String,
    isLoading: Boolean
) {
    Box {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(56.dp).align(Alignment.Center))
        } else {
            CircularProgressIndicator(progress = 1f, color = Color.White, modifier = Modifier.size(56.dp).align(Alignment.Center))
        }

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "$title Icon",
            modifier = Modifier
                .border(width = 2.dp, shape = CircleShape, color = Color.White)
                .padding(8.dp)
                .size(48.dp)
                .align(Alignment.Center)
        )
    }
}