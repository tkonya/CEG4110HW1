package io.konya.ceg4110hw1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val userEditableText = findViewById<EditText>(R.id.user_text_box_id)
        userEditableText.hint = "Tap to Change Color"

        val colorTextBox = findViewById<TextView>(R.id.color_text_box_id)

        // get reference to button 1
        val colorChangeButton = findViewById<Button>(R.id.color_change_button_id)

        // default color is black
        colorTextBox.text = "COLOR: 255r, 255g, 255b, #FFFFFF"
        userEditableText.setTextColor(Color.BLACK)
        userEditableText.setHintTextColor(Color.BLACK)

        colorChangeButton.setOnClickListener {
            val colorR = Random().nextInt(256)
            val hexR = Integer.toHexString(colorR)
            val colorG = Random().nextInt(256)
            val hexG = Integer.toHexString(colorG)
            val colorB = Random().nextInt(256)
            val hexB = Integer.toHexString(colorB)
            userEditableText.setTextColor(Color.rgb(colorR, colorG, colorB))
            userEditableText.setHintTextColor(Color.rgb(colorR, colorG, colorB))
            colorTextBox.text = "COLOR: " + colorR + "r, " + colorG + "g, " + colorB + "b, #" + hexR + hexG + hexB
        }
    }
}
