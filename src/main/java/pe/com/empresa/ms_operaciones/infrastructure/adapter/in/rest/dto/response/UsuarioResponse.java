package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

public record UsuarioResponse(
        Long id,
        String usuario,
        String perfil,
        String nombreUsuario,
        String nombreLocal,
        String descripcionEstado
) {
}
