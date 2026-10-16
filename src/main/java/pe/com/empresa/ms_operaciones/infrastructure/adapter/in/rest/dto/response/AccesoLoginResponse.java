
package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;

public record AccesoLoginResponse(
        UsuarioAcceso usuario,
        String accessToken,
        String tokenType,
        long expiresIn
) {}
