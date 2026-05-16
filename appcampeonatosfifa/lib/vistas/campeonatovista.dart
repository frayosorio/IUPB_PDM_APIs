import 'package:appcampeonatosfifa/modelos/campeonato.dart';
import 'package:appcampeonatosfifa/servicios/campeonatoservicio.dart';
import 'package:flutter/material.dart';

class CampeonatoVista extends StatefulWidget {
  const CampeonatoVista({super.key});

  @override
  State<CampeonatoVista> createState() => _CampeonatoVistaState();
}

class _CampeonatoVistaState extends State<CampeonatoVista> {
  final CampeonatoServicio _servicio = CampeonatoServicio();
  List<Campeonato> _campeonatos = [];
  List<dynamic> _grupos = [];
  Campeonato? _campeonatoSeleccionado;
  bool _cargando = true;

  Future<void> _listarCampeonatos() async {
    final datos = await _servicio.getCampeonatos();
    setState(() {
      _campeonatos = datos;
      _cargando = false;
    });
  }

  @override
  void initState() {
    super.initState();
    _listarCampeonatos();
  }

  @override
  Widget build(BuildContext contexto) {
    return Scaffold(
      backgroundColor: Colors.grey[100],
      appBar: AppBar(
        title: const Text(
          "Campeonatos Mundiales",
          style: TextStyle(fontWeight: FontWeight.bold, color: Colors.white),
        ),
        centerTitle: true,
        backgroundColor: Colors.indigo,
        elevation: 4,
      ),
      body: _cargando
          ? const Center(
              child: CircularProgressIndicator(
                valueColor: AlwaysStoppedAnimation<Color>(Colors.indigo),
              ),
            )
          : Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    "Selección de Torneo",
                    style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      color: Colors.indigo,
                    ),
                  ),
                  const SizedBox(height: 10),

                  // Tarjeta contenedora del Dropdown
                  Card(
                    elevation: 3,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    child: Padding(
                      padding: const EdgeInsets.all(12.0),
                      child: DropdownButtonFormField<Campeonato>(
                        initialValue: _campeonatoSeleccionado,
                        hint: const Text("Seleccione un campeonato"),
                        isExpanded: true,
                        decoration: InputDecoration(
                          prefixIcon: const Icon(
                            Icons.emoji_events,
                            color: Colors.amber,
                          ),
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                            borderSide: BorderSide.none,
                          ),
                          filled: true,
                          fillColor: Colors.grey[50],
                        ),
                        items: _campeonatos.map((campeonato) {
                          return DropdownMenuItem<Campeonato>(
                            value: campeonato,
                            child: Text(
                              "${campeonato.nombre} (${campeonato.anio})",
                              style: const TextStyle(fontSize: 15),
                            ),
                          );
                        }).toList(),
                        onChanged: (Campeonato? seleccionado) {
                          setState(() {
                            _campeonatoSeleccionado = seleccionado;
                            _grupos = [];
                          });
                        },
                      ),
                    ),
                  ),

                  const SizedBox(height: 20),
                ],
              ),
            ),
    );
  }
}
