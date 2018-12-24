package com.penguin.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    // num users
    val num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tax_input = findViewById(R.id.user_tax_input) as EditText

        val sixtwosix_button = findViewById(R.id.sixtwosix_button) as Button
        val sd_button = findViewById(R.id.sd_button) as Button
        val bay_button = findViewById(R.id.bay_button) as Button
        sixtwosix_button.setOnClickListener {
            tax_input.setText("9.5")
        }
        sd_button.setOnClickListener {
            tax_input.setText("7.75")
        }
        bay_button.setOnClickListener {
            tax_input.setText("9.25")
        }
    }
}
