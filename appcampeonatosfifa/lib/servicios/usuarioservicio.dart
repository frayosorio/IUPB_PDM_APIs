import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

class UsuarioServicio {
  final String urlBase = "http://10.0.2.2:8080/api/usuarios";

  final variablesApp = FlutterSecureStorage();


  Future<bool> login(String usuario, String clave) async {
    final url = Uri.parse("$urlBase/validar/$usuario/$clave");
    final respuesta = await http.get(url);

    if (respuesta.statusCode == 200) {
      final datos = jsonDecode(respuesta.body);
      print(datos["token"]);
      print(datos["usuario"]);

      await variablesApp.write(key: "token", value: datos["token"]);
      await variablesApp.write(key: "usuario", value: jsonEncode(datos["usuario"]));
      
      return true;
    }

    return false;
  }
}
