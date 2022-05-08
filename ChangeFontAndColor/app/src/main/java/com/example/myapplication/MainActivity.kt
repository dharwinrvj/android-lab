package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b1 = findViewById(R.id.button) as Button;
        val b2 = findViewById(R.id.button2) as Button;
        val text = findViewById(R.id.text) as TextView;
        var ch = 1;
        var font = 20f;
        b1.setOnClickListener() {
            text.setTextSize(font);
            font = font + 5;
            if (font == 50f) {
                font = 30f;

            }
        }
        b2.setOnClickListener() {
            when (ch) {
                1 -> text.setTextColor(Color.parseColor("#FF0000"));
                2 -> text.setTextColor(Color.parseColor("#E9F542"));
                3 -> text.setTextColor(Color.parseColor("#4B42F5"));
                4 -> text.setTextColor(Color.parseColor("#F542F2"));
                5 -> text.setTextColor(Color.parseColor("#F54275"));
            }
            ch++;
            if (ch == 5) {
                ch = 1;
            }

        }
    }
}