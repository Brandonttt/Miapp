# 🌈 Capas App

¡Bienvenido a **Capas**, una app Android que te lleva a través de una jerarquía de niveles con transiciones animadas y selector de tema claro/oscuro! 🚀

---

## 📝 Descripción Detallada

**Capas** es una aplicación educativa desarrollada para mostrar cómo navegar entre niveles jerárquicos en Android, usando animaciones y guardando la elección de tema del usuario.  
El objetivo principal es demostrar buenas prácticas de arquitectura, navegación, persistencia de preferencias y personalización visual.

---

## ⚙️ Instrucciones para Ejecutar el Proyecto

1. 📥 **Clona el repositorio:**
   ```bash
   git clone https://github.com/Brandonttt/Capas.git
   ```
2. 👨‍💻 **Abre el proyecto en Android Studio.**
3. 🔄 **Sincroniza y descarga dependencias.**
4. 📱 **Conecta un dispositivo Android o inicia un emulador.**
5. ▶️ **Pulsa "Run" y ¡disfruta Capas!**

---

## 🎨 Decisiones de Diseño y Mecanismos de Transición

- 🧩 **Jerarquía:** Cada nivel es una actividad o fragmento independiente.
- 🎬 **Transiciones:** Animaciones suaves entre niveles para una navegación intuitiva.
- 💾 **Persistencia de Tema:** Se utiliza `SharedPreferences` para guardar la preferencia de tema del usuario.
- 🎚️ **Selector de Tema:** Permite cambiar entre modo claro y oscuro en cualquier momento desde el menú principal.

---

## 🖼️ Capturas de Pantalla de la Jerarquía

> **Recuerda:** Reemplaza los nombres de las imágenes por los reales cuando los subas.

### Nivel 1
- 🌞 Claro  
  ![Nivel 1 Claro](images/nivel1_claro.png)
- 🌚 Oscuro  
  ![Nivel 1 Oscuro](images/nivel1_oscuro.png)

### Nivel 2
- 🌞 Claro  
  ![Nivel 2 Claro](images/nivel2_claro.png)
- 🌚 Oscuro  
  ![Nivel 2 Oscuro](images/nivel2_oscuro.png)

### Nivel 3
- 🌞 Claro  
  ![Nivel 3 Claro](images/nivel3_claro.png)
- 🌚 Oscuro  
  ![Nivel 3 Oscuro](images/nivel3_oscuro.png)

_(Agrega más niveles si tu jerarquía es más profunda)_

---

## 🌓 Implementación de Temas con SharedPreferences

### 📖 Descripción

El cambio de tema se realiza con `SharedPreferences`, guardando la preferencia seleccionada por el usuario.  
¡Así, tu tema favorito se mantiene incluso al cerrar la app! 🌗

### 🛠️ ¿Cómo se implementó?

- Al cambiar de tema, se guarda la selección en `SharedPreferences`.
- Al iniciar la app, se lee la preferencia y se aplica el tema correspondiente.

```java
SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
boolean isDarkTheme = prefs.getBoolean("dark_theme", false);
if (isDarkTheme) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
} else {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
}
```

### 🖱️ Cómo usar el Selector de Tema

1. Abre el menú principal ☰.
2. Pulsa “Cambiar tema” 🎚️.
3. Elige entre “Claro” 🌞 y “Oscuro” 🌚.
4. ¡La app cambiará el tema al instante y lo recordará!

### 📷 Capturas de Pantalla: Jerarquía en ambos temas

_(Repite las imágenes de cada nivel mostrando ambos temas)_

---

## 🙌 Créditos

Desarrollado con ❤️ por **Brandonttt**.

¿Te gustó el proyecto? ¡No olvides dejar tu ⭐ y tus comentarios!
