package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import pe.com.empresa.ms_operaciones.application.port.in.ListarUniversidadesUseCase;
import pe.com.empresa.ms_operaciones.application.port.in.RegistrarUniversidadUseCase;
import pe.com.empresa.ms_operaciones.domain.model.Universidad;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.UniversidadRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UniversidadResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/universidades")
@Tag(name = "Universidades", description = "Catálogo de universidades")
public class UniversidadController {
    private final ListarUniversidadesUseCase listarUniversidadesUseCase;
    private final RegistrarUniversidadUseCase registrarUniversidadUseCase;

    public UniversidadController(
            ListarUniversidadesUseCase listarUniversidadesUseCase,
            RegistrarUniversidadUseCase registrarUniversidadUseCase
    ) {
        this.listarUniversidadesUseCase = listarUniversidadesUseCase;
        this.registrarUniversidadUseCase = registrarUniversidadUseCase;
    }

    @GetMapping
    @Operation(summary = "Listar universidades")
    public List<UniversidadResponse> listar() {
        return listarUniversidadesUseCase.execute().stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Crear universidad")
    public ResponseEntity<ApiResponse<Integer>> crear(
            @Valid @RequestBody UniversidadRequest request
    ) {
        Integer codigoUniversidad = registrarUniversidadUseCase.execute(request.nombre());

        return ResponseEntity.status(201)
                .body(ApiResponse.created(codigoUniversidad));
    }

    private UniversidadResponse toResponse(Universidad universidad) {
        return new UniversidadResponse(universidad.codigo(), universidad.nombre());
    }
}
