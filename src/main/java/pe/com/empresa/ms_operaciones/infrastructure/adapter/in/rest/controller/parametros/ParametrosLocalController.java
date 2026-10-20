package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller.parametros;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.application.port.in.ListarLocalesUseCase;
import pe.com.empresa.ms_operaciones.domain.model.Local;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.LocalResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parametros/locales")
@Tag(name = "Parametros", description = "Catálogos y parámetros del sistema")
public class ParametrosLocalController {
    private final ListarLocalesUseCase listarLocalesUseCase;

    public ParametrosLocalController(ListarLocalesUseCase listarLocalesUseCase) {
        this.listarLocalesUseCase = listarLocalesUseCase;
    }

    @GetMapping
    @Operation(summary = "Listar locales", description = "Obtiene el código y la descripción de cada local")
    public List<LocalResponse> listar() {
        return listarLocalesUseCase.execute().stream()
                .map(this::toResponse)
                .toList();
    }

    private LocalResponse toResponse(Local local) {
        return new LocalResponse(local.codigo(), local.descripcion());
    }
}
