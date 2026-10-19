package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.application.port.in.ValidarConexionBaseDatosUseCase;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Salud", description = "Verificación del estado del microservicio")
public class SaludController {
    private final ValidarConexionBaseDatosUseCase validarConexionBaseDatosUseCase;

    public SaludController(ValidarConexionBaseDatosUseCase validarConexionBaseDatosUseCase) {
        this.validarConexionBaseDatosUseCase = validarConexionBaseDatosUseCase;
    }

    @GetMapping("/database")
    @Operation(summary = "Validar conexión a la base de datos")
    public ResponseEntity<ApiResponse<String>> validarConexionBaseDatos() {
        if (validarConexionBaseDatosUseCase.execute()) {
            return ResponseEntity.ok(ApiResponse.success("UP"));
        }

        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "No hay conexión con la base de datos",
                "DOWN"
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
