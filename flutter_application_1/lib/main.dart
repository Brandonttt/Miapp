import 'package:flutter/material.dart';
import 'screens/text_fields_screen.dart';
import 'screens/botones_screen.dart';
import 'screens/seleccion_screen.dart';
import 'screens/listas_screen.dart';
import 'screens/informacion_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mi App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  
  
  final List<Widget> _screens = [
    const TextFieldsScreen(),
    const BotonesScreen(),
    const SeleccionScreen(),
    const ListasScreen(),
    const InformacionScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: _screens[_selectedIndex],
      ),
      bottomNavigationBar: NavigationBar(
        selectedIndex: _selectedIndex,
        onDestinationSelected: (int index) {
          setState(() {
            _selectedIndex = index;
          });
        },
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.text_fields),
            label: 'Text Fields',
          ),
          NavigationDestination(
            icon: Icon(Icons.smart_button),
            label: 'Botones',
          ),
          NavigationDestination(
            icon: Icon(Icons.check_box),
            label: 'Selección',
          ),
          NavigationDestination(
            icon: Icon(Icons.list),
            label: 'Listas',
          ),
          NavigationDestination(
            icon: Icon(Icons.info),
            label: 'Información',
          ),
        ],
      ),
    );
  }
}