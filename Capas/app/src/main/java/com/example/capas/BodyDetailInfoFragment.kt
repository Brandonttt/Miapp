package com.example.capas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.text.Html
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat

class BodyDetailInfoFragment : Fragment() {

    private var partName: String? = null
    private var partDescription: String? = null
    private var partImageId: Int = R.drawable.body_default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            partName = it.getString(ARG_PART_NAME)
            partDescription = it.getString(ARG_PART_DESC)
            partImageId = it.getInt(ARG_PART_IMAGE, R.drawable.body_default)
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
        val titleText = view.findViewById<TextView>(R.id.detail_title)
        val bodyImage = view.findViewById<ImageView>(R.id.body_image)

        // Establecer el título según la parte del cuerpo
        titleText.text = partName ?: "Parte del cuerpo"

        // Aplicar animaciones solo al contenido que existe
        val slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        infoText.startAnimation(slideUp)

        // Cambiar la imagen según la parte del cuerpo - USAR SOLO PNG
        val imageResource = when (partName) {
            "Ojos" -> R.drawable.eyes
            "Boca" -> R.drawable.mouth
            "Cerebro" -> R.drawable.brain
            "Corazón" -> R.drawable.heart
            "Pulmones" -> R.drawable.lungs
            "Estómago" -> R.drawable.stomach
            "Rodilla" -> R.drawable.knee
            "Muslo" -> R.drawable.thigh
            "Pie" -> R.drawable.foot
            else -> R.drawable.human_body
        }

        // Asignar la imagen correspondiente al cuerpo humano
        bodyImage.setImageResource(imageResource)

        // Obtener el color de resaltado y aplicarlo al cuerpo humano
        val highlightColor = when (partName) {
            "Ojos" -> R.color.highlight_eyes
            "Boca" -> R.color.highlight_mouth
            "Cerebro" -> R.color.highlight_brain
            "Corazón" -> R.color.highlight_heart
            "Pulmones" -> R.color.highlight_lungs
            "Estómago" -> R.color.highlight_stomach
            "Rodilla" -> R.color.highlight_knee
            "Muslo" -> R.color.highlight_thigh
            "Pie" -> R.color.highlight_foot
            else -> R.color.default_highlight
        }

        // Aplicar el color de resaltado al cuerpo humano
        context?.let {
            val color = ContextCompat.getColor(it, highlightColor)
            bodyImage.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY)
        }

        // Proporcionar información detallada según la parte del cuerpo con formato HTML
        val detailInfo = when (partName) {
            "Ojos" -> """
                <h3>Los Ojos: Ventanas al Mundo</h3>
                <p>Los ojos son órganos fotosensibles que nos permiten percibir el mundo visual. Están compuestos por varias estructuras clave:</p>
                <ul>
                    <li><b>Córnea:</b> La ventana transparente externa que ayuda a enfocar la luz</li>
                    <li><b>Iris:</b> La parte colorida que controla la cantidad de luz que entra</li>
                    <li><b>Pupila:</b> La abertura central que se expande y contrae</li>
                    <li><b>Cristalino:</b> Lente natural que enfoca la luz en la retina</li>
                    <li><b>Retina:</b> Capa sensible a la luz con fotorreceptores (conos y bastones)</li>
                </ul>
                <p>Los ojos procesan aproximadamente el 80% de toda la información que recibimos del entorno, haciendo de la vista nuestro sentido dominante.</p>
            """
            "Boca" -> """
                <h3>La Boca: Puerta de entrada al sistema digestivo</h3>
                <p>La boca es mucho más que una simple apertura - es un órgano complejo con múltiples funciones:</p>
                <ul>
                    <li><b>Funciones principales:</b> Alimentación, comunicación y expresión</li>
                    <li><b>Componentes clave:</b>
                        <ul>
                            <li>Dientes: Trituran y procesan los alimentos</li>
                            <li>Lengua: Órgano muscular para el gusto y la manipulación de alimentos</li>
                            <li>Glándulas salivales: Producen saliva para iniciar la digestión</li>
                            <li>Paladar: Define la cavidad oral</li>
                        </ul>
                    </li>
                </ul>
                <p>La saliva contiene enzimas digestivas que comienzan a descomponer los almidones incluso antes de tragar.</p>
            """
            "Cerebro" -> """
                <h3>El Cerebro: Centro de control del cuerpo</h3>
                <p>El cerebro es el órgano más complejo y fascinante del cuerpo humano:</p>
                <ul>
                    <li><b>Estructura:</b> Dividido en dos hemisferios conectados por el cuerpo calloso</li>
                    <li><b>Regiones principales:</b>
                        <ul>
                            <li><b>Lóbulo frontal:</b> Pensamiento, planificación y control motor</li>
                            <li><b>Lóbulo parietal:</b> Procesamiento sensorial</li>
                            <li><b>Lóbulo temporal:</b> Audición y memoria</li>
                            <li><b>Lóbulo occipital:</b> Procesamiento visual</li>
                        </ul>
                    </li>
                    <li><b>Datos fascinantes:</b>
                        <ul>
                            <li>Pesa aproximadamente 1.4 kg (2% del peso corporal)</li>
                            <li>Contiene unos 86 mil millones de neuronas</li>
                            <li>Consume 20% del oxígeno y energía del cuerpo</li>
                        </ul>
                    </li>
                </ul>
                <p>El cerebro humano es capaz de generar aproximadamente 23 vatios de energía eléctrica cuando está despierto - suficiente para encender una bombilla LED.</p>
            """
            "Corazón" -> """
                <h3>El Corazón: El motor de la vida</h3>
                <p>El corazón es un órgano muscular extraordinario que mantiene la circulación sanguínea:</p>
                <ul>
                    <li><b>Estructura:</b> Dividido en cuatro cámaras: dos aurículas y dos ventrículos</li>
                    <li><b>Funcionamiento:</b> 
                        <ul>
                            <li>Late aproximadamente 100,000 veces al día</li>
                            <li>Bombea más de 7,500 litros de sangre diariamente</li>
                            <li>Se contrae mediante impulsos eléctricos generados por el nodo sinoauricular</li>
                        </ul>
                    </li>
                    <li><b>Curiosidades:</b>
                        <ul>
                            <li>El corazón puede seguir latiendo incluso fuera del cuerpo si tiene oxígeno</li>
                            <li>Los latidos se sincronizan con nuestras emociones y estado físico</li>
                        </ul>
                    </li>
                </ul>
                <p>Durante toda una vida, el corazón late aproximadamente 2,500 millones de veces sin descansar.</p>
            """
            "Pulmones" -> """
                <h3>Los Pulmones: Nuestra conexión con el aire</h3>
                <p>Los pulmones son órganos vitales del sistema respiratorio:</p>
                <ul>
                    <li><b>Estructura:</b>
                        <ul>
                            <li>El pulmón derecho tiene tres lóbulos; el izquierdo tiene dos (para acomodar el corazón)</li>
                            <li>Contienen millones de alvéolos (pequeños sacos de aire)</li>
                        </ul>
                    </li>
                    <li><b>Función:</b>
                        <ul>
                            <li>Intercambio de oxígeno y dióxido de carbono</li>
                            <li>Filtración de partículas y microorganismos</li>
                            <li>Regulación del pH sanguíneo</li>
                        </ul>
                    </li>
                    <li><b>Datos interesantes:</b>
                        <ul>
                            <li>La superficie interna total es aproximadamente del tamaño de una cancha de tenis</li>
                            <li>Respiramos unas 20,000 veces al día</li>
                        </ul>
                    </li>
                </ul>
                <p>Los pulmones son los únicos órganos que pueden flotar en el agua debido al aire residual que siempre contienen.</p>
            """
            "Estómago" -> """
                <h3>El Estómago: Laboratorio químico del cuerpo</h3>
                <p>El estómago es un órgano muscular expandible con forma de J:</p>
                <ul>
                    <li><b>Capacidad:</b> Puede expandirse hasta contener 1-1.5 litros</li>
                    <li><b>Funciones:</b>
                        <ul>
                            <li>Almacenamiento temporal de alimentos</li>
                            <li>Producción de ácido clorhídrico (pH 1.5-3.5)</li>
                            <li>Secreción de enzimas digestivas como la pepsina</li>
                            <li>Conversión de alimentos en quimo</li>
                        </ul>
                    </li>
                    <li><b>Protección:</b> Una capa de moco protege las paredes del estómago de su propio ácido</li>
                </ul>
                <p>El revestimiento del estómago se renueva completamente cada 3-4 días para evitar la autodigestión por sus poderosos ácidos.</p>
            """
            "Rodilla" -> """
                <h3>La Rodilla: La articulación más compleja</h3>
                <p>La rodilla es una obra maestra biomecánica:</p>
                <ul>
                    <li><b>Estructura:</b>
                        <ul>
                            <li>Conecta el fémur con la tibia y el peroné</li>
                            <li>Incluye la rótula, el hueso sesamoideo más grande</li>
                            <li>Estabilizada por ligamentos cruzados y colaterales</li>
                        </ul>
                    </li>
                    <li><b>Componentes:</b>
                        <ul>
                            <li>Cartílago articular: Amortigua el impacto</li>
                            <li>Meniscos: Mejoran la estabilidad y distribución de peso</li>
                            <li>Líquido sinovial: Lubrica la articulación</li>
                        </ul>
                    </li>
                    <li><b>Capacidades:</b> Soporta hasta 7 veces el peso corporal durante actividades como saltar</li>
                </ul>
                <p>La rodilla es la articulación más vulnerable a lesiones en deportistas debido a las fuerzas que soporta y su complejo funcionamiento.</p>
            """
            "Muslo" -> """
                <h3>El Muslo: Centro de potencia del cuerpo</h3>
                <p>El muslo es una región fundamental para la movilidad humana:</p>
                <ul>
                    <li><b>Estructura ósea:</b> Contiene el fémur, el hueso más largo y fuerte del cuerpo</li>
                    <li><b>Grupos musculares:</b>
                        <ul>
                            <li><b>Cuádriceps:</b> Grupo de cuatro músculos en la parte frontal que extienden la rodilla</li>
                            <li><b>Isquiotibiales:</b> Tres músculos en la parte posterior que flexionan la rodilla</li>
                            <li><b>Aductores:</b> Músculos de la parte interna que juntan las piernas</li>
                        </ul>
                    </li>
                    <li><b>Función:</b> Proporciona la fuerza para caminar, correr, saltar y mantener la postura</li>
                </ul>
                <p>Los músculos del muslo son los más grandes y poderosos del cuerpo humano, y pueden generar fuerzas tremendas en atletas entrenados.</p>
            """
            "Pie" -> """
                <h3>El Pie: Base de soporte y movilidad</h3>
                <p>El pie es una estructura arquitectónica sorprendentemente compleja:</p>
                <ul>
                    <li><b>Composición:</b>
                        <ul>
                            <li>26 huesos (25% de todos los huesos del cuerpo)</li>
                            <li>33 articulaciones para flexibilidad</li>
                            <li>Más de 100 músculos, tendones y ligamentos</li>
                        </ul>
                    </li>
                    <li><b>Estructura:</b>
                        <ul>
                            <li><b>Tarso:</b> Huesos posteriores que forman el tobillo</li>
                            <li><b>Metatarso:</b> Huesos medios que forman el arco</li>
                            <li><b>Falanges:</b> Huesos de los dedos</li>
                        </ul>
                    </li>
                    <li><b>Arcos:</b> Distribuyen el peso y absorben impactos durante la locomoción</li>
                </ul>
                <p>Durante un día normal de caminata, los pies soportan una fuerza acumulada equivalente a cientos de toneladas, demostrando su increíble resistencia.</p>
            """
            else -> """
                <h3>El Cuerpo Humano: Una máquina perfecta</h3>
                <p>El cuerpo humano es una obra maestra de la naturaleza compuesta por aproximadamente 37 billones de células organizadas en tejidos, órganos y sistemas:</p>
                <ul>
                    <li><b>Sistemas principales:</b>
                        <ul>
                            <li><b>Sistema nervioso:</b> Centro de control dirigido por el cerebro</li>
                            <li><b>Sistema circulatorio:</b> Transporte de nutrientes y oxígeno liderado por el corazón</li>
                            <li><b>Sistema respiratorio:</b> Intercambio de gases a través de los pulmones</li>
                            <li><b>Sistema digestivo:</b> Procesamiento de alimentos desde la boca hasta el intestino</li>
                            <li><b>Sistema muscular:</b> Más de 600 músculos que permiten el movimiento</li>
                            <li><b>Sistema esquelético:</b> 206 huesos que proporcionan estructura y protección</li>
                        </ul>
                    </li>
                    <li><b>Datos fascinantes:</b>
                        <ul>
                            <li>El ADN humano estirado mediría 2 veces la distancia de la Tierra al Sol y vuelta</li>
                            <li>Producimos aproximadamente 50,000 pensamientos cada día</li>
                            <li>El intestino delgado mide entre 6-7 metros de longitud</li>
                        </ul>
                    </li>
                </ul>
                <p>Selecciona una parte específica del cuerpo para obtener información más detallada.</p>
            """
        }

        // Aplicar formato HTML al texto
        infoText.text = Html.fromHtml(detailInfo, Html.FROM_HTML_MODE_COMPACT)
    }

    companion object {
        private const val ARG_PART_NAME = "part_name"
        private const val ARG_PART_DESC = "part_description"
        private const val ARG_PART_IMAGE = "part_image"

        @JvmStatic
        fun newInstance(partName: String, partDescription: String, partImageId: Int) =
            BodyDetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PART_NAME, partName)
                    putString(ARG_PART_DESC, partDescription)
                    putInt(ARG_PART_IMAGE, partImageId)
                }
            }
    }
}