package com.nramos.cabifymobilechallenge.core.presentation.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun FullLoadingScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    Crossfade(targetState = isLoading) {
        if (it) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .clickable(
                        enabled = false,
                        onClick = { /*Do nothing*/ }
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.25F)
                        .width(LocalConfiguration.current.screenHeightDp.dp * 0.25F)
                        .clip(RoundedCornerShape(10))
                        .align(Alignment.Center)
                        .background(MaterialTheme.colors.background.copy(alpha = 0.7F)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}