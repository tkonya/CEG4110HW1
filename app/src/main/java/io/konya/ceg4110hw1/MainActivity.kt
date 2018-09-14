package io.konya.ceg4110hw1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button 1
        val textButton = findViewById(R.id.button_1_id) as Button
        textButton.setOnClickListener {
            val intent = Intent(this, TextActivity::class.java).apply {
                putExtra("Thing to do", "Start Text Coloring!")
            }
            startActivity(intent)
        }

        // get reference to button 2
        val drawButton = findViewById(R.id.button_2_id) as Button
        drawButton.setOnClickListener {
            val intent = Intent(this, DrawActivity::class.java).apply {
                putExtra("Thing to do", "Start Drawing!")
            }
            startActivity(intent)
        }
    }
}
