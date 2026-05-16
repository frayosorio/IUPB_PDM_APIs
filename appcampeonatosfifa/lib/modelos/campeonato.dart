class Campeonato {
  final int id;
  final String nombre;
  final int anio; // Es buena práctica evitar la 'ñ' en nombres de variables

  Campeonato({
    required this.id,
    required this.nombre,
    required this.anio,
  });

  // Constructor Factory para crear una instancia desde un Map (JSON)
  factory Campeonato.fromJson(Map<String, dynamic> json) {
    return Campeonato(
      id: json['id'] as int,
      nombre: json['nombre'] as String,
      anio: json['año'] as int, 
    );
  }

  // Método para convertir una lista de JSON en una lista de Modelos
  static List<Campeonato> desdeListaJson(dynamic jsonList) {
    if (jsonList == null) return [];
    return (jsonList as List)
        .map((item) => Campeonato.fromJson(item as Map<String, dynamic>))
        .toList();
  }
}