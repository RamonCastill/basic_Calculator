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
    private val mInteractor: DisplayOperations? = null

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
            btnPercent.setOnClickListener { setView("%") }
            btnDivide.setOnClickListener { setView("÷") }
            btnMultiplication.setOnClickListener { setView("x") }
            btnDot.setOnClickListener { setNumber(".") }
            btnEqual.setOnClickListener { setView("=") }
        }

    }

    @SuppressLint("SetTextI18n")
    fun setNumber(butt: Any){
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
            if((num1.toInt()).toFloat() == num1 ){mBinding.tvDisplayResult.text = num1.toInt().toString()}
            else {mBinding.tvDisplayResult.text = num1.toString()}
            operation = ""
        }
        mBinding.tvDisplayOperation.text = "0"
        if (butt != "c")mBinding.showOperation.text = butt else mBinding.showOperation.text = ""

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
            "="-> {
                when (operation) {
                    "sum" -> {
                        doAsync {
                            val c: Float = num1 + num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                if((c.toInt()).toFloat() == c ){mBinding.tvDisplayResult.text = c.toInt().toString()}
                                else {mBinding.tvDisplayResult.text = c.toString()}
                                //mBinding.tvDisplayResult.text = c.toString()
                                operation = ""
                            }
                        }
                    }

                    "rest" -> {
                        doAsync {
                            val c: Float = num1 - num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                if((c.toInt()).toFloat() == c ){mBinding.tvDisplayResult.text = c.toInt().toString()}
                                else {mBinding.tvDisplayResult.text = c.toString()}
                                //mBinding.tvDisplayResult.text = c.toString()
                                operation = ""
                            }
                        }
                    }
                    "percentage" -> {
                        doAsync {
                            val c: Float = num2*(num1 / 100)
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                if((c.toInt()).toFloat() == c ){mBinding.tvDisplayResult.text = c.toInt().toString()}
                                else {mBinding.tvDisplayResult.text = c.toString()}
                                //mBinding.tvDisplayResult.text = c.toString()
                                operation = ""
                            }
                        }
                    }
                    "division" -> {
                        doAsync {
                            val c: Float = num1/num2
                            num1 = c
                            num2 = 0.0F
                            uiThread {
                                if((c.toInt()).toFloat() == c ){mBinding.tvDisplayResult.text = c.toInt().toString()}
                                else {mBinding.tvDisplayResult.text = c.toString()}
                                //mBinding.tvDisplayResult.text = c.toString()
                                operation = ""
                            }
                        }
                    }
                    "multiplication" -> {doAsync {
                        val c: Float = num1 * num2
                        num1 = c
                        num2 = 0.0F
                        uiThread {
                            if((c.toInt()).toFloat() == c ){mBinding.tvDisplayResult.text = c.toInt().toString()}
                            else {mBinding.tvDisplayResult.text = c.toString()}
                            //mBinding.tvDisplayResult.text = c.toString()
                            operation = ""
                        }
                    }}
                }
            }
        }

    }

}