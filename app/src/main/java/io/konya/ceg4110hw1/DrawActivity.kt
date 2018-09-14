package io.konya.ceg4110hw1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import android.support.annotation.ColorInt
import android.view.View
import android.widget.TextView


class DrawActivity : AppCompatActivity() {

    lateinit var drawView : CustomDrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        drawView = findViewById(R.id.custom_draw_view_id)

        val selectColorButton = findViewById<Button>(R.id.select_color_button_id)
        selectColorButton.setOnClickListener {
            val colorPicker = ColorPicker(this, 0,0,0)
            colorPicker.show()

            colorPicker.setCallback { color ->
                drawView.setPaintColor(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color))
                colorPicker.dismiss()
            }

        }

        val saveImageButton = findViewById<Button>(R.id.save_drawing_button_id)
        saveImageButton.setOnClickListener {
            println("It's something!")
        }
    }
}
