package com.baiana.debugproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var clicksDebugBtn = 0
    private var clicksErrorBtn = 0
    private var clicksInfoBtn = 0
    private var clicksWarningBtn = 0
    private var clicksVerboseBtn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLogListeners()

        val currentThread = Thread.currentThread()
        currentThread.setUncaughtExceptionHandler { _, throwable ->
            //todo implemente aqui seu exception handler
            val x = throwable.message
            val cause = throwable.cause
        }

    }

    private fun setLogListeners() {
        //todo altere os logs e adicione mais possibilidade de exceptions
        debugBTN?.setOnClickListener {
            //indexOutOfBoundTryCatch()
            clicksDebugBtn++
            Log.d("click", "Opa! Cliquei no botão 'Debug Log' $clicksDebugBtn vezes.")
        }
        errorBTN?.setOnClickListener {
            indexOutOfBoundTryCatch()
            clicksErrorBtn++
            Log.e("click", "Ah, Cliquei no botão 'Error log' $clicksErrorBtn vezes!")
            //throw Exception()
            /*try {
                val list = listOf<Int>(2, 1, 4)
                val a = list[6]
            } catch (e:NullPointerException){
                val x = 0
            }*/
        }
        infoBTN?.setOnClickListener {
            //indexOutOfBoundTryCatch()
            clicksInfoBtn++
            Log.i("click", "Opa! Cliquei no botão 'Info Log' $clicksInfoBtn vezes.")
        }
        warningBTN?.setOnClickListener {
            //indexOutOfBoundTryCatch()
            clicksWarningBtn++
            Log.w("click", "Opa! Cliquei no botão 'Warning Log' $clicksWarningBtn vezes.")
        }
        verboseBTN?.setOnClickListener {
            //indexOutOfBoundTryCatch()
            clicksVerboseBtn++
            Log.v("click", "Opa! Cliquei no botão 'Verbose Log' $clicksVerboseBtn vezes.")
        }
    }

    private fun indexOutOfBoundTryCatch() {
        var message: String = ""
        try {
            val list = listOf<Int>(2, 1, 4, 3, 5)
            val crashInt: Int = list[5]
            message = "Sobrevivi ao 'try'... Ufa! (Risos.)"
        } catch (e: IndexOutOfBoundsException) {
            message = "Ôpa! Entrei no 'catch' certo! =)"
        } catch (i: NullPointerException) {
            message = "Ah, entrei no 'catch' errado!... =("
        } finally {
            inputEDT?.setText(message)
            val t = 35
        }
    }
}