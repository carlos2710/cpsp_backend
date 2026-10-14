package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.AccesoResponse;

import java.util.List;
import java.util.Optional;

public interface AccesoRepositoryPort {
    Optional<UsuarioAcceso> validarCredenciales(String userName);
    List<AccesoResponse> obtenerLocales(Integer idUsuario);
}
