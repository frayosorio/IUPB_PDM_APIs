class UsuarioDto{
  final String usuario;
  final String token;

  UsuarioDto({
    required this.usuario,
    required this.token,
  });

  factory UsuarioDto.fromJson(Map<String, dynamic> json) {
    return UsuarioDto(
      usuario: json['usuario'],
      token: json['token'],
    );
  }

}