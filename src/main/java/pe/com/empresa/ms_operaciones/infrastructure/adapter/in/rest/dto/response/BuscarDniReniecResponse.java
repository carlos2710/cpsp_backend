package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BuscarDniReniecResponse(
        boolean success,
        String message,
        Data data
) {
    public record Data(
            String numero,
            @JsonProperty("nombre_completo") String nombreCompleto,
            String nombres,
            @JsonProperty("apellido_paterno") String apellidoPaterno,
            @JsonProperty("apellido_materno") String apellidoMaterno,
            @JsonProperty("codigo_verificacion") Integer codigoVerificacion,
            String direccion,
            @JsonProperty("direccion_completa") String direccionCompleta,
            String departamento,
            String provincia,
            String distrito,
            @JsonProperty("ubigeo_reniec") String ubigeoReniec,
            @JsonProperty("ubigeo_sunat") String ubigeoSunat,
            List<String> ubigeo
    ) {
    }
}
