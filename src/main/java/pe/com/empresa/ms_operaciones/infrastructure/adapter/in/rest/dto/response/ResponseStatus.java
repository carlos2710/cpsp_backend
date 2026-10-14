package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

public enum ResponseStatus {
    SUCCESS(200, "Consulta realizada correctamente"),
    CREATED(201, "Registro creado correctamente"),
    BAD_REQUEST(400, "Solicitud inválida"),
    NOT_FOUND(404, "Registro no encontrado"),
    INTERNAL_ERROR(500, "Error interno del servidor");

    private final Integer code;
    private final String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
