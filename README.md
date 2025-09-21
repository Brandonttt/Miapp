# Miapp - Aplicación de Demostración de Componentes Android

## Descripción del Proyecto

Miapp es una aplicación Android desarrollada como parte de una tarea académica que muestra diferentes componentes de la interfaz de usuario de Android. La aplicación incluye múltiples pantallas con diversos elementos interactivos como campos de texto, botones, checkboxes, listas desplegables y una calculadora funcional.

El objetivo principal del proyecto es demostrar el uso y la implementación correcta de diferentes widgets y componentes de UI en una aplicación Android, así como la navegación entre actividades.

## Tecnologías Utilizadas

- Kotlin (lenguaje principal para la lógica de la aplicación)
- XML (para diseño de interfaces)
- Android Studio

## Características Principales

1. **Formularios de entrada de texto** - Validación de campos como correo electrónico
2. **Componentes interactivos** - Botones con cambio de color y contadores
3. **Elementos de selección** - Checkboxes y switches
4. **Listas desplegables** - Menús con múltiples opciones
5. **Calculadora** - Implementación de una calculadora funcional en una actividad separada

## Capturas de Pantalla

### Pantalla 1 - Campos de Texto
<p align="center">
  <img src="https://github.com/user-attachments/assets/537897e3-e9d0-4f6d-86ae-8e9b632add5d" width="300" alt="Pantalla 1" />
</p>
Esta pantalla permite verificar componentes de texto, como el ingreso de datos y la validación de formatos (por ejemplo, correo electrónico).

### Pantalla 2 - Botones
<p align="center">
  <img src="https://github.com/user-attachments/assets/e312ce33-bf2d-41cd-93cb-a90a02144943" width="300" alt="Pantalla 2" />
</p>
En esta sección se muestran diferentes tipos de botones, incluyendo algunos que cambian de color al interactuar con ellos y otros que funcionan como contadores.

### Pantalla 3 - Elementos de Selección
<p align="center">
  <img src="https://github.com/user-attachments/assets/e78a6c4f-26fa-470e-9ef2-868ac9af9f12" width="300" alt="Pantalla 3" />
</p>
Esta pantalla contiene varios tipos de elementos de selección, como checkboxes y switches, mostrando diferentes formas de capturar la selección del usuario.

### Pantalla 4 - Listas Desplegables
<p align="center">
  <img src="https://github.com/user-attachments/assets/29fddac7-0327-44a8-908b-948b1ff1cddd" width="300" alt="Pantalla 4" />
</p>
Aquí se presentan diferentes tipos de listas que despliegan múltiples opciones para el usuario.

### Pantalla 5 - Vista Adicional
<p align="center">
  <img src="https://github.com/user-attachments/assets/1d76249a-381c-4e19-9ba0-5fc0983222b4" width="300" alt="Pantalla 5" />
</p>
Una vista adicional que muestra otros componentes de la interfaz de usuario.

### Calculadora
<p align="center">
  <img src="https://github.com/user-attachments/assets/fecc8966-bbfe-45a6-9704-3f97b7ea568e" width="300" alt="Calculadora" />
</p>
Esta pantalla implementa una calculadora funcional en una segunda actividad, demostrando la navegación entre activities y la implementación de operaciones matemáticas.

## Instrucciones para Ejecutar la Aplicación

### Requisitos Previos
- Android Studio (versión recomendada: última estable)
- JDK 8 o superior
- Dispositivo Android o emulador con API 21 (Android 5.0) o superior

### Pasos para Ejecutar
1. Clonar el repositorio:
   ```
   git clone https://github.com/Brandonttt/Miapp.git
   ```
2. Abrir Android Studio
3. Seleccionar "Open an existing project" y navegar hasta la carpeta donde se clonó el repositorio
4. Esperar a que Gradle sincronice el proyecto
5. Conectar un dispositivo Android mediante USB o iniciar un emulador
6. Hacer clic en el botón "Run" (▶️) en la barra de herramientas
7. Seleccionar el dispositivo o emulador donde desea ejecutar la aplicación
8. La aplicación se instalará y ejecutará automáticamente

## Pruebas

Para probar la aplicación:
1. Verificar que todos los elementos de UI responden correctamente a las interacciones
2. Probar la validación de campos de texto (como el formato de correo electrónico)
3. Interactuar con los botones para comprobar los cambios visuales y el contador
4. Seleccionar diferentes opciones en los checkboxes y switches
5. Expandir las listas y seleccionar diferentes opciones
6. Navegar a la calculadora y realizar operaciones matemáticas básicas para verificar su funcionamiento

## Dificultades Encontradas y Soluciones

### Desafío 1: Implementación de la Navegación Entre Actividades
**Problema:** Configurar la transición fluida entre la actividad principal y la calculadora.
**Solución:** Utilicé Intents explícitos para manejar la navegación y transferir datos entre actividades, además de configurar correctamente el archivo AndroidManifest.xml para declarar todas las actividades.

### Desafío 2: Validación de Campos de Entrada
**Problema:** Implementar validación en tiempo real para campos como el correo electrónico.
**Solución:** Utilicé TextWatcher para monitorear los cambios en los EditText y aplicar patrones regex para validar el formato del correo electrónico.

### Desafío 3: Diseño Responsive
**Problema:** Asegurar que la aplicación se vea bien en diferentes tamaños de pantalla.
**Solución:** Implementé layouts con ConstraintLayout y utilicé dimensiones relativas en lugar de valores fijos para asegurar la adaptabilidad a diferentes tamaños de pantalla.

## Hallazgos y Aprendizajes

1. **Arquitectura de la Aplicación:** Comprendí la importancia de estructurar adecuadamente una aplicación Android para facilitar su mantenimiento y escalabilidad.

2. **Patrones de Diseño de UI:** Aprendí sobre las mejores prácticas para diseñar interfaces de usuario intuitivas y accesibles en Android.

3. **Manejo de Eventos:** Profundicé en la implementación de listeners y el manejo eficiente de las interacciones del usuario.

4. **Navegación:** Mejoré mi comprensión sobre los diferentes métodos de navegación entre actividades y fragmentos en Android.

5. **Optimización de Rendimiento:** Descubrí técnicas para mejorar el rendimiento de la aplicación, como la reutilización de vistas y la optimización de layouts.

## Contribución

Si deseas contribuir a este proyecto, por favor:
1. Haz un fork del repositorio
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`)
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Autor

Brandon - [@Brandonttt](https://github.com/Brandonttt)

## Licencia

Este proyecto está bajo licencia [MIT](https://choosealicense.com/licenses/mit/) - ver el archivo LICENSE.md para más detalles.
