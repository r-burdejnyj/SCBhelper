package ru.telembot.scbhelperkotlin.ui.docs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.telembot.scbhelperkotlin.databinding.ActivityDocsBinding

class DocsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDocsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}