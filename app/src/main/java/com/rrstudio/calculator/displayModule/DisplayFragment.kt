package com.rrstudio.calculator.displayModule

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.rrstudio.calculator.R
import com.rrstudio.calculator.databinding.FragmentDisplayBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DisplayFragment : Fragment(R.layout.fragment_display) {

    private lateinit var mBinding: FragmentDisplayBinding

    private var num1: Float = 0.0F
    private var num2: Float = 0.0F
    private var operation: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentDisplayBinding.bind(view)

        with(mBinding){
            tvDisplayOperation.text = "0"
            btn1.setOnClickListener { setNumber(1) }
            btn2.setOnClickListener { setNumber(2) }
            btn3.setOnClickListener { setNumber(3) }
            btn4.setOnClickListener { setNumber(4) }
            btn5.setOnClickListener { setNumber(5) }
            btn6.setOnClickListener { setNumber(6) }
            btn7.setOnClickListener { setNumber(7) }
            btn8.setOnClickListener { setNumber(8) }
            btn9.setOnClickListener { setNumber(9) }
            btnZero.setOnClickListener { setNumber(0) }
            btnSum.setOnClickListener { setView("+") }
            btnC.setOnClickListener { setView("c") }
            btnRest.setOnClickListener { setView("-") }
            btnParenthesis1.setOnClickListener { setView("(")}
            btnParenthesis2.setOnClickListener { setView(")")}
            btnPercent.setOnClickListener { setView("%") }
            btnDivide.setOnClickListener { setView("÷") }
            btnMultiplication.setOnClickListener { setView("x") }
            btnDot.setOnClickListener { setView(".") }
            btnEqual.setOnClickListener { setView("=") }
        }

    }

    @SuppressLint("SetTextI18n")
    fun setNumber(butt: Int){
        if (mBinding.tvDisplayOperation.text == "0") {
            mBinding.tvDisplayOperation.text = ""
        }
        mBinding.tvDisplayOperation.text = mBinding.tvDisplayOperation.text.toString() + butt.toString()

    }

    @SuppressLint("SetTextI18n")
    private fun setView(butt: String) {

        if ( num1 != 0.0F) {
            num2 = (mBinding.tvDisplayOperation.text.toString()).toFloat()
        }else{
            num1 = (mBinding.tvDisplayOperation.text.toString()).toFloat()
            mBinding.tvDisplayResult.text = num1.toString()
            operation = ""
        }
        mBinding.tvDisplayOperation.text = "0"

        when(butt) {
            "c" -> {
                mBinding.tvDisplayOperation.text = "0"
                mBinding.tvDisplayResult.text = "0"
                num1 = 0.0F
                num2 = 0.0F
            }
            "+" ->{ operation = "sum"}
            "-"-> { operation = "rest"}
            "%"-> { operation = "percentage" }
            "÷"-> { operation = "division" }
            "x"-> { operation = "multiplication"}
            "."-> { mBinding.tvDisplayOperation.text = mBinding.tvDisplayOperation.text.toString() + "."}
            "("-> { mBinding.tvDisplayOperation.text = mBinding.tvDisplayOperation.text.toString() + "("}
            ")"-> { mBinding.tvDisplayOperation.text = mBinding.tvDisplayOperation.text.toString() + ")"}
            "="-> {
                when (operation) {
                    "sum" -> {
                        doAsync {
                            val c: Float = num1 + num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                mBinding.tvDisplayResult.text = c.toString()
                            }
                        }
                    }

                    "rest" -> {
                        doAsync {
                            val c: Float = num1 - num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                mBinding.tvDisplayResult.text = c.toString()
                            }
                        }
                    }
                    "percentage" -> {
                        doAsync {
                            val c: Float = 100*(num1 / num2)
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                mBinding.tvDisplayResult.text = c.toString()
                            }
                        }
                    }
                    "division" -> {
                        doAsync {
                            val c: Float = num1/num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                mBinding.tvDisplayResult.text = c.toString()
                            }
                        }
                    }
                    "multiplication" -> {doAsync {
                        val c: Float = num1 * num2
                        num1 = c
                        num2 = 0.0F
                        uiThread {
                            mBinding.tvDisplayResult.text = c.toString()
                        }
                    }}
                    "dot" -> {doAsync {

                        uiThread {

                        }
                    }}
                }
            }
        }

    }

}