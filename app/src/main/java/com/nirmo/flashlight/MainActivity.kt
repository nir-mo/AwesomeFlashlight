package com.nirmo.flashlight

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var cameraManager: CameraManager? = null
    private var cameraId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        try {
            cameraId = cameraManager!!.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                turnOnFlashlight()
            } else {
                turnOffFlashlight()
            }
        }
    }

    private fun turnOnFlashlight() {
        try {
            cameraManager!!.setTorchMode(cameraId!!, true)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun turnOffFlashlight() {
        try {
            cameraManager!!.setTorchMode(cameraId!!, false)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}
