package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.kafka.common.metrics.internals.IntGaugeSuite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.UsuarioRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UsuarioResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(
        name = "Usuarios",
        description = "Endpoints relacionados con las operaciones del sistema"
)
public class UsuarioController {

    @GetMapping("")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> healt(){
        UsuarioResponse usuario = new UsuarioResponse(
                1L,
                "cfernandez",
                "ADMIN",
                "Carlos Fernandez",
                "Local Principal",
                "ACTIVO"
        );

        List<UsuarioResponse> listUsuarios = new ArrayList<UsuarioResponse>();

        listUsuarios.add(usuario);

        UsuarioResponse usuario2 = new UsuarioResponse(
                2L,
                "jalva",
                "ADMIN",
                "Juliana Alva",
                "Local Principal",
                "ACTIVO"
        );

        listUsuarios.add(usuario2);
        ApiResponse<List<UsuarioResponse>> response = ApiResponse.success(listUsuarios);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listarStatus")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> ListarPorEstado(
            @RequestParam Integer codStatus
            ){
        UsuarioResponse usuario = new UsuarioResponse(
                1L,
                "jalvaz",
                "ADMIN",
                "Juliana Alva Zapata",
                "Local Principal",
                "ACTIVO"
        );

        List<UsuarioResponse> listUsuarios = new ArrayList<UsuarioResponse>();
        listUsuarios.add(usuario);
        ApiResponse<List<UsuarioResponse>> response = ApiResponse.success(listUsuarios);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    @Operation(
            summary = "Registrar usuario",
            description = "Registra un nuevo usuario en el sistema"
    )
    public ResponseEntity<ApiResponse<UsuarioResponse>> GuardarUsuario(
            @Valid @RequestBody UsuarioRequest request
    ){
        UsuarioResponse usuario = new UsuarioResponse(
                1L,
                "jalvaz",
                "ADMIN",
                "Juliana Alva Zapata",
                "Local Principal",
                "ACTIVO"
        );
        ApiResponse<UsuarioResponse> response = ApiResponse.success(usuario);

        return ResponseEntity.ok(response);
    }

    @PutMapping("")
    @Operation(
            summary = "Modificar usuario",
            description = "Modifica un usuario en el sistema"
    )
    public ResponseEntity<ApiResponse<UsuarioResponse>> ModificarUsuario(
            @Valid @RequestBody UsuarioRequest request
    ){
        UsuarioResponse usuario = new UsuarioResponse(
                1L,
                "jalvaz",
                "ADMIN",
                "Juliana Alva Zapata",
                "Local Principal",
                "ACTIVO"
        );
        ApiResponse<UsuarioResponse> response = ApiResponse.success(usuario);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario por su identificador"
    )
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(null)
        );
    }


}
