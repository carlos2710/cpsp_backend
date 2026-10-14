package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.SolicitudRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.SolicitudResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/api/v1/solicitud")
@Tag(
        name = "Solicitud",
        description = "Endpoints relacionados con las solicitudes del sistema"
)
public class SolicitudController {
    @GetMapping("")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<List<SolicitudResponse>>> healt(
            @RequestParam Integer codSolicitud
    ){
        List<SolicitudResponse> listSolicitud = List.of(construirRespuestaVacia());
        ApiResponse<List<SolicitudResponse>> response = ApiResponse.success(listSolicitud);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Verificar Solicitud del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<SolicitudResponse>> buscarId(){
        SolicitudResponse  solicitudResponse = null;

        ApiResponse<SolicitudResponse> apiResponse = ApiResponse.success(solicitudResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<String>> GuardarSolicitud(
            @RequestBody SolicitudRequest request
    ){


        return ResponseEntity.ok(null);

    }

    @PutMapping("")
    @Operation(
            summary = "Verificar estado del microservicio",
            description = "Permite verificar que el microservicio se encuentra disponible"
    )
    public ResponseEntity<ApiResponse<String>> ModificarSolicitud(
            @RequestBody SolicitudRequest request
    ){


        return ResponseEntity.ok(null);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario por su identificador"
    )
    public ResponseEntity<ApiResponse<Void>> ModificarSolicitud(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                ApiResponse.success(null)
        );
    }

    private SolicitudResponse construirRespuestaVacia() {

        return new SolicitudResponse(
                new SolicitudResponse.DatosPersonales(
                        "", "", "", "", null, ""
                ),
                new SolicitudResponse.Vivienda(
                        null, null, null, ""
                ),
                new SolicitudResponse.Contacto(
                        "", "", "", "", "",
                        "", "", "", ""
                ),
                new SolicitudResponse.Familia(
                        "", "", "", "", "", List.of()
                ),
                new SolicitudResponse.FormacionAcademica(
                        "", "", "", "", "", ""
                ),
                new SolicitudResponse.Postgrado(
                        new SolicitudResponse.FormacionPostgrado(
                                "", "", "", "", ""
                        ),
                        new SolicitudResponse.FormacionPostgrado(
                                "", "", "", "", ""
                        )
                ),
                new SolicitudResponse.Idiomas(
                        List.of()
                ),
                new SolicitudResponse.ExperienciaLaboral(
                        false, List.of()
                ),
                new SolicitudResponse.Actividades(
                        List.of(), List.of()
                ),
                new SolicitudResponse.Capacitacion(
                        "", "", "", "", ""
                ),
                new SolicitudResponse.Documentos(
                        new SolicitudResponse.Documento("", "", ""),
                        new SolicitudResponse.Documento("", "", ""),
                        new SolicitudResponse.Documento("", "", ""),
                        new SolicitudResponse.Documento("", "", ""),
                        "", "", "", "", ""
                ),
                new SolicitudResponse.EstadoSolicitud(
                        "", "", null, null, "", ""
                )
        );
    }
}
