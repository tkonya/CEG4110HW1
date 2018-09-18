package io.konya.ceg4110hw1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import android.widget.Toast


class DrawActivity : AppCompatActivity() {

    lateinit var drawView : CustomDrawingView

    private var colorAlpha = 255
    private var colorRed = 0
    private var colorGreen = 0
    private var colorBlue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        drawView = findViewById(R.id.custom_draw_view_id)

        val selectColorButton = findViewById<Button>(R.id.select_color_button_id)
        selectColorButton.setOnClickListener {
            val colorPicker = ColorPicker(this, colorRed,colorGreen,colorBlue)
            colorPicker.show()

            colorPicker.setCallback { color ->
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

            // check permissions
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                drawView.saveBitmap()
            } else {
                val toast = Toast.makeText(applicationContext, "Cannot save, try granting storage permission", Toast.LENGTH_SHORT)
                toast.show()
                val writePermission = Array(1) { Manifest.permission.WRITE_EXTERNAL_STORAGE}
                requestPermissions(writePermission, 666)
            }

        }

        val clearButton = findViewById<Button>(R.id.clear_button_id)
        clearButton.setOnClickListener {
            // it just resets the activity, simple
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}
