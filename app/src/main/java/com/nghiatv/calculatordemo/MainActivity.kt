package com.nghiatv.calculatordemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MainActivity"

    val ZERO = "0"
    val ONE = "1"
    val TWO = "2"
    val THREE = "3"
    val FOUR = "4"
    val FIVE = "5"
    val SIX = "6"
    val SEVEN = "7"
    val EIGHT = "8"
    val NINE = "9"
    val DOT = "."
    val PLUS = "+"
    val MINUS = "-"
    val TIMES = "*"
    val DIV = "/"
    val OPEN = "("
    val CLOSE = ")"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            text_clear.id -> {
                text_expression.text = ""
                text_result.text = ""
            }

            text_back.id -> {
                val string = text_expression.text.toString()
                if (string.isNotEmpty()) {
                    text_expression.text = string.substring(0, string.length - 1)
                }
                text_result.text = ""
            }

            text_equal.id -> {
                try {
                    val expression = ExpressionBuilder(text_expression.text.toString()).build()
                    val doubleResult = expression.evaluate()
                    val longResult = doubleResult.toLong()
                    if (doubleResult == longResult.toDouble()) {
                        text_result.text = longResult.toString()
                    } else {
                        text_result.text = doubleResult.toString()
                    }
                } catch (e: Exception) {
                    Log.d(TAG, " message: " + e.message)
                }
            }
        }
    }

    fun initialize() {
        // Numbers
        text_zero.setOnClickListener { appendOnExpression(ZERO, true) }
        text_one.setOnClickListener { appendOnExpression(ONE, true) }
        text_two.setOnClickListener { appendOnExpression(TWO, true) }
        text_three.setOnClickListener { appendOnExpression(THREE, true) }
        text_four.setOnClickListener { appendOnExpression(FOUR, true) }
        text_five.setOnClickListener { appendOnExpression(FIVE, true) }
        text_six.setOnClickListener { appendOnExpression(SIX, true) }
        text_seven.setOnClickListener { appendOnExpression(SEVEN, true) }
        text_eight.setOnClickListener { appendOnExpression(EIGHT, true) }
        text_nine.setOnClickListener { appendOnExpression(NINE, true) }
        text_dot.setOnClickListener { appendOnExpression(DOT, true) }

        // Operators
        text_plus.setOnClickListener { appendOnExpression(PLUS, false) }
        text_minus.setOnClickListener { appendOnExpression(MINUS, false) }
        text_times.setOnClickListener { appendOnExpression(TIMES, false) }
        text_div.setOnClickListener { appendOnExpression(DIV, false) }
        text_open.setOnClickListener { appendOnExpression(OPEN, false) }
        text_close.setOnClickListener { appendOnExpression(CLOSE, false) }

        // Set event onclick
        text_clear.setOnClickListener(this)
        text_back.setOnClickListener(this)
        text_equal.setOnClickListener(this)
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (text_result.text.isNotEmpty()) {
            text_expression.text = ""
        }

        if (canClear) {
            text_result.text = ""
            text_expression.append(string)
        } else {
            text_expression.append(text_result.text)
            text_expression.append(string)
            text_result.text = ""
        }
    }
}
