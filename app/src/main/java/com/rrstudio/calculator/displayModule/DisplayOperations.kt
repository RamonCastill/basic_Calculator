package com.rrstudio.calculator.displayModule

import com.rrstudio.calculator.databinding.FragmentDisplayBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DisplayOperations {

    private val mBinding: FragmentDisplayBinding? = null

    private var c: Float = 0.0F

    fun makeOperations(operations: String, numa: Float, numb: Float){

        var num1 = numa
        var num2 = numb

        when (operations) {
            "sum" -> {
                doAsync {
                    c = num1 + num2
                    num1 = c
                    num2 = 0.0F
                    uiThread {
                        mBinding?.tvDisplayResult?.text  = c.toString()
                    }
                }
            }

            "rest" -> {
                doAsync {
                    val c: Float = num1 - num2
                    num1 = c
                    num2 = 0.0F
                    uiThread {
                        mBinding?.tvDisplayResult?.text = c.toString()
                    }
                }
            }
            "percentage" -> {
                doAsync {
                    val c: Float = 100*(num1 / num2)
                    num1 = c
                    num2 = 0.0F
                    uiThread {
                        mBinding?.tvDisplayResult?.text = c.toString()
                    }
                }
            }
            "division" -> {
                doAsync {
                    val c: Float = num1/num2
                    num1 = c
                    num2 = 0.0F
                    uiThread {
                        mBinding?.tvDisplayResult?.text = c.toString()
                    }
                }
            }
            "multiplication" -> {doAsync {
                val c: Float = num1 * num2
                num1 = c
                num2 = 0.0F
                uiThread {
                    mBinding?.tvDisplayResult?.text = c.toString()
                }
            }}
            "dot" -> {doAsync {

                uiThread {

                }
            }}
        }

    }

}