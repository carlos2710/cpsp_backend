package pe.com.empresa.ms_operaciones.domain.model;

public record UsuarioAcceso(
        Integer idUsuario,
        String userName,
        String passwordHash,
        Integer estado,
        Integer marca
) {
}
