import 'dart:convert';

import 'package:appcampeonatosfifa/configuracion/configuracionapi.dart';
import 'package:appcampeonatosfifa/modelos/campeonato.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

class CampeonatoServicio {
  final String urlBase = "${ConfiguracionApi.urlBase}/campeonatos";

  final variablesApp = FlutterSecureStorage();

  Future<String?> _getToken() async {
    return await variablesApp.read(key: "token");
  }

  Future<Map<String, String>> _getEncabezado() async {
    final token = await _getToken();
    return {
      "Authorization": "Bearer $token",
      "Content-Type": "application/json",
    };
  }

  Future<dynamic> getCampeonatos() async {
    final url = Uri.parse("$urlBase/listar");
    final encabezado = await _getEncabezado();
    try {
      final respuesta = await http.get(url, headers: encabezado);

      if (respuesta.statusCode == 200) {
        final List<dynamic> cuerpoJson = jsonDecode(respuesta.body);
        return Campeonato.desdeListaJson(cuerpoJson);
      }
      return [];
    } catch (e) {
      // Es buena idea capturar excepciones de red
      print("Error en el servicio de campeonatos: $e");
      return [];
    }
  }
}
