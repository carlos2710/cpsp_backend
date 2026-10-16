package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccesoLocalRequest(
    @Schema(description = "Datos de entrada")
    Integer idUsuario
){}
