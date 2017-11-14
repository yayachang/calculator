package com.test.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    var result:Double = 0.0
    var inputNum:Double = 0.0
    var tmpNum:Double = 0.0
    var operator = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun actionNum(action: String) = View.OnClickListener {

            if (isOperator(showTextView.text.toString())) {
                var removeItem = showTextView.text[showTextView.text.lastIndex].toString()
                showTextView.text = showTextView.text.removeSuffix(removeItem)
            }
            if (operator != "") {
                result = doCalculate(tmpNum, inputNum, operator)

                var resultText = result.toString()
                showTextView.text = ignoreDotZero(resultText)
                operator = ""
                Log.d("test", "result:"+showTextView.text)
            }

            showTextView.append(action)

        }
        addBtn.setOnClickListener(actionNum("+"))
        minusBtn.setOnClickListener(actionNum("-"))
        multipleBtn.setOnClickListener(actionNum("×"))
        divideBtn.setOnClickListener(actionNum("÷"))

        fun clickNum(digit: Int) = View.OnClickListener {

            if (isOperator(showTextView.text.toString())) {

                tmpNum = showTextView.text.toString().substring(0, showTextView.text.toString().length-1).toDouble()
                operator = showTextView.text[showTextView.text.lastIndex].toString()
                showTextView.text = "0"
            }

            if (showTextView.text.toString() == "0") {
                showTextView.text = digit.toString()
            } else {
                showTextView.append(digit.toString())
            }
            inputNum = showTextView.text.toString().toDouble()
        }

        num0Btn.setOnClickListener(clickNum(0))
        num1Btn.setOnClickListener(clickNum(1))
        num2Btn.setOnClickListener(clickNum(2))
        num3Btn.setOnClickListener(clickNum(3))
        num4Btn.setOnClickListener(clickNum(4))
        num5Btn.setOnClickListener(clickNum(5))
        num6Btn.setOnClickListener(clickNum(6))
        num7Btn.setOnClickListener(clickNum(7))
        num8Btn.setOnClickListener(clickNum(8))
        num9Btn.setOnClickListener(clickNum(9))
        dotBtn.setOnClickListener(View.OnClickListener {

            if (isOperator(showTextView.text.toString())) {

                tmpNum = showTextView.text.toString().substring(0, showTextView.text.toString().length-1).toDouble()
                operator = showTextView.text[showTextView.text.lastIndex].toString()
                showTextView.text = "0"
            }

            var lastStr = showTextView.text[showTextView.text.lastIndex].toString()
            if(lastStr != "."){
                showTextView.append(".")
            }
        })

        deleteBtn.setOnClickListener {
            if (showTextView.text.length > 0) {
                var deleteItem = showTextView.text[showTextView.text.lastIndex].toString()
                showTextView.text = showTextView.text.removeSuffix(deleteItem)
                if (deleteItem.contains("+")
                        || deleteItem.contains("-")
                        || deleteItem.contains("×")
                        || deleteItem.contains("÷")) {

                }
            }
        }
        clearBtn.setOnClickListener {
            showTextView.text = ""
        }
    }

    fun isOperator(showText: String): Boolean {
        return showText.contains("+") ||
                showText.contains("-") ||
                showText.contains("×") ||
                showText.contains("÷")
    }

    fun doCalculate(left: Double, right: Double, action: String): Double {

        var result:Double = 0.0

        when (action) {
            "+" -> {
                result = left + right

            }
            "-" -> {
                result = left - right

            }
            "×" -> {
                result = left * right

            }
            "÷" -> {
                result = left / right
            }
        }
        return result
    }

    fun ignoreDotZero(showText: String): String {

        if (showText.substring(showText.length - 2, showText.length) == ".0") {
            val decimalFormat = DecimalFormat("#")
            return decimalFormat.format(result)
        } else {
            return showText
        }
    }
}
