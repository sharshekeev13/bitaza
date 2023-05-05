package com.example.bitaza.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitaza.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}