import 'package:appcampeonatosfifa/servicios/campeonatoservicio.dart';
import 'package:flutter/material.dart';

class Campeonato extends StatefulWidget {
  const Campeonato({super.key});

  @override
  State<Campeonato> createState() => _CampeonatoState();
}

class _CampeonatoState extends State<Campeonato> {
  List<dynamic> campeonatos = [];

  Future<void> _listarCampeonatos() async {
    final servicio = Campeonatoservicio();
    final datos = await servicio.getCampeonatos();
    setState(() {
      campeonatos = datos;
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
      appBar: AppBar(title: const Text("Campeonatos Mundiales de la FIFA")),
    );
  }
}
