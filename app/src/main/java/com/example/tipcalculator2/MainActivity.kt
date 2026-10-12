package com.example.tipcalculator2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tipcalculator2.ui.theme.TipCalculator2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Life", "onCreate")
        enableEdgeToEdge()
        setContent {
            TipCalculator2Theme {
                ProjectApp()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Life", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Life", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life", "onDestroy")
    }
}