package com.nramos.cabifymobilechallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nramos.cabifymobilechallenge.ui.theme.CabifyChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CabifyChallengeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabifyChallengeTheme {

            }
        }
    }

}