package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.application.port.in.ListarUbicacionesUseCase;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UbicacionResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubicaciones")
@Tag(name = "Ubicaciones", description = "Catálogos de departamentos, provincias y distritos")
public class UbicacionController {
    private final ListarUbicacionesUseCase listarUbicacionesUseCase;

    public UbicacionController(ListarUbicacionesUseCase listarUbicacionesUseCase) {
        this.listarUbicacionesUseCase = listarUbicacionesUseCase;
    }

    @GetMapping("/departamentos")
    @Operation(summary = "Listar departamentos")
    public List<UbicacionResponse> listarDepartamentos() {
        return listarUbicacionesUseCase.listarDepartamentos();
    }

    @GetMapping("/departamentos/{codigoDepartamento}/provincias")
    @Operation(summary = "Listar provincias por departamento")
    public List<UbicacionResponse> listarProvincias(@PathVariable Integer codigoDepartamento) {
        return listarUbicacionesUseCase.listarProvincias(codigoDepartamento);
    }

    @GetMapping("/provincias/{codigoProvincia}/distritos")
    @Operation(summary = "Listar distritos por provincia")
    public List<UbicacionResponse> listarDistritos(@PathVariable Integer codigoProvincia) {
        return listarUbicacionesUseCase.listarDistritos(codigoProvincia);
    }
}
