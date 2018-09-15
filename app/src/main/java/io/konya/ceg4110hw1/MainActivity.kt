package io.konya.ceg4110hw1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ask for permissions upfront if they aren't granted
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val writePermission = Array(1) { Manifest.permission.WRITE_EXTERNAL_STORAGE}
            requestPermissions(writePermission, 16846846)
        }

        // get reference to button 1
        val textButton = findViewById<Button>(R.id.button_1_id)
        textButton.setOnClickListener {
            val intent = Intent(this, TextActivity::class.java).apply {
                putExtra("Thing to do", "Start Text Coloring!")
            }
            startActivity(intent)
        }

        // get reference to button 2
        val drawButton = findViewById<Button>(R.id.button_2_id)
        drawButton.setOnClickListener {
            val intent = Intent(this, DrawActivity::class.java).apply {
                putExtra("Thing to do", "Start Drawing!")
            }
            startActivity(intent)
        }
    }
}
