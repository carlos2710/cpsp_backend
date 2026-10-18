package pe.com.empresa.ms_operaciones.domain.model;

import java.util.List;

public record DniReniec(
        String numero,
        String nombreCompleto,
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        Integer codigoVerificacion,
        String direccion,
        String direccionCompleta,
        String departamento,
        String provincia,
        String distrito,
        String ubigeoReniec,
        String ubigeoSunat,
        List<String> ubigeo
) {
}
