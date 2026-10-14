package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccesoResponse (
        @Schema(description =  "Codigo de Local")
        Integer codLocal,
        @Schema(description =  "Codigo de Empresa")
        Integer codEmpresa,
        @Schema(description =  "Numero de Orden")
        Integer nroOrden,
        @Schema(description =  "Nombre del Local")
        String descLocal
){
}
