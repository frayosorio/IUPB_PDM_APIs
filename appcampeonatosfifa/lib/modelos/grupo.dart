import 'dart:convert';

import 'package:appcampeonatosfifa/modelos/campeonato.dart';

class Grupo {
  final int id;
  final String nombre;
  final Campeonato campeonato;

  Grupo({
    required this.id,
    required this.nombre,
    required this.campeonato,
  });

  factory Grupo.fromJson(Map<String, dynamic> json) {
    return Grupo(
      id: json['id'] as int,
      nombre: json['nombre'] as String,
      campeonato: Campeonato.fromJson(json['campeonato'] as Map<String, dynamic>),
    );
  }

  static List<Grupo> desdeListaJson(dynamic jsonList) {
    if (jsonList == null) return [];
    return (jsonList as List)
        .map((item) => Grupo.fromJson(item as Map<String, dynamic>))
        .toList();
  }
}