package com.penguin.tipcalculator

import android.graphics.Typeface
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * A placeholder fragment containing a simple view.
 */
class DisplayActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_display, container, false)
    }


    override fun onViewCreated(view : View, savedInstanceState: Bundle?){
        val bundle = getActivity()!!.intent.getExtras()
        val names = bundle.getStringArray("NAMES")
        val costs = bundle.getFloatArray("COSTS")
        val tip = bundle.getFloat("TIP")
        val tax = bundle.getFloat("TAX")

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN

        var total_tax = 0.0
        var total_tip = 0.0
        var subtotal = 0.0

        for (i in names.indices){
            val tax_amt : Float = df.format(costs[i] * tax / 100.0).toFloat()
            val tip_amt : Float = df.format(costs[i] * tip / 100.0).toFloat()

            subtotal += costs[i]
            total_tax += tax_amt
            total_tip += tip_amt

            addToView(names[i], costs[i], costs[i] + tax_amt + tip_amt)
        }
        total_tax = df.format(total_tax).toDouble()
        total_tip = df.format(total_tip).toDouble()

        val subtotal_tv = (getView()!!.findViewById(R.id.display_subtotal) as TextView)
        subtotal_tv.text = "\$${subtotal}"
        subtotal_tv.setGravity(Gravity.CENTER)

        val tax_percentage_tv = getView()!!.findViewById(R.id.tax_percent_text_view) as TextView
        tax_percentage_tv.setText("@${tax}%")
        tax_percentage_tv.setGravity(Gravity.CENTER)

        val tax_applied_tv = (getView()!!.findViewById(R.id.total_with_tax_textview) as TextView)
        tax_applied_tv.setText("\$${total_tax+subtotal}")
        tax_applied_tv.setGravity(Gravity.CENTER)
        tax_applied_tv.setTypeface(tax_applied_tv.typeface, Typeface.BOLD)

        val tip_percent_tv = (getView()!!.findViewById(R.id.display_tip_rate_textview) as TextView)
        tip_percent_tv.setText("@${tip}%")
        tip_percent_tv.setGravity(Gravity.CENTER)

        val total_tip_tv = getView()!!.findViewById(R.id.display_tip_amt_textview) as TextView
        total_tip_tv.setText("\$${total_tip}")
        total_tip_tv.setGravity(Gravity.CENTER)
        total_tip_tv.setTypeface(total_tip_tv.typeface, Typeface.BOLD)

        val final_amt_tv = getView()!!.findViewById(R.id.display_final_amt_textview) as TextView
        final_amt_tv.text = "\$${total_tip + total_tax+subtotal}"
        final_amt_tv.setGravity(Gravity.CENTER)
        final_amt_tv.setTypeface(final_amt_tv.typeface, Typeface.BOLD)
    }

    fun addToView(name : String, cost : Float, total : Float){
        val LL : LinearLayout = getView()!!.findViewById(R.id.display_LL)

        val user_LL = LinearLayout(getActivity())
        val name_tv = TextView(getActivity())
        val subtotal_tv = TextView(getActivity())
        val total_tv = TextView(getActivity())

        user_LL.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        user_LL.orientation = LinearLayout.HORIZONTAL

        name_tv.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
        subtotal_tv.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        total_tv.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)


        name_tv.setPadding(10,10,10,10)
        subtotal_tv.setPadding(10,10,10,10)
        total_tv.setPadding(10,10,10,10)

        name_tv.gravity = Gravity.CENTER
        subtotal_tv.gravity = Gravity.CENTER
        total_tv.gravity = Gravity.CENTER

        name_tv.text = name
        subtotal_tv.text = "\$${cost}"
        total_tv.text = "\$${total}"


        total_tv.setTypeface(total_tv.typeface, Typeface.BOLD)


        user_LL.addView(name_tv)
        user_LL.addView(subtotal_tv)
        user_LL.addView(total_tv)
        LL.addView(user_LL)
    }
}
