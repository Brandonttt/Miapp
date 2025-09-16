import 'package:flutter/material.dart';

class SeleccionScreen extends StatefulWidget {
  const SeleccionScreen({super.key});

  @override
  State<SeleccionScreen> createState() => _SeleccionScreenState();
}

class _SeleccionScreenState extends State<SeleccionScreen> {
  bool _checkboxValue1 = false;
  bool _checkboxValue2 = false;
  bool _switchValue = false;
  double _sliderValue = 50;
  DateTime _selectedDate = DateTime.now();
  TimeOfDay _selectedTime = TimeOfDay.now();
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Selección'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Componentes de selección:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 24),
            
            // Checkboxes
            Row(
              children: [
                Checkbox(
                  value: _checkboxValue1,
                  onChanged: (value) {
                    setState(() {
                      _checkboxValue1 = value!;
                    });
                  },
                ),
                const Text('Opción 1'),
              ],
            ),
            Row(
              children: [
                Checkbox(
                  value: _checkboxValue2,
                  onChanged: (value) {
                    setState(() {
                      _checkboxValue2 = value!;
                    });
                  },
                ),
                const Text('Opción 2'),
              ],
            ),
            
            const Divider(),
            
            // Switch
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text('Activar/Desactivar'),
                Switch(
                  value: _switchValue,
                  onChanged: (value) {
                    setState(() {
                      _switchValue = value;
                    });
                  },
                ),
              ],
            ),
            
            const Divider(),
            
            // Slider
            Text('Valor: ${_sliderValue.round()}'),
            Slider(
              value: _sliderValue,
              min: 0,
              max: 100,
              divisions: 10,
              label: _sliderValue.round().toString(),
              onChanged: (value) {
                setState(() {
                  _sliderValue = value;
                });
              },
            ),
            
            const Divider(),
            
            // Date Picker
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Fecha: ${_selectedDate.day}/${_selectedDate.month}/${_selectedDate.year}'),
                ElevatedButton(
                  onPressed: () async {
                    final DateTime? picked = await showDatePicker(
                      context: context,
                      initialDate: _selectedDate,
                      firstDate: DateTime(2000),
                      lastDate: DateTime(2100),
                    );
                    if (picked != null && picked != _selectedDate) {
                      setState(() {
                        _selectedDate = picked;
                      });
                    }
                  },
                  child: const Text('Seleccionar fecha'),
                ),
              ],
            ),
            
            const SizedBox(height: 16),
            
            // Time Picker
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Hora: ${_selectedTime.format(context)}'),
                ElevatedButton(
                  onPressed: () async {
                    final TimeOfDay? picked = await showTimePicker(
                      context: context,
                      initialTime: _selectedTime,
                    );
                    if (picked != null && picked != _selectedTime) {
                      setState(() {
                        _selectedTime = picked;
                      });
                    }
                  },
                  child: const Text('Seleccionar hora'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}