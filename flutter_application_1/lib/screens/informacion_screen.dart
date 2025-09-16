import 'package:flutter/material.dart';

class InformacionScreen extends StatelessWidget {
  const InformacionScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Información'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Acerca de la aplicación',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 16),
            const Text(
              'Esta es una aplicación de demostración creada con Flutter para mostrar diferentes componentes de UI.',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 24),
            const Text(
              'Características',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const ListTile(
              leading: Icon(Icons.text_fields),
              title: Text('Text Fields'),
              subtitle: Text('Demostración de campos de texto'),
            ),
            const ListTile(
              leading: Icon(Icons.smart_button),
              title: Text('Botones'),
              subtitle: Text('Diferentes tipos de botones'),
            ),
            const ListTile(
              leading: Icon(Icons.check_box),
              title: Text('Selección'),
              subtitle: Text('Componentes de selección como checkboxes y switches'),
            ),
            const ListTile(
              leading: Icon(Icons.list),
              title: Text('Listas'),
              subtitle: Text('Ejemplo de listas dinámicas'),
            ),
            const Spacer(),
            const Center(
              child: Text(
                'Versión 1.0.0',
                style: TextStyle(fontSize: 14, color: Colors.grey),
              ),
            ),
          ],
        ),
      ),
    );
  }
}