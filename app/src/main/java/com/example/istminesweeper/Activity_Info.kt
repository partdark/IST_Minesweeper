package com.example.istminesweeper

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity_Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val back : Button = findViewById(R.id.button_back)
        back.setOnClickListener(btnClick)

    }
    private val btnClick = View.OnClickListener { onBackPressed() }
}