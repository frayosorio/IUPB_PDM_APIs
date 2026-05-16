import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

class Campeonatoservicio {
  final String urlBase = "http://10.0.2.2:8080/api/campeonatos";

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
    final respuesta = await http.get(url, headers: encabezado);

    print("Body: ${respuesta.body}");
    if (respuesta.statusCode == 200) {
      return jsonDecode(respuesta.body);
    }
    return null;
  }
}
