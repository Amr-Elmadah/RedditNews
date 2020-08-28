package com.loblaw.redditnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loblaw.redditnews.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
    }
}