package com.nramos.cabifymobilechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.nramos.cabifymobilechallenge.ui.CabifyApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CabifyChallengeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CabifyApp()
        }
    }

}