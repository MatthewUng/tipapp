package com.penguin.tipcalculator

import android.content.Context
import android.text.InputType
import android.text.method.QwertyKeyListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class User(context : Context) {
    var LL: LinearLayout = LinearLayout(context)
    var name_edittext : EditText = EditText(context)
    var cost_edittext : EditText = EditText(context)
    var delete_button : Button = Button(context)

    init {

        LL.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        name_edittext.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
        name_edittext.inputType = InputType.TYPE_CLASS_TEXT
        name_edittext.hint = "name"

        cost_edittext.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
        cost_edittext.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        cost_edittext.hint = "cost"

        delete_button.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        delete_button.setText("delete")

        LL.orientation = LinearLayout.HORIZONTAL
        LL.addView(name_edittext)
        LL.addView(cost_edittext)
        LL.addView(delete_button)
    }

    fun getName() : String{
        return this.name_edittext.text.toString()
    }

    fun getCost() : String {
        return this.cost_edittext.text.toString()
    }

}