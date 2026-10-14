package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.application.port.in.ObtenerLocalesUseCase;
import pe.com.empresa.ms_operaciones.application.port.in.ValidarIngresoUseCase;
import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.AccesoRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.AccesoResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.SolicitudResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/logeo")
@Tag(
        name = "Acceso",
        description = "Endpoints relacionados con el acceso al sistema"
)
public class AccesoController {

    private final ValidarIngresoUseCase validarIngresoUseCase;
    private final ObtenerLocalesUseCase obtenerLocalesUseCase;

    public AccesoController(
            ValidarIngresoUseCase validarIngresoUseCase,
            ObtenerLocalesUseCase obtenerLocalesUseCase) {
        this.validarIngresoUseCase = validarIngresoUseCase;
        this.obtenerLocalesUseCase = obtenerLocalesUseCase;
    }

    @PostMapping("")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<Optional<Integer>> ValidarIngreso(
            @RequestBody AccesoRequest request
            ){
        Optional<Integer> valido = validarIngresoUseCase.execute(request);

        return ResponseEntity.ok(valido);
    }

    @PostMapping("/locales")
    @Operation(
            summary = "Obtener Locales asignados al usuario",
            description = "Permite obtener el listado de locales que tienen asignado un Usuario"
    )
    public ResponseEntity<List<AccesoResponse>> ObtenerLocales(
            @RequestBody Integer idUsuario
    ){

        List<AccesoResponse> listAcceso = obtenerLocalesUseCase.execute(idUsuario);

        return ResponseEntity.ok(listAcceso);

    }
}


