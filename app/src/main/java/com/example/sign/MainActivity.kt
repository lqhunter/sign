package com.example.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = getSignature("aaa".toByteArray()).decodeToString()
    }

    /**
     * A native method that is implemented by the 'sign' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun getSignature(value:ByteArray): ByteArray

    companion object {
        // Used to load the 'sign' library on application startup.
        init {
            System.loadLibrary("sign")
            System.loadLibrary("crypto")
            System.loadLibrary("ssl")
        }
    }
}