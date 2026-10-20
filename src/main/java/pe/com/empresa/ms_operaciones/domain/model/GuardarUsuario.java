package pe.com.empresa.ms_operaciones.domain.model;

import java.util.List;

public record GuardarUsuario(
        Integer codigoUsuario,
        List<Integer> locales,
        Integer codigoPerfil,
        Integer codigoNatural,
        Integer codigoPersona,
        Integer tipoUsuario,
        String usuario,
        String clave,
        Integer estado,
        Integer marca,
        String usuarioRegistro,
        Integer marcaDetalle,
        String telefono,
        String email,
        Integer codigoEmpresa
) {
}
