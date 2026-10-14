package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.PermisosResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UsuarioResponse;

@RestController
@RequestMapping("/api/v1/permisos")
@Tag(
        name = "Permisos",
        description = "Endpoints relacionados con las operaciones del sistema"
)
public class PermisosController {
    @GetMapping("/{id}")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<PermisosResponse>> healt() {
        PermisosResponse permisos = new PermisosResponse(
                1L,
                "cfernandez"
        );
        ApiResponse<PermisosResponse> response = ApiResponse.success(permisos);

        return ResponseEntity.ok(response);
    }

}
