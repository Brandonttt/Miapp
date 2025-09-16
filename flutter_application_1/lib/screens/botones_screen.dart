import 'package:flutter/material.dart';

class BotonesScreen extends StatefulWidget {
  const BotonesScreen({super.key});

  @override
  State<BotonesScreen> createState() => _BotonesScreenState();
}

class _BotonesScreenState extends State<BotonesScreen> {
  bool _isButtonPressed = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Botones'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Diferentes tipos de botones en Flutter:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 24),
            ElevatedButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Elevated Button presionado')),
                );
              },
              child: const Text('Elevated Button'),
            ),
            const SizedBox(height: 16),
            FilledButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Filled Button presionado')),
                );
              },
              child: const Text('Filled Button'),
            ),
            const SizedBox(height: 16),
            OutlinedButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Outlined Button presionado')),
                );
              },
              child: const Text('Outlined Button'),
            ),
            const SizedBox(height: 16),
            TextButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Text Button presionado')),
                );
              },
              child: const Text('Text Button'),
            ),
            const SizedBox(height: 16),
            IconButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Icon Button presionado')),
                );
              },
              icon: const Icon(Icons.favorite),
            ),
            const SizedBox(height: 16),
            FloatingActionButton(
              onPressed: () {
                setState(() {
                  _isButtonPressed = !_isButtonPressed;
                });
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(content: Text(_isButtonPressed 
                    ? 'Acción activada' 
                    : 'Acción desactivada')),
                );
              },
              child: Icon(_isButtonPressed ? Icons.favorite : Icons.favorite_border),
            ),
          ],
        ),
      ),
    );
  }
}