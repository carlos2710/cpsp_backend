package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.application.port.in.ListarMenuPorPerfilUseCase;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.ApiResponse;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.MenuPerfilResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
@Tag(
        name = "Permisos",
        description = "Endpoints relacionados con las operaciones del sistema"
)
public class MenuController {
    private final ListarMenuPorPerfilUseCase listarMenuPorPerfilUseCase;

    public MenuController(
            ListarMenuPorPerfilUseCase listarMenuPorPerfilUseCase) {
        this.listarMenuPorPerfilUseCase = listarMenuPorPerfilUseCase;
    }

    @GetMapping("/perfil/{codPerfil}")
    public ResponseEntity<List<MenuPerfilResponse>> listarPorPerfil(
            @PathVariable Integer codPerfil) {

        List<MenuPerfilResponse> menus =
                listarMenuPorPerfilUseCase.ejecutar(codPerfil);

        return ResponseEntity.ok(menus);
    }

}
