package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.MenuPerfilResponse;

import java.util.List;

public interface MenuPerfilPort {
    List<MenuPerfilResponse> listarPorPerfil (Integer codPerfil);
}
