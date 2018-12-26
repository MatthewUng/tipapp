package com.penguin.tipcalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var main_LL : LinearLayout
    lateinit var tax_input : EditText
    lateinit var tip_input : EditText
    lateinit var submit_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_LL = findViewById(R.id.scroll_ll)

        tax_input = findViewById(R.id.user_tax_input)
        tip_input = findViewById(R.id.user_tip_input)
        submit_button = findViewById(R.id.submit_button)


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

        val add_user_button = findViewById(R.id.add_user_button) as Button
        add_user_button.setOnClickListener {
            var temp_user = User(this)
            main_LL.addView(temp_user.LL)
            temp_user.delete_button.setOnClickListener {
                main_LL.removeView(temp_user.LL)
            }
        }

        submit_button.setOnClickListener {
            if (validate()) {
                val intent = Intent(this, DisplayActivity::class.java)
                val extras = Bundle()

                val names : Array<String?> = arrayOfNulls(main_LL.childCount)
                val costs = FloatArray(main_LL.childCount)

                for (i in 0 until main_LL.childCount){
                    val temp = main_LL.getChildAt(i)
                    val name = ((temp as LinearLayout).getChildAt(0) as EditText).text.toString()
                    val cost = (temp.getChildAt(1) as EditText).text.toString().toFloat()

                    names[i] = name
                    costs[i] = cost
                }

                extras.putFloat("TIP", tip_input.text.toString().toFloat())
                extras.putFloat("TAX", tax_input.text.toString().toFloat())
                extras.putStringArray("NAMES", names)
                extras.putFloatArray("COSTS", costs)

                intent.putExtras(extras)
                startActivity(intent)
            }
        }
    }

    fun validate() : Boolean {
        if (main_LL.childCount == 0) {
            Toast.makeText(this, "There is no bill to pay!", Toast.LENGTH_SHORT).show()
            return false
        }
        for(i in 0 until main_LL.childCount){
            println(i)
            val temp_LL : LinearLayout = main_LL.getChildAt(i) as LinearLayout
            if ((temp_LL.getChildAt(0) as EditText).text.toString() == "" ||
                (temp_LL.getChildAt(1) as EditText).text.toString() == ""){
                Toast.makeText(this, "Inputs can not be blank", Toast.LENGTH_SHORT).show()

                return false
            }
            // var cost : Float = (temp_LL.getChildAt(1) as EditText).text.toString().toFloat()
        }

        if (tax_input.text.toString() == "" || tip_input.text.toString() == ""){
             Toast.makeText(this, "Inputs can not be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        val tip = tip_input.text.toString().toFloat()
        if (tip >= 100){
            Toast.makeText(this, "Invalid tip input", Toast.LENGTH_SHORT).show()
            return false
        }

        val tax = tax_input.text.toString().toFloat()
        if (tax >= 100){
            Toast.makeText(this, "Invalid tax input", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }
}
