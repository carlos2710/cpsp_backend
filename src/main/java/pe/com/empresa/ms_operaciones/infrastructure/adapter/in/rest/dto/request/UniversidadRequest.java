package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UniversidadRequest(
        @NotBlank String nombre
) {
}
