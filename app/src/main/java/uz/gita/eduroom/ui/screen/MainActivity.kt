package uz.gita.eduroom.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import uz.gita.eduroom.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = View(this)

    }
}