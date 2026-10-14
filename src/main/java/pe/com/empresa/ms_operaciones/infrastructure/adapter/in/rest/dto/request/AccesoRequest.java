package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para registrar una solicitud de colegiatura")
public record AccesoRequest(
        @Schema(description = "Datos generales de la solicitud")
        String userName,

        @Schema(description = "Datos generales de la solicitud")
        String password
) {
}
