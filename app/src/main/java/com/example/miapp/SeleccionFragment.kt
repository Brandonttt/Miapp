package com.example.miapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SeleccionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // CheckBoxes
        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
        val btnComprobarCheckbox = view.findViewById<Button>(R.id.btnComprobarCheckbox)
        val resultadoCheckbox = view.findViewById<TextView>(R.id.resultadoCheckbox)

        btnComprobarCheckbox.setOnClickListener {
            val selecciones = mutableListOf<String>()

            if (checkBox1.isChecked) selecciones.add("Pizza")
            if (checkBox2.isChecked) selecciones.add("Hamburguesa")
            if (checkBox3.isChecked) selecciones.add("Tacos")

            val mensaje = if (selecciones.isEmpty()) {
                "No has seleccionado ninguna comida"
            } else {
                "Has seleccionado: ${selecciones.joinToString(", ")}"
            }

            resultadoCheckbox.text = mensaje
        }

        // RadioButtons
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val btnComprobarRadio = view.findViewById<Button>(R.id.btnComprobarRadio)
        val resultadoRadio = view.findViewById<TextView>(R.id.resultadoRadio)

        btnComprobarRadio.setOnClickListener {
            val radioButtonId = radioGroup.checkedRadioButtonId
            val mensaje = if (radioButtonId == -1) {
                "No has seleccionado ninguna opción"
            } else {
                val radioButton = view.findViewById<RadioButton>(radioButtonId)
                "Has seleccionado: ${radioButton.text}"
            }

            resultadoRadio.text = mensaje
        }

        // Switch
        val switchWifi = view.findViewById<Switch>(R.id.switchWifi)
        val switchBluetooth = view.findViewById<Switch>(R.id.switchBluetooth)
        val resultadoSwitch = view.findViewById<TextView>(R.id.resultadoSwitch)

        val actualizarEstadoSwitch = {
            val estadoWifi = if (switchWifi.isChecked) "activado" else "desactivado"
            val estadoBluetooth = if (switchBluetooth.isChecked) "activado" else "desactivado"
            resultadoSwitch.text = "WiFi: $estadoWifi\nBluetooth: $estadoBluetooth"
        }

        switchWifi.setOnCheckedChangeListener { _, _ -> actualizarEstadoSwitch() }
        switchBluetooth.setOnCheckedChangeListener { _, _ -> actualizarEstadoSwitch() }

        // Inicialización del texto
        actualizarEstadoSwitch()
    }
}