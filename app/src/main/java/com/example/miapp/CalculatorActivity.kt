package com.example.miapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private lateinit var resultTextView: TextView

    private var currentInput = StringBuilder()
    private var operand1: Double? = null
    private var pendingOperation: String = ""
    private val decimalFormat = DecimalFormat("#.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Configurar ActionBar
        supportActionBar?.apply {
            title = "Calculadora"
            setDisplayHomeAsUpEnabled(true)
        }

        // Inicializar vistas
        displayTextView = findViewById(R.id.displayTextView)
        resultTextView = findViewById(R.id.resultTextView)

        // Configurar estado inicial
        displayTextView.text = ""
        resultTextView.text = ""
    }

    fun onDigitClick(view: View) {
        val button = view as Button
        currentInput.append(button.text)
        updateDisplay()
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val operator = button.text.toString()

        // Si ya hay un operando y una operación pendiente, calcular el resultado
        if (operand1 != null && currentInput.isNotEmpty()) {
            calculate()
        } else if (currentInput.isNotEmpty()) {
            // Guardar el primer operando
            operand1 = currentInput.toString().toDouble()
        }

        pendingOperation = operator
        currentInput.clear()
        updateDisplay()
    }

    fun onDecimalClick(view: View) {
        // Agregar punto decimal solo si no existe ya
        if (!currentInput.contains('.')) {
            if (currentInput.isEmpty()) {
                currentInput.append("0")
            }
            currentInput.append(".")
            updateDisplay()
        }
    }

    fun onEqualsClick(view: View) {
        calculate()
        pendingOperation = ""
    }

    fun onClearClick(view: View) {
        currentInput.clear()
        operand1 = null
        pendingOperation = ""
        updateDisplay()
        resultTextView.text = ""
    }

    private fun calculate() {
        if (operand1 != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toString().toDouble()
            val result = when (pendingOperation) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "×" -> operand1!! * operand2
                "÷" -> {
                    if (operand2 == 0.0) {
                        resultTextView.text = "Error: División por cero"
                        return
                    }
                    operand1!! / operand2
                }
                else -> operand2
            }

            resultTextView.text = decimalFormat.format(result)
            operand1 = result
            currentInput.clear()
        }
    }

    private fun updateDisplay() {
        val display = StringBuilder()

        if (operand1 != null) {
            display.append(decimalFormat.format(operand1))
            display.append(" ")
            display.append(pendingOperation)
            display.append(" ")
        }

        display.append(currentInput)
        displayTextView.text = display.toString()
    }

    // Manejar el botón de regreso de la ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}