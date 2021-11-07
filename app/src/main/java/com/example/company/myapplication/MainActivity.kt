package com.example.company.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQ_CODE = 1
    var button: Button? = null
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        button?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        REQ_CODE)
            }
        }
    }

//    private fun requestOnButton() {
//        val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//        if (status == PackageManager.PERMISSION_GRANTED){
//            //use camera
//
//        }
//        else {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), REQ_CODE)
//
//        }
//    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        textView = findViewById(R.id.textView)
        when (requestCode) {
            REQ_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Разрешение выдано, можно использовать "опасный" функционал

                        textView?.text = "Granted"

                } else {
                    // Разрешение не выдано, необходимо использовать другой способ
                    textView?.text = "Denied"

                }
                return
            }
            else -> {
                // id запроса не совпадает с ожидаемым, игнорируем
            }
        }
    }
}
