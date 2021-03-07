package ru.netology.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.R

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

            intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=O13ghWrSz7c"))
            startActivity(intent)
    }
}