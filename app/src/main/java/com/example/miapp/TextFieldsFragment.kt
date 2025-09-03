package com.example.miapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TextFieldsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_textfields, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // EditText básico
        val editTextBasico = view.findViewById<EditText>(R.id.editTextBasico)
        val resultadoBasico = view.findViewById<TextView>(R.id.resultadoBasico)

        editTextBasico.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                resultadoBasico.text = "Texto ingresado: ${s.toString()}"
            }
        })

        // TextInputLayout con validación
        val textInputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEditText = view.findViewById<TextInputEditText>(R.id.textInputEditText)
        val btnValidar = view.findViewById<Button>(R.id.btnValidar)

        btnValidar.setOnClickListener {
            val email = textInputEditText.text.toString()
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textInputLayout.error = "Por favor ingresa un email válido"
            } else {
                textInputLayout.error = null
                Toast.makeText(context, "Email válido: $email", Toast.LENGTH_SHORT).show()
            }
        }

        // EditText para números
        val editTextNumero = view.findViewById<EditText>(R.id.editTextNumero)
        val btnSumar = view.findViewById<Button>(R.id.btnSumar)
        val resultadoNumerico = view.findViewById<TextView>(R.id.resultadoNumerico)

        btnSumar.setOnClickListener {
            val numeroTexto = editTextNumero.text.toString()
            if (numeroTexto.isNotEmpty()) {
                try {
                    val numero = numeroTexto.toInt()
                    val resultado = numero + 10
                    resultadoNumerico.text = "Resultado de la suma: $resultado"
                } catch (e: NumberFormatException) {
                    resultadoNumerico.text = "Por favor ingresa un número válido"
                }
            } else {
                resultadoNumerico.text = "Ingresa un número"
            }
        }
    }
}