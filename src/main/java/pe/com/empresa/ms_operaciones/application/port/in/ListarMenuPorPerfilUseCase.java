package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.MenuPerfilResponse;

import java.util.List;

public interface ListarMenuPorPerfilUseCase {
    List<MenuPerfilResponse> ejecutar (Integer codPerfil);
}
