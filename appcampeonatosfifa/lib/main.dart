import 'package:appcampeonatosfifa/vistas/campeonato.dart';
import 'package:appcampeonatosfifa/vistas/inicio.dart';
import 'package:appcampeonatosfifa/vistas/login.dart';
import 'package:appcampeonatosfifa/vistas/seleccion.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Campeonatos FIFA App',
      theme: ThemeData(
        colorScheme: .fromSeed(seedColor: Colors.deepPurple),
      ),
      routes:{
        "/login":(context)=>const Login(),
        "/inicio":(context)=>const Inicio(),
        "/campeonatos":(context)=>const Campeonato(),
        "/selecciones":(context)=>const Seleccion(),
      },
      initialRoute: "/login",
    );
  }
}
