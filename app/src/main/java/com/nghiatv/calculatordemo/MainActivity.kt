package com.nghiatv.calculatordemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
            textClear.id -> {
                textExpression.text = ""
                textResult.text = ""
            }

            textBack.id -> {
                val string = textExpression.text.toString()
                if (string.isNotEmpty()) {
                    textExpression.text = string.substring(0, string.length - 1)
                }
                textResult.text = ""
            }

            textEqual.id -> {
                try {
                    val expression = ExpressionBuilder(textExpression.text.toString()).build()
                    val doubleResult = expression.evaluate()
                    val longResult = doubleResult.toLong()
                    if (doubleResult == longResult.toDouble()) {
                        textResult.text = longResult.toString()
                    } else {
                        textResult.text = doubleResult.toString()
                    }
                } catch (e: Exception) {

                }
            }
        }
    }

    fun initialize() {
        // Numbers
        textZero.setOnClickListener { appendOnExpression(ZERO, true) }
        textOne.setOnClickListener { appendOnExpression(ONE, true) }
        textTwo.setOnClickListener { appendOnExpression(TWO, true) }
        textThree.setOnClickListener { appendOnExpression(THREE, true) }
        textFour.setOnClickListener { appendOnExpression(FOUR, true) }
        textFive.setOnClickListener { appendOnExpression(FIVE, true) }
        textSix.setOnClickListener { appendOnExpression(SIX, true) }
        textSeven.setOnClickListener { appendOnExpression(SEVEN, true) }
        textEight.setOnClickListener { appendOnExpression(EIGHT, true) }
        textNine.setOnClickListener { appendOnExpression(NINE, true) }
        textDot.setOnClickListener { appendOnExpression(DOT, true) }

        // Operators
        textPlus.setOnClickListener { appendOnExpression(PLUS, false) }
        textMinus.setOnClickListener { appendOnExpression(MINUS, false) }
        textTimes.setOnClickListener { appendOnExpression(TIMES, false) }
        textDiv.setOnClickListener { appendOnExpression(DIV, false) }
        textOpen.setOnClickListener { appendOnExpression(OPEN, false) }
        textClose.setOnClickListener { appendOnExpression(CLOSE, false) }

        // Set event onclick
        textClear.setOnClickListener(this)
        textBack.setOnClickListener(this)
        textEqual.setOnClickListener(this)
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (textResult.text.isNotEmpty()) {
            textExpression.text = ""
        }

        if (canClear) {
            textResult.text = ""
            textExpression.append(string)
        } else {
            textExpression.append(textResult.text)
            textExpression.append(string)
            textResult.text = ""
        }
    }
}
