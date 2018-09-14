package io.konya.ceg4110hw1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val textColorBox = findViewById<TextView>(R.id.color_text_box_id)

        // get reference to button 1
        val colorChangeButton = findViewById(R.id.color_change_button_id) as Button

        colorChangeButton.setOnClickListener {
            val colorR = Random().nextInt(256)
            val hexR = Integer.toHexString(colorR)
            val colorG = Random().nextInt(256)
            val hexG = Integer.toHexString(colorG)
            val colorB = Random().nextInt(256)
            val hexB = Integer.toHexString(colorB)
            textColorBox.setTextColor(Color.rgb(colorR, colorG, colorB))
            textColorBox.text = "COLOR: " + colorR + "r, " + colorG + "g, " + colorB + "b, #" + hexR + hexG + hexB
        }
    }
}
