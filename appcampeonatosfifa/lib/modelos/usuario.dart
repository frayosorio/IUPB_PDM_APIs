class Usuario {
  final int id;
  final String nombre;
  final String usuario;
  final String clave;
  final String roles;

  Usuario({
    required this.id,
    required this.nombre,
    required this.usuario,
    required this.clave,
    required this.roles,
  });

  factory Usuario.fromJson(Map<String, dynamic> json) {
    return Usuario(
      id: json['id'],
      nombre: json['nombre'],
      usuario: json['usuario'],
      clave: json['clave'],
      roles: json['roles'],
    );
  }
}
