package com.example.capas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BodyDetailInfoFragment : Fragment() {

    private var partName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            partName = it.getString(ARG_PART_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_body_detail_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoText = view.findViewById<TextView>(R.id.detail_info_text)

        // Proporcionar información detallada según la parte del cuerpo
        val detailInfo = when (partName) {
            "Ojos" -> "Los ojos son órganos fotosensibles que permiten la visión. Están compuestos por la córnea, iris, pupila, cristalino y retina. La retina contiene fotorreceptores (conos y bastones) que convierten la luz en señales nerviosas."
            "Boca" -> "La boca es la entrada al sistema digestivo y también juega un rol importante en la comunicación. Contiene los dientes, la lengua, las glándulas salivales y está conectada con la faringe."
            "Cerebro" -> "El cerebro es el órgano principal del sistema nervioso central. Está dividido en hemisferios y lóbulos, cada uno responsable de diferentes funciones como el pensamiento, memoria, visión, audición y control motor."
            "Corazón" -> "El corazón es un músculo que bombea sangre a través del sistema circulatorio. Tiene cuatro cámaras: dos aurículas y dos ventrículos. Late aproximadamente 100,000 veces al día."
            "Pulmones" -> "Los pulmones son órganos del sistema respiratorio donde ocurre el intercambio de oxígeno y dióxido de carbono. Contienen millones de alvéolos donde la sangre absorbe oxígeno y libera CO2."
            "Estómago" -> "El estómago es un órgano muscular del sistema digestivo que almacena y digiere alimentos. Produce ácido clorhídrico y enzimas para descomponer proteínas y otros nutrientes."
            "Rodilla" -> "La rodilla es una articulación de bisagra que conecta el fémur con la tibia y el peroné. Es la articulación más grande del cuerpo y permite la flexión y extensión de la pierna."
            "Muslo" -> "El muslo contiene el fémur, el hueso más largo del cuerpo, y músculos potentes como el cuádriceps y los isquiotibiales, fundamentales para caminar, correr y saltar."
            "Pie" -> "El pie está compuesto por 26 huesos, 33 articulaciones y más de 100 músculos, tendones y ligamentos. Proporciona soporte, balance y movilidad para la locomoción."
            else -> "Información detallada no disponible."
        }

        infoText.text = detailInfo
    }

    companion object {
        private const val ARG_PART_NAME = "part_name"

        @JvmStatic
        fun newInstance(partName: String) =
            BodyDetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PART_NAME, partName)
                }
            }
    }
}