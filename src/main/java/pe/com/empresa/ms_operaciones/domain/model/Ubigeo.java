package pe.com.empresa.ms_operaciones.domain.model;

public record Ubigeo(
        Integer codigoDepartamento,
        String departamento,
        Integer codigoProvincia,
        String provincia,
        Integer codigoDistrito,
        String distrito
) {
}
