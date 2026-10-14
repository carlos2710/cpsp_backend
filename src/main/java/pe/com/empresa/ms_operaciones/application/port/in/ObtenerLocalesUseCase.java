package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.AccesoResponse;

import java.util.List;

public interface ObtenerLocalesUseCase {
    List<AccesoResponse> execute(Integer idUsuario);
}
