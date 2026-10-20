package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.com.empresa.ms_operaciones.application.port.in.GuardarUsuarioUseCase;
import pe.com.empresa.ms_operaciones.domain.model.GuardarUsuario;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.UsuarioRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ResponseStatus;
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
    private final GuardarUsuarioUseCase guardarUsuarioUseCase;

    public UsuarioController(GuardarUsuarioUseCase guardarUsuarioUseCase) {
        this.guardarUsuarioUseCase = guardarUsuarioUseCase;
    }

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
            @Valid @RequestBody UsuarioRequest request,
            Authentication authentication
    ){
        Integer codigoUsuario = guardarUsuarioUseCase.execute(toCommand(request, null, authentication));
        UsuarioResponse usuario = toResponse(codigoUsuario, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(usuario));
    }

    @PutMapping("")
    @Operation(
            summary = "Modificar usuario",
            description = "Modifica un usuario en el sistema"
    )
    public ResponseEntity<ApiResponse<UsuarioResponse>> ModificarUsuario(
            @Valid @RequestBody UsuarioRequest request,
            Authentication authentication
    ){
        if (request.idUsuario() == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error(ResponseStatus.BAD_REQUEST));
        }
        Integer codigoUsuario = guardarUsuarioUseCase.execute(
                toCommand(request, request.idUsuario(), authentication));
        UsuarioResponse usuario = toResponse(codigoUsuario, request);
        return ResponseEntity.ok(ApiResponse.success(usuario));
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

    private GuardarUsuario toCommand(
            UsuarioRequest request,
            Integer codigoUsuario,
            Authentication authentication
    ) {
        List<Integer> locales = request.locales() != null && !request.locales().isEmpty()
                ? request.locales().stream().map(Math::toIntExact).toList()
                : request.local() == null ? List.of() : List.of(Math.toIntExact(request.local()));
        return new GuardarUsuario(
                codigoUsuario,
                locales,
                Math.toIntExact(request.perfil()),
                1,
                1,
                request.tipoUsuario(),
                request.usuario(),
                request.clave(),
                1,
                0,
                authentication.getName(),
                1,
                request.telefono(),
                request.email(),
                request.codigoEmpresa() == null ? null : Math.toIntExact(request.codigoEmpresa())
        );
    }

    private UsuarioResponse toResponse(Integer codigoUsuario, UsuarioRequest request) {
        String nombreCompleto = String.join(" ", List.of(
                request.primerNombre(),
                request.segundoNombre() == null ? "" : request.segundoNombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno()
        )).replaceAll("\\s+", " ").trim();

        return new UsuarioResponse(
                codigoUsuario.longValue(),
                request.usuario(),
                request.perfil().toString(),
                nombreCompleto,
                request.locales() != null && !request.locales().isEmpty()
                        ? request.locales().stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(", "))
                        : String.valueOf(request.local()),
                "ACTIVO"
        );
    }


}
