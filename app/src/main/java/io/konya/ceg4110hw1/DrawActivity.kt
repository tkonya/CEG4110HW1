package io.konya.ceg4110hw1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import android.support.annotation.ColorInt
import android.view.View


class DrawActivity : AppCompatActivity() {

    var colorRed = 150
    var colorGreen = 150
    var colorBlue = 150
    var colorAlpha = 1

    lateinit var drawView : CustomDrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        drawView = findViewById<CustomDrawingView>(R.id.custom_draw_view_id)

        val selectColorButton = findViewById<Button>(R.id.select_color_button_id)
        selectColorButton.setOnClickListener {
            val colorPicker = ColorPicker(this, 150, 150, 150)
            colorPicker.show()

            colorPicker.setCallback { color ->
                // Do whatever you want
                // Examples
                colorAlpha = Color.alpha(color)
                colorRed = Color.red(color)
                colorGreen = Color.green(color)
                colorBlue = Color.blue(color)

                drawView.setPaintColor(colorAlpha, colorRed, colorGreen, colorBlue)

                colorPicker.dismiss()
            }

        }

        val saveImageButton = findViewById<Button>(R.id.save_drawing_button_id)
        saveImageButton.setOnClickListener {
            println("It's something!")
        }
    }
}
